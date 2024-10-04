package AddVas;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;

import AddVasPageObjects.SelectVasPlanAndTypePageObjects;

public class SelectVasPlanAndType {
	public WebDriver driver;
	private SelectVasPlanAndTypePageObjects Svpatpo;
	public SelectVasPlanAndType(WebDriver driver)
	{
		this.driver=driver;
	}
public void SelectVasPlanAndTypePageActions(String VasName)
{
	Svpatpo=new SelectVasPlanAndTypePageObjects(driver);
	assertTrue( Svpatpo.BalnceDispled().isDisplayed());
	Svpatpo.ProductNameToSearch().sendKeys(VasName);
	Svpatpo.ClickOnSearchIcon().click();
	Svpatpo.AddProductToCart().click();
	System.out.println(Svpatpo.ProductAddedCheck().getText());
	assertTrue(Svpatpo.ProductAddedCheck().getText().contains("added to the cart"));
	Svpatpo.ClickOnProceed().click();
}
}
