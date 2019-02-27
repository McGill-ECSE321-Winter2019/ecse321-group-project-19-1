package ca.mcgill.ecse321.cooperator.services;

import ca.mcgill.ecse321.cooperator.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@Service
public class CooperatorService {

    Boolean CheckNotEmpty(String s) {
        return s != null && !s.equals("") && s.trim().length() > 0;
    }
    
    @Autowired
    CoopPositionService coopPositionService;
    
    @Autowired
    RequiredDocumentService requiredDocumentService;
    
    @Autowired
    StudentService studentService;
    
    @Autowired
    CoursesService coursesService;

    @Autowired
    UserEntityService userEntityService;
    
    @Autowired
    EmployerService employerService;
    
    //Employer
    @Transactional
    public Employer createEmployer() {
        return employerService.createEmployer();
    }
    @Transactional
    public List<Employer> getAllEmployers() {
        return employerService.getAllEmployers();
    }
    @Transactional
    public Employer getEmployerbyId(int id) {
    	return employerService.getEmployer(id);
    }
    
    //Course
    @Transactional
    public Course createCourse(String name) {
        return coursesService.createCourse(name);
    }
    
    @Transactional
    public List<Course> getAllCourses() {
        return coursesService.getAllCourses();
    }
    
    //User entities
    @Transactional
    public List<UserEntity> getAllUserEntities() {
        return userEntityService.getAllUserEntities();
    }
    @Transactional
    public UserEntity login(String email, String password) {
        UserEntity user = userEntityService.getUserEntityByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
    @Transactional
    public UserEntity getUserEntityByEmail(String email) {
    	return userEntityService.getUserEntityByEmail(email);
    }
    //ProgramManager
    @Transactional
    public ProgramManager createProgramManager(String firstName, String lastName, String email, String password) {
        return userEntityService.createProgramManager(firstName,lastName,email,password);
    }
    //Term instructor
    @Transactional
    public TermInstructor createTermInstructor(String firstName, String lastName, String email, String password) {
        return userEntityService.createTermInstructor(firstName,lastName,email,password);
    }

    //Student
    @Transactional
	public List<Student> getAllStudents() {
		return studentService.getAllStudents();
	}
    @Transactional
    public Student getStudentById(int id) {
    	return studentService.getStudentById(id);
    }
    @Transactional
    public List<Student> getAllProblematicStudents(){
    	return studentService.getAllProblematicStudents();
    }
    @Transactional
	public Student createStudent() {
		return studentService.createStudent();
	}
    
	//Coop
    @Transactional
	public CoopPosition createCoopPosition(Date startDate, Date endDate,String description,String location, String term, Student student) {
		return coopPositionService.createCoopPosition(startDate, endDate, description, location, term, student);
	}
    @Transactional
	public List<CoopPosition> getAllCoopPositions() {
		return coopPositionService.getAllCoopPositions();
	}
    @Transactional
    public CoopPosition getCoopPositionByID(Integer id){
    	return coopPositionService.getCoopPositionByID(id);
    }
    
    //Required documents
    @Transactional
   	public List<RequiredDocument> getAllRequiredDocuments() {
   		return requiredDocumentService.getAllRequiredDocuments();
   	}
    @Transactional
   	public List<RequiredDocument> getAllRequiredDocumentsByCoopPosition(CoopPosition cp) {
   		return requiredDocumentService.getRequiredDocumentByCoopPosition(cp);
   	}
    @Transactional
    public RequiredDocument getDocumentByDocumentID(Integer id) {
    	return requiredDocumentService.getRequiredDocument(id);
    }
    
    //Report
    @Transactional
   	public Report createReport(String name, Date dueDate, CoopPosition cp, ReportType type) {
   		return requiredDocumentService.createReport(name, dueDate, cp,type);
   	}
    //Form
    @Transactional
	public Form createForm(String name, Date dueDate, CoopPosition cp) {
		return requiredDocumentService.createForm(name, dueDate, cp);
	}
    //Employer contract
    @Transactional
	public EmployerContract createEmployerContract(String name, Date dueDate, CoopPosition cp, Employer employer) {
		return requiredDocumentService.createEmployerContract(name, dueDate, cp, employer);
	}
    @Transactional
	public Course getCourseByCourseName(String name) {
		return coursesService.getCourseByCourseName(name);
	}

}