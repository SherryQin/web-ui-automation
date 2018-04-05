package qinsTest.test.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Login extends PageBase {
	
	public String url = null;
	public String user = null;
	public String pwd = null;
	
	final String loginXpath = "//*[@id=\"loginname\"]";
	@FindBy(xpath = loginXpath)
	WebElement loginInput;
	
	final String pwdXpath = "//*[@id=\"pl_login_form\"]/div/div[3]/div[2]/div/input";
	@FindBy(xpath = pwdXpath)
	WebElement pwdInput;

	final String submitXpath = "//*[@id=\"pl_login_form\"]/div/div[3]/div[6]/a/span"; 
	@FindBy(xpath = submitXpath)
	WebElement submitButton;
	

	public Login(WebDriver driver) {
		super(driver);
		this.url = config.getProperty("url");
		this.user = config.getProperty("user");
		this.pwd = config.getProperty("pwd");
	}
	
	
	public void accessLoginPage() throws InterruptedException {
		log.info("Go to "+config.getProperty("website") + "...");		
		driver.get(url);
	}

	public void loginToDashboard() throws Exception {
		accessLoginPage();
		log.info("Loaded "+url + "!" );
		waitForElementPresent(loginXpath);
		waitForElementPresent(pwdXpath);
		waitForElementPresent(submitXpath);
		loginInput.clear();
		loginInput.sendKeys(this.user);		
		pwdInput.clear();
		pwdInput.sendKeys(this.pwd);
		submitButton.click();
		
	}
	
	
}
