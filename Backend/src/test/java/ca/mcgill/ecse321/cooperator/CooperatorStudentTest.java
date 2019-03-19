package ca.mcgill.ecse321.cooperator;


import java.util.ArrayList;
import java.util.HashSet;
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
import ca.mcgill.ecse321.cooperator.services.StudentService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CooperatorStudentTest{

	@Mock
	private StudentRepository studentDao;
	
	@InjectMocks
	private StudentService studentService;

	private Student student;
	private static final Integer STUDENT_KEY = 10;
	private static final Integer WRONG_STUDENT_KEY = 69;
	private static final Boolean STUDENT_STATUS = true;
	private static final Boolean NEW_STUDENT_STATUS = false;
	
	private List<Student> expectedList = new ArrayList<Student>();
	
	
	@Before
	public void setMockOutput() {
		when(studentDao.findById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(STUDENT_KEY)) {
				Student student = new Student();
				student.setStudentID(STUDENT_KEY);
				student.setProblematic(STUDENT_STATUS);
				return student;
			} else {
				return null;
			}
		
		});
		when(studentDao.findStudentByProblematic(STUDENT_STATUS)).thenAnswer((InvocationOnMock invocation) ->{
			List<Student> list = new ArrayList<Student>();
			list.add(student);
			return list;
			
		});
	}

	
	@Before
	public void setUpMocks() {
		student = mock(Student.class);
		student = studentService.createStudent("yoyo","yaya");
		expectedList.add(student);
	}
	
	@Test
	public void testStudentCreation() {
		assertNotNull(student);
	}
	
	@Test
	public void testStudentQueryFound() {
		assertEquals(STUDENT_KEY, studentService.getStudentById(STUDENT_KEY).getStudentID());
		assertEquals(STUDENT_STATUS,studentService.getStudentById(STUDENT_KEY).getProblematic());
		assertNull(studentService.getStudentById(STUDENT_KEY).getFirstName());
		assertNull(studentService.getStudentById(STUDENT_KEY).getLastName());
		assertEquals(new HashSet<>(),studentService.getStudentById(STUDENT_KEY).getCoopPosition());

	}
	
	@Test
	public void testStudentQueryNotFound() {
		assertNull(studentService.getStudentById(WRONG_STUDENT_KEY));
	}
	
	@Test
	public void testGetProblematicStudents() {
		assertEquals(expectedList, studentService.getAllProblematicStudents());
	}
	
	@Test
	public void testSetProblematicStatus() {
		Student s = studentService.getStudentById(STUDENT_KEY);
		s.setProblematic(NEW_STUDENT_STATUS);
		assertEquals(NEW_STUDENT_STATUS, s.getProblematic());
	}
	
	@Test
	public void testStudentDeletion() {
		assertEquals(true, studentService.deleteStudent(STUDENT_KEY));
	}
	
}