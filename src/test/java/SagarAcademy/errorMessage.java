package SagarAcademy;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import basecomponent.BaseTest;
import login.pagefactory.Login;
import login.pagefactory.ProductCatalogs;

public class errorMessage extends BaseTest {

	@Test(dataProvider = "getdata")
	public void validationErrorMessage(String username, String password) throws Exception {

//		Login Login = LaunchApplication();
		ProductCatalogs ProductCatalogs = Login.LoginApplication(username, password);
		Assert.assertEquals("Incorrect email or password.", Login.LoginErrorMessage());
	}

	@DataProvider
	public Object[][] getdata() {
		Object[][] credentails = new Object[2][2];
		credentails[0][0] = "john@mailinator.com";
		credentails[0][1] = "Sagar@91";

		credentails[1][0] = "Sagar@mail.com";
		credentails[1][1] = "Sagar@91";
		return credentails;

	}

}
