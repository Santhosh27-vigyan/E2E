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
import RevokeSuspension.RevokeSuspensionCustomer360;
import RevokeSuspension.RevokeSuspensionCustomerSearch;
import RevokeSuspension.RevokeSuspensionSelectSubscription;
import RevokeSuspension.RevokeSuspensionStatus;
import RevokeSuspension.RevokeSuspensionSummary;
import resources.Excel_Utils;
import resources.base;

public class RevokeSuspensionFinalCalls extends base{
	public WebDriver driver;
	private LoginPage loginPage;
	private RevokeSuspensionCustomerSearch RevokeSuspensionCustomerSearch;
	private RevokeSuspensionCustomer360 C360;
	private RevokeSuspensionSelectSubscription SelectSub;
	private RevokeSuspensionSummary summary;
	private RevokeSuspensionStatus Status;
	public String CustomerID;
	public String CustomerStatus;
	public String VasName;
	public static String type;
	
	@BeforeClass
	public void DriverIntialization(ITestContext context) throws Exception {
		
		if (System.getProperty("xml").equalsIgnoreCase(Xml1) || System.getProperty("xml").equalsIgnoreCase(Xml6) ||  System.getProperty("xml").equalsIgnoreCase(Xml8)) {
			Boolean skipTests = (Boolean) context.getAttribute("SkippedTests");
			Boolean failTests = (Boolean) context.getAttribute("FailedTests");
			if (skipTests != null && skipTests || failTests != null && failTests) {
				throw new SkipException(
						"Skipping tests in RevokeSuspensionFinalCalls because method in Suspension final call was skipped or failed.");
			}
			this.driver = (WebDriver) context.getSuite().getAttribute("WebDriver");
			if (driver == null) {
				throw new RuntimeException("WebDriver is not initialized.");
			}
		}
		else if(System.getProperty("xml").equalsIgnoreCase(Xml7))
			driver = initializeDriver();
		else {
			throw new SkipException("Skipping DriverIntialization() method.");
		}
	}
	@Parameters("type")
	@Test(priority = 1)
	public void login(String Type) throws IOException, InterruptedException {
		RevokeSuspensionFinalCalls.type=Type;
		if (!(System.getProperty("xml").equalsIgnoreCase(Xml1) || System.getProperty("xml").equalsIgnoreCase(Xml6)) && System.getProperty("xml").equalsIgnoreCase(Xml7) ) {
			
			loginPage = new LoginPage(driver);
			loginPage.basePageNavigation();
		}
	}

	@Test(priority = 2, alwaysRun = false, dataProvider = "dataProvider", dependsOnMethods = "login")
	public void RevokeSuspensionSearchCustomer(String SearchCustomerIdToAddVas,String SearchCustomerIdToRemoveVas,String SearchCustomerIdToSuspension,String SearchCustomerIdToRevokeSuspension,String SearchCustomerIdToAddVasPostPaid,String SearchCustomerIdToRemoveVasPostapid,String SearchCustomerIdToSuspensionPostpaid,String SearchCustomerIdToRevokeSuspensionPostapid) {

		//System.out.println(SearchCustomerIdToRemoveVas);
		RevokeSuspensionCustomerSearch = new RevokeSuspensionCustomerSearch(driver);
		if (System.getProperty("xml").equalsIgnoreCase(Xml1) ||  System.getProperty("xml").equalsIgnoreCase(Xml8))
			RevokeSuspensionCustomerSearch.CustomerSearchTests(onboardingFinalCalls.CustomerID);
		if(	System.getProperty("xml").equalsIgnoreCase(Xml6))
			RevokeSuspensionCustomerSearch.CustomerSearchTests(SuspensionFinalCalls.CustomerID);
		if(	System.getProperty("xml").equalsIgnoreCase(Xml7))
		{
			if(type.equalsIgnoreCase("prepaid"))
				RevokeSuspensionCustomerSearch.CustomerSearchTests(SearchCustomerIdToRevokeSuspension);
			if(type.equalsIgnoreCase("Postpaid"))
				RevokeSuspensionCustomerSearch.CustomerSearchTests(SearchCustomerIdToRevokeSuspensionPostapid);
		}
			
	}
	@Test(priority = 3, alwaysRun = false, dependsOnMethods = "RevokeSuspensionSearchCustomer")
	public void RevokeSuspensionCustomer360View() {
		C360 = new RevokeSuspensionCustomer360(driver);
		C360.Customer360PageActions();
	}
	@Test(priority = 4, alwaysRun = false, dependsOnMethods = "RevokeSuspensionCustomer360View")
	public void RevokeSuspensionSelectSubscriptionPage() {
		SelectSub = new RevokeSuspensionSelectSubscription(driver);
		SelectSub.selectSubscriptionPageActions();
	}
	@Test(priority = 5, alwaysRun = false, dependsOnMethods = "RevokeSuspensionSelectSubscriptionPage")
	public void RevokeSuspensionSummaryPage() {
		summary = new RevokeSuspensionSummary(driver);
		summary.SummarypageActions();
	}
	@Test(priority = 6, alwaysRun = false, dependsOnMethods = "RevokeSuspensionSummaryPage")
	public void RevokeSuspensionStatusCheck() throws Exception {
		Status = new RevokeSuspensionStatus(driver);
		CustomerID = Status.StatusCheck(type);
		CustomerStatus = Status.CustStatus;
		System.out.println(CustomerID);
	}
	@DataProvider
	public Object[][] dataProvider(Method method) throws IOException {
		if (method.getName().equals("RevokeSuspensionSearchCustomer")) {
			Object[][] data = Excel_Utils.GetDbData(
					System.getProperty("user.dir") + "\\src\\main\\java\\ExcelSheets\\dashboard_page.xlsx",
					"SearchCustomer");
			return data;
		}
		return null;
	}
}
