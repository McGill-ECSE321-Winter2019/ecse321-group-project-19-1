package ca.mcgill.ecse321.cooperator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import ca.mcgill.ecse321.cooperator.dao.*;
import ca.mcgill.ecse321.cooperator.services.*;
import org.mockito.invocation.InvocationOnMock;
import org.springframework.test.context.junit4.SpringRunner;
import ca.mcgill.ecse321.cooperator.model.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestGrading {
	@Mock
	private RequiredDocumentRepository requiredDocumentRepository;
	@Mock
	private UserEntityRepository userEntityRepository;
	@Mock
	private CoopPositionRepository coopPositionRepository;
	@Mock
	private StudentRepository studentRepository;

	@InjectMocks
	private RequiredDocumentService rdocService;
	@InjectMocks
	private UserEntityService userEntityService;
	@InjectMocks
	private CoopPositionService coopPositionService;
	@InjectMocks
	private StudentService studentService;
	
	//private CooperatorController controller;

	// Term instructor
	private TermInstructor ti;
	private static final String PERSON_KEY = "TestUser";
	private static final String NONEXISTING_KEY = "NotAUser";

	// Required document
	private RequiredDocument rdoc;
	private static final Integer DOC_KEY = 4;
	private static final String NONEXISTINGDOC_KEY = "NotADoc";

	// Student
	private Student student;
	private static final Integer STUDENT_KEY = 5;
	private static final String NONEXISTINGSTUDENT_KEY = "NotAStudent";

	// Coop position
	private CoopPosition cp;
	private static final Integer COOP_KEY = 6;
	private static final String NONEXISTINGCOOP_KEY = "NotACoop";

	private static final Boolean GRADE = true;
	
	@Before
	public void setMockOutput() {
		MockitoAnnotations.initMocks(this);
		// Creating a student
		when(studentRepository.findById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(STUDENT_KEY)) {
				Student student = new Student();
				student.setStudentID(STUDENT_KEY);
				return student;
			} else {
				return null;
			}
		});
		// Creating a coop position
		when(coopPositionRepository.findById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(COOP_KEY)) {
				CoopPosition cp = new CoopPosition();
				cp.setCoopId(COOP_KEY);
				cp.setStudent(studentService.getStudentById(STUDENT_KEY));
				return cp;
			} else {
				return null;
			}
		});
		// Creating Required document
		when(requiredDocumentRepository.findById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(DOC_KEY)) {
				Report rdoc = new Report();
				rdoc.setAccepted(true);
				rdoc.setCoopPosition(coopPositionService.getCoopPositionById(COOP_KEY));
				rdoc.setDocumentId(DOC_KEY);
				return rdoc;
			} else {
				return null;
			}
		});

		// Creating Term instructor
		when(userEntityRepository.findUserEntityByEmail(anyString())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(PERSON_KEY)) {
				TermInstructor ti = new TermInstructor();
				Set<CoopPosition> cps = new HashSet<>();
				cps.add(coopPositionService.getCoopPositionById(COOP_KEY));
				ti.setCoopPosition(cps);
				ti.setEmail(PERSON_KEY);
				return ti;
			} else {
				return null;
			}
		});
	}

	@Before
	public void setupMock() {
		ti = mock(TermInstructor.class);
		rdoc = mock(RequiredDocument.class);
		student = mock(Student.class);
		cp = mock(CoopPosition.class);
	}

	// Mock creation tests
	@Test
	public void testMockTermInstructorCreation() {
//		TermInstructorDto tiDto= controller.createTermInstructor("hello", "world", "weak", "yoyo@gmail.com");
//		assertEquals("hello",tiDto.getFirstName());
//		assertEquals("world",tiDto.getLastName());
//		assertEquals("weak",tiDto.getPassword());
//		assertEquals("yoyo@gmail.com",tiDto.getEmail());
		assertNotNull(ti);
		
	}
	
	@Test
	public void testMockStudentCreation() {
		assertNotNull(student);
	}

	@Test
	public void testMockCoopCreation() {
		assertNotNull(cp);
	}

	@Test
	public void testMockDocCreation() {
		assertNotNull(rdoc);
	}

	// Query found tests
	@Test
	public void testTermInstructorQueryFound() {
		assertEquals(PERSON_KEY, userEntityService.getUserEntityByEmail(PERSON_KEY).getEmail());
	}

	@Test
	public void testStudentQueryFound() {
		assertEquals(STUDENT_KEY, studentService.getStudentById(STUDENT_KEY).getStudentID());
	}

	@Test
	public void testCoopQueryFound() {
		assertEquals(COOP_KEY, coopPositionService.getCoopPositionById(COOP_KEY).getCoopId());
	}

	@Test
	public void testDocQueryFound() {
		assertEquals(DOC_KEY, rdocService.getRequiredDocumentById(DOC_KEY).getDocumentId());
	}

	// Grading tests
	@Test
	public void testGrading() {
		assertEquals(GRADE, rdocService.getRequiredDocumentById(DOC_KEY).getAccepted());
	}

//	@Test
//	public void testViewGrade() {
//		
//	}

}
