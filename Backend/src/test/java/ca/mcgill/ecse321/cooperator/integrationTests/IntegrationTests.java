package ca.mcgill.ecse321.cooperator.integrationTests;

import ca.mcgill.ecse321.cooperator.dao.*;
import ca.mcgill.ecse321.cooperator.model.Status;
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

            // creating coop position for students 1 and 2
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

            // create a coop
            JSONObject cp = SendRequests.sendRequest("POST", BASE_URL, "/createCoop",
                    "startDate=05/01/2018" + "&endDate=05/01/2019" + "&description=world" + "&location=montreal"
                            + "&term=fall" + "&studentId=" + s_Id);
            int cp_Id = cp.getInt("coopID");

            // create a term instructor
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
            assertNotEquals(evaluation, contract.getString("evaluation"));

            // Evaluate the coop through the contract

            // Create employer contract
            JSONObject evaluated_contract = SendRequests.sendRequest("POST", BASE_URL, "/setEmployerContractEvaluation",
                    "employerId=" + e_Id + "&employerContractId=" + doc_Id + "&evaluation=" + evaluation);

            assertEquals(evaluation, evaluated_contract.getString("evaluation"));

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testCourseRanking() {
        try {
            String course_name1 = "CRAP101";
            String course_name2 = "ECSE321";

            // creating student
            JSONObject s = SendRequests.sendRequest("POST", BASE_URL, "/createStudent",
                    "firstName=" + "sophie" + "&lastName=" + "deng");
            int s_Id = s.getInt("studentId");

            // Create coop position
            JSONObject cp = SendRequests.sendRequest("POST", BASE_URL, "/createCoop",
                    "startDate=05/01/2018" + "&endDate=05/01/2022" + "&description=niceshit" + "&location=yorktown"
                            + "&term=wow" + "&studentId=" + s_Id);
            int cp_Id = cp.getInt("coopID");

            // Create course 1
            JSONObject course1 = SendRequests.sendRequest("POST", BASE_URL, "/createCourse",
                    "courseName=" + course_name1);

            // Create course 2
            JSONObject course2 = SendRequests.sendRequest("POST", BASE_URL, "/createCourse",
                    "courseName=" + course_name2);
            System.out.println(course2);
            int course2_Id = course2.getInt("courseId");

            // Mention course 2 as useful for the coop
            JSONObject new_coop = SendRequests.sendRequest("POST", BASE_URL, "/rateCourse",
                    "courseId=" + course2_Id + "&coopId=" + cp_Id);

            // Get all the courses without sorting
            JSONArray all_courses = SendRequests.sendRequestArray("GET", BASE_URL, "/courses");
            assertEquals(course_name1, all_courses.getJSONObject(0).getString("courseName"));
            assertEquals(course_name2, all_courses.getJSONObject(1).getString("courseName"));

            // Get a sorted view of the courses
            JSONArray sorted_all_courses = SendRequests.sendRequestArray("GET", BASE_URL, "/ranking");
            assertEquals(course_name2, sorted_all_courses.getJSONObject(0).getString("courseName"));
            assertEquals(course_name1, sorted_all_courses.getJSONObject(1).getString("courseName"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testAdjudicateCoop() {
        try {
            final String PASSWORD = "strong";
            // creating student
            JSONObject s = SendRequests.sendRequest("POST", BASE_URL, "/createStudent",
                    "firstName=" + "mia" + "&lastName=" + "zhou");
            int s_Id = s.getInt("studentId");

            // Create a coop position
            JSONObject cp = SendRequests.sendRequest("POST", BASE_URL, "/createCoop",
                    "startDate=06/02/2018" + "&endDate=05/01/2022" + "&description=goodtimes" + "&location=toronto"
                            + "&term=lala" + "&studentId=" + s_Id);
            int cp_Id = cp.getInt("coopID");

            // Create a program manager
            JSONObject pm = SendRequests.sendRequest("POST", BASE_URL, "/createProgramManager",
                    "firstName=sophie" + "&lastName=Deng" + "&password=" + PASSWORD);
            String pm_email = pm.getString("email");

            // Adjudicate the problematic status of a student through the academic program manager
            JSONObject new_coop = SendRequests.sendRequest("POST", BASE_URL, "/setCoopStatus" + EMAIL,
                    "coopId="+cp_Id + "&status="+ Status.ACCEPTED + "&programManagerEmail=" + EMAIL+"&programManagerPassword="+PASSWORD);

            String new_coop_status = new_coop.getString("status");
            // Assert that the coop is completed
            assertEquals(Status.ACCEPTED.toString(),new_coop_status);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            fail();
        }
    }
}

