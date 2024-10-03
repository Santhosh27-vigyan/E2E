package AddVas;
import org.openqa.selenium.WebDriver;
import AddVasPageObjects.AddVasStatusPageObjects;
public class AddVasStatus {
	public WebDriver driver;
	private AddVasStatusPageObjects Avspo;
	public String CustStatus;
	public AddVasStatus(WebDriver driver) {
		this.driver = driver;
	}
	public String StatusCheck(String Type) throws Exception {
		Avspo = new AddVasStatusPageObjects(driver);
		CustStatus = Avspo.checkStatus(Type);
		String CustomerID = Avspo.GetCustomerID();
		Avspo.GoBackToDashboard().click();
		return CustomerID;
	}
}
