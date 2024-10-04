package Academy;

import org.openqa.selenium.WebDriver;

import pageObjects.SummayPageObjects;



public class Summary {
	public WebDriver driver;
	public SummayPageObjects Spo;
	public Summary(WebDriver driver)
	{
		this.driver=driver;
	}
	public void SummaryActions() throws InterruptedException
	{
		Spo=new SummayPageObjects(driver);
		Spo.Proceed().click();
	}
}
