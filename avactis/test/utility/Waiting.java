package utility;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Waiting 
{
 private static final int DEFAULT_WAIT_FOR_PAGE = 30;
	public static WebElement WaitForElement(WebDriver driver, final By by, int timeOutInSeconds)
	{
		WebElement element;
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS); //Nullify implicit wait
		
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		
		driver.manage().timeouts().implicitlyWait(DEFAULT_WAIT_FOR_PAGE, TimeUnit.SECONDS);
		return element;
	}
	
	public static boolean waitForTitle(WebDriver driver, String title) 
	{
		try
		{
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS); //nullify implicitlyWait() 			
			WebDriverWait wait = new WebDriverWait(driver, 30); 
			wait.until(ExpectedConditions.titleIs(title));			
			driver.manage().timeouts().implicitlyWait(DEFAULT_WAIT_FOR_PAGE, TimeUnit.SECONDS); //reset implicitlyWait
			return true; //return the element
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return false;
		} 
		 
		
	}
}
