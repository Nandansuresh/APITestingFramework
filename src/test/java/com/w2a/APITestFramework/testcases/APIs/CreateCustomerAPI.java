package com.w2a.APITestFramework.testcases.APIs;

import static io.restassured.RestAssured.given;

import java.util.Hashtable;

import com.w2a.APITestFramework.setUp.BaseTest;

import io.restassured.response.Response;

public class CreateCustomerAPI extends BaseTest{
	
	
	public static Response postRequesttoCreateCustomerwithValidSecKey(Hashtable<String,String> data) {
		
		Response res =	given().auth().basic(config.getProperty("validSecretKey"), "")
				.formParam("email", data.get("email")).formParam("description", data.get("description"))
				.post(config.getProperty("customerAPIEndPoint"));
		
		return res;
	}

	
	public static Response postRequesttoCreateCustomerwithInValidSecKey(Hashtable<String,String> data) {
		
		Response res =	given().auth().basic(config.getProperty("invalidSecretKey"), "")
				.formParam("email", data.get("email")).formParam("description", data.get("description"))
				.post(config.getProperty("customerAPIEndPoint"));
		
		return res;
		
	}
}
