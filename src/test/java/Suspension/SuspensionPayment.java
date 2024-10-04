package Suspension;

import org.openqa.selenium.WebDriver;

import SuspensionPageObjects.SuspensionPaymentPageObjects;
public class SuspensionPayment {
	public WebDriver driver;
	private SuspensionPaymentPageObjects Sppo;
	public SuspensionPayment(WebDriver driver)
	{
		this.driver=driver;
	}
	public void PaymentPageActions()
	{
		Sppo=new SuspensionPaymentPageObjects(driver);
		Sppo.Cash().click();
		Sppo.Payment().click();
		Sppo.Comments().sendKeys("test");
		Sppo.Proceed().click();
		
	}
}
