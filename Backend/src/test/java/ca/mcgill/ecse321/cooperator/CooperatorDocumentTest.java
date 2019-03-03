package ca.mcgill.ecse321.cooperator;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.invocation.InvocationOnMock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ca.mcgill.ecse321.cooperator.dao.*;
import ca.mcgill.ecse321.cooperator.model.*;
import ca.mcgill.ecse321.cooperator.services.EmployerService;
import ca.mcgill.ecse321.cooperator.services.RequiredDocumentService;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CooperatorDocumentTest{

	@Mock
	private RequiredDocumentRepository docDao;
	
	@InjectMocks
	private RequiredDocumentService docService;

	private EmployerContract docs;
	private static final Integer DOCUMENT_ID = 10;
	private static final Integer WRONG_DOCUMENT_ID = 11;
	private static final Date DOCUMENT_DATE = new Date();
	private static final String DOCUMENT_NAME = "*DOCUMENT NAME*";
	private static final CoopPosition cp = new CoopPosition();
	
	private List<RequiredDocument> expectedList = new ArrayList<RequiredDocument>();
	
	
	@Before
	public void setMockOutput() {
		when(docDao.findById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(DOCUMENT_ID)) {
				EmployerContract docs = new EmployerContract();
				docs.setDocumentId(DOCUMENT_ID);
				docs.setDueDate(DOCUMENT_DATE);
				docs.setName(DOCUMENT_NAME);
				docs.setCoopPosition(cp);
				docs.setAccepted(false);
				return docs;
			} else {
				return null;
			}
		});
		when(docDao.findRequiredDocumentByDueDate((Date)Matchers.any())).thenAnswer((InvocationOnMock invocation) ->{
			return null; //TO DO
		});
			
	}

	@Before
	public void setUpMocks() {
		docs = mock(EmployerContract.class);
		docs = docService.createEmployerContract(DOCUMENT_NAME, DOCUMENT_DATE, cp);
		expectedList.add(docs);
	}
	
	@Test
	public void testemployerCreation() {
		assertNotNull(docs);
	}
	
	@Test
	public void testDocumentQueryFound() {
		assertEquals(DOCUMENT_ID, docService.getRequiredDocumentById(DOCUMENT_ID).getDocumentId());
	}
	@Test
	public void testDocumentQueryNotFound() {
		assertNull(docService.getRequiredDocumentById(WRONG_DOCUMENT_ID));
	}
	
	@Test
	public void testSetDocumentStatus() {
		
	}
	

	
	
	
	
	
	
	
}