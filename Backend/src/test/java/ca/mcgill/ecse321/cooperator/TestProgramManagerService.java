package ca.mcgill.ecse321.cooperator;
import static org.junit.Assert.*;
import static org.junit.Assert.fail;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ca.mcgill.ecse321.cooperator.dao.CourseRepository;
import ca.mcgill.ecse321.cooperator.service.ProgramManagerService;
import ca.mcgill.ecse321.cooperator.model.Course;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestProgramManagerService {
    @Autowired
    private ProgramManagerService service;

    @Autowired
    private CourseRepository courseRepository;


    @After
    public void clearDatabase() {
        // Clear all courses
        courseRepository.deleteAll();
    }

    @Test
    public void testCreateCourse() {

        assertEquals(0,service.getAllCourses().size());

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
}
