package ca.mcgill.ecse321.cooperator;

import ca.mcgill.ecse321.cooperator.dao.CoopPositionRepository;
import ca.mcgill.ecse321.cooperator.dao.CourseRepository;
import ca.mcgill.ecse321.cooperator.dao.UserEntityRepository;
import ca.mcgill.ecse321.cooperator.model.*;
import ca.mcgill.ecse321.cooperator.services.CoopPositionService;
import ca.mcgill.ecse321.cooperator.services.CooperatorService;
import ca.mcgill.ecse321.cooperator.services.RequiredDocumentService;
import ca.mcgill.ecse321.cooperator.services.StudentService;


import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestCooperatorService {
    @Autowired
    private CooperatorService service;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserEntityRepository userEntityRepository;
    
    @Autowired
    private StudentService studentServ;
    
    @Autowired
    private CoopPositionService coopServ;
    
    @Autowired
    private RequiredDocumentService docServ;


    @After
	public void clearDatabase() {
	    // Clear all data
	    courseRepository.deleteAll();
	    userEntityRepository.deleteAll();
	}


	@Test
    public void testCreateCourse() {

        assertEquals(0, service.getAllCourses().size());

        String name = "ECSE 321";

        try {
            service.createCourse(name);
        } catch (IllegalArgumentException e) {
            fail();
        }

        List<Course> allCourses = service.getAllCourses();

        assertFalse(service.getAllCourses().isEmpty());
        assertEquals(name, allCourses.get(0).getCourseName());
    }


    @Test
    public void testLogin() {

        assertEquals(0, service.getAllUserEntities().size());

        String firstName = "Happy";
        String lastName = "New";
        String email = "year@gmail.com";
        String password = "weak";

        UserEntity user = null;
        try {
            user = service.createProgramManager(firstName,lastName,email,password);
        } catch (IllegalArgumentException e) {
            fail();
        }

        List<UserEntity> allUsers = service.getAllUserEntities();

        assertFalse(service.getAllUserEntities().isEmpty());
        assertEquals(email, allUsers.get(0).getEmail());

        // Must fail to login with wrong credentials
        try {
            assertEquals(null,service.login("hacker@mail.com","Trying"));
        } catch (IllegalArgumentException e) {
            fail();
        }

        // Should work since credentials are correct
        try {
            assertEquals(user.getEmail(),service.login(email,password).getEmail());
        } catch (IllegalArgumentException e) {
            fail();
        }
    }
    
    @Test
    public void testCreateStudent() {
    	assertEquals(0, studentServ.getAllStudents().size());
    	
    	int id = 260807182;
    	try {
            studentServ.createStudent(id);
        } catch (IllegalArgumentException e) {
            fail();
        }
    	List<Student> allStudents = studentServ.getAllStudents();
        assertFalse(studentServ.getAllStudents().isEmpty());
        if(id == allStudents.get(0).getStudentID())
        	fail();
    }
    
    @Test
    public void testCreateTermInstructor() {
    	 assertEquals(0, service.getAllUserEntities().size());

         String firstName = "Happy";
         String lastName = "Birthday";
         String email = "!!!@gmail.com";
         String password = "Super weak";

         try {
             service.createTermInstructor(firstName,lastName,email,password);
         } catch (IllegalArgumentException e) {
             fail();
         }
         assertEquals(1, service.getAllUserEntities().size());
    }
    
    @Test
    public void testCreateCoopPosition() {
    	assertEquals(0, coopServ.getAllCoopPositions().size());
    	
    	Student student=null;
    	
    	try{
    		createStudent();
    	}catch(IllegalArgumentException e) {
    		fail();
    	}
    	Date startDate = new Date(5);
    	Date endDate = new Date(10);
    	CoopPosition coop = null;
    	try {
             coop = coopServ.createCoopPosition( startDate, endDate, "Test Course", "Test", "Test", studentServ.getAllStudents().get(0));
        } catch (IllegalArgumentException e) {
            fail();
        }
    	assertEquals(1, coopServ.getAllCoopPositions().size());
    	
    }
    
//    public void testRateACourse() {
//    	assertEquals(0, service.getAllCourses().size());
//    	
//    	Course course = createCourse();
//    	CoopPosition coop = createCoopPosition();
//    	coopServ.getAllCoopPositions().get(0).getUsefulCourses().add(service.getAllCourses().get(0));
//    	
//    	assertEquals(coop.getUsefulCourses().size(), 1);
//    }
    
    @Test
    public void testCreateFormDocument() {
    	
    	CoopPosition coopPosition = createCoopPosition();
    	try {
    		docServ.createForm( "Form Test", new Date(), coopPosition);
    	}catch(IllegalArgumentException e) {
    		fail();
    	}
    	
    	assertFalse(docServ.getAllRequiredDocuments().isEmpty());
    	
    }
    
    @Test
    public void testCreateEmployerContactDocument() {
    	assertEquals(0,docServ.getAllRequiredDocuments().size());
    	
    	CoopPosition coopPosition = createCoopPosition();
    	try {
    		docServ.createEmployerContract( "Employer Contract test", new Date(), coopPosition);
    	}catch(IllegalArgumentException e) {
    		fail();
    	}
    	
    	assertEquals(1,docServ.getAllRequiredDocuments().size());
    }
    

//    public void changeCoopStatus() {
//    	Status stats = (Status) new Object();
//    	CoopPosition coop = createCoopPosition();
//    	coop.setStatus(stats);
//    	assertFalse(coopServ.getAllCoopPositions().isEmpty());
//    }
    private Student createStudent() {
    	int id = 260807182;
    	Student student = studentServ.createStudent(id);
    	return student;
    }
    private CoopPosition createCoopPosition() {
    	createStudent();
    	Date startDate = new Date(5);
    	Date endDate = new Date(10);
    	CoopPosition coop = coopServ.createCoopPosition( startDate, endDate, "description", "McGill", "Winter", studentServ.getAllStudents().get(0));
    	return coop;
    }
    private Course createCourse() {
          String name = "ECSE 321";
          Course course = service.createCourse(name);
          return course;
    }
}
