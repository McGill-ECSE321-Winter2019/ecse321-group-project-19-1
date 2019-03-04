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

    @Test
    public void TestAddingCourse() {
        try {
            joResponse = SendRequests.sendRequest("POST", BASE_URL, "/createCourse", "courseName=" + COURSE_NAME);
            System.out.println("Received: " + joResponse.toString());
            assertEquals(COURSE_NAME, joResponse.getString("courseName"));
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            fail();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    @Test
//    public void testGetProblematic() {
//        try {
//
//            // creating two students
//            JSONObject student1 = SendRequests.sendRequest("POST", BASE_URL, "/createStudent",
//                    "firstName=" + "max" + "&lastName=" + "brodeur");
//            SendRequests.sendRequest("POST", BASE_URL, "/createStudent", "firstName=" + "andre" + "&lastName=" + "kaba");
//            int STUDENT_ID = student1.getInt("studentId");
//
//            // creating coop position for student1
//            JSONObject cp = SendRequests.sendRequest("POST", BASE_URL, "/createCoop", "startDate=02/01/2018" + "&endDate=02/01/2019"
//                    + "&description=hello" + "&location=montreal" + "&term=fall" + "&studentId=" + STUDENT_ID);
//            System.out.println("Received: " + cp.toString());
//
//            // getting problematic student. expected output to be student2
//            jaResponse = SendRequests.sendRequestArray("GET", BASE_URL, "/problematicStudents");
//            System.out.println("Received: " + jaResponse.toString());
//            assertEquals("andre", jaResponse.getJSONObject(4).get("firstName"));
//            assertNotEquals("max", jaResponse.getJSONObject(0).get("firstName"));
//
//        } catch (RuntimeException e) {
//            System.out.println(e.getMessage());
//            e.printStackTrace();
//            fail();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

//	@Test
//	public void testGradeDocument() {
//		try {
//			// creating two students
//			JSONObject student1 = SendRequests.sendRequest("POST", BASE_URL, "/createStudent",
//					"firstName=" + "mia" + "&lastName=" + "zhou");
//			int s_Id = student1.getInt("studentId");
//
//			// creating coop position for student1
//			JSONObject cp = SendRequests.sendRequest("POST", BASE_URL, "/createCoop", "startDate=02/01/2018" + "&endDate=02/01/2020"
//					+ "&description=yo" + "&location=murica" + "&term=fall" + "&studentId=" + s_Id);
//			int cp_Id = cp.getInt("coopID");
//
//			//Creating a form
//			JSONObject doc=SendRequests.sendRequest("POST", BASE_URL, "/createForm", "name=hello" + "&dueDate=02/01/2019" + "&coopId="+cp_Id);
//			int doc_id = doc.getInt("documentId");
//			System.out.println("DEBUG: " + doc.toString());
//
//			//Creating a term instructor
//			JSONObject ti=SendRequests.sendRequest("POST", BASE_URL, "/createTermInstructor/"+EMAIL, "firstName=sophie" + "&lastName=Deng" + "&password=123");
//			System.out.println("DEBUG: " + ti.toString());
//
//			//Grading the document
//			joResponse = SendRequests.sendRequest("POST", BASE_URL, "/gradeDocument",
//					"documentId=" + doc_id + "&grade=True" + "&instructorEmail=" + EMAIL);
//			System.out.println("Received: " + joResponse.toString());
//			assertEquals(true, joResponse.getString("grade"));
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
//			joResponse=SendRequests.sendRequest("GET", BASE_URL, "/grade", "documentId=19");
//			System.out.println("Received: " + joResponse.toString());
//			assertEquals("COMPLETED", joResponse.getString("status"));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	}

//	@Test
//	public void testAdjudicateCoop() {
//		try {
//			joResponse=SendRequests.sendRequest("POST", BASE_URL, "/setCoopStatus", "coopId=19" +"&status=COMPLETED");
//			System.out.println("Received: " + joResponse.toString());
//			assertEquals("COMPLETED", joResponse.getString("status"));
//		} catch (RuntimeException e) {
//			System.out.println(e.getMessage());
//			e.printStackTrace();
//			fail();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

    @Test
    public void testCourseRanking() {
        //TODO
        //Create courses
        //Assign coop positions to courses
        //Assert by getting the name of the course at predetermined index
    }

    @Test
    public void testAssignTermInstructorToCoop() {
        //TODO
        //Create term instructor
        //Assign coop
        //Assert by ...
    }

}