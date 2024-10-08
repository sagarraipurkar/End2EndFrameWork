package multiUseElementAbstract;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import login.pagefactory.Cart;
import login.pagefactory.Checkout;

public class MultiUseComponent {

	WebDriver driver;

	public MultiUseComponent(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}

	@FindBy(css = "[routerlink*='cart']")
	WebElement clickAddToCart;

	@FindBy(css = "[class='subtotal cf ng-star-inserted'] button")
	WebElement ClickOnCheckButton;

	public void visibilityofELement(By Findby) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(Findby));
	}
	
	public void visibilityofWebElement(WebElement Findby) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(Findby));
	}

	public void inVisibilityofELement(WebElement spinner) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOf(spinner));
	}

	public Cart clickOnCart() {
		clickAddToCart.click();
		Cart Cart = new Cart(driver);
		return Cart;
	}

	public Checkout ClickOnCheckout() {
		ClickOnCheckButton.click();
		Checkout Checkout = new Checkout(driver);
		return Checkout;
	}
}
