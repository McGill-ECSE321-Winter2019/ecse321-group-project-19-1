package ca.mcgill.ecse321.cooperator;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import ca.mcgill.ecse321.cooperator.controller.CooperatorController;
import ca.mcgill.ecse321.cooperator.dao.*;
import ca.mcgill.ecse321.cooperator.model.*;
import ca.mcgill.ecse321.cooperator.services.CoursesService;
import ca.mcgill.ecse321.cooperator.services.StudentService;
import ca.mcgill.ecse321.cooperator.services.UserEntityService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CooperatorCourseTest{

	@Mock
	private CourseRepository courseDao;
	
	@InjectMocks
	private CoursesService courseService;

	private Course course;
	private Course course2;
	private static final int COURSE_ID = 1;
	private static final int COURSE_ID2 = 2;
	private static final String COURSE_NAME = "Ecse 321";
	private static final String COURSE_NAME2 = "Ecse 321 Tutorial";
	private static final String WRONG_COURSE_NAME = "Ecse 223";
	
	
	private List<Course> expectedList = new ArrayList<Course>();
	
	
	@Before
	public void setMockOutput() {
		when(courseDao.findCourseByCourseName(anyString())).thenAnswer((InvocationOnMock invocation) -> {
			if(invocation.getArgument(0).equals(COURSE_NAME)) {
				Course course = new Course();
				course.setCourseId(COURSE_ID);
				course.setCourseName(COURSE_NAME);
				return course;
			}else if(invocation.getArgument(0).equals(COURSE_NAME2)){
				Course course = new Course();
				course.setCourseId(COURSE_ID2);
				course.setCourseName(COURSE_NAME2);
				return course;
			}
			return null;
		
		});
	}

	
	@Before
	public void setUpMocks() {
		course = mock(Course.class);
		course = courseService.createCourse(COURSE_NAME);
		expectedList.add(course);
		course2 = mock(Course.class);
		course = courseService.createCourse(COURSE_NAME2);
		expectedList.add(course2);
	}
	
	@Test
	public void testUserEntityCreation() {
		assertNotNull(course);
		assertNotNull(course2);
	}
	
	@Test
	public void testUserEntityQueryFound() {
		assertEquals(COURSE_NAME, courseService.getCourseByCourseName(COURSE_NAME).getCourseName());
		assertEquals(COURSE_NAME2, courseService.getCourseByCourseName(COURSE_NAME2).getCourseName());
	}
	
	@Test
	public void testUserEntityQueryNotFound() {
		assertNull(courseService.getCourseByCourseName(WRONG_COURSE_NAME));
	}
	
}