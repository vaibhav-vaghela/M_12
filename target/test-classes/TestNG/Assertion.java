package TestNG;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class Assertion {
	@Test
	public void assertioninTestng() {
			  Playwright playwright = Playwright.create();
		      Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		      BrowserContext context = browser.newContext();
		      Page page = context.newPage();
		      page.navigate("http://demo.guru99.com/test/newtours/");  
	      
		      System.out.println("launching firefox browser"); 
		  
		      String expectedTitle = "Welcome: Mercury Tours";
		      String actualTitle = page.title();
		      
		      Assert.assertEquals(actualTitle, expectedTitle);
		      
		      page.close();
		      browser.close();
		      playwright.close();
	}
}
