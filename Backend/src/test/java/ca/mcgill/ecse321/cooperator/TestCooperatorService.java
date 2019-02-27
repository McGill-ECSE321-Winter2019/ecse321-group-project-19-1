package ca.mcgill.ecse321.cooperator;

import ca.mcgill.ecse321.cooperator.dao.CoopPositionRepository;
import ca.mcgill.ecse321.cooperator.dao.CourseRepository;
import ca.mcgill.ecse321.cooperator.dao.EmployerRepository;
import ca.mcgill.ecse321.cooperator.dao.RequiredDocumentRepository;
import ca.mcgill.ecse321.cooperator.dao.UserEntityRepository;
import ca.mcgill.ecse321.cooperator.model.*;
import ca.mcgill.ecse321.cooperator.services.CoopPositionService;
import ca.mcgill.ecse321.cooperator.services.CooperatorService;
import ca.mcgill.ecse321.cooperator.services.RequiredDocumentService;
import ca.mcgill.ecse321.cooperator.services.StudentService;

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
	private CooperatorService service;

	@Autowired
	private EmployerRepository employerRepository;

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private UserEntityRepository userEntityRepository;

	@Autowired
	private RequiredDocumentRepository requiredDocumentRepository;

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
			user = service.createProgramManager(firstName, lastName, email, password);
		} catch (IllegalArgumentException e) {
			fail();
		}

		List<UserEntity> allUsers = service.getAllUserEntities();

		assertFalse(service.getAllUserEntities().isEmpty());
		assertEquals(email, allUsers.get(0).getEmail());

		// Must fail to login with wrong credentials
		try {
			assertEquals(null, service.login("hacker@mail.com", "Trying"));
		} catch (IllegalArgumentException e) {
			fail();
		}

		// Should work since credentials are correct
		try {
			assertEquals(user.getEmail(), service.login(email, password).getEmail());
		} catch (IllegalArgumentException e) {
			fail();
		}
	}

	@Test
	public void testCreateStudent() {
		assertEquals(0, service.getAllStudents().size());

		try {
			service.createStudent();
		} catch (IllegalArgumentException e) {
			fail();
		}
		assertEquals(1, service.getAllStudents().size());
	}

	@Test
	public void testCreateTermInstructor() {
		assertEquals(0, service.getAllUserEntities().size());

		String firstName = "Happy";
		String lastName = "Birthday";
		String email = "!!!@gmail.com";
		String password = "Super weak";

		try {
			service.createTermInstructor(firstName, lastName, email, password);
		} catch (IllegalArgumentException e) {
			fail();
		}
		assertEquals(1, service.getAllUserEntities().size());
	}

	@Test
	public void testCreateCoopPosition() {
		assertEquals(0, service.getAllCoopPositions().size());

		Student student = null;

		try {
			student = createStudent();
		} catch (IllegalArgumentException e) {
			fail();
		}
		Date startDate = new Date(5);
		Date endDate = new Date(10);
		CoopPosition coop = null;
		try {
			coop = service.createCoopPosition(startDate, endDate, "Test Course", "Test", "Test",
					service.getAllStudents().get(0));
		} catch (IllegalArgumentException e) {
			fail();
		}
		assertEquals(1, service.getAllCoopPositions().size());

	}

	@Test
	public void testCreateFormDocument() {

		CoopPosition coopPosition = createCoopPosition();
		try {
			service.createForm("Form Test", new Date(1), coopPosition);
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertFalse(service.getAllRequiredDocuments().isEmpty());

	}

	@Test
	public void testCreateEmployerContactDocument() {
		assertEquals(0, service.getAllRequiredDocuments().size());

		CoopPosition coopPosition = createCoopPosition();
		try {
			createEmployer();
			service.createEmployerContract("Employer Contract test", new Date(), coopPosition,
					service.getAllEmployers().get(0));
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertEquals(1, service.getAllRequiredDocuments().size());
	}

	private Student createStudent() {
		Student student = service.createStudent();
		return student;
	}

	private CoopPosition createCoopPosition() {
		createStudent();
		Date startDate = new Date(5);
		Date endDate = new Date(10);
		CoopPosition coop = service.createCoopPosition(startDate, endDate, "description", "McGill", "Winter",
				service.getAllStudents().get(0));
		return coop;
	}

	private Course createCourse() {
		String name = "ECSE 321";
		Course course = service.createCourse(name);
		return course;
	}

	private Employer createEmployer() {
		Employer em = service.createEmployer();
		return em;
	}
}
