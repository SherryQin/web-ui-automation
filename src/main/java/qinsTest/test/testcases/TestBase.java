package qinsTest.test.testcases;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import qinsTest.test.utils.ScreenShot;

public class TestBase {	
	   
    public static Properties env = null;
    public static Properties data = null;
    public static Properties config = null;
    public static FileInputStream inputFile = null;
    public static Logger log = null;
	
	private static boolean isInitialized = false;
	
	public static ScreenShot screenShot = null;
	
	public static WebDriver driver = null;	
	
	// log4j configuration file location 
	private static final String LOG4J_XML_NAME =  System.getProperty("user.dir")+ File.separator + "config" + File.separator+"log4j2.xml";
	
	
	protected TestBase(){
		
		if(!isInitialized){
			initialLogger();
			initialConfig();			
			initialScreenShot();
		}			
	}
	
	
	private static void initialScreenShot(){
		if(screenShot == null){
			log.info("Initializing Screen Shot folder ... ");
			screenShot = new ScreenShot(driver, System.getProperty("user.dir")+ File.separator + "test-result" + File.separator+ "screen_shot");
		}
	}
	
	private static void initialConfig(){
		
		if(config == null){
			
			try{
				
				log.info("Initializing configuration ... ");
	
				//Initialize environment properties file
				env = new Properties();
				String fileName = "env"+".properties";
				inputFile = new FileInputStream(System.getProperty("user.dir")+ File.separator + "config" + File.separator+ fileName);		
				env.load(inputFile);
				log.info("Environment = "+ env.getProperty("Environment"));
	
				//Initialize configuration properties file
				config = new Properties();
				fileName = "config_" + env.getProperty("Environment")+ ".properties";
				String path = System.getProperty("user.dir") + File.separator+ "config" + File.separator + fileName;
				inputFile = new FileInputStream(path);
				config.load(inputFile);
				log.info("Config file initialized for environment = "+ env.getProperty("Environment"));
	
				//Initialize data properties file
				data = new Properties();
				fileName = "data_" + env.getProperty("Environment")+ ".properties";
				path = System.getProperty("user.dir") + File.separator+ "config" + File.separator + fileName;
				inputFile = new FileInputStream(path);
				data.load(inputFile);
				log.info("Data file initialized for environment = "+ env.getProperty("Environment"));							
			} 
			
			catch(FileNotFoundException e) {
				e.printStackTrace();
			}
			
			catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	protected static void initialDriver(){
		if(driver == null){
			log.info("Initializing WebDriver ...");
			String browser = config.getProperty("browser");
			if(browser == "FIREFOX"){
				
			}
			else if(browser == "IE"){
				
			} 
			else{
				log.info("Starting Chrome Driver ... ");
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")
						+ File.separator+"webDriver"+File.separator+"chromedriver.exe");
				driver = new ChromeDriver();
			}			
		}		
	}
	
	@SuppressWarnings("deprecation")
	public static void initialLogger(){
		
	    try{
	    	
	    	System.out.println("Initializing logger ... ");
	    	ConfigurationSource source = new ConfigurationSource(new FileInputStream(LOG4J_XML_NAME));
	    	File config=new File(LOG4J_XML_NAME);
			source = new ConfigurationSource(new FileInputStream(config),config);
			String path= LOG4J_XML_NAME;
			source = new ConfigurationSource(new FileInputStream(path),new File(path).toURL());
			Configurator.initialize(null, source);                
			log = LogManager.getLogger("");     // need to be confirm
		
	    } catch (Exception e) {
	    	
	    	System.out.println("Failed to initialize log4j file ... ");
			e.printStackTrace();
		
	    }    	    
	}
	
	public static void quitDriver() {
		driver.quit();
		driver = null;
		log.info("Closing Browser.");
	}

}
