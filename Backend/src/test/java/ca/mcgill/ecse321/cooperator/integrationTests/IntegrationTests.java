package ca.mcgill.ecse321.cooperator.integrationTests;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ca.mcgill.ecse321.cooperator.dao.CoopPositionRepository;
import ca.mcgill.ecse321.cooperator.dao.CourseRepository;
import ca.mcgill.ecse321.cooperator.dao.EmployerRepository;
import ca.mcgill.ecse321.cooperator.dao.RequiredDocumentRepository;
import ca.mcgill.ecse321.cooperator.dao.StudentRepository;
import ca.mcgill.ecse321.cooperator.dao.UserEntityRepository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

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

	public int[] objectsCreation() {
		int[] arr = new int[3];
		try {
			// creating two student
			JSONObject student1 = SendRequests.sendRequest("POST", BASE_URL, "/createStudent",
					"firstName=" + "max" + "&lastName=" + "brodeur");
			SendRequests.sendRequest("POST", BASE_URL, "/createStudent",
					"firstName=" + "andre" + "&lastName=" + "kaba");
			int STUDENT_ID = student1.getInt("studentId");
			arr[0] = STUDENT_ID;

			// creating coop position for student1
			JSONObject cp = SendRequests.sendRequest("POST", BASE_URL, "/createCoop",
					"startDate=02/01/2018" + "&endDate=02/01/2019" + "&description=hello" + "&location=montreal"
							+ "&term=fall" + "&studentId=" + STUDENT_ID);
			int COOP_ID = cp.getInt("coopID");
			arr[1] = COOP_ID;

			// Creating a form
			JSONObject doc = SendRequests.sendRequest("POST", BASE_URL, "/createForm",
					"name=hello" + "&dueDate=02/01/2019" + "&coopId=" + COOP_ID);
			int DOC_ID = doc.getInt("documentId");
			arr[2] = DOC_ID;
			System.out.println("FORM: " + doc.toString());

			// Creating a term instructor
			JSONObject ti = SendRequests.sendRequest("POST", BASE_URL, "/createTermInstructor/" + EMAIL,
					"firstName=sophie" + "&lastName=Deng" + "&password=123");
			System.out.println("TERM_INSTRUCTOR: " + ti.toString());
			
			//Assign coop to ti
			JSONObject assign = SendRequests.sendRequest("POST", BASE_URL, "/assignCoop",
					"email=" +EMAIL+ "&coopId=" +COOP_ID);
			System.out.println("TERM_INSTRUCTOR: " + assign.toString());

		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			fail();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arr;

	}

//	@Test
//	public void testAddingCourse() {
//		try {
//
//			joResponse = SendRequests.sendRequest("POST", BASE_URL, "/createCourse", "courseName=" + COURSE_NAME);
//			System.out.println("Received: " + joResponse.toString());
//			assertEquals(COURSE_NAME, joResponse.getString("courseName"));
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
//	public void testGetProblematic() {
//		try {
//			objectsCreation();
//			// getting problematic student. expected output to be student2
//			jaResponse = SendRequests.sendRequestArray("GET", BASE_URL, "/problematicStudents");
//			System.out.println("PROBLEMATIC: " + jaResponse.toString());
//			assertEquals("andre", jaResponse.getJSONObject(0).get("firstName"));
//			assertNotEquals("max", jaResponse.getJSONObject(0).get("firstName"));
//
//		} catch (JSONException e) {
//			e.printStackTrace();
//			fail();
//		} catch (RuntimeException e) {
//			System.out.println(e.getMessage());
//			e.printStackTrace();
//			fail();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	@Test
	public void testGradeDocument() {
		try {
			int[] arr = objectsCreation();
			// Grading the document
			String answer = "True";
			joResponse = SendRequests.sendRequest("POST", BASE_URL, "/gradeDocument",
					"documentId=" + arr[2] + "&grade="+answer + "&instructorEmail=" + EMAIL);
			System.out.println("GRADE_DOC: " + joResponse.toString());
			//assertEquals("True", joResponse.getString("grade").toString());

		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			fail();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

//	@Test
//	public void testViewGrade() {
//		try {
//			int[] arr = objectsCreation();
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
//			int[] arr = objectsCreation();
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

	@Test
	public void testCourseRanking() {
		// TODO
		// Create courses
		// Assign coop positions to courses
		// Assert by getting the name of the course at predetermined index
	}

	@Test
	public void testAssignTermInstructorToCoop() {
		// TODO
		// Create term instructor
		// Assign coop
		// Assert by ...
	}

}
