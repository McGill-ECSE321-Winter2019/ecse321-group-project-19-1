package ca.mcgill.ecse321.cooperator.integrationTests;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SendRequests {
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

	public static JSONObject sendRequest(String requestType, String baseUrl, String path) {
		return sendRequest(requestType, baseUrl, path, null);
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

	public static JSONArray sendRequestArray(String requestType, String baseUrl, String path) {
		return sendRequestArray(requestType, baseUrl, path, null);
	}

}
