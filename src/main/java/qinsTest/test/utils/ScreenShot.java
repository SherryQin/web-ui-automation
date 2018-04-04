package qinsTest.test.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import qinsTest.test.testcases.TestCaseBase;

/**
 * @author qins
 * @since 2017/11/12
 */
public class ScreenShot {
	
	public WebDriver driver;
	public String screenShotPath;
	private static Logger log = TestCaseBase.log;
	
	public ScreenShot(WebDriver driver, String screenShotPath) {
		setDriver(driver);
		this.setScreenShotPath(screenShotPath);
	}
	
	public String getScreenShotSavePath(String screenShotPath) {
		File dir = new File(screenShotPath + File.separator);
		dir.mkdirs();
		return dir.getAbsolutePath();
	}
	
	/**
	 * take screen shot on failure.
	 * @throws IOException 
	 */
	public void takeScreenShot(WebDriver driver, String url) throws IOException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String date = sdf.format(new Date());
		String extention =".png";
		String path = getScreenShotSavePath(screenShotPath) + File.separator + date + "_" + url + extention;
		
		if (driver instanceof TakesScreenshot) {
			File tmpFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			try {
				org.openqa.selenium.io.FileHandler.copy(tmpFile, new File(path));
			} catch (IOException e) {
				e.printStackTrace();
				throw e;
			}
		}
		
		log.error("Captured Screenshot for Failure: " + path);
		
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public String getScreenShotPath() {
		return screenShotPath;
	}

	public void setScreenShotPath(String screenShotPath) {
		this.screenShotPath = screenShotPath;
	}
}
