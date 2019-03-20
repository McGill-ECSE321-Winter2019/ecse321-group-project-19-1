package ca.mcgill.ecse321.cooperator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Utilities {
    // Group 6
    public static final String BASE_URL_ADMINVIEW = "https://cooperator-backend-3417.herokuapp.com";
    // Group 3
    public static final String BASE_URL_STUDENTVIEW = "https://student-backend-n.herokuapp.com";
    // Group 11
    public static final String BASE_URL_EMPLOYERVIEW = "https://cooperator-backend-060606.herokuapp.com";
    // Default base url
    private static final String DEFAULT_BASE_URL = "http://cooperator-backend-260.herokuapp.com";

    public static Boolean checkNotEmpty(String s) {
        return s != null && !s.equals("") && s.trim().length() > 0;
    }

    public static JSONObject sendRequest(String requestType, String baseUrl, String path, String parameters) {
        try {
            URL url = new URL(baseUrl + path + ((parameters == null) ? "" : ("?" + parameters)));
            System.out.println("Sending: " + url.toString());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(requestType);
            connection.setRequestProperty("Accept", "application/json");
            if (connection.getResponseCode() != 200) {
                throw new RuntimeException(
                        url.toString() + " failed : HTTP error code : " + connection.getResponseCode());
            }
            JSONObject r = new JSONObject(
                    new BufferedReader(new InputStreamReader((connection.getInputStream()))).readLine());
            connection.disconnect();
            if (200 != connection.getResponseCode())
                return null;
            return r;
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static JSONArray sendRequestArray(String requestType, String baseUrl, String path, String parameters) {
        try {
            URL url = new URL(baseUrl + path + ((parameters == null) ? "" : ("?" + parameters)));
            System.out.println("Sending: " + url.toString());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(requestType);
            connection.setRequestProperty("Accept", "application/json");
            if (connection.getResponseCode() != 200) {
                throw new RuntimeException(
                        url.toString() + " failed : HTTP error code : " + connection.getResponseCode());
            }
            JSONArray r = new JSONArray(
                    new BufferedReader(new InputStreamReader((connection.getInputStream()))).readLine());
            if (200 != connection.getResponseCode())
                return null;
            connection.disconnect();
            return r;
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static JSONArray sendRequestArray(String requestType, String baseUrl, String path) {
        return sendRequestArray(requestType, baseUrl, path, null);
    }

    public static JSONObject sendRequest(String requestType, String baseUrl, String path) {
        return sendRequest(requestType, baseUrl, path, null);
    }

    public static JSONObject clearDB() {
        return sendRequest("POST", DEFAULT_BASE_URL, "/clearDB", null);
    }
}
