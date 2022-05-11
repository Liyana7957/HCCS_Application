package com.hccs.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.hccs.core.basePage;

public class firstPage extends basePage{


	public firstPage(WebDriver driver) {
		super(driver);
	}
	
	
	public void test12() throws Exception {
		Thread.sleep(2000);
		a.sendKeys("jain");
		wait.implicitWait(driver, lowWaitTime);
		b.click();
	}

	@FindBy(xpath = "//input[@name='q1']")
	WebElement a;
	@FindBy(xpath = "(//input[@name='btnK'])[2]")
	WebElement b;

}
