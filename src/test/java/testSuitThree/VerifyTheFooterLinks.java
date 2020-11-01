package testSuitThree;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class VerifyTheFooterLinks {
	
	WebDriver driver;
	@Test
	public void openApplication() {
		System.setProperty("webdriver.chrome.driver","D:\\Drivers\\chromedriver_win32\\chromedriver.exe");
			driver=new ChromeDriver();
		driver.get("http://demowebshop.tricentis.com/");
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        
        
        List<WebElement> links=driver.findElements(By.tagName("a"));
    	
    	System.out.println("Total links are "+links.size());
    	
    	for(int i=0;i<links.size();i++)
    	{
    		
    		WebElement ele= links.get(i);
    		
    		String Activelinks = ele.getAttribute("href");
    		
    		verifyLinkActive(Activelinks);
    		
    	}
	}	
   
    @Parameters("Openlinks")
	@Test
	public  void verifyLinkActive(String ll)
	{
        try 
        {
           URL url = new URL(ll);
           
           HttpURLConnection httpURLConnect=(HttpURLConnection)url.openConnection();
           
           httpURLConnect.setConnectTimeout(3000);
           
           httpURLConnect.connect();
           
           if(httpURLConnect.getResponseCode()==200)
           {
               System.out.println(ll+" - "+httpURLConnect.getResponseMessage());
            }
          if(httpURLConnect.getResponseCode()==HttpURLConnection.HTTP_NOT_FOUND)  
           {
               System.out.println(ll+" - "+httpURLConnect.getResponseMessage() + " - "+ HttpURLConnection.HTTP_NOT_FOUND);
            }
        } catch (Exception e) {
           
        }
	
	}
	}