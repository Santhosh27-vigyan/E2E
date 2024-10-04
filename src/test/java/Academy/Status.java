package Academy;



import org.openqa.selenium.WebDriver;

import pageObjects.StatusPageObjects;

public class Status {
	public WebDriver driver;
	private StatusPageObjects Spo;
	public String CustStatus;
	public Status(WebDriver driver)
	{
		this.driver=driver;
	}
public String StatusCheck(String Type) throws Exception
{
	
	Spo=new StatusPageObjects(driver);
	CustStatus=Spo.checkStatus(Type);
	String CustomerID=Spo.GetCustomerID();
	Spo.GoBackToDashboard().click();
	return CustomerID;
}
}
