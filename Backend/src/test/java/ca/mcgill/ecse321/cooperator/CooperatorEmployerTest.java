package ca.mcgill.ecse321.cooperator;


import java.util.ArrayList;
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
import ca.mcgill.ecse321.cooperator.services.EmployerService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CooperatorEmployerTest{

	@Mock
	private EmployerRepository employerDao;
	
	@InjectMocks
	private EmployerService employerService;

	private Employer employer;
	private EmployerContract contract;
	private static final Integer EMPLOYER_KEY = 10;
	private static final Integer WRONG_EMPLOYER_KEY = 11;
	
	
	private List<Employer> expectedList = new ArrayList<Employer>();
	private Set<EmployerContract> contracts = new HashSet<EmployerContract>();
	
	
	@Before
	public void setMockOutput() {
		when(employerDao.findById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(EMPLOYER_KEY)) {
				Employer employer = new Employer();
				employer.setEmployerID(EMPLOYER_KEY);
				employer.setEmployerContract(contracts);
				return employer;
			} else {
				return null;
			}
		
		});
		
	}

	@Before
	public void setUpMocks() {
		employer = mock(Employer.class);
		contract = mock(EmployerContract.class);
		contract = new EmployerContract();
		contracts.add(contract);
		employer = employerService.createEmployer();
		employer.setEmployerContract(contracts);
		expectedList.add(employer);
	}
	
	@Test
	public void testemployerCreation() {
		assertNotNull(employer);
	}
	
	@Test
	public void testemployerQueryFound() {
		assertEquals(EMPLOYER_KEY, employerService.getEmployerById(EMPLOYER_KEY).getEmployerID());
	
	}
	
	@Test
	public void testemployerQueryNotFound() {
		assertNull(employerService.getEmployerById(WRONG_EMPLOYER_KEY));
	}
	
	@Test
	public void testEmployerContract() {
		assertEquals(contracts, employerService.getEmployerById(EMPLOYER_KEY).getEmployerContract());
	}
	

	
	
	
	
	
	
	
}