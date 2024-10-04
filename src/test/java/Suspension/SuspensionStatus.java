package Suspension;

import org.openqa.selenium.WebDriver;

import SuspensionPageObjects.SuspensionStatusPageObjects;

public class SuspensionStatus {
	public WebDriver driver;
	private SuspensionStatusPageObjects Sspo;
	public String CustStatus;

	public SuspensionStatus(WebDriver driver) {
		this.driver = driver;
	}

	public String StatusCheck(String Type) throws Exception {
		Sspo = new SuspensionStatusPageObjects(driver);
		CustStatus = Sspo.checkStatus(Type);
		String CustomerID = Sspo.GetCustomerID();
		Sspo.GoBackToDashboard().click();
		return CustomerID;
	}
}
