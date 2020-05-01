package com.w2a.APITestFramework.utilities;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Hashtable;

import org.json.JSONObject;
import org.testng.annotations.DataProvider;

import com.w2a.APITestFramework.listeners.ExtentListeners;
import com.w2a.APITestFramework.setUp.BaseTest;

public class TestUtils extends BaseTest {


	/*@DataProvider(name="dp")
	public Object[][] getData(Method m) {

		String SheetName = m.getName();
		int rows = excel.getRowCount(SheetName);
		int cols = excel.getColumnCount(SheetName);
		

		Object[][] data = new Object[rows - 1][1];
		
		Hashtable<String,String> table = null;

		for (int rowNum = 2; rowNum <= rows; rowNum++) { // 2

			table = new Hashtable<String,String>();
			
			for (int colNum = 0; colNum < cols; colNum++) {

				// data[0][0]
				table.put(excel.getCellData(SheetName, colNum, 1), excel.getCellData(SheetName, colNum, rowNum));
				data[rowNum - 2][0] = table;
			}

		}

		return data;

	}*/
	
	@DataProvider(name="dp")
	public Object[][] getData(Method m) {

		String SheetName = "testData";
		int rows = excel.getRowCount(SheetName);
		System.out.println("Total num of rows are:"+ rows);
		
		String testName= m.getName();

		int testCaseRowNum=1;
		
		for(testCaseRowNum=1; testCaseRowNum<rows;testCaseRowNum++) {
			
			String testCaseName= excel.getCellData(SheetName, 0, testCaseRowNum);
			
			if(testCaseName.equals(testName))
				break;
		}
		
		System.out.println("Test Case Starts from row num:" + testCaseRowNum);
		
		
		//Checking total rows in test case
		
		int dataStartRowNum = testCaseRowNum+2;
		
		int testRows=0;
		
		while(!excel.getCellData(SheetName, 0, dataStartRowNum+testRows).equals("")) {
			
			testRows++;
	
		}
		
		System.out.println("Total rows of data are:" + testRows);
		
		// checking total columns in the test case
		
		int colStartColNum= testCaseRowNum+1;
		int testCols=0;
		
       while(!excel.getCellData(SheetName, testCols, colStartColNum).equals("")) {
			
    	   testCols++;
	
		}
		
		System.out.println("Total cols are:" + testCols);
		
	    //Printing all the data
		
		//Object[][] data = new Object[testRows][testCols];
		Object[][] data = new Object[testRows][1];

		
		int i=0;
		for(int rNum=dataStartRowNum;rNum<(dataStartRowNum+testRows);rNum++)
		{
			Hashtable<String,String> table= new Hashtable<String,String>();
			
			for(int cNum=0;cNum<testCols;cNum++)
			{
			 String testData=excel.getCellData(SheetName, cNum, rNum);
			 String colName=excel.getCellData(SheetName, cNum, colStartColNum);
			 
			 table.put(colName, testData);
			}
			  data[i][0]=table;
			  i++;
		}
	       return data;
	}
	
	public static boolean jsonHasKey(String json, String key)
	{
		
		JSONObject jsonobject = new JSONObject(json);
		 ExtentListeners.testReport.get().info("Validating the presence of key:"+ key);
		return jsonobject.has(key);
		
	}
	
	public static String getJSONKeyValue(String json, String key)
	{
		JSONObject jsonobject = new JSONObject(json);
		ExtentListeners.testReport.get().info("Validating the value of key:"+ key);
		return jsonobject.get(key).toString();
		
	}
	
}
