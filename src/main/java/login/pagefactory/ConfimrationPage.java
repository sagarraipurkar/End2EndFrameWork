package login.pagefactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import multiUseElementAbstract.MultiUseComponent;

public class ConfimrationPage extends MultiUseComponent  {

	WebDriver driver;
	public ConfimrationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	@FindBy(css =".hero-primary")
	WebElement confirmMessage ;
	
	
	public String GetConfirmationMessage() {
	return confirmMessage.getText();
	}
}
