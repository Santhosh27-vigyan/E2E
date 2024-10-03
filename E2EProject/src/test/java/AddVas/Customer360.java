package AddVas;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;

import AddVasPageObjects.Customer360PageObjects;

public class Customer360 {
	public WebDriver driver;
	private Customer360PageObjects C360PO;

	public Customer360(WebDriver driver) {
		this.driver = driver;
	}

	public void Customer360PageActions() {
		C360PO = new Customer360PageObjects(driver);
		assertTrue(C360PO.CustomerStatus().getText().equalsIgnoreCase("Active"));
		C360PO.SelectProduct().click();
		C360PO.SelectVasTab().click();
		C360PO.SelectVasOption().click();
		C360PO.AddNewVas();
		C360PO.SelectRegistrationType().click();
		C360PO.ClickOnProceed().click();
	}
}
