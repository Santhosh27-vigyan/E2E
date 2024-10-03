package AddVas;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;

import AddVasPageObjects.ProductConfigurationPageObjects;

public class ProductConfiguration {
	public WebDriver driver;
	private ProductConfigurationPageObjects Pcpo;
	public ProductConfiguration(WebDriver driver) {
		this.driver = driver;
	}

	public void ProductConfigurationpageActions(String VasName) {
		Pcpo=new ProductConfigurationPageObjects(driver,VasName);
		assertTrue(Pcpo.CheckHistoryDisplayed().isDisplayed());
		Pcpo.ClickOnProceed().click();
		
	}

}
