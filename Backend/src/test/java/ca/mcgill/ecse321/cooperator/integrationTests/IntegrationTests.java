package ca.mcgill.ecse321.cooperator.integrationTests;

import ca.mcgill.ecse321.cooperator.dao.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IntegrationTests {

    private final String BASE_URL = "http://cooperator-backend-260.herokuapp.com";
    private final String COURSE_NAME = "test101";
    private final String EMAIL = "yoyo@gmail.com";
    private JSONObject joResponse;
    private JSONArray jaResponse;

    @Autowired
    private RequiredDocumentRepository rdocRepo;
    @Autowired
    private CourseRepository courseRepo;
    @Autowired
    private UserEntityRepository userRepo;
    @Autowired
    private CoopPositionRepository cpRepo;
    @Autowired
    private StudentRepository studentRepo;
    @Autowired
    private EmployerRepository employerRepo;


    @Before
    public void clearDatabase() {
        rdocRepo.deleteAll();
        employerRepo.deleteAll();
        courseRepo.deleteAll();
        userRepo.deleteAll();
        cpRepo.deleteAll();
        studentRepo.deleteAll();
    }

//    @Test
//    public void TestAddingCourse() {
//        try {
//            joResponse = SendRequests.sendRequest("POST", BASE_URL, "/createCourse", "courseName=" + COURSE_NAME);
//            System.out.println("Received: " + joResponse.toString());
//            assertEquals(COURSE_NAME, joResponse.getString("courseName"));
//        } catch (RuntimeException e) {
//            System.out.println(e.getMessage());
//            e.printStackTrace();
//            fail();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }


//	@Test
//	public void testGetProblematic() {
//		try {
//			RESTtestDatabaseSetup.databaseSetup();
//			// getting problematic student. expected output to be student2
//			jaResponse = SendRequests.sendRequestArray("GET", BASE_URL, "/problematicStudents");
//			System.out.println("PROBLEMATIC: " + jaResponse.toString());
//			assertEquals("andre", jaResponse.getJSONObject(0).get("firstName"));
//			assertNotEquals("max", jaResponse.getJSONObject(0).get("firstName"));
//
//		} catch (RuntimeException e) {
//			System.out.println(e.getMessage());
//			e.printStackTrace();
//			fail();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	}

//	@Test
//	public void testGradeDocument() {
//		try {
//			int[] arr = RESTtestDatabaseSetup.databaseSetup();
//			// Grading the document
//			String answer = "True";
//			joResponse = SendRequests.sendRequest("POST", BASE_URL, "/gradeDocument",
//					"documentId=" + arr[2] + "&grade="+answer + "&instructorEmail=" + EMAIL);
//			System.out.println("GRADE_DOC: " + joResponse.toString());
//			//assertEquals("True", joResponse.getString("grade").toString());
//
//		} catch (RuntimeException e) {
//			System.out.println(e.getMessage());
//			e.printStackTrace();
//			fail();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	}

//	@Test
//	public void testViewGrade() {
//		try {
//			int[] arr = RESTtestDatabaseSetup.databaseSetup();
//			joResponse = SendRequests.sendRequest("GET", BASE_URL, "/grade", "documentId=" + arr[2]);
//			System.out.println("VIEW_GRADE: " + joResponse.toString());
//			assertEquals("COMPLETED", joResponse.getString("status"));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	}

//	@Test
//	public void testAdjudicateCoop() {
//		try {
//			int[] arr = RESTtestDatabaseSetup.databaseSetup();
//			joResponse = SendRequests.sendRequest("POST", BASE_URL, "/setCoopStatus",
//					"coopId=" + arr[1] + "&status=COMPLETED");
//			System.out.println("ADJUDICATE_COOP: " + joResponse.toString());
//			assertEquals("COMPLETED", joResponse.getString("status"));
//		} catch (RuntimeException e) {
//			System.out.println(e.getMessage());
//			e.printStackTrace();
//			fail();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
//	@Test
//	public void testRateCourse() {
//		try {
//		int[] arr =RESTtestDatabaseSetup.databaseSetup();
//		joResponse = SendRequests.sendRequest("POST", BASE_URL, "/rateCourse","courseName=ECSE"+"&coopId="+arr[1]+"&useful=True");
//		assertNotEquals(null,joResponse.getString("CoopPositions"));
//		} catch (RuntimeException e) {
//			System.out.println(e.getMessage());
//			e.printStackTrace();
//			fail();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	@Test
//	public void testCourseRanking() {
//		try {
//			int[] arr =RESTtestDatabaseSetup.databaseSetup();
//			//rating courses
//			joResponse = SendRequests.sendRequest("POST", BASE_URL, "/rateCourse/ECSE321","coopId="+arr[1]+"&useful=True");
//			joResponse = SendRequests.sendRequest("POST", BASE_URL, "/rateCourse/ECSE321","coopId="+arr[3]+"&useful=False");
//			joResponse = SendRequests.sendRequest("POST", BASE_URL, "/rateCourse/ECSE310","coopId="+arr[3]+"&useful=True");
//			joResponse = SendRequests.sendRequest("POST", BASE_URL, "/rateCourse/ECSE321","coopId="+arr[4]+"&useful=True");
//			joResponse = SendRequests.sendRequest("POST", BASE_URL, "/rateCourse/ECSE310","coopId="+arr[3]+"&useful=False");
//			
//			//Getting list of courses ranked
//			jaResponse = SendRequests.sendRequestArray("POST", BASE_URL, "/ranking");
//
//			//Asserting
//			assertEquals("ECSE321",jaResponse.getJSONObject(0).getString("courseName"));
//			assertEquals("ECSE310",jaResponse.getJSONObject(1).getString("courseName"));
//			
//			} catch (RuntimeException e) {
//				System.out.println(e.getMessage());
//				e.printStackTrace();
//				fail();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//	
//	}

//	@Test
//	public void testAssignTermInstructorToCoop() {
//		try {
//		RESTtestDatabaseSetup.databaseSetup();
//		jaResponse = SendRequests.sendRequestArray("GET", BASE_URL, "/termInstructors");
//		assertEquals("sophie",jaResponse.getJSONObject(0).getString("firstName"));
//		}catch (RuntimeException e) {
//			System.out.println(e.getMessage());
//			e.printStackTrace();
//			fail();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

}

