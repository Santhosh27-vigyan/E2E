package RemoveVas;

import org.openqa.selenium.WebDriver;


import RemoveVasPageObjects.RemoveVasCustomerSearchPageObjects;

public class RemoveVasCustomerSearch {
	public WebDriver driver;
	private RemoveVasCustomerSearchPageObjects Rvcspo;
	public RemoveVasCustomerSearch(WebDriver driver)
	{
		this.driver=driver;
	}
	public void CustomerSearchTests(String CustomerID)
	{
		
		Rvcspo=new RemoveVasCustomerSearchPageObjects(driver);
		Rvcspo.Search().sendKeys(CustomerID);
		Rvcspo.SelectCustomer(CustomerID).click();
		
	}
}
