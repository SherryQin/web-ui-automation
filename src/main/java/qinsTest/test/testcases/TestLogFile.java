package qinsTest.test.testcases;

import org.testng.annotations.Test;

public class TestLogFile extends TestCaseBase {
	
	@Test
	public void ttTestLog(){		
		log.info("This is an info log message");
		log.debug("This is a debug log message");
		log.error("This is an error log message");		
	}
}
