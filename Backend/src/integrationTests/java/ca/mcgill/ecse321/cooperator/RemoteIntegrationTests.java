package integrationTests.java.ca.mcgill.ecse321.cooperator;

import ca.mcgill.ecse321.cooperator.Utilities;
import org.json.JSONObject;
import org.junit.Test;

import static org.junit.Assert.*;

public class RemoteIntegrationTests {

    @Test
    public void testGettingAllEmployers_Team11() {
        try {
            JSONObject s = Utilities.sendRequest("GET", Utilities.BASE_URL_ADMINVIEW, "/employers");
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
            JSONObject s = Utilities.sendRequest("GET", Utilities.BASE_URL_ADMINVIEW, "/coops");
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
            JSONObject s = Utilities.sendRequest("GET", Utilities.BASE_URL_ADMINVIEW, "/forms");
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
            JSONObject s = Utilities.sendRequest("GET", Utilities.BASE_URL_STUDENTVIEW, "/allUsers");
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
            JSONObject s = Utilities.sendRequest("GET", Utilities.BASE_URL_STUDENTVIEW, "/allCoopCourses");
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
            JSONObject s = Utilities.sendRequest("GET", Utilities.BASE_URL_STUDENTVIEW, "/allCoopTerms");
            System.out.println(s);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            fail();
        }
    }
}
