package finalCall;

import java.io.IOException;
import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Academy.LoginPage;
import RemoveVas.RemoveVasCustomer360;
import RemoveVas.RemoveVasCustomerSearch;
import RemoveVas.RemoveVasSelectSubscription;
import RemoveVas.RemoveVasStatus;
import RemoveVas.RemoveVasSummary;
import resources.Excel_Utils;
import resources.base;

public class RemoveVasFinalCalls extends base {
	public WebDriver driver;
	private LoginPage loginPage;
	private RemoveVasCustomerSearch RemoveVasCustomerSearch;
	private RemoveVasCustomer360 C360;
	private RemoveVasSelectSubscription SelectSub;
	private RemoveVasSummary summary;
	private RemoveVasStatus Status;
	public String CustomerID;
	public String CustomerStatus;
	public String VasName;
	public static String type;
	
	@BeforeClass
	public void DriverIntialization(ITestContext context) throws Exception {
		
		if (System.getProperty("xml").equalsIgnoreCase(Xml1) || System.getProperty("xml").equalsIgnoreCase(Xml3) || System.getProperty("xml").equalsIgnoreCase(Xml8)) {
			Boolean skipTests = (Boolean) context.getAttribute("SkippedTests");
			Boolean failTests = (Boolean) context.getAttribute("FailedTests");
			if (skipTests != null && skipTests || failTests != null && failTests) {
				throw new SkipException(
						"Skipping tests in RemoveVasFinalCalls because method in AddVas final call was skipped or failed.");
			}
			this.driver = (WebDriver) context.getSuite().getAttribute("WebDriver");
			if (driver == null) {
				throw new RuntimeException("WebDriver is not initialized.");
			}
		}
		else if(System.getProperty("xml").equalsIgnoreCase(Xml4))
			driver = initializeDriver();
		else {
			throw new SkipException("Skipping DriverIntialization() method.");
		}
	}
	@Parameters("type")
	@Test(priority = 1)
	public void login(String Type) throws IOException, InterruptedException {
		RemoveVasFinalCalls.type=Type;
		if (!(System.getProperty("xml").equalsIgnoreCase(Xml1) || System.getProperty("xml").equalsIgnoreCase(Xml3)) && System.getProperty("xml").equalsIgnoreCase(Xml4) ) {
			
			loginPage = new LoginPage(driver);
			loginPage.basePageNavigation();
		}
	}

	@Test(priority = 2, alwaysRun = false, dataProvider = "dataProvider", dependsOnMethods = "login")
	public void RemoveVasSearchCustomer(String SearchCustomerIdToAddVas,String SearchCustomerIdToRemoveVas,String SearchCustomerIdToSuspension,String SearchCustomerIdToRecokeSuspension,String SearchCustomerIdToAddVasPostPaid,String SearchCustomerIdToRemoveVasPostapid,String SearchCustomerIdToSuspensionPostpaid,String SearchCustomerIdToRecokeSuspensionPostapid) {

		//System.out.println(SearchCustomerIdToRemoveVas);
		RemoveVasCustomerSearch = new RemoveVasCustomerSearch(driver);
		if (System.getProperty("xml").equalsIgnoreCase(Xml1) || System.getProperty("xml").equalsIgnoreCase(Xml8) )
			RemoveVasCustomerSearch.CustomerSearchTests(onboardingFinalCalls.CustomerID);
		if(	System.getProperty("xml").equalsIgnoreCase(Xml3))
			RemoveVasCustomerSearch.CustomerSearchTests(AddVasFinalCalls.CustomerID);
		if(	System.getProperty("xml").equalsIgnoreCase(Xml4))
		{
			if(type.equalsIgnoreCase("prepaid"))
				RemoveVasCustomerSearch.CustomerSearchTests(SearchCustomerIdToRemoveVas);
			if(type.equalsIgnoreCase("Postpaid"))
				RemoveVasCustomerSearch.CustomerSearchTests(SearchCustomerIdToRemoveVasPostapid);
		}
			
	}

	@Test(priority = 3, alwaysRun = false, dependsOnMethods = "RemoveVasSearchCustomer", dataProvider = "dataProvider")
	public void RemoveVasCustomer360View(String Postpaid, String Prepaid) {
		C360 = new RemoveVasCustomer360(driver);
		if (type.equalsIgnoreCase("Prepaid"))
			C360.Customer360PageActions(Prepaid);
		if (type.equalsIgnoreCase("postpaid"))
			C360.Customer360PageActions(Postpaid);
	}

	@Test(priority = 4, alwaysRun = false, dependsOnMethods = "RemoveVasCustomer360View")
	public void RemoveVasSelectSubscriptionPage() {
		SelectSub = new RemoveVasSelectSubscription(driver);
		SelectSub.selectSubscriptionPageActions();
	}

	@Test(priority = 5, alwaysRun = false, dependsOnMethods = "RemoveVasSelectSubscriptionPage")
	public void RemoveVasSummaryPage() {
		summary = new RemoveVasSummary(driver);
		summary.SummarypageActions();
	}

	@Test(priority = 6, alwaysRun = false, dependsOnMethods = "RemoveVasSummaryPage")
	public void RemoveVasStatusCheck() throws Exception {
		Status = new RemoveVasStatus(driver);
		CustomerID = Status.StatusCheck(type);
		CustomerStatus = Status.CustStatus;
		System.out.println(CustomerID);
	}

	@DataProvider
	public Object[][] dataProvider(Method method) throws IOException {
		if (method.getName().equals("RemoveVasSearchCustomer")) {
			Object[][] data = Excel_Utils.GetDbData(
					System.getProperty("user.dir") + "\\src\\main\\java\\ExcelSheets\\dashboard_page.xlsx",
					"SearchCustomer");
			return data;
		}
		if (method.getName().equals("RemoveVasCustomer360View")) {
			Object[][] data = Excel_Utils.GetDbData(
					System.getProperty("user.dir") + "\\src\\main\\java\\ExcelSheets\\dashboard_page.xlsx",
					"VasDetails");
			return data;
		}
		return null;
	}
}
