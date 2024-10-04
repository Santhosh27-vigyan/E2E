package Academy;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;

import pageObjects.ProductConfigurationPageObjects;
import resources.db_check;


public class ProductConfiguration extends db_check{
	public WebDriver driver;
	public ProductConfigurationPageObjects Pcpo;
public ProductConfiguration(WebDriver driver)
{
	this.driver=driver;
}
public void ProductConfigurationTests() throws InterruptedException
{
	Pcpo=new ProductConfigurationPageObjects(driver);	
	Pcpo.Msisdn().sendKeys(msisdn());
	Pcpo.SearchIcon("MSISDN").click();
	Pcpo.SelectGivenMsisdn().click();
	System.out.println(Pcpo.IsReserved().getText());
	assertTrue(Pcpo.IsReserved().getText().contains("Reserved"));
	Pcpo.Sim().sendKeys(Sim());
	Pcpo.SearchIcon("SIM").click();
	Thread.sleep(1000);
	System.out.println(Pcpo.IsReserved().getText());
	assertTrue(Pcpo.IsReserved().getText().contains("Reserved"));
	Thread.sleep(500);
	Pcpo.Proceed().click();
}

}
