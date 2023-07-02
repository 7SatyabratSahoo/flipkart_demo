package test_Flipkart;


import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import genericFunction.GenericFunctions;
import genericFunction.Utility;
import io.github.bonigarcia.wdm.WebDriverManager;
import pom_Flipkart.Pom;


public class Test1  {

	public WebDriver driver;
	ChromeOptions options;
	GenericFunctions gf;
	Pom pg;
	Utility util;
	Properties prop;
	String flipkartUrl;
	String title;
	String configpath=".\\src\\test\\java\\com\\flipkart\\config\\config.properties";
	ExtentReports extent = new ExtentReports();
	ExtentTest test;
	WebDriverWait wait;
	String testMethodName;
	Boolean value;
	String descriptiveTestName;

	


	@BeforeSuite
	public void initialConfig() {
		ExtentSparkReporter spark = new ExtentSparkReporter("FlipkartReport.html");
		spark.config().setDocumentTitle("Flipkart Integration Test Execution Report");
		spark.config().setTheme(Theme.DARK);
		spark.config().setReportName("Automation Test Report");  
		extent.attachReporter(spark);    
		extent.setSystemInfo("Test Type ", "Flipkart Demo project");
		extent.setSystemInfo("Environment", "Stage");
		extent.setSystemInfo("Author", "Prasant Rangin");
		extent.attachReporter(spark);
		WebDriverManager.chromedriver().setup();
		ChromeOptions dc = new ChromeOptions();
		dc.addArguments("--remote-allow-origins=*");
		driver=new ChromeDriver(dc);
		driver.manage().window().maximize();



	}

	@BeforeTest 
	public void setUp() throws IOException {

		prop = new Properties();
		FileInputStream ip = new FileInputStream(configpath);
		prop.load(ip);
		flipkartUrl=prop.getProperty("flipkartUrl");
		System.out.println(flipkartUrl);
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		gf=new GenericFunctions(driver);
		pg=new Pom(driver);
		util=new Utility(driver);


	}

	@BeforeMethod
	public void setup(Method method) {
		testMethodName = method.getName();
		descriptiveTestName = method.getAnnotation(Test.class).testName();
		test = extent.createTest(descriptiveTestName);
	}
	

	@Test(priority=1,testName ="Verify that flipkart url is open")
	public void openFlipkart1 () throws IOException {
		try {
			test.info("Test to check login functionality");
			pg.openUrl(flipkartUrl);
			gf.waitForPageLoad(300);
			title=gf.getTitle();
			System.out.println(title);
			String actualTitle = prop.getProperty("actualt_title_of_flipkart");
			value=gf.Assert(title, actualTitle);
			if(value) {
				test.pass("url opened Successfully");
				test.addScreenCaptureFromPath(util.getScreenShot(driver,testMethodName));
			}
			else {
				test.fail("url is invalid");
				test.addScreenCaptureFromPath(util.getScreenShot(driver,testMethodName));
			}		
		}catch(Exception e) {
			test.fail(e);
			test.addScreenCaptureFromPath(util.getScreenShot(driver,testMethodName));
		}

	}
	

	@Test(priority=2,testName ="verify that the pop up has been cleared")
	public void clearThePopUp() {
		
		try {
			pg.clearPopUp();
			test.pass("pop up has been deleted");
			test.addScreenCaptureFromPath(util.getScreenShot(driver,testMethodName));
			}catch(Exception e) {
				test.fail( "pop up is not cleared "+ e);
			}
	}
	
	@Test(priority=3,testName ="verify that mobile section is viewed")
	public void viewMobile() throws IOException {
		try {
		pg.viewMobilePhone();
		test.pass("mobile section is displayed properly");
		test.addScreenCaptureFromPath(util.getScreenShot(driver,testMethodName));
		}catch(Exception e) {
			test.fail("mobile section is not displayed "+ e);
		}
	}
	
	@Test(priority=4,testName ="verify that realme device section is opened")
	public void shopNowFromRealme() {
		try {		
			pg.shopNowRealMePhones();
			test.pass("realme devices are displayed");
			test.addScreenCaptureFromPath(util.getScreenShot(driver,testMethodName));
			
		}catch(Exception e) {
			test.fail("realme devices are not opened"+ e);
		}
	}
	
	@Test(priority=5,testName ="verify that realme C55 (Rainforest, 128 GB) device is selected")
	public void selectDevice() throws IOException {
		try {		
			pg.selectDeviceToPurchase();
			test.pass("realme C55 (Rainforest, 128 GB) device is selected");
			test.addScreenCaptureFromPath(util.getScreenShot(driver,testMethodName));
			
		}catch(Exception e) {
			test.fail("realme devices are not opened"+ e);
			test.addScreenCaptureFromPath(util.getScreenShot(driver,testMethodName));
		}
	}
	

	@Test(priority=6,testName ="verify that the product added to cart")
	public void addToCart() throws IOException {
		
		try {		
			pg.addToCart();
			test.pass("The product is added to cart successfuly");
			test.addScreenCaptureFromPath(util.getScreenShot(driver,testMethodName));
			
		}catch(Exception e) {
			test.fail("The product not added to cart"+ e);
			test.addScreenCaptureFromPath(util.getScreenShot(driver,testMethodName));
		}
	}
	
	@Test(priority=7,testName ="verify that order is placed")
	public void placeOrder() throws IOException {
		try {		
			pg.plcaeOrder();
			test.pass("The order is placed successfuly");
			test.addScreenCaptureFromPath(util.getScreenShot(driver,testMethodName));
			
		}catch(Exception e) {
			test.fail("The order is not placed"+ e);
			test.addScreenCaptureFromPath(util.getScreenShot(driver,testMethodName));
		}	
	}
	
	@Test(priority=8,testName ="Add number to place order")
	public void typeNumber() throws IOException {
		String number = prop.getProperty("numberToPlaceOrder");
		try {		
			pg.addNumber(number);
			test.pass("number is added to place order");
			test.addScreenCaptureFromPath(util.getScreenShot(driver,testMethodName));
			
		}catch(Exception e) {
			test.fail("number is not added"+ e);
			test.addScreenCaptureFromPath(util.getScreenShot(driver,testMethodName));
		}	
	}
	
	@Test(priority=9,testName ="Continue to confirm your number")
	public void continueToRecieve() throws IOException {
		try {		
			pg.confirmNumber();
			test.pass("number is added to place order");
			test.addScreenCaptureFromPath(util.getScreenShot(driver,testMethodName));
			
		}catch(Exception e) {
			test.fail("number is not added"+ e);
			test.addScreenCaptureFromPath(util.getScreenShot(driver,testMethodName));
		}	
	}
	
	@AfterTest
	public void closeDriver() {

		driver.quit();


	}
	@AfterSuite 
	public void tearDown() throws IOException {

		extent.flush();
		Desktop.getDesktop().browse(new File("FlipkartReport.html").toURI());


	}
	
}
