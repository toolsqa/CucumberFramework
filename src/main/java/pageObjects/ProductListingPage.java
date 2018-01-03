package pageObjects;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class ProductListingPage {
	
	public ProductListingPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.CSS, using = "button.single_add_to_cart_button") 
	private WebElement btn_AddToCart;
	
	@FindAll(@FindBy(how = How.CSS, using = ".noo-product-inner"))
	private List<WebElement> prd_List;	
	
	public void clickOn_AddToCart() {
		btn_AddToCart.click();
	}
	
	public void select_Product(int productNumber) {
		prd_List.get(productNumber).click();
	}

}
