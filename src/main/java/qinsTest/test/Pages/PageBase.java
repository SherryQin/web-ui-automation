package qinsTest.test.Pages;

import java.util.Properties;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import qinsTest.test.testcases.TestBase;

public class PageBase {
	
	protected WebDriver driver = null;
	protected static Logger log = TestBase.log;
	protected static Properties config = TestBase.config;
	protected String curPageUrl = null;
	
	public PageBase(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void acceptAlert(boolean acceptAlertCondition) {
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		
		if(acceptAlertCondition) {
			
			try {
				wait.until(ExpectedConditions.alertIsPresent());
				Alert alert = driver.switchTo().alert();
				alert.accept();				
			} catch (Exception e) {
				log.warn("No alert pop-up, continue ... ");
			}	
			
		} else {
			
			try {
				wait.until(ExpectedConditions.alertIsPresent());
				Alert alert = driver.switchTo().alert();
				alert.dismiss();
			} catch (Exception e) {
				log.warn("No alert pop-up, continue ... ");
			}
			
		}
		
	}
	
	
	public void waitForElementPresent(String xpath) {
		
		final String myXpath = xpath;

		//get time out
		long waitTimeout = Math.round(60*Float.parseFloat(config.getProperty("page_timeout")));
		WebDriverWait wait = new WebDriverWait(driver, waitTimeout);
		
		wait.until(
				new ExpectedCondition<WebElement>() {
					public WebElement apply(WebDriver d) 
					{
						return d.findElement(By.xpath(myXpath));
					}
				}
				
		);
		
		
	}
	
	public void waitForPageLoaded() throws InterruptedException {
		int i = 0;
		int waitUnit = 10; // wait for 10 millisecond for each loop
		
		//calculate total loop counts from global page timeout setting
		int totalLoop = Math.round(Float.parseFloat(config.getProperty("page_timeout"))*60*1000/waitUnit);
		
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		if((Boolean) executor.executeScript("return window.jQuery != undefined")) {
			while(!(Boolean)executor.executeScript("return jQuery.active == 0")) {
				i++;
				Thread.sleep(waitUnit);
				if(i > totalLoop) return;
			}
		}
		
	}

}
