package Academy;

import org.openqa.selenium.WebDriver;

import pageObjects.PaymentPageObjects;

public class Payment {
public WebDriver driver;
private PaymentPageObjects Ppo;
public Payment(WebDriver driver)
{
	this.driver=driver;
}
public void PaymentPageActions()
{
	Ppo=new PaymentPageObjects(driver);
	Ppo.Cash().click();
	Ppo.Payment().click();
	Ppo.Comments().sendKeys("test");
	Ppo.Proceed().click();
	
}
}
