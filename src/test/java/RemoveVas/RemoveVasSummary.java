package RemoveVas;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import RemoveVasPageObjects.RemoveVasSummaryPageObjects;

public class RemoveVasSummary {
	public WebDriver driver;
	private RemoveVasSummaryPageObjects Spo;

	public RemoveVasSummary(WebDriver driver) {
		this.driver = driver;
	}

	public void SummarypageActions() {
		Spo = new RemoveVasSummaryPageObjects(driver);
		assertTrue(Spo.CheckOrderformDisplayed().isDisplayed());
		Spo.ClickOnProceed().click();
		;
	}
}
