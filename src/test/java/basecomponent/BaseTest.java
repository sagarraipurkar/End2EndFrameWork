package basecomponent;

import java.io.FileInputStream;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import login.pagefactory.Login;

public class BaseTest {

	public static WebDriver driver;
	public static Login Login;

	public static WebDriver Initilizaton() throws Exception {

		Properties prop = new Properties();
		FileInputStream file = new FileInputStream(
				"C:\\Eclipse New\\NewWorkSpace\\End2EndFramework\\src\\main\\java\\projectResources\\global.properties");
		prop.load(file);
		String browser = prop.getProperty("browser");

		String UserName = prop.getProperty("username");
//	
		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();

		} else if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();

		} else if (browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		return driver;
	}

	@BeforeMethod
	public static Login LaunchApplication() throws Exception {
		driver = Initilizaton();
		Login = new Login(driver);
		Login.ApplicationUrl();
		return Login;
	}

	@AfterMethod
	public static void teardown() {
		driver.close();
	}
}
