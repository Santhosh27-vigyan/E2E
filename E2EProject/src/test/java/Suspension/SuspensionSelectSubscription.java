package Suspension;
import static org.testng.Assert.assertTrue;
import org.openqa.selenium.WebDriver;
import SuspensionPageObjects.SuspensionSelectSubscriptionPageObjects;
public class SuspensionSelectSubscription {
	public WebDriver driver;
	private SuspensionSelectSubscriptionPageObjects Ssspo;
	public SuspensionSelectSubscription(WebDriver driver) {
		this.driver = driver;
	}
	public void selectSubscriptionPageActions() {
		Ssspo = new SuspensionSelectSubscriptionPageObjects(driver);
		assertTrue(Ssspo.CheckProductSelected().isDisplayed());
		Ssspo.ClickOnProceed().click();
	}
}
