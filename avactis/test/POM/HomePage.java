package POM;

import static org.testng.AssertJUnit.assertEquals;
import scripts.Log;
import utility.Waiting;
import POM.SignInPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;


public class HomePage extends LoadableComponent<HomePage>
{
	private WebDriver driver;
	private String url = "http://avactis:avactis%40123@sandbox.avactis.com/ketan479/";
	private String title = "Avactis Demo Store";
	
	@FindBy (xpath = "//button[contains(text(),'Register')]")
	public static WebElement goToRegistrationPage;
	
	@FindBy (css = "*[href*='ketan479/sign-in.php']")
	public WebElement signInLink;
	
	@FindBy (css = "a[href='sign-in.php']")
	public WebElement myAccountLink;
	
	@FindBy (css = "*[href*='cart.php']")
	public WebElement cartLink;

	@FindBy (css = "*[href*='checkout.php']")
	public WebElement checkOutLink;
	
	public HomePage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
		Log.info("Browser is getting initialized");		
	}

	@Override
	protected void isLoaded() throws Error 
	{
		Waiting.waitForTitle(driver,title);
		assertEquals("Acvatis Demo Store page not loaded properly",title,driver.getTitle());
		
	}

	@Override
	protected void load() 
	{
		driver.manage().window().maximize();
		driver.get(url);		
	}
	
	public void close()
	{
		driver.quit();
	}
	
	public SignInPage goToSignInPage()
	{
		signInLink.click();
		SignInPage signInPage = new SignInPage(driver);
		return signInPage;
		
	}
	
	public static void goToRegistrationPageUsingButton()
	{
		goToRegistrationPage.click();
		
	}
	 
	
	
	
}
