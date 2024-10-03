package Academy;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pageObjects.ProductSelectionPageObjects;

public class ProductSelection {
	public WebDriver driver;
	public ProductSelectionPageObjects Pspo;

	public ProductSelection(WebDriver driver) {
		this.driver = driver;
	}

	public void ProductSelectionActions(String Type, String Productname) throws InterruptedException {
		Pspo = new ProductSelectionPageObjects(driver);
		Pspo.SelectTypeDropDown().click();
		Pspo.SelectType(Type).click();
		Pspo.Search(Productname).click();
		Pspo.AddProductToTheCart(Productname).click();
		Assert.assertTrue(Pspo.ProductAddedConfirmation().isDisplayed());
		Pspo.Proceed().click();
	}
}
