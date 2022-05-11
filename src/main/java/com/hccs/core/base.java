package com.hccs.core;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.relevantcodes.extentreports.LogStatus;

public class base {
	protected static ReadConfig config = new ReadConfig();
	public static WebDriver driver;

	@BeforeMethod
	public static WebDriver launchBrowser() throws Exception {
		String getBrowser = config.returnDataFromFile("\\data\\testData.properties", "browser");
		if (getBrowser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\src\\test\\resources\\drivers\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			return driver;
		}
		return null;
	}

	public void naigateTo(String url) throws Exception {
		try {
			driver.navigate().to(url);
			Loggers.getTest().log(LogStatus.INFO, "Navigate to URL", "Successfully navigated to URL : " + url);
		} catch (Exception e) {
			Loggers.getTest().log(LogStatus.ERROR, "Navigate to URL",
					"Unable to naviga to URL : " + url + " Exception Message : " + e.getMessage().toString());
		}
	}

	public void tearDown(ITestResult result) throws IOException {
		String methodname = result.getName();
		if (result.getStatus() == ITestResult.FAILURE || result.getStatus() == ITestResult.SKIP) {
			Loggers.getTest().log(LogStatus.INFO, "Test Failed", "Take Screenshot");
			Loggers.getTest().log(LogStatus.INFO, "Current URL", driver.getCurrentUrl());
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String currentDir = System.getProperty("user.dir");
			System.out.println();
			FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + methodname + ".png"));
			driver.quit();
		} else {
			driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
			driver.quit();
		}
		Loggers.endTest();
	}

	@AfterMethod
	protected void afterMethod(ITestResult result) {
		String methodname = result.getName();
		if (result.getStatus() == ITestResult.FAILURE) {
			System.out.println("---------------------------------");
			String screenshotPath = System.getProperty("user.dir") + "/screenshots/" + methodname + ".png";
			Loggers.getTest().log(LogStatus.FAIL, Loggers.getTest().addScreenCapture(screenshotPath));
			Loggers.getTest().log(LogStatus.FAIL, result.getThrowable());
		} else if (result.getStatus() == ITestResult.SKIP) {
			Loggers.getTest().log(LogStatus.SKIP, "Test skipped " + result.getThrowable());
		} else {
			Loggers.getTest().log(LogStatus.PASS, "Test passed");
		}
		ExtentManager.getReporter().endTest(Loggers.getTest());
		ExtentManager.getReporter().flush();
	}

}
