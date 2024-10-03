package RevokeSuspension;

import org.openqa.selenium.WebDriver;

import RevokeSupensionPgaeObjects.RevokeSuspensionCustomerSearchPageObjects;


public class RevokeSuspensionCustomerSearch {
	public WebDriver driver;
	private RevokeSuspensionCustomerSearchPageObjects  RScspo;

	public RevokeSuspensionCustomerSearch(WebDriver driver) {
		this.driver = driver;
	}

	public void CustomerSearchTests(String CustomerID) {

		RScspo = new RevokeSuspensionCustomerSearchPageObjects(driver);
		RScspo.Search().sendKeys(CustomerID);
		RScspo.SelectCustomer(CustomerID).click();

	}
}
