package ca.mcgill.ecse321.cooperator;

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
import ca.mcgill.ecse321.cooperator.services.CoursesService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CooperatorApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Mock
	private UserEntityRepository userDao;
	@Mock
	private StudentRepository studentDao;
	@Mock
	private CoopPositionRepository coopPositionDao;
	@Mock
	private CourseRepository courseDao;
	@Mock
	private EmployerRepository employerDao;
	@Mock
	private RequiredDocumentRepository requiredDocumentDao;

	@InjectMocks
	private CoursesService coursesServicce;

	private Course course;
	private static final String SYSTEM_KEY = "test";
	private static final String NONEXISTING_KEY = "nothing";

	@Before
	public void setMockOutput() {
		when(courseDao.findCourseByCourseName(anyString())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(SYSTEM_KEY)) {
				Course course = new Course();
				course.setCourseName(SYSTEM_KEY);
				return course;
			} else {
				return null;
			}
		});
	}

	@Before
	public void setupMock() {
		course = mock(Course.class);
	}

	@Test
	public void testMockCourseCreation() {
		assertNotNull(course);
	}

	@Test
	public void testMockCourseQueryFound() {
		assertNotNull(coursesServicce.getCourseByCourseName(SYSTEM_KEY));
	}

}