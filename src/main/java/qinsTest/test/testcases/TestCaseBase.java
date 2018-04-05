package qinsTest.test.testcases;

import java.io.IOException;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;


/**
 * @author QinS
 * @since 2017/11/19
 */
public abstract class TestCaseBase extends TestBase {
	
	@AfterSuite
	public void tearDown() {
		quitDriver();
	}
	
	@BeforeSuite
	public void clearOutput(){		
		initialDriver();	
		log.info("initial log file ... ");
		// clear log file
		log.info("initializing screen shot folder");
		// clear screen shot folders
	}	
	
	@BeforeClass
	public void startTest(){
		
		log.info("Starting test case " + this.getClass().getSimpleName().replace("_", "-"));
	
	}
		
	@AfterMethod
	public void takeScreenshotOnFailure(ITestResult testResult) throws IOException{
	
		if(testResult.getStatus() == ITestResult.FAILURE){
			log.error("Test "+getClass().getName() + " >> "+testResult.getName() + " failed, please check!");
			screenShot.takeScreenShot(driver, getClass().getName().replace("qa.sav.moodys.nova.test.", "").replace(".", "_")+ "_"+testResult.getName());		
		} else {
			log.info("Test case "+getClass().getName() + " >> "+testResult.getName() + "run successfully!");
		}
	}
	
}
