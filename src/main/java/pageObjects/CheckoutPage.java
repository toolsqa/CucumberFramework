package pageObjects;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import selenium.Wait;
import testDataTypes.Customer;

public class CheckoutPage {
	WebDriver driver;
	
	public CheckoutPage(WebDriver driver) {
		this.driver = driver;
	    PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.CSS, using = "#billing_first_name") 
	private WebElement txtbx_FirstName;
	
	@FindBy(how = How.CSS, using = "#billing_last_name") 
	private WebElement txtbx_LastName;
	
	@FindBy(how = How.CSS, using = "#billing_email") 
	private WebElement txtbx_Email;
	
	@FindBy(how = How.CSS, using = "#billing_phone") 
	private WebElement txtbx_Phone;
	
	@FindBy(how = How.CSS, using = "#billing_country_field .select2-arrow") 
	private WebElement drpdwn_CountryDropDownArrow;
	
	@FindBy(how = How.CSS, using = "#billing_state_field .select2-arrow") 
	private WebElement drpdwn_CountyDropDownArrow;
	
	@FindAll(@FindBy(how = How.CSS, using = "#select2-drop ul li"))
	private List<WebElement> country_List;	
	
	@FindBy(how = How.CSS, using = "#billing_city") 
	private WebElement txtbx_City;
	
	@FindBy(how = How.CSS, using = "#billing_address_1") 
	private WebElement txtbx_Address;
	
	@FindBy(how = How.CSS, using = "#billing_postcode") 
	private WebElement txtbx_PostCode;
	
	@FindBy(how = How.CSS, using = "#ship-to-different-address-checkbox") 
	private WebElement chkbx_ShipToDifferetAddress;
	
	@FindAll(@FindBy(how = How.CSS, using = "ul.wc_payment_methods li"))
	private List<WebElement> paymentMethod_List;	
	
	@FindBy(how = How.CSS, using = "#terms.input-checkbox") 
	private WebElement chkbx_AcceptTermsAndCondition;
	
	@FindBy(how = How.CSS, using = "#place_order") 
	private WebElement btn_PlaceOrder;
	
	
	public void enter_Name(String name) {
		txtbx_FirstName.sendKeys(name);
	}
	
	public void enter_LastName(String lastName) {
		txtbx_LastName.sendKeys(lastName);
	}

	public void enter_Email(String email) {
		txtbx_Email.sendKeys(email);
	}
	
	public void enter_Phone(String phone) {
		txtbx_Phone.sendKeys(phone);
	}
	
	public void enter_City(String city) {
		txtbx_City.sendKeys(city);
	}
	
	public void enter_Address(String address) {
		txtbx_Address.sendKeys(address);
	}
	
	public void enter_PostCode(String postCode) {
		txtbx_PostCode.sendKeys(postCode);
	}
	
	public void check_ShipToDifferentAddress(boolean value) {
		if(!value) chkbx_ShipToDifferetAddress.click();
		Wait.untilJqueryIsDone(driver);
	}
	
	public void select_Country(String countryName) {
		drpdwn_CountryDropDownArrow.click();
		Wait.untilJqueryIsDone(driver);

		for(WebElement country : country_List){
			if(country.getText().equals(countryName)) {
				country.click();	
				Wait.untilJqueryIsDone(driver);
				break;
			}
		}

	}
	
	public void select_County(String countyName) {
		drpdwn_CountyDropDownArrow.click();
		Wait.untilJqueryIsDone(driver);
		for(WebElement county : country_List){
			if(county.getText().equals(countyName)) {
				county.click();	
				//Wait.untilJqueryIsDone(driver);
				break;
			}
		}
	}
	
	public void select_PaymentMethod(String paymentMethod) {
		if(paymentMethod.equals("CheckPayment")) {
			paymentMethod_List.get(0).click();
		}else if(paymentMethod.equals("Cash")) {
			paymentMethod_List.get(1).click();
		}else {
			new Exception("Payment Method not recognised : " + paymentMethod);
		}
		Wait.untilJqueryIsDone(driver);
		
	}
	
	public void check_TermsAndCondition(boolean value) {
		if(value) chkbx_AcceptTermsAndCondition.click();
	}
	
	public void clickOn_PlaceOrder() {
		btn_PlaceOrder.submit();
		Wait.untilJqueryIsDone(driver);
		Wait.untilPageLoadComplete(driver);
	}
	
	
	public void fill_PersonalDetails(Customer customer) {
		enter_Name(customer.firstName);
		enter_LastName(customer.lastName);
		enter_Phone(customer.phoneNumber.mob);
		enter_Email(customer.emailAddress);
		enter_City(customer.address.city);
		enter_Address(customer.address.streetAddress);
		enter_PostCode(customer.address.postCode);
		select_Country(customer.address.country);
		select_County(customer.address.county);		
	}

}
