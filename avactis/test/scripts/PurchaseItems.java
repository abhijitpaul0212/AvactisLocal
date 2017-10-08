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

import POM.AddToCartPage;
import POM.CheckOutPage;
import POM.MyAccountPage;
import POM.SignInPage;
import POM.SignOutPage;
import POM.ViewOrderPage;
import junit.framework.Assert;
import utility.Homepage;
import utility.Waiting;

import org.testng.annotations.BeforeClass;

import static org.testng.Assert.*;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
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
  private SignInPage signInP;
  private MyAccountPage myAccountP;
  private AddToCartPage addToCartP;
  private CheckOutPage checkOutP;
  private SignOutPage signOutP;
  private String OrderID = null;
  private String multiLevelMenu = "DVD;House, M.D.#Computers;mobile";
  private String singleLevelMenu = "Furniture;EKTORP TULLSTA Chair";
//  private Dictionary<String, String> dict;
//  private HashMap<String, List<String>> map;
  private static ArrayList<List<String>> expCartItemDetails;
  
  @BeforeClass(alwaysRun = true)
   public void beforeClass() 
  {
	  System.setProperty("webdriver.gecko.driver", "test\\resources\\geckodriver64bit.exe");
	  driver = new FirefoxDriver();
   	  wait = new WebDriverWait(driver, 30);
   	  signInP = new SignInPage(driver);
   	  signInP.get();
   	  myAccountP = new MyAccountPage(driver);
   	  new ViewOrderPage(driver);
   	  addToCartP = new AddToCartPage(driver);
   	  checkOutP = new CheckOutPage(driver); 
   	  signOutP = new SignOutPage(driver);
  }

	
  @Test(priority=1)
  public void shopping()
  {
	  Log.info("---------------- Test Case : 'Placing order' begins ----------------");
	  signInP.doSignIn("abhijitpaul_02@yahoo.com", "password123");	
	  addToCartP.navigateMenuAddToCart(driver, 2, multiLevelMenu);	  
	  addToCartP.navigateMenuAddToCart(driver, 1, singleLevelMenu);	  
	  String expectedMenuList = singleLevelMenu+"#"+multiLevelMenu;	  
	  expCartItemDetails = new ArrayList<List<String>>();
	  expCartItemDetails = checkOutP.myCartCheckOut(expectedMenuList);
	  
//	  checkOutP.myCartCheckOut(map,expectedMenuList);
	  checkOutP.billingShippingAddress();
	  String shippingMethod="Ground Shipping";
	  checkOutP.billingShippingMethod(shippingMethod);
	  OrderID = checkOutP.placeOrder(expCartItemDetails);
      signOutP.signOut();
	  Log.info("---------------- Test Case : 'Placing order' ends ----------------");
  }
  
//  @Test(priority=2)
  public void orderValidating()
  {
	  Log.info("---------------- Test Case : 'Validating placed order' begins ----------------");
//	  String expectedMenuList = singleLevelMenu+"#"+multiLevelMenu;
	  signInP.doSignIn("abhijitpaul_02@yahoo.com", "password123");
//	  myAccountP.searchByOrderID(OrderID);
	  myAccountP.searchByOrderID("00943");
//	  ViewOrderPage.compareItems(driver,expectedMenuList);
	  Boolean returnVal = ViewOrderPage.compareItems(driver,expCartItemDetails);
	  Assert.assertTrue(returnVal);
	  signOutP.signOut();
	  Log.info("---------------- Test Case : 'Validating placed order' ends ----------------");
  }
 
 @AfterClass
 public void afterClass() 
 {
	  driver.quit();
 }
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
  
  
  /*public void signIn() 
  {
//	  Homepage.clickSignIn(driver);
	  
	  By waitForAccountSignInForm= By.cssSelector("*[for='account_sign_in_form_email_id']");
	  By waitForOrderSearch = By.className("orders_search1");
	    
	  Waiting.WaitForElement(driver, waitForAccountSignInForm, 30);
	 
	  driver.findElement(By.id("account_sign_in_form_email_id")).sendKeys("abhijitpaul_02@yahoo.com");
	  driver.findElement(By.id("account_sign_in_form_passwd_id")).sendKeys("password123");
	  driver.findElement(By.cssSelector("*[value='Sign In']")).click();
	  
//	  Waiting.WaitForElement(driver, waitForOrderSearch, 30).equals("ORDERS");
//	  assertEquals("ORDERS", driver.findElement(By.className("orders_search1")).getText());
  }*/
  

  /*public void addToCart()
  {
	 String navigateToProduct1 = "DVD>";
	 String navigateToProduct2 = "DVD>";
	    String navigateToProduct3 = "DVD>Classic_Films";
	  String navigateToProduct4 = "Sport>cid45";
	  String navigateToProduct5 = "Furniture";   ;Up;Lost
	  
	  String navigateToProduct1 = "DVD;House_M_D;Lost";
	  String navigateToProduct2 = "Computers;pid172";
	 
//	  String multiLevelMenu = "DVD;House_M_D#Computers;pid172";
	  String multiLevelMenu = "DVD;Forbidden Planet#Computers;mobile";
	  navigateMenuAddToCart(driver, 2, multiLevelMenu);
	  
	  String singleLevelMenu = "Furniture;EKTORP TULLSTA Chair";
	  navigateMenuAddToCart(driver, 1, singleLevelMenu);
	  
	  String expectedMenuList = singleLevelMenu+multiLevelMenu;
	
	//*[contains(@href,'EKTORP Neckroll')]/following-sibling::div[@class='product_buttons']//*[@value='Add To Cart'
	  
	  navigateMenuAddToCart(driver, 2, navigateToProduct1);
	  driver.findElement(By.xpath("//*[contains(@href,'Up')]/following-sibling::div[@class='product_buttons']//*[@value='Add To Cart']")).click();
	  driver.navigate().refresh();
//	  navigateMenuAddToCart(driver, 2, navigateToProduct2);
	  
	  
	  try {
		Thread.sleep(10000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	  	navigateMenu(driver,2,navigateToProduct1);	
	  driver.findElement(By.xpath("//*[contains(@href,'House_M_D')]/following-sibling::div[@class='product_buttons']//*[@value='Add To Cart']")).click();
	  try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  driver.navigate().refresh();
	  
	  System.out.println("Done with first add to cart");
	 
	  
  try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  navigateMenu(driver,2,navigateToProduct2);	
		  driver.findElement(By.xpath("//*[contains(@href,'Up')]/following-sibling::div[@class='product_buttons']//*[@value='Add To Cart']")).click();
		  try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  driver.navigate().refresh();
		  
		  System.out.println("Done with second add to cart");
	  
	  
	  navigateMenu(driver,2,navigateToProduct2);
	  driver.findElement(By.xpath("//*[contains(@href,'Up')]/following-sibling::div[@class='product_buttons']//*[@value='Add To Cart']")).click();
	  System.out.println("Done with second add to cart");

	  navigateMenu(driver,2,navigateToProduct3);
      driver.findElement(By.xpath("//*[contains(@href,'James')]/following-sibling::div[@class='product_buttons']//*[@value='Add To Cart']")).click();
      System.out.println("Done with third add to cart");
 	  
	  navigateMenu(driver,1,navigateToProduct5);
	  driver.findElement(By.xpath("//*[contains(@href,'EKTORP_TULLSTA')]/following-sibling::div[@class='product_buttons']//*[@value='Add To Cart']")).click();
	  System.out.println("Done with fourth add to cart");
  }*/
  
 /* @Test(priority=3)
  public void checkOut()
  {
	driver.navigate().refresh();
	WebElement checkOutPreview = driver.findElement(By.xpath("//*[contains(@class,'preview cartpreview')]/i"));
	Actions action4 = new Actions(driver);
	action4.moveToElement(checkOutPreview).build().perform();
	driver.findElement(By.xpath("//*[@class='top-cart-content']//*[contains(text(),'Checkout')]")).click();
  }*/
  
  
/*  public void myCart()
  {
	driver.navigate().refresh();
	driver.findElement(By.cssSelector("a[href='cart.php']")).click();
	try {
		Thread.sleep(10000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	System.out.println("I am about to click Checkout button");
//	driver.findElement(By.cssSelector("*[class='top-cart-content'] a[href*='checkout.php']")).click();
	driver.findElement(By.xpath("html/body/div[4]/div/div/div/div/div/div[1]/div/div/div[3]/a[2]")).click();
	
//	"//*[@class='top-cart-content']//a[contains(@href,'checkout.php')]"
//	html/body/div[4]/div/div/div/div/div/div[1]/div/div/div[3]/a[1]
	//*[@class='top-cart-content']//a[contains(@href,'checkout.php')]
  }*/
  
  
 /* public void billingShippingAddress()
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
	

  }*/
  
 
  /*public void billingShippingMethod()
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
  }*/
  
 
/*  public String placeOrder()
  { 
	 try {
		Thread.sleep(10000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 driver.findElement(By.xpath("//*[@value='Place Order']")).click();
//	 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	 try {
		Thread.sleep(15000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 
	 WebDriverWait wait = new WebDriverWait(driver, 30);
	 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(text(),'Order Id')]")));
//	 Waiting.WaitForElement(driver, By.xpath("//label[contains(text(),'Order Id')]"), 30);
	 String orderIds = driver.findElement(By.xpath("//label[contains(text(),'Order Id')]/following-sibling::div")).getText();
	 String[] orderId = orderIds.split("#");
	 Log.info("Your order ID is "+orderId[1]);
	 return orderId[1];
	 
//	 validateOrder(driver, orderId[1]);
	 
  } */
  
 
 /* public void signOut()
  {
	  driver.findElement(By.xpath("//*[contains(@href,'customer_sign_out')]")).click();
	  //Post logout validation
	  assertEquals("Sign In",driver.findElement(By.xpath("//*[contains(@href,'sign-in')]")).getText());
	  
  }*/
  
  

  /*public void handleUserAuthentication() //This method runs on Internet Explorer only
  {
	  String uname = "avactis";
	  String pwd = "avactis@123";
	  
	  WebDriverWait wait = new WebDriverWait(driver, 10);      
	  Alert alert = wait.until(ExpectedConditions.alertIsPresent());     
	  alert.authenticateUsing(new UserAndPassword(uname, pwd));	  
  }*/
  
 /* public void navigateMenu(WebDriver driver, int menuLevels, String menuString) //DVD > TV on DVD > House_M_D
  {
	  String[] menus = menuString.split(">");
	  System.out.println(menus[0]);
	  System.out.println(menus[1]);
	
	  WebElement mainMenu;
	  Actions action;
      switch (menuLevels) 
      {
          case 1:  Waiting.WaitForElement(driver, By.cssSelector("*[class='header-navigation'] *[href*='"+menus[0]+"']"), 20);
        	  	   driver.findElement(By.cssSelector("*[class='header-navigation'] *[href*='"+menus[0]+"']")).click();
                   break;
          case 2:  Waiting.WaitForElement(driver, By.cssSelector("*[href*='"+menus[0]+"'][class='dropdown-toggle']"), 20);
        	  	   driver.findElement(By.cssSelector("*[href*='"+menus[0]+"'][class='dropdown-toggle']")).click();
        	  	   mainMenu = driver.findElement(By.cssSelector("*[href*='"+menus[0]+"'][class='dropdown-toggle']"));
    	  		   action = new Actions(driver);
    	  		   action.moveToElement(mainMenu).build().perform();
    	  		   driver.findElement(By.cssSelector("*[class='dropdown dropdown-megamenu'] *[href*='"+menus[1]+"']")).click();
    	  		   break;    
	  
      }  

  }
*/  
/*  public void navigateMenuAddToCart(WebDriver driver, int menuLevels, String menuString)
  {
	  WebElement AddToCart;
	  
	  
	  String[] fullMenu = menuString.split("#");
	  for(int i = 0; i < fullMenu.length; i++)
	  {
		  String[] partialMenu = fullMenu[i].split(";");  
		
	      switch (menuLevels) 
	      {
	          case 1:  driver.findElement(By.cssSelector("*[class='header-navigation'] *[href*='"+partialMenu[0]+"']")).click();
//	        	  	   for (int i = 1; i < items.size(); i++) 
	          		   for(int j = 1; j < partialMenu.length;j++)
	        		   { 
//	        	  		 AddToCart = driver.findElement(By.xpath("//*[contains(@href,'"+partialMenu[j]+"')]/following-sibling::div[@class='product_buttons']//*[@value='Add To Cart']"));
	          			 AddToCart = driver.findElement(By.xpath("//h3[text()='"+partialMenu[j]+"']/ancestor::a[1]/following-sibling::div[@class='product_buttons']//*[@value='Add To Cart']"));
	          			 AddToCart.click();
	        	  		 System.out.println(AddToCart);
	        			 System.out.println(partialMenu[j]+" is added to cart");
	        			 try {
							Thread.sleep(10000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	//        			 driver.navigate().refresh();
	        		   }
	                   break;
	                   
	          case 2:  driver.findElement(By.cssSelector("*[href*='"+partialMenu[0]+"'][class='dropdown-toggle']")).click();
//	          		   for (int i = 1; i < items.size(); i++) 
	          		   for(int j = 1; j < partialMenu.length;j++)
	          		   { 
//	          			 AddToCart = driver.findElement(By.xpath("//*[contains(@href,'"+partialMenu[j]+"')]/following-sibling::div[@class='product_buttons']//*[@value='Add To Cart']"));
	          			 AddToCart = driver.findElement(By.xpath("//h3[text()='"+partialMenu[j]+"']/ancestor::a[1]/following-sibling::div[@class='product_buttons']//*[@value='Add To Cart']"));
	          			 AddToCart.click();
	        	  		 System.out.println(AddToCart);
	          			 System.out.println(partialMenu[j]+" is added to cart");
	          			 try {
							Thread.sleep(10000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	//          			 driver.navigate().refresh();
	          		   }
	    	  		   break;    
		  
	      }
	  }

  }
*/  
/*  public int[] validateOrder(WebDriver driver, String orderId, String expectedItems)
  {
	  driver.findElement(By.xpath("//*[@href='sign-in.php']")).click();
	  driver.findElement(By.xpath("//*[@name='order_id']")).sendKeys(orderId);
	  driver.findElement(By.xpath("//*[@class='col-lg-1']/*[@class='en btn blue button_order_search input_submit']")).click();
	  try {
		Thread.sleep(5000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  driver.findElement(By.xpath("//*[@title='Order Info']")).click();
//	  System.out.println("Validation started");
	  
	  
	  List<WebElement> actualOrderItems = driver.findElements(By.xpath("//div[@class = 'product_name']"));		
	  String actualItems = "";
	  
	  for(WebElement allItems: actualOrderItems)
		{
//			System.out.println(allItems.getText());
			actualItems = allItems.getText()+";"+actualItems;			
		}
//	  System.out.println(actualItems);
	  int[] result = compareItems(expectedItems, actualItems);
	  return result;
	  
		
	  
  }
  
  public static int[] compareItems(String expectedMenuList, String actualMenuList)
	{
		String matchValue = null;
		int actMatch = 0;
		int expMatch = 0;
		
		 String[] fullMenu = expectedMenuList.split("#");
		  for(int i = 0; i < fullMenu.length; i++)
		  {
			  String[] partialMenu = fullMenu[i].split(";");
			  expMatch = expMatch + (partialMenu.length -1);
		  }
		
		
		
		String replaceExp = expectedMenuList.replaceAll("#|;", ">"); 
		String[] fullMenuExp = replaceExp.split(">");        
		List<String> itemsExp= new ArrayList<String>();
		for(int i = 0; i< fullMenuExp.length ; i++)
		{
			itemsExp.add(fullMenuExp[i]);
		}
//		System.out.println("Added items to expected list "+itemsExp);
		String replaceAct = actualMenuList.replaceAll(" \\(.*?\\)", "");
//		System.out.println("Modified actual list "+replaceAct);
		for(String item: itemsExp)
		{
//			System.out.println(item);
			if (replaceAct.matches("(.*)"+item+"(.*)"))
			{
				actMatch++;
//				System.out.println("Actual match count "+actMatch);
			}
		}
    ArrayList<Integer> result = new ArrayList<Integer>();
    result.add(expMatch);
    result.add(actMatch);
  	return result;  	
  	
		int[] result = new int[2];
		result[0] = expMatch;
		result[1] = actMatch;
  	
		System.out.println(result[0]+":"+result[1]);
		return result;
  	if (actMatch==expMatch)
  	{
  		return matchValue = "Matched. Expected count: "+expMatch+" and Actual count: "+actMatch; 
  		
  	}
		return matchValue = "Not matched. Expected count: "+expMatch+" and Actual count: "+actMatch;
		
	}*/
  
}
