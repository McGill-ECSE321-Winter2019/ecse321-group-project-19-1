package ca.mcgill.ecse321.cooperator.integrationTests;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import ca.mcgill.ecse321.cooperator.model.CoopPosition;
import ca.mcgill.ecse321.cooperator.model.RequiredDocument;
import ca.mcgill.ecse321.cooperator.model.Student;
import ca.mcgill.ecse321.cooperator.services.CoopPositionService;
import ca.mcgill.ecse321.cooperator.services.RequiredDocumentService;
import ca.mcgill.ecse321.cooperator.services.StudentService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class IntegrationTests {

    private final String BASE_URL = "http://cooperator-backend-260.herokuapp.com";
    private final String COURSE_NAME = "test101";
    private Integer DOC_ID=0;
    private final String EMAIL = "yoyo@gmail.com";
    private JSONObject respose;
    private JSONArray response;
    
    @Autowired
    StudentService studentService;
    @Autowired
   CoopPositionService cpService;
    @Autowired 
    RequiredDocumentService rdocService;

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
    
    private JSONArray sendRequest1(String requestType, String baseUrl, String path, String parameters) {
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
            JSONArray r = new JSONArray(new BufferedReader(new InputStreamReader(
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
    private JSONArray sendRequest1(String requestType, String baseUrl, String path) {
        return sendRequest1(requestType,baseUrl,path,null);
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
    
    
    @Test
    public void testGetProblematic() {
    	 try {
    		 
    		 //creating two students
    		 JSONObject student1 = sendRequest("POST", BASE_URL, "/createStudent", "firstName="+"max"+"&lastName="+"brodeur");
    		 JSONObject student2 = sendRequest("POST", BASE_URL, "/createStudent", "firstName="+"andre"+"&lastName="+"kaba");
    		 System.out.println("Received: "+student1.toString());
    		 int STUDENT_ID = student1.getInt("studentId");
    		 
    		 //creating coop position for student1
    		 JSONObject cp =  sendRequest("POST", BASE_URL, "/createCoop", "startDate=02/01/2018"+"&endDate=02/01/2019"
    		 +"&description=hello"+"&location=montreal"+"&term=fall"+"&studentId="+STUDENT_ID);
    		 System.out.println("Received: "+cp.toString());
    		 
    		 //getting problematic student. expected output to be student2
             response = sendRequest1("GET", BASE_URL, "/problematicStudents");
             System.out.println("Received: "+response.toString());
             //JSONObject jo= new JSONObject();
             //System.out.println("Received: "+jo.toString());
             assertEquals("andre", response.getString(2));
         } catch (Exception e) {
             e.printStackTrace();
         }	
    }
    
//    @Test
//    public void testGradeDocument() {
//    	try {
//    		sendRequest("POST",BASE_URL,"/createForm","name=hello"+"&dueDate="+new Date(5)+"&coopId=");
//            respose = sendRequest("POST", BASE_URL, "/gradeDocument",
//            		"documentId="+DOC_ID+"grade="+true+"instructorEmail="+EMAIL);
//            System.out.println("Received: "+respose.toString());
//            assertEquals(true, respose.getString("grade"));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }	
//    	
//    }
    
}
