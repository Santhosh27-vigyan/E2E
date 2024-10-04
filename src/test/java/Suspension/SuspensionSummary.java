package Suspension;

import static org.testng.Assert.assertTrue;
import org.openqa.selenium.WebDriver;
import SuspensionPageObjects.SuspensionSummaryPageObjects;

public class SuspensionSummary {
	public WebDriver driver;
	private SuspensionSummaryPageObjects Sspo;

	public SuspensionSummary(WebDriver driver) {
		this.driver = driver;
	}

	public void SummarypageActions() {
		Sspo = new SuspensionSummaryPageObjects(driver);
		assertTrue(Sspo.CheckOrderformDisplayed().isDisplayed());
		Sspo.ClickOnProceed().click();
		;
	}
}
