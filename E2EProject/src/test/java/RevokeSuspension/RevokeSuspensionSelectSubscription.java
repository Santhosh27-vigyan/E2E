package RevokeSuspension;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;

import RevokeSupensionPgaeObjects.RevokeSuspensionSelectSubscriptionPageObjects;

public class RevokeSuspensionSelectSubscription {
	public WebDriver driver;
	private RevokeSuspensionSelectSubscriptionPageObjects Rssspo;

	public RevokeSuspensionSelectSubscription(WebDriver driver) {
		this.driver = driver;
	}

	public void selectSubscriptionPageActions() {
		Rssspo = new RevokeSuspensionSelectSubscriptionPageObjects(driver);
		assertTrue(Rssspo.CheckProductSelected().isDisplayed());
		Rssspo.ClickOnProceed().click();
	}
}
