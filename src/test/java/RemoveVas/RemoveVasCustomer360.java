package RemoveVas;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;


import RemoveVasPageObjects.RemoveVasCustomer360PageObjects;

public class RemoveVasCustomer360 {
	public WebDriver driver;
	private RemoveVasCustomer360PageObjects C360PO;

	public RemoveVasCustomer360(WebDriver driver) {
		this.driver = driver;
	}

	public void Customer360PageActions(String Offer) {
		C360PO = new RemoveVasCustomer360PageObjects(driver);
		assertTrue(C360PO.CustomerStatus().getText().equalsIgnoreCase("Active"));
		C360PO.SelectProduct().click();
		C360PO.SelectVasTab().click();
		C360PO.SelectVasOption().click();
		assertTrue( C360PO.CheckVasIsPresent(Offer).isDisplayed());
		C360PO.ClickOnDelete().click();
		
	}
}
