package Academy;

import java.io.FileInputStream;
import java.io.IOException;

import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import pageObjects.LoginPageObjects;


public class LoginPage  {
	public WebDriver driver;
	private static Logger log=LogManager.getLogger(DashboardPage.class.getName());
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public void basePageNavigation() throws IOException, InterruptedException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir")+"\\src\\main\\java\\resources\\data.properties");
		prop.load(fis);
		String username = prop.getProperty("username");
		String password = prop.getProperty("password");
		String url=prop.getProperty("url");
		driver.get(url);
		driver.manage().window().maximize();
		LoginPageObjects LPO = new LoginPageObjects(driver);
		LPO.cert();
		log.info("certification byPassed");
		LPO.username().sendKeys(username);
		log.info("username sent");
		LPO.password().sendKeys(password);
		log.info("password sent");
		LPO.submit().click();

	}

}
