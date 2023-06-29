import java.awt.Dimension;
import java.awt.Toolkit;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class Browsers {
    public static void main(String[] args) throws InterruptedException {
		
    	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    	double width = screenSize.getWidth();
    	double height = screenSize.getHeight();
	
	
		Playwright playwright = Playwright.create(); 
    
        Browser browser = playwright.chromium()/*firefox()*/.launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));
        BrowserContext browsercontext = browser.newContext(new Browser.NewContextOptions().setViewportSize((int)width,(int)height));//browser context for full screen

        
        Page page = browsercontext.newPage();
        
        page.navigate("http://radixweb.com");
        
        System.out.println(page.title());
     Thread.sleep(5000);
       page.close();
     playwright.close();
        
}
}
