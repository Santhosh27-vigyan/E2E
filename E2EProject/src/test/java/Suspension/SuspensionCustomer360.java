package Suspension;
import static org.testng.Assert.assertTrue;
import org.openqa.selenium.WebDriver;
import SuspensionPageObjects.SuspensionCustomer360PageObjects;
public class SuspensionCustomer360 {
	public WebDriver driver;
	private SuspensionCustomer360PageObjects SC360PO;
	public SuspensionCustomer360(WebDriver driver) {
		this.driver = driver;
	}
	public void Customer360PageActions() {
		SC360PO = new SuspensionCustomer360PageObjects(driver);
		assertTrue(SC360PO.CustomerStatus().getText().equalsIgnoreCase("Active"));
		SC360PO.SelectProduct().click();
		SC360PO.MoreActions().click();
		SC360PO.SelectSuspensionOption().click();
	}
}
