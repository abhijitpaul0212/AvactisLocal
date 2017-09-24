package utility;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Homepage 
{
	public static void clickSignIn(WebDriver driver)
	  {
		  driver.findElement(By.cssSelector("*[href*='ketan479/sign-in.php']")).click();
	  }

	}
