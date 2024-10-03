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
import AddVas.AddVasStatus;
import AddVas.Customer360;
import AddVas.CustomerSearch;
import AddVas.ProductConfiguration;
import AddVas.SelectSubscription;
import AddVas.SelectVasPlanAndType;
import AddVas.Summary;
import resources.Excel_Utils;
import resources.base;
public class AddVasFinalCalls extends base {
	public WebDriver driver;
	private LoginPage loginPage;
	private CustomerSearch CustomerSearch;
	private Customer360 C360;
	private SelectSubscription SelectSub;
	private SelectVasPlanAndType SelectVas;
	private ProductConfiguration Conf;
	private Summary summary;
	private AddVasStatus Status;
	public static String CustomerID;
	public String CustomerStatus;
	public String VasName;
	public static String type;
	
	
	@BeforeClass
	public void DriverIntialization(ITestContext context) throws Exception {
		
		if(System.getProperty("xml").equalsIgnoreCase(Xml1)||System.getProperty("xml").equalsIgnoreCase(Xml8))
		{
		Boolean skipTests = (Boolean) context.getAttribute("SkippedTests");
		Boolean failTests = (Boolean) context.getAttribute("FailedTests");
        if (skipTests != null && skipTests || failTests != null && failTests) {
            throw new SkipException("Skipping tests in AddVasFinalCalls because method in onboarding final call was skipped or failed.");
        }		
        this.driver = (WebDriver) context.getSuite().getAttribute("WebDriver");
        if (driver == null) {
            throw new RuntimeException("WebDriver is not initialized.");
        }
		}
		else if(System.getProperty("xml").equalsIgnoreCase(Xml2) || System.getProperty("xml").equalsIgnoreCase(Xml3))
		{
			driver = initializeDriver();
			context.getSuite().setAttribute("WebDriver", driver);
		}
		else
		{
			throw new SkipException("Skipping DriverIntialization() method.");
		}
	}
	@Parameters("type")
	@Test(priority = 1)
	public void login(String Type) throws IOException, InterruptedException {
		AddVasFinalCalls.type=Type;
		if(System.getProperty("xml").equalsIgnoreCase(Xml2) || System.getProperty("xml").equalsIgnoreCase(Xml3))
		{
			
		loginPage = new LoginPage(driver);
		loginPage.basePageNavigation();
		}
		
	}
	@Test(priority = 2, alwaysRun = false, dataProvider = "dataProvider", dependsOnMethods = "login")
	public void SearchCustomer(String SearchCustomerIdToAddVas,String SearchCustomerIdToRemoveVas,String SearchCustomerIdToSuspension,String SearchCustomerIdToRevokeSuspension,String SearchCustomerIdToAddVasPostPaid,String SearchCustomerIdToRemoveVasPostapid,String SearchCustomerIdToSuspensionPostpaid,String SearchCustomerIdToRevokeSuspensionPostapid) {
		

		CustomerSearch = new CustomerSearch(driver);
		if(System.getProperty("xml").equalsIgnoreCase(Xml1) || System.getProperty("xml").equalsIgnoreCase(Xml8))
		CustomerSearch.CustomerSearchTests(onboardingFinalCalls.CustomerID);
		if(System.getProperty("xml").equalsIgnoreCase(Xml2) || System.getProperty("xml").equalsIgnoreCase(Xml3))
		{
			if(type.equalsIgnoreCase("prepaid"))
				CustomerSearch.CustomerSearchTests(SearchCustomerIdToAddVas);
			if(type.equalsIgnoreCase("Postpaid"))
				CustomerSearch.CustomerSearchTests(SearchCustomerIdToAddVasPostPaid);
		}
			
	}
	@Test(priority = 3, alwaysRun = false, dependsOnMethods = "SearchCustomer")
	public void Customer360View() {
		C360 = new Customer360(driver);
		C360.Customer360PageActions();
	}
	@Test(priority = 4, alwaysRun = false, dependsOnMethods = "Customer360View")
	public void SelectSubscriptionPage() {
		SelectSub = new SelectSubscription(driver);
		SelectSub.selectSubscriptionPageActions();
	}
	@Test(priority = 5, alwaysRun = false, dependsOnMethods = "SelectSubscriptionPage", dataProvider = "dataProvider")
	public void SelectVasPlanAndTypePage(String PostpaidVasName, String PrepaidVasName) {
		SelectVas = new SelectVasPlanAndType(driver);
		
		if(type.equalsIgnoreCase("Prepaid"))
		{
		SelectVas.SelectVasPlanAndTypePageActions(PrepaidVasName);
		this.VasName=PrepaidVasName;
		}
		if(type.equalsIgnoreCase("Postpaid"))
		{
		SelectVas.SelectVasPlanAndTypePageActions(PostpaidVasName);
		this.VasName=PostpaidVasName;
		}
		
	}
	@Test(priority = 6, alwaysRun = false, dependsOnMethods = "SelectVasPlanAndTypePage")
	public void ProductConfigurationPage() {
		Conf = new ProductConfiguration(driver);
		Conf.ProductConfigurationpageActions(VasName);
	}
	@Test(priority = 7, alwaysRun = false, dependsOnMethods = "ProductConfigurationPage")
	public void SummaryPage() {
		summary = new Summary(driver);
		summary.SummarypageActions();
	}
	@Test(priority = 8, alwaysRun = false, dependsOnMethods = "SummaryPage")
	public void AddvasStatusCheck() throws Exception {
		Status = new AddVasStatus(driver);
		CustomerID = Status.StatusCheck(type);
		CustomerStatus = Status.CustStatus;
		System.out.println(CustomerID);
	}
	@DataProvider
	public Object[][] dataProvider(Method method) throws IOException {
		if (method.getName().equals("SearchCustomer")) {
			Object[][] data = Excel_Utils.GetDbData(
					System.getProperty("user.dir") + "\\src\\main\\java\\ExcelSheets\\dashboard_page.xlsx",
					"SearchCustomer");
			return data;
		}
		if (method.getName().equals("SelectVasPlanAndTypePage")) {
			Object[][] data = Excel_Utils.GetDbData(
					System.getProperty("user.dir") + "\\src\\main\\java\\ExcelSheets\\dashboard_page.xlsx",
					"VasDetails");
			return data;
		}
		return null;
	}
}
