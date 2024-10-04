package RevokeSuspension;

import org.openqa.selenium.WebDriver;

import RevokeSupensionPgaeObjects.RevokeSuspensionStatusPageObjects;

public class RevokeSuspensionStatus {
	public WebDriver driver;
	private RevokeSuspensionStatusPageObjects Rsspo;
	public String CustStatus;

	public RevokeSuspensionStatus(WebDriver driver) {
		this.driver = driver;
	}

	public String StatusCheck(String Type) throws Exception {
		Rsspo = new RevokeSuspensionStatusPageObjects(driver);
		CustStatus = Rsspo.checkStatus(Type);
		String CustomerID = Rsspo.GetCustomerID();
		Rsspo.GoBackToDashboard().click();
		return CustomerID;
	}
}
