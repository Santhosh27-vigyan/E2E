package RemoveVas;
import static org.testng.Assert.assertTrue;
import org.openqa.selenium.WebDriver;
import RemoveVasPageObjects.RemoveVasSelectSubscriptionPageObjects;
public class RemoveVasSelectSubscription {
	public WebDriver driver;
	private RemoveVasSelectSubscriptionPageObjects Sspo;
	public RemoveVasSelectSubscription(WebDriver driver) {
		this.driver = driver;
	}
	public void selectSubscriptionPageActions() {
		Sspo = new RemoveVasSelectSubscriptionPageObjects(driver);
		assertTrue(Sspo.CheckProductSelected().isDisplayed());
		Sspo.ClickOnProceed().click();
	}
}
