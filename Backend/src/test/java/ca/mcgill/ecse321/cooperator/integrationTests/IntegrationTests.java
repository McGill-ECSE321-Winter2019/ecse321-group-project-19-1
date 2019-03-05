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
    public void testCourseCreation() {
        try {
            joResponse = SendRequests.sendRequest("POST", BASE_URL, "/createCourse", "courseName=" + COURSE_NAME);
            System.out.println("Received: " + joResponse.toString());
            assertEquals(COURSE_NAME, joResponse.getString("courseName"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            fail();
        }
    }


    @Test
    public void testGetProblematic() {
        try {
            // creating students
            JSONObject s1 = SendRequests.sendRequest("POST", BASE_URL, "/createStudent",
                    "firstName=" + "max" + "&lastName=" + "brodeur");
            int s1_id = s1.getInt("studentId");
            JSONObject s2 = SendRequests.sendRequest("POST", BASE_URL, "/createStudent",
                    "firstName=" + "andre" + "&lastName=" + "kaba");

            // creating coop position for students s1,s3,s4 (not s2 for testGetProblematic)
            JSONObject cp1 = SendRequests.sendRequest("POST", BASE_URL, "/createCoop",
                    "startDate=02/01/2018" + "&endDate=02/01/2019" + "&description=hello" + "&location=montreal"
                            + "&term=fall" + "&studentId=" + s1_id);

            // getting problematic student. expected output to be student2
            jaResponse = SendRequests.sendRequestArray("GET", BASE_URL, "/problematicStudents");
            System.out.println("PROBLEMATIC: " + jaResponse.toString());
            assertEquals("andre", jaResponse.getJSONObject(0).get("firstName"));
            assertNotEquals("max", jaResponse.getJSONObject(0).get("firstName"));

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testGradeDocument() {
        try {
            // creating student
            JSONObject s = SendRequests.sendRequest("POST", BASE_URL, "/createStudent",
                    "firstName=" + "mia" + "&lastName=" + "zhou");
            int s_Id = s.getInt("studentId");
            JSONObject cp = SendRequests.sendRequest("POST", BASE_URL, "/createCoop",
                    "startDate=05/01/2018" + "&endDate=05/01/2019" + "&description=world" + "&location=montreal"
                            + "&term=fall" + "&studentId=" + s_Id);
            int cp_Id = cp.getInt("coopID");
            JSONObject ti = SendRequests.sendRequest("POST", BASE_URL, "/createTermInstructor/" + EMAIL,
                    "firstName=sophie" + "&lastName=Deng" + "&password=123");
            JSONObject doc = SendRequests.sendRequest("POST", BASE_URL, "/createForm",
                    "name=hello" + "&dueDate=02/01/2019" + "&coopId=" + cp_Id);
            int doc_Id = doc.getInt("documentId");
            JSONObject graded_doc = SendRequests.sendRequest("POST", BASE_URL, "/gradeDocument",
                    "documentId=" + doc_Id + "&grade=True" + "&instructorEmail=" + EMAIL);
            System.out.println(graded_doc);
            assertTrue(graded_doc.getBoolean("accepted"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testEmployerEvaluation() {
        try {
            String evaluation = "goodjobdawg!";

            // creating student
            JSONObject s = SendRequests.sendRequest("POST", BASE_URL, "/createStudent",
                    "firstName=" + "carl" + "&lastName=" + "alkhoury");
            int s_Id = s.getInt("studentId");

            // Create coop position
            JSONObject cp = SendRequests.sendRequest("POST", BASE_URL, "/createCoop",
                    "startDate=05/01/2018" + "&endDate=05/01/2019" + "&description=google" + "&location=murica"
                            + "&term=inator" + "&studentId=" + s_Id);
            int cp_Id = cp.getInt("coopID");

            // Create an employer
            JSONObject e = SendRequests.sendRequest("POST", BASE_URL, "/createEmployer");
            int e_Id = e.getInt("employerId");

            // Create employer contract
            JSONObject contract = SendRequests.sendRequest("POST", BASE_URL, "/createEmployerContract",
                    "name=googleOffer" + "&dueDate=02/01/2019" + "&coopId=" + cp_Id + "&employerId=" + e_Id);

            int doc_Id = contract.getInt("documentId");
            assertNotEquals(evaluation,contract.getString("evaluation"));

            // Evaluate the coop through the contract

            // Create employer contract
            JSONObject evaluated_contract = SendRequests.sendRequest("POST", BASE_URL, "/setEmployerContractEvaluation",
                    "employerId=" +e_Id+ "&employerContractId=" +doc_Id+ "&evaluation=" + evaluation);

            assertEquals(evaluation,evaluated_contract.getString("evaluation"));

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            fail();
        }
    }

//    @Test
//    public void testViewGrade() {
//        try {
//            int[] arr = RESTtestDatabaseSetup.databaseSetup();
//            joResponse = SendRequests.sendRequest("GET", BASE_URL, "/grade", "documentId=" + arr[2]);
//            System.out.println("VIEW_GRADE: " + joResponse.toString());
//            assertEquals("COMPLETED", joResponse.getString("status"));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }

//    @Test
//    public void testAdjudicateCoop() {
//        try {
//            int[] arr = RESTtestDatabaseSetup.databaseSetup();
//            joResponse = SendRequests.sendRequest("POST", BASE_URL, "/setCoopStatus",
//                    "coopId=" + arr[1] + "&status=COMPLETED");
//            System.out.println("ADJUDICATE_COOP: " + joResponse.toString());
//            assertEquals("COMPLETED", joResponse.getString("status"));
//        } catch (RuntimeException e) {
//            System.out.println(e.getMessage());
//            e.printStackTrace();
//            fail();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void testRateCourse() {
//        try {
//            int[] arr = RESTtestDatabaseSetup.databaseSetup();
//            joResponse = SendRequests.sendRequest("POST", BASE_URL, "/rateCourse", "courseName=ECSE321" + "&coopId=" + arr[1] + "&useful=True");
//            System.out.println("RATED_COURSE: " + joResponse.toString());
//            assertNotEquals(null, joResponse.getString("coopPositions"));
//            assertEquals(arr[1], joResponse.getString("coopPositions"));
//        } catch (JSONException e) {
//            fail();
//        } catch (RuntimeException e) {
//            System.out.println(e.getMessage());
//            e.printStackTrace();
//            fail();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void testCourseRanking() {
//        try {
//            int[] arr = RESTtestDatabaseSetup.databaseSetup();
//            //rating courses
//            joResponse = SendRequests.sendRequest("POST", BASE_URL, "/rateCourse/ECSE321", "coopId=" + arr[1] + "&useful=True");
//            joResponse = SendRequests.sendRequest("POST", BASE_URL, "/rateCourse/ECSE321", "coopId=" + arr[3] + "&useful=False");
//            joResponse = SendRequests.sendRequest("POST", BASE_URL, "/rateCourse/ECSE310", "coopId=" + arr[3] + "&useful=True");
//            joResponse = SendRequests.sendRequest("POST", BASE_URL, "/rateCourse/ECSE321", "coopId=" + arr[4] + "&useful=True");
//            joResponse = SendRequests.sendRequest("POST", BASE_URL, "/rateCourse/ECSE310", "coopId=" + arr[3] + "&useful=False");
//
//            //Getting list of courses ranked
//            jaResponse = SendRequests.sendRequestArray("POST", BASE_URL, "/ranking");
//
//            //Asserting
//            assertEquals("ECSE321", jaResponse.getJSONObject(0).getString("courseName"));
//            assertEquals("ECSE310", jaResponse.getJSONObject(1).getString("courseName"));
//
//        } catch (RuntimeException e) {
//            System.out.println(e.getMessage());
//            e.printStackTrace();
//            fail();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    @Test
//    public void testAssignTermInstructorToCoop() {
//        try {
//            RESTtestDatabaseSetup.databaseSetup();
//            jaResponse = SendRequests.sendRequestArray("GET", BASE_URL, "/termInstructors");
//            assertEquals("sophie", jaResponse.getJSONObject(0).getString("firstName"));
//        } catch (RuntimeException e) {
//            System.out.println(e.getMessage());
//            e.printStackTrace();
//            fail();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

}

