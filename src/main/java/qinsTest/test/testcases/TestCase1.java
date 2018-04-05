package qinsTest.test.testcases;

import org.testng.annotations.Test;

import qinsTest.test.Pages.Login;

public class TestCase1 extends TestCaseBase {
	
	@Test
	public void ttest() throws Exception {
		
		Login login = (new Login(driver));
		login.loginToDashboard();	
		
	}
}
