package Helper;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Utility {

	// Select the date from the calender
	public static void verifySelectDateFromTheCalender(WebDriver driver, String xpath, String value) {

		List<WebElement> alldates = driver.findElements(By.xpath(xpath));
		for (WebElement date : alldates) {
			if (date.getText().contains(value)) {
				System.out.println("The date selected from the calender");
				System.out.println("Log -info : Element found :" +date.getText());
				date.click();
				break;
			}
		}
	}
	
	
	public static void verifySelectValueFromDropDown(WebDriver driver, String xpath, String value) {

		List<WebElement> allSugestion = driver.findElements(By.xpath(xpath));

		for (WebElement element : allSugestion) {

			if (element.getText().contains(value)) {
				System.out.println("LOG-INFO Elemenet found");
				element.click();
				System.out.println(element.getText());
				break;
			}

		}

	}
	public static void verifyTheBrokenLinkImage(String url) throws MalformedURLException, IOException {

		HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
		conn.connect();
		int code = conn.getResponseCode();
		String responseMessage = conn.getResponseMessage();
		System.out.println("checking url: " + url);
		System.out.println("code from service is :" + code);
		System.out.println("Response from server is  : " + responseMessage);

		if (code == 200 || code == 301 || code == 302) {
			System.out.println("the link is working fine");
		} else {
			System.out.println("Broken link " + url);
		}
	}
}
