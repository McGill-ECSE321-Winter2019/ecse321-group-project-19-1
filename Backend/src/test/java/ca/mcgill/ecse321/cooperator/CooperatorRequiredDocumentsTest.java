package ca.mcgill.ecse321.cooperator;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ca.mcgill.ecse321.cooperator.dao.*;
import ca.mcgill.ecse321.cooperator.model.*;
import ca.mcgill.ecse321.cooperator.services.RequiredDocumentService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CooperatorRequiredDocumentsTest {
	
	@Test
	public void contextLoads() {
	}

	@Mock
	private RequiredDocumentRepository docDao;
	
	@InjectMocks
	private RequiredDocumentService docService;

	private EmployerContract docs;
	private EmployerContract docs2;
	private static final Integer DOCUMENT_ID = 10;
	private static final Integer DOCUMENT_ID2 = 20;
	private static final Integer WRONG_DOCUMENT_ID = 11;
	private static final Date DOCUMENT_DATE1 = new Date(1);
	private static final Date DOCUMENT_DATE2 = new Date(2);
	private static final Date WRONG_DOCUMENT_DATE = new Date(10);
	private static final String DOCUMENT_NAME1 = "*DOCUMENT NAME1*";
	private static final String DOCUMENT_NAME2 = "*DOCUMENT NAME1*";
	private static final CoopPosition cp1 = new CoopPosition();
	private static final CoopPosition cp2 = new CoopPosition();
	private static final CoopPosition WRONG_CP = new CoopPosition();
	
	private List<RequiredDocument> expectedList = new ArrayList<RequiredDocument>();
	
	
	
	@Before
	public void setMockOutput() {
		when(docDao.findById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(DOCUMENT_ID)) {
				EmployerContract docs = new EmployerContract();
				cp1.setDescription("First cp");
				docs.setDocumentId(DOCUMENT_ID);
				docs.setDueDate(DOCUMENT_DATE1);
				docs.setName(DOCUMENT_NAME1);
				docs.setCoopPosition(cp1);
				docs.setAccepted(false);
				return docs;
			} else if(invocation.getArgument(0).equals(DOCUMENT_ID2)){
				EmployerContract docs = new EmployerContract();
				docs.setDocumentId(DOCUMENT_ID2);
				docs.setDueDate(DOCUMENT_DATE2);
				docs.setName(DOCUMENT_NAME2);
				cp2.setDescription("Second cp");
				docs.setCoopPosition(cp2);
				docs.setAccepted(true);
				return docs;
			}else
				return null;
		});
		when(docDao.findRequiredDocumentByDueDate((Date)Matchers.any())).thenAnswer((InvocationOnMock invocation) ->{
			List<RequiredDocument> result = new ArrayList<RequiredDocument>();
			if(invocation.getArgument(0).equals(DOCUMENT_DATE1)) {
				result.add(docs);
			} else if(invocation.getArgument(0).equals(DOCUMENT_DATE2)) {
				result.add(docs2);
			} 
			return result;
		});
		when(docDao.findRequiredDocumentByCoopPosition((CoopPosition) Matchers.any())).thenAnswer((InvocationOnMock invocation) ->{
			List<RequiredDocument> result = new ArrayList<RequiredDocument>();
			if(invocation.getArgument(0).equals(cp1))
				result.add(docs);
			else if(invocation.getArgument(0).equals(cp2))
				result.add(docs2);
			
			return result;
				
		});
			
	}

	@Before
	public void setupMocks() {
		docs = mock(EmployerContract.class);
		docs = docService.createEmployerContract(DOCUMENT_NAME1, DOCUMENT_DATE1, cp1);
		expectedList.add(docs);
		docs2 = mock(EmployerContract.class);
		docs2 = docService.createEmployerContract(DOCUMENT_NAME2, DOCUMENT_DATE2, cp2);
		expectedList.add(docs2);
	}
	
	@Test
	public void testemployerCreation() {
		assertNotNull(docs);
		assertNotNull(docs2);
	}
	
	@Test
	public void testDocumentQueryFoundByID() {
		assertEquals(DOCUMENT_ID, docService.getRequiredDocumentById(DOCUMENT_ID).getDocumentId());
		assertEquals(DOCUMENT_ID2, docService.getRequiredDocumentById(DOCUMENT_ID2).getDocumentId());
	}
	
	@Test
	public void testDocumentQueryFoundByDueDate() {
		assertTrue(!docService.getRequiredDocumentByDueDate(DOCUMENT_DATE1).isEmpty());
		assertEquals(1, docService.getRequiredDocumentByDueDate(DOCUMENT_DATE1).size());
		assertEquals(DOCUMENT_DATE1, docService.getRequiredDocumentByDueDate(DOCUMENT_DATE1).get(0).getDueDate());
		assertTrue(!docService.getRequiredDocumentByDueDate(DOCUMENT_DATE2).isEmpty());
		assertEquals(1, docService.getRequiredDocumentByDueDate(DOCUMENT_DATE2).size());
		assertEquals(DOCUMENT_DATE2, docService.getRequiredDocumentByDueDate(DOCUMENT_DATE2).get(0).getDueDate());
	}
	
	@Test
	public void testDocumentQueryFoundByCoopPosition() {
		assertTrue(!docService.getRequiredDocumentByCoopPosition(cp1).isEmpty());
		assertEquals(1, docService.getRequiredDocumentByCoopPosition(cp1).size());
		assertEquals(cp1, docService.getRequiredDocumentByCoopPosition(cp1).get(0).getCoopPosition());
		assertTrue(!docService.getRequiredDocumentByCoopPosition(cp2).isEmpty());
		assertEquals(1, docService.getRequiredDocumentByCoopPosition(cp2).size());
		assertEquals(cp2, docService.getRequiredDocumentByCoopPosition(cp2).get(0).getCoopPosition());
	}
	@Test
	public void testDocumentQueryNotFound() {
		assertNull(docService.getRequiredDocumentById(WRONG_DOCUMENT_ID));
		assertTrue(docService.getRequiredDocumentByDueDate(WRONG_DOCUMENT_DATE).isEmpty());
		assertTrue(docService.getRequiredDocumentByCoopPosition(WRONG_CP).isEmpty());
	}
	
	@Test
	public void testGetDocumentAcceptedStatus() {
		assertTrue(!docService.getRequiredDocumentById(DOCUMENT_ID).getAccepted());
		assertTrue(docService.getRequiredDocumentById(DOCUMENT_ID2).getAccepted());
	}
	
	@Test
	public void testSetDocumentAcceptedStatus() {
		RequiredDocument document = docService.getRequiredDocumentById(DOCUMENT_ID);
		boolean pastStatus = document.getAccepted();
		document.setAccepted(!pastStatus);
		assertEquals(!pastStatus, document.getAccepted());
	}
	

	
}