package ca.mcgill.ecse321.cooperator;

import ca.mcgill.ecse321.cooperator.dao.CourseRepository;
import ca.mcgill.ecse321.cooperator.dao.UserEntityRepository;
import ca.mcgill.ecse321.cooperator.model.Course;
import ca.mcgill.ecse321.cooperator.model.UserEntity;
import ca.mcgill.ecse321.cooperator.services.CooperatorService;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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

}
