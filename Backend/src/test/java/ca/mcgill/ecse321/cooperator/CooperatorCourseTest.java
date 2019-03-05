package ca.mcgill.ecse321.cooperator;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ca.mcgill.ecse321.cooperator.dao.*;
import ca.mcgill.ecse321.cooperator.model.*;
import ca.mcgill.ecse321.cooperator.services.CoopPositionService;
import ca.mcgill.ecse321.cooperator.services.CoursesService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CooperatorCourseTest{

	@Mock
	private CoopPositionRepository coopPositionDao;
	@Mock
	private CourseRepository courseDao;
	
	@InjectMocks
	private CoopPositionService coopPositionService;
	
	@InjectMocks
	private CoursesService courseService;

	private Course course;
	private Course course2;
	private static final int COURSE_ID = 1;
	private static final int COURSE_ID2 = 2;
	private static final String COURSE_NAME = "Ecse 321";
	private static final String COURSE_NAME2 = "Ecse 321 Tutorial";
	private static final String WRONG_COURSE_NAME = "Ecse 223";
	private static final Integer COOP_KEY = 10;
	private static final Date startDate = new Date(5);
	private static final Date endDate = new Date(10);
	private CoopPosition coop;
	
	
	private List<Course> expectedList = new ArrayList<Course>();
	
	
	@Before
	public void setMockOutput() {
		when(courseDao.findCourseByCourseName(anyString())).thenAnswer((InvocationOnMock invocation) -> {
			if(invocation.getArgument(0).equals(COURSE_NAME)) {
				return course;
			}else if(invocation.getArgument(0).equals(COURSE_NAME2)){
				return course2;
			}
			return null;
		
		});
		when(coopPositionDao.findByCoopId(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(COOP_KEY)) {
				coop.setCoopId(COOP_KEY);
				return coop;
			
			} else {
				return null;
			}
		});
	}

	
	@Before
	public void setUpMocks() {
		course = mock(Course.class);
		course = courseService.createCourse(COURSE_NAME);
		expectedList.add(course);
		course2 = mock(Course.class);
		course2 = courseService.createCourse(COURSE_NAME2);
		expectedList.add(course2);
		coop = mock(CoopPosition.class);
		coop = coopPositionService.createCoopPosition(startDate, endDate, "description", "McGill", "Winter", new Student());
	}
	
	@Test
	public void testCourseCreation() {
		assertNotNull(course);
		assertNotNull(course2);
		assertNotNull(coop);
	}
	
	@Test
	public void testCourseQueryFound() {
		assertEquals(COURSE_NAME, courseService.getCourseByCourseName(COURSE_NAME).getCourseName());
		assertEquals(COURSE_NAME2, courseService.getCourseByCourseName(COURSE_NAME2).getCourseName());
	}
	
	@Test
	public void testCourseNotFound() {
		assertNull(courseService.getCourseByCourseName(WRONG_COURSE_NAME));
	}
	
	@Test
	public void testCourseRating() {
		courseService.rateCourse(COURSE_NAME,COOP_KEY, true);
		assertEquals(courseService.getCourseByCourseName(COURSE_NAME).getCoopPosition().size(), 1);
		assertTrue(courseService.getCourseByCourseName(COURSE_NAME).getCoopPosition().contains(coopPositionService.getById(COOP_KEY)));
		courseService.rateCourse(COURSE_NAME2, COOP_KEY, false);
		assertTrue(!coopPositionService.getById(COOP_KEY).getUsefulCourses().contains(courseService.getCourseByCourseName(COURSE_NAME2)));
	}
	
}