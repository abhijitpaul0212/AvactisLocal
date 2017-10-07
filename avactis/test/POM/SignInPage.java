package POM;

import static org.testng.AssertJUnit.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import scripts.Log;
import utility.Waiting;

public class SignInPage extends LoadableComponent<SignInPage> 
{
	private static WebDriver driver;
	private String url = "http://avactis:avactis%40123@sandbox.avactis.com/ketan479/";
	private String title = "Avactis Demo Store";
	
	@FindBy(css = "*[href*='ketan479/sign-in.php']")
	WebElement signInLink;
	
	@FindBy(xpath = "//a[contains(@href,'register.php')]")	
	WebElement registerLink;
	
	@FindBy(xpath = "//input[contains(@value,'Sign In')]")	
	WebElement signInButton;
	
	@FindBy(id = "account_sign_in_form_email_id")	
	WebElement emailID;
	
	@FindBy(id = "account_sign_in_form_passwd_id")	
	WebElement password;

	
	public SignInPage(WebDriver driver)
	{
		SignInPage.driver = driver;
		PageFactory.initElements(driver, this);
		Log.info("Home page is launched");
		
	}
	
	@Override
	protected void isLoaded() throws Error 
	{
		assertEquals("Acvatis Demo Store page not loaded properly", title,  driver.getTitle());		
	}

	@Override
	protected void load() 
	{
		driver.get(url);
		
	}
	
	public void close()
	{
		driver.quit();
		Log.info("Browser closed");
	}	


	public void doSignIn(String emailID, String password)
	{
		signInLink.click();
		this.emailID.sendKeys(emailID);
		this.password.sendKeys(password);
		signInButton.click();
	}




}
