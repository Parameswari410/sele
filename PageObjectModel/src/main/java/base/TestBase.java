package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

public class TestBase {

	public static Properties prop;
	public static WebDriver driver;


	public static void loadProperty() {

		try {

			prop = new Properties();
			FileInputStream in = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/config/config.properties");
			prop.load(in);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	//BeforeSuite
	@BeforeSuite
	public static void initialization() {

		loadProperty();
		String browserName = prop.getProperty("browser");

		if(browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", prop.getProperty("chromedriverpath"));
			driver = new ChromeDriver();
		}

		if(browserName.equals("ie")) {
			System.setProperty("webdriver.chrome.driver", prop.getProperty("iedriverpath"));
			driver = new InternetExplorerDriver();
		}

	}

	//BeforeClass
	@BeforeClass
	public static void lunchBrowser() {

		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

	}

	@AfterMethod
	public static void take_Screensot_Of_Failed_Tescase(ITestResult result) {

		if(ITestResult.FAILURE ==result.getStatus()) {

			TakesScreenshot ts = (TakesScreenshot)driver;
			File sourse = ts.getScreenshotAs(OutputType.FILE);
			String target = System.getProperty("user.dir")+"/FailedTestsScreenshots/"+result.getName()+".png";
			File des = new File(target);
			try {
				FileHandler.copy(sourse, des);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@AfterSuite
	public static void tearDown() {
		driver.quit();

	}

}
