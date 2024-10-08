package login.pagefactory;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import multiUseElementAbstract.MultiUseComponent;

public class Cart extends MultiUseComponent {

	WebDriver driver;

	public Cart(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	@FindBy(css = "[class='cartSection'] h3")
	List<WebElement> AddToCartProduct;

	public Boolean verifyProductNameinCart(String productname) {
		Boolean match = AddToCartProduct.stream()
				.anyMatch(cartproducts -> cartproducts.getText().equalsIgnoreCase(productname));
		return match;
	}

}
