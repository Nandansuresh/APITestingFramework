package com.w2a.APITestFramework.testcases;

import static io.restassured.RestAssured.*;

import java.lang.reflect.Method;
import java.util.Hashtable;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.w2a.APITestFramework.listeners.ExtentListeners;
import com.w2a.APITestFramework.setUp.BaseTest;
import com.w2a.APITestFramework.testcases.APIs.CreateCustomerAPI;
import com.w2a.APITestFramework.testcases.APIs.DeleteCustomerAPI;
import com.w2a.APITestFramework.utilities.TestUtils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class DeleteCustomerTest extends BaseTest {
	
	
	@Test(dataProviderClass=TestUtils.class, dataProvider="dp")
	public void deleteCustomer(Hashtable<String,String> data) {
	        
		Response res= DeleteCustomerAPI.deletRequesttoDeleteCustomerwithValidID(data);
	     res.prettyPrint();
	     
	     ExtentListeners.testReport.get().info(data.toString());
	     
	     System.out.println("Deleted Id is:"+ res.jsonPath().get("id").toString());
	        
	     System.out.println(res.statusCode());
	     
	     Assert.assertEquals(res.getStatusCode(), 200);
		
	     /*String Acrual_id= res.jsonPath().get("id").toString();
	     String Expected_id= data.get("id");
	     
	     Assert.assertEquals(Acrual_id, Expected_id,"ID is not matching");*/
	     
	     /*JSONObject jsonobject = new JSONObject(res.asString());
	     System.out.println(jsonobject.has("id"));
	     Assert.assertTrue(jsonobject.has("id"), "ID is present in the output");*/
	     
	     Assert.assertTrue(TestUtils.jsonHasKey(res.asString(), "id"), "ID is present in the output");
	     Assert.assertTrue(TestUtils.jsonHasKey(res.asString(), "object"), "object is present in the output");	
	     Assert.assertTrue(TestUtils.jsonHasKey(res.asString(), "deleted"), "deleted is present in the output");
	     
         /*String Actual_id= jsonobject.get("id").toString();
         String Expected_id= data.get("id");
         
	     Assert.assertEquals(Actual_id, Expected_id,"ID is not matching");*/
	     
	     String Actual_id= TestUtils.getJSONKeyValue(res.asString(), "id");
         String Expected_id= data.get("id");
	     
         Assert.assertEquals(Actual_id, Expected_id,"ID is not matching");
         
	     
	}
	
}
