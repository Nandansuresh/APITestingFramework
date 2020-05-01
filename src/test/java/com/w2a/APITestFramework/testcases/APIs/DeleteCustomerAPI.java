package com.w2a.APITestFramework.testcases.APIs;

import static io.restassured.RestAssured.given;

import java.util.Hashtable;

import com.w2a.APITestFramework.setUp.BaseTest;

import io.restassured.response.Response;

public class DeleteCustomerAPI extends BaseTest{
	
	
	public static Response deletRequesttoDeleteCustomerwithValidID(Hashtable<String,String> data) {
		
		Response res =	given().auth().basic(config.getProperty("validSecretKey"), "")
				.delete(config.getProperty("customerAPIEndPoint")+ "/" +data.get("id"));
		
		return res;
	}
}
