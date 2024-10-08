package login.pagefactory;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import multiUseElementAbstract.MultiUseComponent;

public class MyOrderPage extends MultiUseComponent {

	WebDriver driver;

	public MyOrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "tr td:nth-child(3)")
	List<WebElement> Products;

	public Boolean verifyOrderProduct(String productname) {
		Boolean match = Products.stream()
				.anyMatch(cartproducts -> cartproducts.getText().equalsIgnoreCase(productname));
		return match;
	}

}
