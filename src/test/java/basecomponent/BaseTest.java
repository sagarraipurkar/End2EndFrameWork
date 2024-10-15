package basecomponent;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	public Login LaunchApplication() throws Exception {
		driver = Initilizaton();
		Login = new Login(driver);
		Login.ApplicationUrl();
		return Login;
	}

	@AfterMethod
	public void teardown() {
		driver.close();
	}

	public void getScreenshot(String methodname) throws IOException {
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File source = screenshot.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(source, new File("C:\\Eclipse New\\Screenshot\\screenshot4.png"));
	}

	public List<HashMap<String, String>> getdata_Reader(String filePath) throws IOException {
		String jsoncontent = FileUtils.readFileToString(
				new File(filePath),
				StandardCharsets.UTF_8);

		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsoncontent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;
	}
}
