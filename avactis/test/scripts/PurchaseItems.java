/*1. scenario3
1. Go to Avactis Store
2. Purchase 3 products from various product categories
3. Do CheckOut and provide shipping and billing address
4. Select shipment method and payment methods
5. Verify that the products selected, quantity and rate match with the final order
confirmation screen. 
5. Make the payment
6. Verify the order placed is same as what you had selected by verifying the order in admin.*/


package scripts;


import org.testng.annotations.Test;

import utility.Homepage;
import utility.Waiting;

import org.testng.annotations.BeforeClass;

import static org.testng.Assert.*;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.security.UserAndPassword;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;

public class PurchaseItems 
{
  private WebDriver driver;
  private WebDriverWait wait;
  
  @BeforeClass(alwaysRun = true)
   public void beforeClass() 
  {
	  /*System.setProperty("webdriver.gecko.driver", "test\\resources\\geckodriver64bit.exe");
	  driver = new FirefoxDriver();
	  */
	  System.setProperty("webdriver.chrome.driver", "test\\resources\\chromedriver.exe");
	 driver = new ChromeDriver();
//	 driver.manage().window().maximize();
//	 driver.manage().window().fullscreen();
	 /*System.setProperty("webdriver.ie.driver", "test\\resources\\IEDriverServer.exe");
	 driver = new InternetExplorerDriver();*/
	 driver.get("http://avactis:avactis%40123@sandbox.avactis.com/ketan479/");
//	 handleAuthentication();
	 wait = new WebDriverWait(driver, 30);
  }

	
  @Test(priority=1)
  public void signIn() 
  {
	  Homepage.clickSignIn(driver);
	  By waitForAccountSignInForm= By.cssSelector("*[for='account_sign_in_form_email_id']");
	  By waitForOrderSearch = By.className("orders_search1");
	    
//	  wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("*[for='account_sign_in_form_email_id']"))).equals("E-Mail:");
	  Waiting.WaitForElement(driver, waitForAccountSignInForm, 30);
	  //Test login
	  driver.findElement(By.id("account_sign_in_form_email_id")).sendKeys("abhijitpaul_02@yahoo.com");
	  driver.findElement(By.id("account_sign_in_form_passwd_id")).sendKeys("password123");
	  driver.findElement(By.cssSelector("*[value='Sign In']")).click();
	  
	  //Test post login condition
//	  wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("orders_search1"))).equals("ORDERS");
	  Waiting.WaitForElement(driver, waitForOrderSearch, 30).equals("ORDERS");
	  assertEquals("ORDERS", driver.findElement(By.className("orders_search1")).getText());
  }
  
  @Test(priority=2)
  public void addToCart()
  {
	  String navigateToProduct1 = "DVD>TV_on_DVD";
	  String navigateToProduct2 = "DVD>Kids";
	  String navigateToProduct3 = "DVD>Classic_Films";
	  String navigateToProduct4 = "Sport>cid45";
	  String navigateToProduct5 = "Furniture";
	  
	  //Navigate to DVD > TV on DVD > House_M_D
	  //System.out.println("Purchasing first item");
	  
	  navigateMenu(driver,2,navigateToProduct1);

	  /*WebElement mainMenu1 = driver.findElement(By.cssSelector("*[href*='DVD'][class='dropdown-toggle']"));
	  Actions action1 = new Actions(driver);
	  action1.moveToElement(mainMenu1).build().perform();
	  driver.findElement(By.cssSelector("*[href*='TV_on_DVD']")).click();*/
	  driver.findElement(By.xpath("//*[contains(@href,'House_M_D')]/following-sibling::div[@class='product_buttons']//*[@value='Add To Cart']")).click();
	  //System.out.println("Purchasing first item completed");
//	  driver.navigate().refresh();
	  
	  //Navigate to DVD > Kids > Up
	  //System.out.println("Purchasing second item");
	  navigateMenu(driver,2,navigateToProduct2);
	  /*WebElement mainMenu2 = driver.findElement(By.cssSelector("*[href*='DVD'][class='dropdown-toggle']"));
	  Actions action2 = new Actions(driver);
	  action2.moveToElement(mainMenu2).build().perform();
	  driver.findElement(By.cssSelector("*[href*='Kids']")).click();
	  */
	  driver.findElement(By.xpath("//*[contains(@href,'Up')]/following-sibling::div[@class='product_buttons']//*[@value='Add To Cart']")).click();
	  //System.out.println("Purchasing second item completed");
//	  driver.navigate().refresh();
	  
//	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  
	  //System.out.println("Purchasing third item");
	  navigateMenu(driver, 2, navigateToProduct3);
/*	  WebElement mainMenu3 = driver.findElement(By.cssSelector("*[href*='DVD'][class='dropdown-toggle']"));
	  Actions action3 = new Actions(driver);
	  action3.moveToElement(mainMenu3).build().perform();
	  driver.findElement(By.cssSelector("*[class='dropdown dropdown-megamenu'] *[href*='Classic_Films']")).click();
*/	  driver.findElement(By.xpath("//*[contains(@href,'James')]/following-sibling::div[@class='product_buttons']//*[@value='Add To Cart']")).click();
	  //System.out.println("Purchasing third item completed");	 

/*
	  navigateMenu(driver,2,navigateToProduct4);
	  driver.findElement(By.xpath("//*[contains(@href,'pid180')]/following-sibling::div[@class='product_buttons']//*[@value='Add To Cart']")).click();
	 */ 
	  navigateMenu(driver,1,navigateToProduct5);
	  driver.findElement(By.xpath("//*[contains(@href,'EKTORP_TULLSTA')]/following-sibling::div[@class='product_buttons']//*[@value='Add To Cart']")).click();
  }
  
  @Test(priority=3)
  public void checkOut()
  {
	driver.navigate().refresh();
	WebElement checkOutPreview = driver.findElement(By.xpath("//*[contains(@class,'preview cartpreview')]/i"));
	Actions action4 = new Actions(driver);
	action4.moveToElement(checkOutPreview).build().perform();
	driver.findElement(By.xpath("//*[@class='top-cart-content']//*[contains(text(),'Checkout')]")).click();
  }
  
  @Test(priority=4)
  public void billingShippingAddress()
  {
	String firstName = driver.findElement(By.xpath("//*[@name='billingInfo[Firstname]']")).getAttribute("value");
	//System.out.println(firstName);
	try {
		Thread.sleep(9000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	if(!firstName.isEmpty())
	{
		driver.findElement(By.xpath("//*[@value='Continue Checkout'][contains(@onclick,'1')]")).click();
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	else
	{
		System.out.println("Billing and shipping details is blank and needs to be filled");
	}
	

  }
  
 @Test(priority=5)
  public void billingShippingMethod()
  {
	  List<WebElement> shippingMethods = driver.findElements(By.xpath("//div[@class ='shipping_method_name']/label"));
	  for(WebElement shippingMethod:shippingMethods)
	  {
		  if(shippingMethod.getText().equalsIgnoreCase("Ground Shipping"))
		  {
			  shippingMethod.click();
		  }
	  }
	  driver.findElement(By.xpath("//div[@class='checkout_buttons']/input[contains(@onclick,'submitStep(2)')]")).click();
  }
  
  @Test(priority=6)
  public void placeOrder()
  { 
	 try {
		Thread.sleep(20000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 driver.findElement(By.xpath("//*[@value='Place Order']")).click();
	 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	 String orderIds = driver.findElement(By.xpath("//label[contains(text(),'Order Id')]/following-sibling::div")).getText();
	 String[] orderId = orderIds.split("#");
	 System.out.println("Your order ID is "+orderId[1]);
  } 
  
 @Test(priority=8)
  public void zignOut()
  {
	  driver.findElement(By.xpath("//*[contains(@href,'customer_sign_out')]")).click();
	  //Post logout validation
	 assertEquals("Sign In",driver.findElement(By.xpath("//*[contains(@href,'sign-in')]")).getText());
	  
  }
  
  @AfterClass
  public void afterClass() 
  {
	  driver.navigate().refresh();
	  driver.quit();
  }
  
  
  
  
  public void handleUserAuthentication() //This method runs on Internet Explorer only
  {
	  String uname = "avactis";
	  String pwd = "avactis@123";
	  
	  WebDriverWait wait = new WebDriverWait(driver, 10);      
	  Alert alert = wait.until(ExpectedConditions.alertIsPresent());     
	  alert.authenticateUsing(new UserAndPassword(uname, pwd));	  
  }
  
  public void navigateMenu(WebDriver driver, int menuLevels, String menuString) //DVD > TV on DVD > House_M_D
  {
	  String[] menus = menuString.split(">");
/*	  System.out.println(menus[0]);
	  System.out.println(menus[1]);*/
	
	  WebElement mainMenu;
	  Actions action;
      switch (menuLevels) 
      {
          case 1:  driver.findElement(By.cssSelector("*[class='header-navigation'] *[href*='"+menus[0]+"']")).click();
                   break;
          case 2:  mainMenu = driver.findElement(By.cssSelector("*[href*='"+menus[0]+"'][class='dropdown-toggle']"));
    	  		   action = new Actions(driver);
    	  		   action.moveToElement(mainMenu).build().perform();
    	  		   driver.findElement(By.cssSelector("*[class='dropdown dropdown-megamenu'] *[href*='"+menus[1]+"']")).click();
    	  		   break;    
	  
      }  

  }
}
