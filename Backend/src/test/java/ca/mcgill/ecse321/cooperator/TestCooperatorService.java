package ca.mcgill.ecse321.cooperator;

import ca.mcgill.ecse321.cooperator.dao.*;
import ca.mcgill.ecse321.cooperator.model.*;
import ca.mcgill.ecse321.cooperator.services.*;

import org.junit.After;
import org.junit.Before;
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

	@Autowired
	private EmployerRepository employerRepository;

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private UserEntityRepository userEntityRepository;

	@Autowired
	private RequiredDocumentRepository requiredDocumentRepository;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private CoopPositionRepository coopPositionRepository;

	@Before
	public void clearDatabase() {
		// Clear all data
		courseRepository.deleteAll();
		requiredDocumentRepository.deleteAll();
		employerRepository.deleteAll();
		coopPositionRepository.deleteAll();
		userEntityRepository.deleteAll();
		studentRepository.deleteAll();
	}

	@Test
	public void testCreateCourse() {

		assertEquals(0, coursesService.getAllCourses().size());

		String name = "ECSE 321";

		try {
			coursesService.createCourse(name);
		} catch (IllegalArgumentException e) {
			fail();
		}

		List<Course> allCourses = coursesService.getAllCourses();

		assertFalse(coursesService.getAllCourses().isEmpty());
		assertEquals(name, allCourses.get(0).getCourseName());
	}

	@Test(expected=NullPointerException.class)
	public void testNullLogin() {
		// Must fail to login with wrong credentials
				try {
					userEntityService.login("hacker@mail.com", "Trying");
					
				} catch (IllegalArgumentException e) {
					fail();
				}
	}
	@Test
	public void testLogin() {

		assertEquals(0, userEntityService.getAllUserEntities().size());

		String firstName = "Happy";
		String lastName = "New";
		String email = "year@gmail.com";
		String password = "weak";

		UserEntity user = null;
		try {
			user = userEntityService.createProgramManager(firstName, lastName, email, password);
		} catch (Exception e) {
			fail();
		}

		List<UserEntity> allUsers = userEntityService.getAllUserEntities();

		assertFalse(userEntityService.getAllUserEntities().isEmpty());
		assertEquals(email, allUsers.get(0).getEmail());


		// Should work since credentials are correct
		try {
			assertEquals(user.getEmail(), userEntityService.login(email, password).getEmail());
		} catch (IllegalArgumentException e) {
			fail();
		}
	}

	@Test
	public void testCreateStudent() {
		assertEquals(0, studentService.getAllStudents().size());

		try {
			studentService.createStudent("who cares?"," no one");
		} catch (IllegalArgumentException e) {
			fail();
		}
		assertEquals(1, studentService.getAllStudents().size());
	}

	@Test
	public void testCreateTermInstructor() {
		assertEquals(0, userEntityService.getAllUserEntities().size());

		String firstName = "Happy";
		String lastName = "Birthday";
		String email = "!!!@gmail.com";
		String password = "Super weak";

		try {
			userEntityService.createTermInstructor(firstName, lastName, email, password);
		} catch (Exception e) {
			fail();
		}
		assertEquals(1, userEntityService.getAllUserEntities().size());
	}

	@Test
	public void testCreateCoopPosition() {
		assertEquals(0, coopPositionService.getAllCoopPositions().size());

		Student student = null;

		try {
			student = createStudent();
		} catch (NullPointerException e) {
			fail();
		}
		Date startDate = new Date(5);
		Date endDate = new Date(10);
		CoopPosition coop = null;
		try {
			coop = coopPositionService.createCoopPosition(startDate, endDate, "Test Course", "Test", "Test",
					studentService.getAllStudents().get(0));
		} catch (Exception e) {
			fail();
		}
		assertEquals(1, coopPositionService.getAllCoopPositions().size());

	}

	@Test
	public void testCreateFormDocument() {

		CoopPosition coopPosition = createCoopPosition();
		try {
			requiredDocumentService.createForm("Form Test", new Date(1), coopPosition);
		} catch (Exception e) {
			fail();
		}

		assertFalse(requiredDocumentService.getAllRequiredDocuments().isEmpty());

	}

	@Test
	public void testCreateEmployerContactDocument() {
		assertEquals(0, requiredDocumentService.getAllRequiredDocuments().size());

		CoopPosition coopPosition = createCoopPosition();
		try {
			createEmployer();
			requiredDocumentService.createEmployerContract("Employer Contract test", new Date(), coopPosition,
					employerService.getAllEmployers().get(0));
		} catch (NullPointerException e) {
			fail();
		}

		assertEquals(1, requiredDocumentService.getAllRequiredDocuments().size());
	}

	private Student createStudent() {
		Student student = studentService.createStudent("who cares?"," no one");
		return student;
	}

	private CoopPosition createCoopPosition() {
		createStudent();
		Date startDate = new Date(5);
		Date endDate = new Date(10);
		CoopPosition coop = coopPositionService.createCoopPosition(startDate, endDate, "description", "McGill", "Winter",
				studentService.getAllStudents().get(0));
		return coop;
	}

	private Course createCourse() {
		String name = "ECSE 321";
		Course course = coursesService.createCourse(name);
		return course;
	}

	private Employer createEmployer() {
		Employer em = employerService.createEmployer();
		return em;
	}
}
