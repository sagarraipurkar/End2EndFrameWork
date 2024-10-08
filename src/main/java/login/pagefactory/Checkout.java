package login.pagefactory;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import multiUseElementAbstract.MultiUseComponent;

public class Checkout extends MultiUseComponent {
	WebDriver driver;

	public Checkout(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	@FindBy(css = "div[class='field'] [class='input txt text-validated']")
	WebElement EnterCardNumber;

	@FindBy(css = "div[class='field small'] [class='input txt']")
	WebElement Cvv;
	@FindBy(css = "div[class='field'] [class='input txt']")
	WebElement CardName;

	@FindBy(xpath = "/html[1]/body[1]/app-root[1]/app-order[1]/section[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[3]/div[2]/div[2]/div[1]/input[1]")
	WebElement emailid;

	@FindBy(xpath = "/html/body/app-root/app-order/section/div/div/div[2]/div/div/div[3]/div[2]/div[2]/div/div[1]/div/input")
	WebElement ClickCountrydropdown;

	@FindBy(xpath = "/html/body/app-root/app-order/section/div/div/div[2]/div/div/div[3]/div[2]/div[2]/div/div[1]/div/input")
	WebElement EnterCountryName;

	@FindBy(xpath = "/html/body/app-root/app-order/section/div/div/div[2]/div/div/div[3]/div[2]/div[2]/div/div[1]/div/section/button")
	List<WebElement> CountryListIn;

	@FindBy(xpath = "(//a[normalize-space()='Place Order'])[1]")
	WebElement Placeorder;

	public void cardNumber(String number) {
		EnterCardNumber.clear();
		EnterCardNumber.sendKeys(number);
	}

	public void EnterCvv(String CVV) {
		Cvv.clear();
		Cvv.sendKeys(CVV);
	}

	public void EnterCardName(String Cardname) {
		CardName.clear();
		CardName.sendKeys(Cardname);
	}

	public void EnterEmail(String email) {
		emailid.clear();
		emailid.sendKeys(email);
	}

	public void ClickonDropdown() throws Exception {
		Thread.sleep(2000);
		ClickCountrydropdown.click();
	}

	public void EnterCountryDropdown(String CountryName) throws Exception {
		Thread.sleep(3000);
		EnterCountryName.sendKeys(CountryName);
	}

	public List<WebElement> getCountryList() {
		return CountryListIn;

	}

	public ConfimrationPage PlaceOrder() throws Exception {
		Thread.sleep(5000);
		Placeorder.click();
		ConfimrationPage ConfimrationPage = new ConfimrationPage(driver);
		return ConfimrationPage;

	}
}
