package Suspension;

import org.openqa.selenium.WebDriver;

import SuspensionPageObjects.SuspensionCustomerSearchPageObjects;

public class SuspensionCustomerSearch {
	public WebDriver driver;
	private SuspensionCustomerSearchPageObjects Scspo;

	public SuspensionCustomerSearch(WebDriver driver) {
		this.driver = driver;
	}

	public void CustomerSearchTests(String CustomerID) {

		Scspo = new SuspensionCustomerSearchPageObjects(driver);
		Scspo.Search().sendKeys(CustomerID);
		Scspo.SelectCustomer(CustomerID).click();

	}
}
