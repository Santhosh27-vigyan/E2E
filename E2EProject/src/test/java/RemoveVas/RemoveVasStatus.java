package RemoveVas;
import org.openqa.selenium.WebDriver;
import RemoveVasPageObjects.RemoveVasStatusPageObjects;
public class RemoveVasStatus {
	public WebDriver driver;
	private RemoveVasStatusPageObjects Avspo;
	public String CustStatus;
	public RemoveVasStatus(WebDriver driver) {
		this.driver = driver;
	}
	public String StatusCheck(String Type) throws Exception {
		Avspo = new RemoveVasStatusPageObjects(driver);
		CustStatus = Avspo.checkStatus(Type);
		String CustomerID = Avspo.GetCustomerID();
		Avspo.GoBackToDashboard().click();
		return CustomerID;
	}
}
