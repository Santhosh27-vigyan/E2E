package AddVas;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;

import AddVasPageObjects.SelectSubscriptionPageObjects;


public class SelectSubscription {
	public WebDriver driver;
	private SelectSubscriptionPageObjects Sspo;
public SelectSubscription(WebDriver driver)
{
	this.driver=driver;
}
public void selectSubscriptionPageActions()
{
	Sspo=new SelectSubscriptionPageObjects(driver);
	assertTrue(Sspo.CheckProductSelected().isDisplayed());
	Sspo.ClickOnProceed().click();
}
}
