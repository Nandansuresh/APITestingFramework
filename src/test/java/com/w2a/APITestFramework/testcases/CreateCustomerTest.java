package com.w2a.APITestFramework.testcases;

import static io.restassured.RestAssured.*;

import java.lang.reflect.Method;
import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.w2a.APITestFramework.listeners.ExtentListeners;
import com.w2a.APITestFramework.setUp.BaseTest;
import com.w2a.APITestFramework.testcases.APIs.CreateCustomerAPI;
import com.w2a.APITestFramework.utilities.TestUtils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class CreateCustomerTest extends BaseTest {
	
	
	@Test(dataProviderClass=TestUtils.class, dataProvider="dp")
	public void validateCreateCustomerAPIwithvalidSecretKey(Hashtable<String,String> data) {
	        
		Response res= CreateCustomerAPI.postRequesttoCreateCustomerwithValidSecKey(data);
	        res.prettyPrint();
	        
	        ExtentListeners.testReport.get().info(data.toString());
	        
	        System.out.println(res.statusCode());
		  
	        Assert.assertEquals(res.getStatusCode(), 200);
		
	}
	
	@Test(dataProviderClass=TestUtils.class, dataProvider="dp")
	public void validateCreateCustomerAPIwithinvalidSecretKey(Hashtable<String,String> data) {
		

		
		Response res= CreateCustomerAPI.postRequesttoCreateCustomerwithInValidSecKey(data);
	    
		ExtentListeners.testReport.get().info(data.toString());
		
	        res.prettyPrint();
	        
	        System.out.println(res.statusCode());
	        
	        Assert.assertEquals(res.getStatusCode(), 200);	
		
	}
	
	
	

}
