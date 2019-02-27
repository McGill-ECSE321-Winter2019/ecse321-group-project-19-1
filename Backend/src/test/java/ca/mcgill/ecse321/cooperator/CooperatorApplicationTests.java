package ca.mcgill.ecse321.cooperator;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
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
import ca.mcgill.ecse321.cooperator.services.CooperatorService;

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
	private CooperatorManagerRepository systemDao;
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
	private CooperatorController controller;
	@InjectMocks
	private CooperatorService service;

	private UserEntity users;
	private static final String SYSTEM_KEY = "TestSystem";
	private static final String NONEXISTING_KEY = "NotASystem";

	@Before
	public void setMockOutput() {
	  when(userDao.findUserEntityByEmail(anyString())).thenAnswer( (InvocationOnMock invocation) -> {
	    if(invocation.getArgument(0).equals(SYSTEM_KEY)) {
	      UserEntity users = new TermInstructor();
	      users.setEmail(SYSTEM_KEY);
	      return users;
	    } else {
	      return null;
	    }
	  });
	}
	
	@Before
	public void setupMock() {
		users = mock(UserEntity.class);
	}

	@Test
	public void testMockSystemCreation() {
		assertNotNull(users);
	}

	@Test
	public void testSystemQueryFound() {
	  assertNotNull(service.getUserEntityByEmail(SYSTEM_KEY));
	}

}