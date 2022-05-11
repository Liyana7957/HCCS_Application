package com.hccs.core;

import org.openqa.selenium.WebDriver;


public class basePage {
	protected static ReadConfig rd = new ReadConfig();
	protected hccsWait wait = new hccsWait();
	public static WebDriver driver;
	protected int superLowWaitTime = 50;
	protected int implicitWaitTime = 2000;
	protected int waitTimeOut = 5000;
	protected int lowWaitTime = 10000;
	protected int mediumWaitTime = 30000;
	protected int highWaitTime = 60000;
	
	
	// Constructor
		public basePage(WebDriver driver) {
			wait.implicitWait(driver, implicitWaitTime);
			this.driver = driver;
		}

}
