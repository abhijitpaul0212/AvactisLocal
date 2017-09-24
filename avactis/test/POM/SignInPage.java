package POM;

import static org.testng.AssertJUnit.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import scripts.Log;
import utility.Waiting;

public class SignInPage  extends LoadableComponent<SignInPage> 
{
	private WebDriver driver;
	private String url = "http://avactis:avactis%40123@sandbox.avactis.com/ketan479/";
	private String title = "Avactis Demo Store";
	

	@FindBy(how= How.XPATH, using="//a[contains(@href,'register.php')]")	
	WebElement registerLink;
	
	@FindBy(how= How.XPATH, using="//input[contains(@value,'Sign In')]")	
	WebElement signInButton;
	
	@FindBy(how= How.ID, using="account_sign_in_form_email_id")	
	WebElement emailID;
	
	@FindBy(how= How.ID, using="account_sign_in_form_passwd_id")	
	WebElement password;
{

}

@Override
protected void isLoaded() throws Error 
{
	Waiting.waitForTitle(driver,title);
    assertEquals("Acvatis Demo Store page not loaded properly", title,  driver.getTitle());		
}

@Override
protected void load() 
{
	driver.manage().window().maximize();
	driver.get(url);
	
}

public SignInPage(WebDriver driver)
{
	this.driver = driver;
	PageFactory.initElements(driver, this);
	Log.info("Browser is getting initialized");		
	
}

public void doSignIn(String emailID, String password)
{
	this.emailID.sendKeys(emailID);
	this.password.sendKeys(password);
	signInButton.click();
}




}
