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
import org.testng.annotations.Parameters;
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
//	String productname = "ADIDAS ORIGINAL";

	@Parameters({ "UserName", "Password", "productname" })
	@org.testng.annotations.Test
	public void submitOrder(String Username, String Password, String product) throws Exception {
		// TODO Auto-generated method stub
		ProductCatalogs ProductCatalogs = Login.LoginApplication(Username, Password);
		List<WebElement> products = ProductCatalogs.getProductList();
		ProductCatalogs.addTocart(product);
		Cart Cart = ProductCatalogs.clickOnCart();
		Cart.verifyProductNameinCart(product);
		Boolean match = Cart.verifyProductNameinCart(product);
		Assert.assertTrue(match);
		Checkout Checkout = Cart.ClickOnCheckout();
		Checkout.EnterEmail(Username);
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

	@Parameters({ "UserName", "Password", "product"  })
	@org.testng.annotations.Test(dependsOnMethods = { "submitOrder" })
	public void Myorder(String Username, String Password, String product) {
		ProductCatalogs ProductCatalogs = Login.LoginApplication(Username, Password);
		MyOrderPage productOrder = ProductCatalogs.ClickonmyOrder();
		Assert.assertTrue(productOrder.verifyOrderProduct(product));

	}
}
