package login.pagefactory;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.github.bonigarcia.wdm.WebDriverManager;
import multiUseElementAbstract.MultiUseComponent;

public class Login extends MultiUseComponent {

	WebDriver driver;

	public Login(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "userEmail")
	WebElement username;

	@FindBy(id = "userPassword")
	WebElement password;

	@FindBy(id = "login")
	WebElement login;
	
@FindBy(xpath = "/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]")
WebElement errorMessage;

	public ProductCatalogs LoginApplication(String UserName, String Password) {
		username.sendKeys(UserName);
		password.sendKeys(Password);
		login.click();
		ProductCatalogs ProductCatalogs = new ProductCatalogs(driver);
		return ProductCatalogs;
	}
	
	public String LoginErrorMessage() {
		visibilityofWebElement(errorMessage);
		return errorMessage.getText();
	}

	public void ApplicationUrl() {

		driver.get("https://rahulshettyacademy.com/client/");
	}
}
