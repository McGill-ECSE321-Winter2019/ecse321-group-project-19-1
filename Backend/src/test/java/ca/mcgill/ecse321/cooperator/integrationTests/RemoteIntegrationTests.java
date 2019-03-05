package ca.mcgill.ecse321.cooperator.integrationTests;

import org.json.JSONObject;
import org.junit.Test;

import static org.junit.Assert.*;

public class RemoteIntegrationTests {
    private final String BASE_URL_TEAM_11 = "https://cooperator-backend-3417.herokuapp.com";
    private final String BASE_URL_TEAM_03 = " https://sturegistration-backend-009b01.herokuapp.com/";
    private final String BASE_URL_TEAM_06 = "";

    @Test
    public void testGettingAllEmployers_Team11() {
        try {
            JSONObject s = SendRequests.sendRequest("GET", BASE_URL_TEAM_11, "/employers");
            System.out.println(s);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testGettingAllCoops_Team11() {
        try {
            JSONObject s = SendRequests.sendRequest("GET", BASE_URL_TEAM_11, "/coops");
            System.out.println(s);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testGettingAllForms_Team11() {
        try {
            JSONObject s = SendRequests.sendRequest("GET", BASE_URL_TEAM_11, "/forms");
            System.out.println(s);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testGetAllStudents_Team3() {
        try {
            JSONObject s = SendRequests.sendRequest("GET", BASE_URL_TEAM_03, "/allUsers");
            System.out.println(s);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testGetAllCoopCourses_Team3() {
        try {
            JSONObject s = SendRequests.sendRequest("GET", BASE_URL_TEAM_03, "/allCoopCourses");
            System.out.println(s);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testGettingAllCoopTerms_Team3() {
        try {
            JSONObject s = SendRequests.sendRequest("GET", BASE_URL_TEAM_03, "/allCoopTerms");
            System.out.println(s);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            fail();
        }
    }
}
