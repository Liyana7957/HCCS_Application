package com.hccs.tests;

import java.io.IOException;

import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.hccs.core.Loggers;
import com.hccs.core.base;
import com.hccs.core.retryTest;
import com.hccs.pages.firstPage;


public class test1 extends base{
	firstPage login;
	
	public void commonStep() throws Exception{
		login=PageFactory.initElements(driver, firstPage.class);
		naigateTo(config.returnDataFromFile("\\data\\testData.properties", "url"));
	}
	
	@Test//(retryAnalyzer = retryTest.class)
	public void test12() throws Exception{
		Loggers.startTest("test12");
		commonStep();		
		Thread.sleep(2000);
		login.test12();
	}
	
	@Test
	public void test123() throws Exception{
		firstPage login=PageFactory.initElements(driver, firstPage.class);
		Loggers.startTest("test123");
		commonStep();
		Thread.sleep(2000);
		login.test12();
	}
	
	
	
	@AfterMethod
	public void tearDown(ITestResult result) throws IOException {
		super.tearDown(result);
	}
	

}
