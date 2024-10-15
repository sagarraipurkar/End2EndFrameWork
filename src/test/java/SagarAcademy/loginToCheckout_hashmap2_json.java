package SagarAcademy;

import java.io.IOException;
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
import data_json.getData_json;
import io.github.bonigarcia.wdm.WebDriverManager;
import login.pagefactory.Cart;
import login.pagefactory.Checkout;
import login.pagefactory.ConfimrationPage;
import login.pagefactory.Login;
import login.pagefactory.MyOrderPage;
import login.pagefactory.ProductCatalogs;
import multiUseElementAbstract.MultiUseComponent;

public class loginToCheckout_hashmap2_json extends BaseTest {

	@org.testng.annotations.Test(dataProvider = "getData")
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

	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = getdata_Reader(
				"C:\\Eclipse New\\NewWorkSpace\\End2EndFramework\\src\\test\\java\\data_json\\purchaseorder_json_credentials");

		return new Object[][] { { data.get(0) }, { data.get(1) } };

	}
}
