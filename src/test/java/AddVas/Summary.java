package AddVas;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;

import AddVasPageObjects.SummaryPageObjects;

public class Summary {
	public WebDriver driver;
	private SummaryPageObjects Spo;

	public Summary(WebDriver driver) {
		this.driver = driver;
	}

	public void SummarypageActions() {
		Spo = new SummaryPageObjects(driver);
		assertTrue(Spo.CheckOrderformDisplayed().isDisplayed());
		Spo.ClickOnProceed().click();
		;
	}
}
