//package ca.mcgill.ecse321.cooperator.integrationTests;
//
//import static org.junit.Assert.fail;
//
//import org.json.JSONObject;
//
//public class RESTtestDatabaseSetup {
//	private static String BASE_URL = "http://cooperator-backend-260.herokuapp.com";
//	private static String EMAIL = "yoyo@gmail.com";
//
//	public static int[] databaseSetup() {
//		int[] arr = new int[5];
//		try {
//
//			JSONObject s3 = SendRequests.sendRequest("POST", BASE_URL, "/createStudent",
//					"firstName=" + "mia" + "&lastName=" + "zhou");
//			JSONObject s4 = SendRequests.sendRequest("POST", BASE_URL, "/createStudent",
//					"firstName=" + "carl" + "&lastName=" + "elkhoury");
//
//			int s1_ID = s1.getInt("studentId");
//			int s2_ID = s2.getInt("studentId");
//			int s3_ID = s3.getInt("studentId");
//			int s4_ID = s4.getInt("studentId");
//
//
//			JSONObject cp3 = SendRequests.sendRequest("POST", BASE_URL, "/createCoop",
//					"startDate=05/01/2018" + "&endDate=05/01/2019" + "&description=world" + "&location=montreal"
//							+ "&term=fall" + "&studentId=" + s3_ID);
//			JSONObject cp4 = SendRequests.sendRequest("POST", BASE_URL, "/createCoop",
//					"startDate=03/01/2018" + "&endDate=03/01/2019" + "&description=haha" + "&location=montreal"
//							+ "&term=fall" + "&studentId=" + s4_ID);
//			int cp1_ID = cp1.getInt("coopID");
//			int cp3_ID = cp3.getInt("coopID");
//			int cp4_ID = cp4.getInt("coopID");
//
//			// Creating a form
//			JSONObject doc = SendRequests.sendRequest("POST", BASE_URL, "/createForm",
//					"name=hello" + "&dueDate=02/01/2019" + "&coopId=" + cp1_ID);
//			int DOC_ID = doc.getInt("documentId");
//			arr[2] = DOC_ID;
//			System.out.println("FORM: " + doc.toString());
//
//			// Creating a term instructor
//			JSONObject ti = SendRequests.sendRequest("POST", BASE_URL, "/createTermInstructor/" + EMAIL,
//					"firstName=sophie" + "&lastName=Deng" + "&password=123");
//			System.out.println("TERM_INSTRUCTOR: " + ti.toString());
////
//			//Assign coop to ti
//			JSONObject assign = SendRequests.sendRequest("POST", BASE_URL, "/assignCoop",
//					"email=" +EMAIL+ "&coopId=" +cp1_ID);
//			System.out.println("TERM_INSTRUCTOR: " + assign.toString());
//
//			//Create courses
//			JSONObject c1 = SendRequests.sendRequest("POST", BASE_URL, "/createCourse",
//					"courseName="+"ECSE321");
//			JSONObject c2 = SendRequests.sendRequest("POST", BASE_URL, "/createCourse",
//					"courseName="+"ECSE310");
//
//			System.out.println("Course 1: " + c1.toString());
//			System.out.println("Course 2: " + c2.toString());
//
//			//Putting info of s1 in an array because needed for tests
//			arr[0] = s1_ID;
//			arr[1] = cp1_ID;
//			arr[2] = DOC_ID;
//			arr[3] = cp3_ID;
//			arr[4] = cp4_ID;
//
//		} catch (RuntimeException e) {
//			System.out.println(e.getMessage());
//			e.printStackTrace();
//			fail();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return arr;
//
//	}
//}
