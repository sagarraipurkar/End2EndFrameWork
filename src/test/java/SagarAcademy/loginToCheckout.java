package SagarAcademy;

import java.time.Duration;
import java.util.List;

import org.apache.hc.core5.util.Asserts;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
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

//	@Parameters({ "UserName", "Password", "productname" })
	@org.testng.annotations.Test (dataProvider = "getData")
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
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,250)", "");
		Checkout.PlaceOrder();
		ConfimrationPage ConfimrationPage = new ConfimrationPage(driver);
		String SuccessText = ConfimrationPage.GetConfirmationMessage();
		Assert.assertTrue(SuccessText.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}

//	@Parameters({ "UserName", "Password", "product" })
//	@org.testng.annotations.Test(dependsOnMethods = { "submitOrder" })
//	public void Myorder(String Username, String Password, String product) {
//		ProductCatalogs ProductCatalogs = Login.LoginApplication(Username, Password);
//		MyOrderPage productOrder = ProductCatalogs.ClickonmyOrder();
//		Assert.assertTrue(productOrder.verifyOrderProduct(product));
//	}

	@DataProvider
	public Object[][] getData() {
		Object[][] credentails = new Object[2][3];
		credentails[0][0] = "John@mailinator.com";
		credentails[0][1] = "Sagar@91";
		credentails[0][2] = "ADIDAS ORIGINAL";

		credentails[1][0] = "dummy@mailinator.com";
		credentails[1][1] = "Dummy@123";
		credentails[1][2] = "ZARA COAT 3";
		return credentails;

	}
}
