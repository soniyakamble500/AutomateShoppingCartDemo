package testSuitOne;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class RegisterUser2 {
	
	WebDriver driver;
	@BeforeClass
	public void openApplication() {
		System.setProperty("webdriver.chrome.driver","C:\\Users\\soniy\\Downloads\\chromedriver_win32 (2)\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.get("http://demowebshop.tricentis.com");
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();
	}
	
	@Test(priority=1)
	public void register() {
		//click on the register
		driver.findElement(By.xpath("//a[contains(text(),'Register')]")).click();
		driver.findElement(By.xpath("//input[@id='gender-female']")).click();
		driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys("nisha");
		driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys("manav");
		driver.findElement(By.xpath("//input[@id='Email']")).sendKeys("nishamanav307@gmail.com");
		driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("pass@123");
		driver.findElement(By.xpath("//input[@id='ConfirmPassword']")).sendKeys("pass@123");
		driver.findElement(By.xpath("//input[@id='register-button']")).click();
	}
	
	
	@Test(priority=2)
	public void verifyRegistration() {
		Assert.assertTrue(driver.getCurrentUrl().contains("registerresult/1"), "registration is incomplete,Please fill the form");	
	}
	
	
	@Test(priority=3)
	public void  logoutAfterRegistration() throws InterruptedException {
		Thread.sleep(4000);
		driver.findElement(By.xpath("//a[@class='ico-logout']")).click();
		
		
		
	}
	
	//3-Login with same user which is created using Step 2
	@Test(priority=4)
	public void verifyloginWithSameCredential() {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//a[contains(text(),'Log in')]")).click();
		
		driver.findElement(By.xpath("//input[@id='Email']")).sendKeys("nishamanav307@gmail.com");
		driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("pass@123");
		driver.findElement(By.xpath("//input[@id='RememberMe']")).click();
		driver.findElement(By.xpath("//input[@value='Log in']")).click();
	}
	
	   //4-Verify after login email should be same which we used in registration process
	    @Test(priority=5)
	    public void verifyLoginEmailId()
	    {
	    WebElement  actual_Element = driver.findElement(By.xpath("//a[contains(text(),'nishamanav307@gmail.com')]"));
	    System.out.println(actual_Element.getText());
	    String expected_Elemenet = "nishamanav307@gmail.com";
	    if(!actual_Element.equals(expected_Elemenet)) {
	    	System.out.println("The login details  same as registered details");
	    }else {
	    	System.out.println("the login details are not same as registered details");	
	    	} 
	    }
	    
	    @Test(priority=6)
	    public void clickOnEmail() throws InterruptedException{
	    	Thread.sleep(5000);
	    	driver.findElement(By.xpath("//a[contains(text(),'nishamanav307@gmail.com')]")).click();
	    }
	    
	    
	    //6-Left Panel click on Address
	    @Test(priority=7)
	    public void leftClickOnAddress() throws InterruptedException {
	    	Thread.sleep(3000);
	        driver.findElement(By.xpath("//a[@class='inactive'][contains(text(),'Addresses')]")).click();	
	    }
	    
	    //7-Click on add new
	    @Test(priority=8)
	    public void addnew() throws InterruptedException {
	    	
	    	Thread.sleep(4000); 
	    	driver.findElement(By.xpath("//input[@value='Add new']")).click();	
	    }
	    
	    //8-Fill complete form
	    @Test(priority=9)
	    public void fillTheForm() throws InterruptedException {
	    	Thread.sleep(4000); 
	    	driver.findElement(By.xpath("//input[@id='Address_FirstName']")).sendKeys("Nishi");
	    	driver.findElement(By.xpath("//input[@id='Address_LastName']")).sendKeys("Manee");
	    	driver.findElement(By.xpath("//input[@id='Address_Email']")).sendKeys("manav@gmail.com");
	    	driver.findElement(By.xpath("//input[@id='Address_Company']")).sendKeys("Inubesoftwaresollutions");
	    	WebElement country = driver.findElement(By.xpath("//select[@id='Address_CountryId']"));
	    	country.click();
	    	Thread.sleep(2000);
	    	Select s =  new Select(country);
	    	Thread.sleep(4000);
	    	driver.findElement(By.xpath("//select[@id='Address_CountryId']//following::option[contains(text(),'India')][2]")).click();
	    	driver.findElement(By.xpath("//select[@id='Address_StateProvinceId']")).click();
	    	driver.findElement(By.xpath("//input[@id='Address_City']")).sendKeys("Banglore");
	    	driver.findElement(By.xpath("//input[@id='Address_Address1']")).sendKeys("kormangala");
	    	driver.findElement(By.xpath("//input[@id='Address_Address2']")).sendKeys("banglore");
	    	driver.findElement(By.xpath("//input[@id='Address_ZipPostalCode']")).sendKeys("543006");
	    	driver.findElement(By.xpath("//input[@id='Address_PhoneNumber']")).sendKeys("7406501839");
	    	driver.findElement(By.xpath("//input[@id='Address_FaxNumber']")).sendKeys("67899");
	    	driver.findElement(By.xpath("//input[@value='Save']")).click();
	      }
	    
	    
	    //Click on the orders
	    @Test(priority=10)
	    public void clickonOrders() throws InterruptedException {
	         Thread.sleep(4000);
	    	 driver.findElement(By.xpath("//ul[@class='list']//a[contains(text(),'Orders')]")).click();	
	    }
	    
	    @Test(priority=11)
	    public void verifyOrdersDetails() throws InterruptedException {
	    	
	    	Thread.sleep(4000);
	    	WebElement orders= driver.findElement(By.xpath("//div[@class='order-list']"));
	    	System.out.println(orders.getText());
	    	Assert.assertEquals(orders.getText(), "No orders", "The order details  are incorrect test case is failed");	
	    }
	    
	    //10-On top verify Shopping Cart is 0 and Wishlist is 0
	    @Test
	    public void verifyShoppingDetails() {
	    String  shopping_cart_Quantity = driver.findElement(By.xpath("//span[@class='cart-qty']")).getText();
	    System.out.println("The shopping car quantity : " + shopping_cart_Quantity);
	    
	    String  shopping_wishlist_Quantity = driver.findElement(By.xpath("//span[@class='wishlist-qty']")).getText();
	    System.out.println("The shopping wishlist quantity : " + shopping_wishlist_Quantity);
	    }
    	    	  
	    @AfterClass
	    public void  CloseApplication() throws InterruptedException {
		Thread.sleep(5000);
		driver.quit();
		
	}
	
}
