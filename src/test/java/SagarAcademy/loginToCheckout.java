package SagarAcademy;

import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.Assertion;

import basecomponent.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import login.pagefactory.Cart;
import login.pagefactory.Checkout;
import login.pagefactory.ConfimrationPage;
import login.pagefactory.Login;
import login.pagefactory.MyOrderPage;
import login.pagefactory.ProductCatalogs;
import multiUseElementAbstract.MultiUseComponent;

public class loginToCheckout extends BaseTest {
	String UserEmail = "John@mailinator.com";
	String Password = "Sagar@91";
	String productname = "ADIDAS ORIGINAL";

	@org.testng.annotations.Test
	public void submitOrder() throws Exception {
		// TODO Auto-generated method stub
		ProductCatalogs ProductCatalogs = Login.LoginApplication(UserEmail, Password);
		List<WebElement> products = ProductCatalogs.getProductList();
		ProductCatalogs.addTocart(productname);
		Cart Cart = ProductCatalogs.clickOnCart();
		Cart.verifyProductNameinCart(productname);
		Boolean match = Cart.verifyProductNameinCart(productname);
		Assert.assertTrue(match);
		Checkout Checkout = Cart.ClickOnCheckout();
		Checkout.EnterEmail(UserEmail);
		Checkout.ClickonDropdown();
		Checkout.EnterCountryDropdown("Ind");
		List<WebElement> CountryList = Checkout.getCountryList();
		for (int i = 0; i < CountryList.size(); i++) {
			String country = CountryList.get(i).getText();
			if (country.equals("India")) {
				CountryList.get(i).click();
				break;
			}
		}
		ConfimrationPage ConfimrationPage = Checkout.PlaceOrder();
		ConfimrationPage.GetConfirmationMessage();
		Assert.assertTrue(ConfimrationPage.equals("Thankyou for the order."));

	}

	@org.testng.annotations.Test(dependsOnMethods = { "submitOrder" })
	public void Myorder() {
		ProductCatalogs ProductCatalogs = Login.LoginApplication(UserEmail, Password);
		MyOrderPage productOrder = ProductCatalogs.ClickonmyOrder();
		Assert.assertTrue(productOrder.verifyOrderProduct(productname));

	}
}
