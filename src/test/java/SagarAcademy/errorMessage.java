package SagarAcademy;

import org.testng.Assert;
import org.testng.annotations.Test;

import basecomponent.BaseTest;
import login.pagefactory.Login;
import login.pagefactory.ProductCatalogs;

public class errorMessage extends BaseTest {

	@Test
	public void validationErrorMessage() throws Exception {

//		Login Login = LaunchApplication();
		ProductCatalogs ProductCatalogs = Login.LoginApplication("Sagar@mail.com", "Sagar@91");
		Assert.assertEquals("Incorrect email or password.", Login.LoginErrorMessage());
	}

}
