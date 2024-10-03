package resources;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
public class base{
	public static WebDriver driver;
	public String Xml1="All.xml";
	public String Xml2="OnlyAddVas.xml";
	public String Xml3="AddVasAndRemoveVas.xml";
	public String Xml4="OnlyremoveVas.xml";
	public String Xml5="OnlySuspension.xml";
	public String Xml6="SuspensionAndRevoke.xml";
	public String Xml7="OnlyRevokeSuspension.xml";
	public String Xml8="PrepaidAndPostpaidAllScenarios.xml";
	
	public  WebDriver initializeDriver() throws IOException {
		Properties prop = new Properties();
		System.out.println(System.getProperty("user.dir"));
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir")+"\\src\\main\\java\\\\resources\\data.properties");
		prop.load(fis);
		//String browserName = prop.getProperty("browser");
		String browserName = System.getProperty("browser");
		System.out.println(browserName);
		String type = prop.getProperty("type");
		if (browserName.contains("chrome")) {
			DesiredCapabilities ch = new DesiredCapabilities();
			if (type.equalsIgnoreCase("remote")) {
				ch.setBrowserName("chrome");
				ch.setPlatform(Platform.WINDOWS);
				driver = new RemoteWebDriver(ch);
			}
			if (type.equalsIgnoreCase("local")) {
//	ch.acceptInsecureCerts();
//	ch.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
//	ch.setCapability("applicationCacheEnabled", false);
				ChromeOptions c = new ChromeOptions();
//	c.merge(ch);
				c.addArguments("--ignore-certificate-errors");
				c.addArguments("incognito");
				if(browserName.contains("headless"))
					c.addArguments("headless");
				driver = new ChromeDriver(c);
			}
			return driver;
		} else if (browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("ie")) {
			driver = new InternetExplorerDriver();
		}
	//	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
	
	public void getScreenshot(String methodName,String Type) throws IOException {
	
		File scr = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//		if(!System.getProperty("xml").equalsIgnoreCase("PrepaidAndPostaidAllScenarios.xml"))
//		FileUtils.copyFile(scr, new File(System.getProperty("user.dir")+"\\screenshots\\" +System.getProperty("type")+"\\"+ methodName + ".png"));
//		else
			FileUtils.copyFile(scr, new File(System.getProperty("user.dir")+"\\screenshots\\" +Type+"\\"+ methodName + ".png"));
			
	}
}
