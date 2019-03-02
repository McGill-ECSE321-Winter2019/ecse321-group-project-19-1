package ca.mcgill.ecse321.cooperator.integrationTests;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.junit.Assert.assertEquals;

public class IntegrationTests {

    private final String BASE_URL = "http://cooperator-backend-260.herokuapp.com";
    private final String COURSE_NAME = "test101";
    private JSONObject respose;

    private JSONObject sendRequest(String requestType, String baseUrl, String path, String parameters) {
        try {
            URL url = new URL(baseUrl + path + ((parameters==null)?"":("?" + parameters)));
            System.out.println("Sending: "+url.toString());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(requestType);
            connection.setRequestProperty("Accept", "application/json");
            if (connection.getResponseCode() != 200) {
                throw new RuntimeException(url.toString() + " failed : HTTP error code : "
                        + connection.getResponseCode());
            }
            JSONObject r = new JSONObject(new BufferedReader(new InputStreamReader(
                    (connection.getInputStream()))).readLine());
            assertEquals(200, connection.getResponseCode());
            connection.disconnect();
            return r;
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    private JSONObject sendRequest(String requestType, String baseUrl, String path) {
        return sendRequest(requestType,baseUrl,path,null);
    }
    @Test
    public void TestAddingCourse() {
        try {
            respose = sendRequest("POST", BASE_URL, "/createCourse", "courseName=" + COURSE_NAME);
            System.out.println("Received: "+respose.toString());
            assertEquals(COURSE_NAME, respose.getString("courseName"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
