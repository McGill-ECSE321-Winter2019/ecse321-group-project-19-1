package ca.mcgill.ecse321.cooperator;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CooperatorCoopPositionTest{

	@Mock
	private CoopPositionRepository coopPositionDao;
	
	@InjectMocks
	private CoopPositionService coopPositionService;

	private CoopPosition coop;
	private Student student;
	private TermInstructor termInstructor;
	private Form form;
	private Course course;
	private static final Integer COOP_KEY = 10;
	private static final Integer WRONG_COOP_KEY = 69;
	private static final Date startDate = new Date(5);
	private static final Date endDate = new Date(10);
	private static final String Tester = "TEST";

	private Set<TermInstructor> TermInstructorSet = new HashSet<TermInstructor>();
	private Set<Course> CourseSet = new HashSet<Course>();
	private Set<RequiredDocument> RequiredDocumentSet = new HashSet<RequiredDocument>();

	@Before
	public void setMockOutput() {
		when(coopPositionDao.findById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(COOP_KEY)) {
				coop.setCoopId(COOP_KEY);
				return coop;
				
			} else {
				return null;
			}
		});
		when(coopPositionDao.findCoopPositionByStudent(any())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(student)) {
				List<CoopPosition> list = new ArrayList<CoopPosition>();
				coop.setStudent(student);
				list.add(coop);
				return list;
					
			} else {
				return null;
			}
		});
		when(coopPositionDao.findCoopPositionByTerm(anyString())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(Tester)) {
				List<CoopPosition> list = new ArrayList<CoopPosition>();
				coop.setTerm(Tester);
				list.add(coop);
				return list;
			} else {
				return null;
			}
		});
		when(coopPositionDao.findCoopPositionByTermInstructor(any())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(termInstructor)) {
				List<CoopPosition> list = new ArrayList<CoopPosition>();
				coop.setTermInstructor(TermInstructorSet);
				list.add(coop);
				return list;
				
			} else {
				return null;
			}
		});
	}

	
	@Before
	public void setUpMocks() {
		coop = mock(CoopPosition.class);
		student = mock(Student.class);
		termInstructor = mock(TermInstructor.class);
		form = mock(Form.class);
		course = mock(Course.class);
		
		
		student = new Student();
		termInstructor = new TermInstructor();
		form = new Form();
		course = new Course();
		
		TermInstructorSet.add(termInstructor);
		CourseSet.add(course);
		RequiredDocumentSet.add(form);
		
		coop = coopPositionService.createCoopPosition(startDate, endDate, Tester, Tester, Tester, student);
		
		coop.setRequiredDocument(RequiredDocumentSet);
		coop.setTermInstructor(TermInstructorSet);
		coop.setUsefulCourses(CourseSet);
	}
	
	@Test
	public void testCoopCreation() {
		assertNotNull(coop);
	}

	@Test
	public void testCoopQueryFound() {
		assertEquals(COOP_KEY, coopPositionService.getCoopPositionById(COOP_KEY).getCoopId());
		assertEquals(TermInstructorSet, coopPositionService.getCoopPositionById(COOP_KEY).getTermInstructor());
		assertEquals(student, coopPositionService.getCoopPositionById(COOP_KEY).getStudent());
		assertEquals(RequiredDocumentSet, coopPositionService.getCoopPositionById(COOP_KEY).getRequiredDocument());
		assertEquals(Tester, coopPositionService.getCoopPositionById(COOP_KEY).getDescription());
		assertEquals(Tester, coopPositionService.getCoopPositionById(COOP_KEY).getTerm());
		assertEquals(Tester, coopPositionService.getCoopPositionById(COOP_KEY).getLocation());
		assertEquals(startDate, coopPositionService.getCoopPositionById(COOP_KEY).getStartDate());
		assertEquals(endDate, coopPositionService.getCoopPositionById(COOP_KEY).getEndDate());
		assertEquals(CourseSet, coopPositionService.getCoopPositionById(COOP_KEY).getUsefulCourses());
	}
	
	
	@Test
	public void testStudentQueryNotFound() {
		assertNull(coopPositionService.getCoopPositionById(WRONG_COOP_KEY));
	}
	
	@Test
	public void testFindByStudent() {
		assertEquals(student,coopPositionService.getCoopPositionsByStudent(student).get(0).getStudent());
	}
	
	@Test
	public void testFindByTermInstructor() {
		assertEquals(termInstructor, coopPositionService.getCoopPositionsByTermInstructor(termInstructor).get(0).getTermInstructor().iterator().next());
	}
	
	@Test
	public void testFindTerm() {
		assertEquals(Tester,coopPositionService.getCoopPositionsByTerm(Tester).get(0).getTerm());
	}
	

	
	
	
	
}