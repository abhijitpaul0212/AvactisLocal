package scripts;

import static org.testng.AssertJUnit.assertEquals;

import java.util.ArrayList;

import org.testng.annotations.Test;

import POM.RegistrationPage;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;

public class Registration
{
  private RegistrationPage registrationP;
  private WebDriver driver;
//  @DataProvider
 /* public Object[][] dp() {
    return new Object[][] {
      new Object[] { 1, "a" },
      new Object[] { 2, "b" },
    }
  };*/
  @BeforeClass (alwaysRun = true)
  public void beforeClass() 
  {
	  System.setProperty("webdriver.chrome.driver", "test\\resources\\chromedriver.exe");
	  driver = new ChromeDriver();
	  Log.info("------------ Registration execution begins ------------");
	  registrationP = new RegistrationPage(driver);
	  registrationP.get();
	  
	  
/*	  System.setProperty("webdriver.chrome.driver", "test\\resources\\chromedriver.exe");
	  driver = new ChromeDriver();	
	  driver.get("http://avactis:avactis%40123@sandbox.avactis.com/ketan479/");*/
  }

 
 @Test(priority = 3)
  public void RegistrationWithDuplicateUserName()
  {
	 Log.info("'RegistrationWithDuplicateUserName' test started");
	 registrationP.register("abhijitpaul_02@yahoo.com","password","Abhijit","Paul","411061","Pune","Lane no. 3","Sadhu Vaswani Road","8408012847");
	 registrationP.errorVerification(1); //Parameter  = number of expected errors
	 Log.info("'RegistrationWithDuplicateUserName' test ends");
  }
 
 @Test(priority = 2)
 public void RegistrationWithFourErrors()
 {
	 Log.info("'RegistrationWithFourErrors' test started");
	 registrationP.register("","","","","","","","","");
	 registrationP.errorVerification(4);
	 Log.info("'RegistrationWithFourErrors' test ends");
 }
 
 @Test(priority = 1)
 public void ValidRegistration()
 {
	 Log.info("'ValidRegistration' test started");
	 registrationP.register("abhijitpaul_02@yahoo.com","password","Abhijit","Paul","411061","Pune","Lane no. 3","Sadhu Vaswani Road","8408012847");
	 registrationP.errorVerification(0);
	 Log.info("'ValidRegistration' test ends");

 }
 
 @Test(priority = 4)
 public void RegistrationWithTwoErrors()
 {
	 Log.info("'RegistrationWithTwoErrors' test started");
	 registrationP.register("abhijitpaul_02@yahoo.com","password","","","","","","","");
	 registrationP.errorVerification(2);
	 Log.info("'RegistrationWithTwoErrors' test ends");
	 
 }
  
 @AfterClass
  public void afterClass() 
  {
	  registrationP.close();
	  Log.info("------------ Registration execution ends ------------");
  }
}

