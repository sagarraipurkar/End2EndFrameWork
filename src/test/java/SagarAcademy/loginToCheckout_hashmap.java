package SagarAcademy;

import java.time.Duration;
import java.util.HashMap;
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

public class loginToCheckout_hashmap extends BaseTest {

	@org.testng.annotations.Test (dataProvider = "getData")
	public void submitOrder(HashMap<String, String> input) throws Exception {
		// TODO Auto-generated method stub
		ProductCatalogs ProductCatalogs = Login.LoginApplication(input.get("email"), input.get("password"));
		List<WebElement> products = ProductCatalogs.getProductList();
		ProductCatalogs.addTocart(input.get("product"));
		Cart Cart = ProductCatalogs.clickOnCart();
		Cart.verifyProductNameinCart(input.get("product"));
		Boolean match = Cart.verifyProductNameinCart(input.get("product"));
		Assert.assertTrue(match);
		Checkout Checkout = Cart.ClickOnCheckout();
		Checkout.EnterEmail(input.get("email"));
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
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("email", "John@mailinator.com");
		map.put("password", "Sagar@91");
		map.put("product", "ADIDAS ORIGINAL");

		HashMap<String, String> map1 = new HashMap<String, String>();
		map1.put("email", "dummy@mailinator.com");
		map1.put("password", "Dummy@123");
		map1.put("product", "ZARA COAT 3");

		return new Object[][] { { map }, { map1 } };

	}
}
