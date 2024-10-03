package finalCall;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Set;
import org.apache.logging.log4j.*;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import Academy.DashboardPage;
import Academy.LoginPage;
import Academy.Payment;
import Academy.ProductConfiguration;
import Academy.ProductSelection;
import Academy.ProductUserDetails;
import Academy.Status;
import Academy.Summary;
import Academy.CustomerCapture;
import resources.Excel_Utils;
import resources.base;

public class onboardingFinalCalls extends base
{
	public  WebDriver driver;
	public LoginPage loginPage;
	public DashboardPage dashboardPage;
	public CustomerCapture customerCapture;
	public ProductSelection ProductSelection;
	public ProductConfiguration ProductConfiguration;
	public ProductUserDetails ProductUserDetails;
	public Summary Summary;
	public Payment Payment;
	public Status Status;
	public String name;
	public String civilId;
	public static String CustomerID;
	public String CustomerStatus;
	public static String Type;
	private static Logger log=LogManager.getLogger(onboardingFinalCalls.class.getName());
	@BeforeTest
	public void getBrowser(ITestContext context) throws IOException
	{
		this.driver = initializeDriver();
		log.info("driver initialized");
		 context.getSuite().setAttribute("WebDriver", driver);
		 Set<String> Windows = driver.getWindowHandles();
		 Iterator<String> I = Windows.iterator();
		 while(I.hasNext())
		 {
			 System.out.println("we have a another window");
			 driver.switchTo().window(I.next());
		 }
	}
	@Parameters("type")
	@Test(priority = 1)
	public void login(String Type) throws IOException, InterruptedException
	{
		System.out.println("Type "+Type);
		onboardingFinalCalls.Type=Type;
		
	loginPage=new LoginPage(driver);
	loginPage.basePageNavigation();
	}
	
	
	@Test(priority = 2,dependsOnMethods = "login",alwaysRun = false,dataProvider = "dataProvider")
	public void Dashboard(String phNum,String Email,String name,String poa,String nationality) throws Exception
	{
		
		log.info("login successfull");
		this.name=name;
		dashboardPage=new DashboardPage(driver);
		this.civilId= dashboardPage.dashboard(phNum,Email,name,poa,nationality);
	}
	
	@Test(priority = 3,dependsOnMethods = "Dashboard",alwaysRun = false,dataProvider = "dataProvider")
	public void CustomerCapture(String resi_type,String dobyear,String dobmonth,String  dobdate,String govyear,String govmonth,String govdate,String GOVERNATE,String AREA,String PACI,String FLOOR,String UNIT,String STREET,String BUILDING,String BILL,String idType,String fullName,String category,String SubCategory,String nationality,String buildingNumber,String BlockNumber,String UnitType,String PreferredLanguage,String Currency,String DunningSchedule,String Periodicity,String BillCycle,String BillCurrency,String Region) throws Exception
	{
		log.info("login successfull");
		customerCapture=new CustomerCapture(name,civilId,driver);
		customerCapture.customercapturePage(resi_type,dobyear,dobmonth,dobdate,govyear,govmonth,govdate,GOVERNATE, AREA, PACI, FLOOR, UNIT, STREET,BUILDING,BILL,idType,fullName,category,SubCategory,nationality,buildingNumber,BlockNumber,UnitType,PreferredLanguage,Currency,DunningSchedule,Periodicity,BillCycle,BillCurrency,Region);
	}
	
	@Test(priority = 4,dataProvider="dataProvider",dependsOnMethods = "CustomerCapture",alwaysRun =  false)
	public void SelectProduct(String Postpaid,String PrepaidProduct,String PostpaidProduct,String Prepaid) throws InterruptedException
	{
		System.out.println(Type);
		log.info("Customer Capture Page Completed");
		ProductSelection=new ProductSelection(driver);
		if(Type.equalsIgnoreCase("prepaid"))
			ProductSelection.ProductSelectionActions(Prepaid, PrepaidProduct);
		if(Type.equalsIgnoreCase("postpaid"))
			ProductSelection.ProductSelectionActions(Postpaid, PostpaidProduct);	
	}
	@Test(priority = 5, dependsOnMethods = "SelectProduct",alwaysRun = false)
	public void ProductConfigurationPage() throws InterruptedException
	{
		log.info("Product Selected");
		ProductConfiguration=new ProductConfiguration(driver);
		ProductConfiguration.ProductConfigurationTests();
		
		
	}
	@Test(priority = 6, dependsOnMethods = "ProductConfigurationPage",alwaysRun = false)
	public void ProductUserDetailsPage()
	{
		log.info("Product configuration done");
		ProductUserDetails=new ProductUserDetails(driver);
		ProductUserDetails.ProductUserDetailsActions();	
	}
	@Test(priority = 6, dependsOnMethods = "ProductUserDetailsPage",alwaysRun = false)
	public void SummaryPage() throws InterruptedException
	{
		log.info("Product User details done");
		Summary=new Summary(driver);
		Summary.SummaryActions();
		
	}
	@Test(priority = 7, dependsOnMethods = "SummaryPage",alwaysRun = false)
	public void Paymentpage()
	{
		log.info("SummaryPage details Done");
		Payment=new Payment(driver);
		Payment.PaymentPageActions();
	}
	@Test(priority = 8, dependsOnMethods = "Paymentpage",alwaysRun = false)
	public void StatusCheck() throws Exception
	{
		log.info("Payment Done");
		Status=new Status(driver);
		CustomerID=Status.StatusCheck(Type);
		CustomerStatus=Status.CustStatus;
		System.out.println(CustomerID);
		log.info("Onboarding Completed");
		log.info("Onboarded Customer ID "+CustomerID);
	}
	
	@DataProvider
	public Object[][] dataProvider(Method method) throws IOException
	{
		if(method.getName().equals("Dashboard"))
		{
	Object[][] data = Excel_Utils.GetDbData(System.getProperty("user.dir")+"\\src\\main\\java\\ExcelSheets\\dashboard_page.xlsx", "dashboard");
	return data;
		}
		if(method.getName().equals("CustomerCapture"))
		{
	Object[][] data = Excel_Utils.GetDbData(System.getProperty("user.dir")+"\\src\\main\\java\\ExcelSheets\\dashboard_page.xlsx", "registartion_req");
	return data;
		}
		if(method.getName().equals("SelectProduct"))
		{
	Object[][] data = Excel_Utils.GetDbData(System.getProperty("user.dir")+"\\src\\main\\java\\ExcelSheets\\dashboard_page.xlsx", "ProductSelection");
	return data;
		}
		
		return null;
	}
	

}
