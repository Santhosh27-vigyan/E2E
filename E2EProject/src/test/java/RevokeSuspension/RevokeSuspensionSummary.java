package RevokeSuspension;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;

import RevokeSupensionPgaeObjects.RevokeSuspensionSummaryPageObjects;

public class RevokeSuspensionSummary {
	public WebDriver driver;
	private RevokeSuspensionSummaryPageObjects Rsspo;

	public RevokeSuspensionSummary(WebDriver driver) {
		this.driver = driver;
	}

	public void SummarypageActions() {
		Rsspo = new RevokeSuspensionSummaryPageObjects(driver);
		assertTrue(Rsspo.CheckOrderformDisplayed().isDisplayed());
		Rsspo.ClickOnProceed().click();
		;
	}
}
