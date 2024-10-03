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
import Suspension.SuspensionCustomer360;
import Suspension.SuspensionCustomerSearch;
import Suspension.SuspensionPayment;
import Suspension.SuspensionSelectSubscription;
import Suspension.SuspensionStatus;
import Suspension.SuspensionSummary;
import resources.Excel_Utils;
import resources.base;

public class SuspensionFinalCalls extends base {
	public WebDriver driver;
	private LoginPage loginPage;
	private SuspensionCustomerSearch SuspensionCustomerSearch;
	private SuspensionCustomer360 C360;
	private SuspensionSelectSubscription SelectSub;
	private SuspensionSummary summary;
	private SuspensionPayment Payment;
	private SuspensionStatus Status;
	public static String CustomerID;
	public String CustomerStatus;
	public String VasName;
	public static String type;

	@BeforeClass
	public void DriverIntialization(ITestContext context) throws Exception {
		if (System.getProperty("xml").equalsIgnoreCase(Xml1) || System.getProperty("xml").equalsIgnoreCase(Xml8)) {
			Boolean skipTests = (Boolean) context.getAttribute("SkippedTests");
			Boolean failTests = (Boolean) context.getAttribute("FailedTests");
			if (skipTests != null && skipTests || failTests != null && failTests) {
				throw new SkipException(
						"Skipping tests in SuspensonFinalCalls because method in onboarding final call was skipped or failed.");
			}
			this.driver = (WebDriver) context.getSuite().getAttribute("WebDriver");
			if (driver == null) {
				throw new RuntimeException("WebDriver is not initialized.");
			}
		} else if (System.getProperty("xml").equalsIgnoreCase(Xml5)
				|| System.getProperty("xml").equalsIgnoreCase(Xml6)) {
			driver = initializeDriver();
			context.getSuite().setAttribute("WebDriver", driver);
		} else {
			throw new SkipException("Skipping DriverIntialization() method.");
		}
	}

	@Parameters("type")
	@Test(priority = 1)
	public void login(String Type) throws IOException, InterruptedException {
		SuspensionFinalCalls.type = Type;
		if (System.getProperty("xml").equalsIgnoreCase(Xml5) || System.getProperty("xml").equalsIgnoreCase(Xml6)) {
			
			loginPage = new LoginPage(driver);
			loginPage.basePageNavigation();
		}
	}

	@Test(priority = 2, alwaysRun = false, dataProvider = "dataProvider", dependsOnMethods = "login")
	public void SuspensionSearchCustomer(String SearchCustomerIdToAddVas, String SearchCustomerIdToRemoveVas,
			String SearchCustomerIdToSuspension, String SearchCustomerIdToRecokeSuspension,
			String SearchCustomerIdToAddVasPostPaid, String SearchCustomerIdToRemoveVasPostapid,
			String SearchCustomerIdToSuspensionPostpaid, String SearchCustomerIdToRecokeSuspensionPostapid) {
		SuspensionCustomerSearch = new SuspensionCustomerSearch(driver);
		if (System.getProperty("xml").equalsIgnoreCase(Xml1) || System.getProperty("xml").equalsIgnoreCase(Xml8))
			SuspensionCustomerSearch.CustomerSearchTests(onboardingFinalCalls.CustomerID);
		if (System.getProperty("xml").equalsIgnoreCase(Xml5) || System.getProperty("xml").equalsIgnoreCase(Xml6)) {
			if (type.equalsIgnoreCase("prepaid"))
				SuspensionCustomerSearch.CustomerSearchTests(SearchCustomerIdToSuspension);
			if (type.equalsIgnoreCase("Postpaid"))
				SuspensionCustomerSearch.CustomerSearchTests(SearchCustomerIdToSuspensionPostpaid);
		}
	}

	@Test(priority = 3, alwaysRun = false, dependsOnMethods = "SuspensionSearchCustomer")
	public void SuspensionCustomer360View() {
		C360 = new SuspensionCustomer360(driver);
		C360.Customer360PageActions();
	}

	@Test(priority = 4, alwaysRun = false, dependsOnMethods = "SuspensionCustomer360View")
	public void SuspensionSelectSubscriptionPage() {
		SelectSub = new SuspensionSelectSubscription(driver);
		SelectSub.selectSubscriptionPageActions();
	}

	@Test(priority = 5, alwaysRun = false, dependsOnMethods = "SuspensionSelectSubscriptionPage")
	public void SuspensionSummaryPage() {
		summary = new SuspensionSummary(driver);
		summary.SummarypageActions();
	}

	@Test(priority = 6, dependsOnMethods = "SuspensionSummaryPage", alwaysRun = false)
	public void SuspensionPaymentpage() {
		if (type.equalsIgnoreCase("Postpaid")) {
			Payment = new SuspensionPayment(driver);
			Payment.PaymentPageActions();
		}
	}

	@Test(priority = 7, alwaysRun = false, dependsOnMethods = "SuspensionPaymentpage")
	public void SuspensionStatusCheck() throws Exception {
		Status = new SuspensionStatus(driver);
		CustomerID = Status.StatusCheck(type);
		CustomerStatus = Status.CustStatus;
		System.out.println(CustomerID);
	}

	@DataProvider
	public Object[][] dataProvider(Method method) throws IOException {
		if (method.getName().equals("SuspensionSearchCustomer")) {
			Object[][] data = Excel_Utils.GetDbData(
					System.getProperty("user.dir") + "\\src\\main\\java\\ExcelSheets\\dashboard_page.xlsx",
					"SearchCustomer");
			return data;
		}
		return null;
	}
}
