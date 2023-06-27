package TestNG;
import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.SelectOption;

public class Paralleltests {
	Playwright playwright = Playwright.create(); 
    Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));
    Page page = browser.newPage();
	@Test
	public void dropdown() throws InterruptedException {
		
	    page.navigate("https://www.wikipedia.org/");
	    Thread.sleep(3000);
	    
	    //select by value
	    page.selectOption("select","hi");
	    Thread.sleep(3000);
	    
	    //select by text
	    page.selectOption("select",new SelectOption().setLabel("English"));
	    Thread.sleep(3000);
	    
	    //select by index
	    page.selectOption("select",new SelectOption().setIndex(1));
	    Thread.sleep(3000);
	    
	    Locator values = page.locator("select>option");
	    System.out.println(values.count());
	    
	    for(int i=0; i<values.count();i++) {
	    	System.out.println( values.nth(i).innerText()+"-----"+values.nth(i).getAttribute("lang"));
	    }
		}
	@Test
	public void links() {
	    page.navigate("https://www.wikipedia.org/");

		Locator links = page.locator("a");
		System.out.println(links.count());
		
		for(int i=0;i<links.count();i++) {
			System.out.println(links.nth(i).innerText()+"---URL--- )"+links.nth(i).getAttribute("href"));
		}
		
		Locator div = page.locator("//*[@id=\"www-wikipedia-org\"]/div[7]/div[3]");
		Locator divlinks = div.locator("a");
		System.out.println("****************************");

		System.out.println(divlinks.count());
		for(int i=0; i<divlinks.count();i++) {
	    	System.out.println( divlinks.nth(i).innerText()+"-----"+divlinks.nth(i).getAttribute("lang"));
	    }
	}
	@Test
	public void checkbox() throws InterruptedException {
		 page.navigate("http://www.tizag.com/htmlT/htmlcheckboxes.php");
		 
		 Locator block = page.locator("//html/body/table[3]/tbody/tr[1]/td[2]/table/tbody/tr/td/div[4]");
		 Locator checkbox = block.locator("[type='checkbox']");
		 
		 for(int i = 0;i<checkbox.count();i++) {
			 checkbox.nth(i).click();
		 }
		 System.out.println(checkbox.count());
		    Thread.sleep(3000);	
		
	}
}
