package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	WebDriver driver;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void perform_Search(String search) {
		driver.navigate().to("http://shop.demoqa.com/?s=" + search + "&post_type=product");
	}
	
	public void navigateTo_HomePage() {
		driver.get("http://www.shop.demoqa.com");
	}

}