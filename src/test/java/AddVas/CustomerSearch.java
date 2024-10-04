package AddVas;

import org.openqa.selenium.WebDriver;

import AddVasPageObjects.CustomerSearchPageObjects;

public class CustomerSearch {
public WebDriver driver;
private CustomerSearchPageObjects Cspo;
public CustomerSearch(WebDriver driver)
{
	this.driver=driver;
}
public void CustomerSearchTests(String CustomerID)
{
	
	Cspo=new CustomerSearchPageObjects(driver);
	Cspo.Search().sendKeys(CustomerID);
	Cspo.SelectCustomer(CustomerID).click();
	
}
}
