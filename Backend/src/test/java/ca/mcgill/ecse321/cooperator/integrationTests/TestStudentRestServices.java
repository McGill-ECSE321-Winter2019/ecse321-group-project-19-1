package ca.mcgill.ecse321.cooperator.integrationTests;

import static org.junit.Assert.fail;

import org.junit.Test;


public class TestStudentRestServices {
	
	private final String BASE_URL_TEAM_11 = "https://cooperator-backend-3417.herokuapp.com";
	private final String BASE_URL_TEAM_03 = " https://sturegistration-backend-009b01.herokuapp.com/";
	private final String BASE_URL_TEAM_06 = "";

	
	@Test
	public void testQueryServicesTeam11() {
		
		try {
			//Getting forms
			SendRequests.sendRequestArray("GET", BASE_URL_TEAM_11, "/forms");
			//Getting Students
			SendRequests.sendRequestArray("GET", BASE_URL_TEAM_11, "/students");
			//Getting Coops
			SendRequests.sendRequestArray("GET", BASE_URL_TEAM_11, "/coops");
			//Getting Employers
			SendRequests.sendRequestArray("GET", BASE_URL_TEAM_11, "/employers");	
		} 
		catch (RuntimeException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			fail();
		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Team 03 needs to merge to master before this test can work.
//	@Test
//	public void testQueryServicesTeam03() {
//		
//		try {
//			//Getting students
//			SendRequests.sendRequestArray("GET", BASE_URL_TEAM_03, "/allStudents");
//			//Getting users
//			SendRequests.sendRequestArray("GET", BASE_URL_TEAM_03, "/allUsers");
//			//Getting CoopPositions
//			SendRequests.sendRequestArray("GET", BASE_URL_TEAM_03, "/allCoopPositions");
//			//Getting Courses
//			SendRequests.sendRequestArray("GET", BASE_URL_TEAM_03, "/allCourses");	
//			//Getting CoopTerms
//			SendRequests.sendRequestArray("GET", BASE_URL_TEAM_03, "/allCoopTerms");
//			//Getting Evaluation Forms
//			SendRequests.sendRequestArray("GET", BASE_URL_TEAM_03, "/allEvaluationForms");	
//		} 
//		
//		catch (RuntimeException e) {
//			System.out.println(e.getMessage());
//			e.printStackTrace();
//			fail();
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	//Team 6 Don't have getAll methods and their URL is not known yet
//	@Test
//	public void testQueryServicesTeam06() {
//		
//		try {
//			//Getting students
//			SendRequests.sendRequestArray("GET", BASE_URL_TEAM_06, "/events");
//			//Getting users
//			SendRequests.sendRequestArray("GET", BASE_URL_TEAM_06, "/allUsers");
//			
//		} 
//		
//		catch (RuntimeException e) {
//			System.out.println(e.getMessage());
//			e.printStackTrace();
//			fail();
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	

}