package RevokeSuspension;

import static org.testng.Assert.assertTrue;
import org.openqa.selenium.WebDriver;

import RevokeSupensionPgaeObjects.RevokeSuspensionCustomer360PageObjects;

public class RevokeSuspensionCustomer360 {
	public WebDriver driver;
	private RevokeSuspensionCustomer360PageObjects RSC360PO;

	public RevokeSuspensionCustomer360(WebDriver driver) {
		this.driver = driver;
	}

	public void Customer360PageActions() {
		RSC360PO = new RevokeSuspensionCustomer360PageObjects(driver);
		assertTrue(RSC360PO.CustomerStatus().getText().equalsIgnoreCase("Active"));
		RSC360PO.SelectProduct().click();
//		Actions a=new Actions(driver);
//		a.moveToElement(RSC360PO.MoreActions()).build().perform();
		assertTrue(RSC360PO.Status.getText().equalsIgnoreCase("Suspended"));
		RSC360PO.MoreActions().click();
		RSC360PO.SelectSuspensionOption().click();
	}
}
