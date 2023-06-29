import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Page.GoBackOptions;
import com.microsoft.playwright.Playwright;

public class navigation {
	 public static void main(String[] args) throws InterruptedException {
		    
	    	
	    	
 		Playwright playwright = Playwright.create(); 
     
         Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));//launching chromium and setting headless execution
         Page page = browser.newPage();
         
         page.navigate("https://tutorial.dev.radixweb.net/login");
         Thread.sleep(3000);
         System.out.println(page.title());
         Thread.sleep(3000);
         page.navigate("https://tutorial.dev.radixweb.net");
         Thread.sleep(3000);
         page.goBack(new GoBackOptions().setTimeout(500));
         Thread.sleep(3000);
         page.goForward();
         Thread.sleep(3000);
         page.reload();
         Thread.sleep(3000);
         page.close();
         playwright.close();

}
}