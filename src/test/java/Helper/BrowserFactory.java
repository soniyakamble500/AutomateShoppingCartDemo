package Helper;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class BrowserFactory {

	public static WebDriver startApplication(String browser, String application) {
		WebDriver driver= null;

		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "D:\\Drivers\\chromedriver_win32\\chromedriver.exe");
			 driver = new ChromeDriver();
		}
		else 
	   if(browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "D:\\Drivers\\geckodriver-v0.26.0-win64\\geckodriver.exe");
			 driver = new FirefoxDriver();
		}
	   else
	   if(browser.equalsIgnoreCase("IE")){
		System.setProperty("webDriver.ie.driver","D:\\Drivers\\IEDriverServer_Win32_3.150.1\\IEDriverServer.exe");
		driver = new InternetExplorerDriver();	
	           }
		
		
		driver.manage().deleteAllCookies();
		
		driver.manage().timeouts().pageLoadTimeout(5000,TimeUnit.SECONDS);
		
		driver.get(application);
		
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
		
		driver.manage().window().maximize();
		
		return driver;	
		
	}

	}