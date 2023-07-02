package pom_Flipkart;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericFunction.GenericFunctions;


public class Pom {

	public WebDriver driver;
	Boolean value;
	public GenericFunctions gf;
	
	
	
	@FindBy(xpath="//button[.='✕']")
	WebElement deletePopUp;
	
	@FindBy(xpath="//img[@alt='Mobiles']")
	WebElement viewMobile;
	
	@FindBy(xpath="(//p[.='Shop Now'])[2]")
	WebElement shopNowRealme;
	
	@FindBy(xpath="//div[.='realme C55 (Rainforest, 128 GB)']")
	WebElement selectDevice;
	
	@FindBy(xpath="//button[@class='_2KpZ6l _2U9uOA _3v1-ww']")
	WebElement addToCart;
	
	@FindBy(xpath="//span[.='Place Order']")
	WebElement placeOrder;
	
	@FindBy(xpath="//input[@type='text']")
	WebElement typeNumber;
	
	@FindBy(xpath="//button[@type='submit']")
	WebElement continueToSendOTP;

	
	public Pom(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		gf=new GenericFunctions(driver);
	}
	


	public void openUrl(String flipkartUrl) {
		gf.getUrl(flipkartUrl);
	}



	public void clearPopUp() {
		Boolean value = gf.isDisplayed(deletePopUp);
		if(value == true) {
		gf.waitTillElementVisible(deletePopUp);
		gf.Click(deletePopUp);
		}
		System.out.println("no popup is displayed to click");
		
	}


	public void viewMobilePhone() {
		
		gf.waitTillElementVisible(viewMobile);
		gf.Click(viewMobile);
		
	}



	public void shopNowRealMePhones() {
		gf.waitTillElementVisible(shopNowRealme);
		gf.Click(shopNowRealme);
	}



	public void selectDeviceToPurchase() {
		gf.waitTillElementVisible(selectDevice);
		gf.Click(selectDevice);
		gf.switchToActiveWindow();
		
	}



	public void addToCart() {
		gf.waitTillElementVisible(addToCart);
		gf.Click(addToCart);
	}



	public void plcaeOrder() {
		gf.waitTillElementVisible(placeOrder);
		gf.Click(placeOrder);
		
	}



	public void addNumber(String number) {
	gf.waitTillElementVisible(typeNumber);
	gf.Sendkeys(typeNumber, number);
	
	}



	public void confirmNumber(){
		gf.waitTillElementVisible(continueToSendOTP);
		gf.Click(continueToSendOTP);
		
	}
	
}
