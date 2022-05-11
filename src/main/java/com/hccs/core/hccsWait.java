package com.hccs.core;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class hccsWait {
	 public int timeout = 0;
	
	 /**
     * Implicit Wait.Time is configurable here . This will be read from the wait properties file.
     */
    public void implicitWait(WebDriver driver, int timeout) {
        driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.MILLISECONDS);
    }
    
    /**
     * Explicit wait.Time is configurable here . This will be read from the wait properties file.
     */
    public void explicitWait(WebDriver driver, int time, WebElement element) {
        timeout = time / 1000;
        new WebDriverWait(driver, timeout).until(ExpectedConditions.elementToBeClickable(element));
    }
    
    /**
     * Specify a condition to wait for. Poll every 10 ms. TimeUnit in MS.
     *
     * @param driver
     * @param timeout
     * @param element
     */
    public void fluentWait(WebDriver driver, int timeout, WebElement element) {
        FluentWait<WebDriver> fwait = new FluentWait<>(driver).withTimeout(Duration.ofMillis(timeout)).pollingEvery(Duration.ofMillis(50)).ignoring(NoSuchElementException.class, StaleElementReferenceException.class);
        fwait.until(ExpectedConditions.elementToBeClickable(element));
    }
    
    /**
     * This wait is used to Check that the string is present in URL This will be read from the wait
     * properties file.
     */
    public boolean waitStringPresentInURL(WebDriver driver, int time, String strURL) {
        int timeout = time / 1000;
        return new WebDriverWait(driver, timeout).until(ExpectedConditions.urlContains(strURL));
    }
}
