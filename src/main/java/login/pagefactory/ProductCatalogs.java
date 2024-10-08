package login.pagefactory;

import java.util.List;

import org.apache.hc.core5.util.Asserts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import multiUseElementAbstract.MultiUseComponent;

public class ProductCatalogs extends MultiUseComponent {
	WebDriver driver;

	public ProductCatalogs(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".col-lg-4")
	List<WebElement> products;

	@FindBy(css = ".ng-animating")
	WebElement text;

	By productlist = By.cssSelector(".col-lg-4");
	By AddtoCart = By.cssSelector(".card-body button:last-of-type");
	By waitloader = By.id("toast-container");

	public List<WebElement> getProductList() {
		visibilityofELement(productlist);
		return products;

	}

	public WebElement getProductName(String productname) {
		WebElement prod = getProductList().stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productname)).findFirst()
				.orElse(null);
		return prod;
	}

	public void addTocart(String productname) {
		WebElement prod = getProductName(productname);
		prod.findElement(AddtoCart).click();
		visibilityofELement(waitloader);
		inVisibilityofELement(text);

	}

}
