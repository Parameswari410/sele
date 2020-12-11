package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.TestBase;

public class LoginPage extends TestBase{

	@FindBy(xpath="//img[@src=\"/webres_5faa8393a82311.28834036/themes/default/images/login/logo.png\"]")
	public static WebElement logo;

	@FindBy(xpath="//input[@name='txtUsername']")
	public static WebElement Username;

	@FindBy(id="txtPassword")
	public static WebElement Password;

	@FindBy(id="btnLogin")
	public static WebElement loginButton;

	@FindBy(id="welcome")
	public static WebElement homepage;

	public LoginPage() {
		PageFactory.initElements(driver, this);
	}

	public boolean validateLogo() {
		return logo.isDisplayed();
	}

	public String validateLoginPageTitle() {
		return driver.getTitle();
	}

	public HomePage Login(String un, String pwd) {

		Username.sendKeys(un);
		Password.sendKeys(pwd);
		loginButton.click();
		return new HomePage();
	}

	public boolean validateHomePage() {
		if(homepage.getText().contains("Welcome")) {
			return true;
		}
		else {
			return false;
		}
	}
}
