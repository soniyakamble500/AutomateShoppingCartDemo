package testSuitTwo;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



public class MakeNewOrders {
	
	WebDriver driver;
	@BeforeClass
	public void openApplication() {
		System.setProperty("webdriver.chrome.driver","D:\\Drivers\\chromedriver_win32\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.get("http://demowebshop.tricentis.com/");
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
	}

	@Test (priority=1)
	public void login() {
		driver.findElement(By.xpath("//a[text()='Log in']")).click();
		driver.findElement(By.xpath("//input[@id='Email']")).sendKeys("nishamanav289@gmail.com");
		driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("pass@123");
		driver.findElement(By.xpath("//input[@id='RememberMe']")).click();
		driver.findElement(By.xpath("//input[@value='Log in']")).click();
		
	
	}
	//click on the book
	@Test(priority=2)
	public void clickOnTheBook() {
		driver.findElement(By.xpath("//div[@class='listbox']//ul[@class='list']//li//a[contains(text(),'Books')]")).click();	
	}
	
	//2-Select Sort by Dropdown and Select value “Name: A to Z”
	@Test(priority=3)
	public void VerifySortBooks() throws InterruptedException {
	
		
		WebElement sortBy = driver.findElement(By.xpath("//select[@id='products-orderby']"));
		sortBy.click();
		Thread.sleep(4000);
		Select s = new Select(sortBy);
		driver.findElement(By.xpath("//select[@id='products-orderby']//option[contains(text(),'Name: A to Z')]")).click();	
	}
	
	//3-Add to cart book title “Computing and Internet” 
	@Test(priority=4 )
	public void addBookToCart() throws InterruptedException {
		Thread.sleep(4000);
		driver.findElement(By.xpath("//h2[@class='product-title']//a[contains(text(),'Computing and Internet')]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@class='button-1 add-to-cart-button']")).click();	
	}
	
	@Test(priority=5)
	public void verifyTheShoppingCartUpdateDetails() throws InterruptedException {
		 String  shoppingCartOne =  driver.findElement(By.xpath("//span[@class='cart-qty']")).getText();
		 Thread.sleep(3000);
		 if(shoppingCartOne.contains("1")) {
			 System.out.println("The shopping cartOne is updated");
		 }
		 else {
			 System.out.println("The shopping cart details are not updated");
		 }
	  
	}
			
	//6-Add to cart another book “fiction”
	@Test(priority=6)
	public void  AddNewBookToCart() throws InterruptedException {
		driver.findElement(By.xpath("//h2[@class='product-title']//a[text()='Fiction']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@type='button'][@class='button-1 add-to-cart-button']")).click();
	}
	
	@Test(priority=7)
	public void verifySecondBookcartUpdate() throws InterruptedException {
	String  shoppingCartTwo  = driver.findElement(By.xpath("//span[@class='cart-qty']")).getText();
	Thread.sleep(3000);
	if(shoppingCartTwo.contains("2")) {
		System.out.println("The second time shopping cart is updated sucessfuly");
	}else {
		System.out.println("The second time shopping cart are not updated");
	}
	}
			 
	//7-Mouse Hover on shopping cart and Click on Go to cart
	@Test(priority=8)
	public void MouseOverOnShoppingCart() throws InterruptedException {
		
		WebElement shopping = driver.findElement(By.xpath("//span[@class='cart-qty']"));
		Actions action = new Actions(driver);
		action.moveToElement(shopping).build().perform();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@type='button'][@value='Go to cart']")).click();	
	}
	
	
	//8-Verify title contains cart
	@Test(priority=9)
	public void verifyTitleOfTheCart() {
		if(driver.getCurrentUrl().contains("/cart")) {
			System.out.println("The test case is passed ,the current url contains cart");
		}else {
			System.out.println("The test case is failed");
		}
	}
		
	//9-Verify Sub-total should be total amount of above added product
	
	@Test(priority=10)
	public void verifySubtotalAndTotalAmount() {
		String subtotal = driver.findElement(By.xpath("//span[contains(text(),'Sub-Total:')]//following::span[1]//span[1]")).getText();
		String total = driver.findElement(By.xpath("//span[contains(text(),'Total:')]//following::span[1]//span[1]")).getText();
		
		if(subtotal.trim().equals(total))
		{
			System.out.println("The subtoal price ("+subtotal+") and total ("+total+")  price are the same");
					
		}else {
			
			System.out.println("The subtotal amount is not same as total amount");
			}	
	 }
	
	
	//10- Click on the check out button
	@Test(priority=11)
	public void clickOnTheCheckOut() throws InterruptedException {
		driver.findElement(By.xpath("//button[@id='checkout']")).click();
		
	}
	
	//11-Verify pop up message 
	@Test(priority=12)
	public void verifyPopUpMessage() throws InterruptedException{
		Thread.sleep(3000);
	WebElement popupMessage = driver.findElement(By.xpath("//div[@class='ui-dialog ui-widget ui-widget-content ui-corner-all ui-front ui-draggable ui-resizable']//div[@id='terms-of-service-warning-box']//p"));
	System.out.println(driver.findElement(By.xpath("//p[contains(text(),'Please accept the terms of service before the next step.')]")).getText());
	//System.out.println(popupMessage.getText());
	if(!popupMessage.equals("Please accept the terms of service before the next step."))
	{
		System.out.println("The pop up message is correct");
	}
	else {
		System.out.println("the popup messsage is incorrect");
	}	
	
	//click on the close button of terms and conditions popup message
	WebElement closebutton = driver.findElement(By.xpath("//button//span[@class='ui-button-icon-primary ui-icon ui-icon-closethick']"));
	System.out.println(closebutton.getText());
	closebutton.click();
	
	//click on the check box of terms and conditions
	driver.findElement(By.xpath("//input[@id='termsofservice']")).click();
	
	//click on the checkout
	driver.findElement(By.xpath("//button[@id='checkout']")).click();
	
	}
	
	//16-Complete the order – Do not make the payment
	@Test(priority=13)
	public void completeOrder() throws InterruptedException {
		System.out.println(driver.getCurrentUrl());
		driver.findElement(By.xpath("//div[@id='billing-buttons-container']//input[@class='button-1 new-address-next-step-button']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@id='shipping-buttons-container']//input[@class='button-1 new-address-next-step-button']")).click();
	
		driver.findElement(By.xpath("//div[@id='shipping-method-buttons-container']//input[@class='button-1 shipping-method-next-step-button']")).click();
	
		driver.findElement(By.xpath("//input[@id='paymentmethod_1']")).click();
		
		driver.findElement(By.xpath("//div[@id='payment-method-buttons-container']//input[@class='button-1 payment-method-next-step-button']")).click();
		
		driver.findElement(By.xpath("//div[@id='payment-info-buttons-container']//input[@class='button-1 payment-info-next-step-button']")).click();
		
		driver.findElement(By.xpath("//div[@id='confirm-order-buttons-container']//input[@class='button-1 confirm-order-next-step-button']")).click();
		
		Thread.sleep(3000);
		System.out.println(driver.getCurrentUrl());
		driver.findElement(By.xpath("//li//a[contains(text(),'Click here for order details.')]")).click();
		System.out.println(driver.getCurrentUrl());
			
	}
	
		
		

}

	
	

	