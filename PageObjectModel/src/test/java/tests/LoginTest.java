package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.TestBase;
import pages.HomePage;
import pages.LoginPage;

public class LoginTest extends TestBase {

	public static LoginPage loginpage;
	public static HomePage homepage;

	@BeforeMethod
	public static void setup() {
		loginpage = new LoginPage();	
	}

	@Test(priority=0)
	public static void titleTest() {
		String title = loginpage.validateLoginPageTitle();
		Assert.assertEquals(title, "OrangeHRM");
	}

	@Test(priority=1)
	public static void logoTest() {
		boolean logo = loginpage.validateLogo();
		Assert.assertEquals(logo, true);
	}

	@Test(priority=2)
	public static void login() {
		homepage = loginpage.Login(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertTrue(loginpage.validateHomePage());
	}
}
