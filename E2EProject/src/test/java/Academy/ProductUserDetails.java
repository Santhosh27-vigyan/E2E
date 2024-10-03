package Academy;

import org.openqa.selenium.WebDriver;

import pageObjects.ProductUserDetailsPageObjects;


public class ProductUserDetails {
	public WebDriver driver;
	public ProductUserDetailsPageObjects Pupo;
	public ProductUserDetails(WebDriver driver)
	{
		this.driver=driver;
	}
	public void ProductUserDetailsActions()
	{
		Pupo=new ProductUserDetailsPageObjects(driver);
		Pupo.Proceed().click();
	}

}
