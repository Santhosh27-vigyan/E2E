package Academy;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import org.apache.logging.log4j.*;
import org.openqa.selenium.WebDriver;
import pageObjects.CustomerCapturePageObjects;
import resources.PreConditions;
public class CustomerCapture {
	public String name, civilID;
	public WebDriver driver;
	public PreConditions pc;
	private Logger log = LogManager.getLogger(CustomerCapture.class.getName());
	public CustomerCapture(String name, String CivilId, WebDriver driver) {
		this.name = name;
		this.civilID = CivilId;
		this.driver = driver;
	}
	public void customercapturePage(String resi_type, String dobyear, String dobmonth, String dobdate, String govyear,
			String govmonth, String govdate, String GOVERNATE, String AREA, String PACI, String FLOOR, String UNIT,
			String STREET, String BUILDING, String BILL, String idType, String fullNmame, String category,
			String SubCategory, String nationality, String buildingNumber, String BlockNumber, String UnitType,
			String PreferredLanguage, String Currency, String DunningSchedule, String Periodicity, String BillCycle,
			String BillCurrency, String Region) throws InterruptedException {
		pc = new PreConditions();
		boolean datecheckDob = pc.date_check(dobyear, dobmonth, dobdate, "dob");
		boolean datecheckGov = pc.date_check(govyear, govmonth, govdate, "gov");
		//System.out.println(govyear + " " + govmonth + " " + govdate);
		System.out.println(govyear + " " + govmonth + " " + govdate);
		int monthDob = pc.month_check(dobmonth);
		int monthGov = pc.month_check(govmonth);
		CustomerCapturePageObjects Ccpo = new CustomerCapturePageObjects(driver);
		assertEquals(Ccpo.NameCheck().getAttribute("value"), name);
		Ccpo.fullNameAr().sendKeys(fullNmame);
		Ccpo.IdType(idType).click();
		assertEquals(Ccpo.idnumber().getAttribute("value"), civilID);
		if (datecheckDob) {
			Ccpo.DOB(dobyear, monthDob, dobdate, dobmonth);
			Ccpo.ResidentialType(resi_type).click();
			Ccpo.CustomerCategory(category).click();
			Ccpo.SubCustomerCategory(SubCategory).click();
			Ccpo.Nationality(nationality).click();
			if (datecheckGov) {
				Ccpo.GovId(govyear, monthGov, govdate, govmonth);
				Ccpo.Governate().sendKeys(GOVERNATE);
				Ccpo.Area().sendKeys(AREA);
				Ccpo.Paci().sendKeys(PACI);
				Ccpo.Building(BUILDING).click();
				Ccpo.buildingNumber().sendKeys(buildingNumber);
				Ccpo.FloorNumber().sendKeys(FLOOR);
				Ccpo.BlockNumber().sendKeys(BlockNumber);
				Ccpo.UnitNumber().sendKeys(UNIT);
				Ccpo.StreetName().sendKeys(STREET);
				Ccpo.UnitType().sendKeys(UnitType);
				Ccpo.SameAsResidentialAddress();
				assertTrue(Ccpo.BillingName().getAttribute("value").equals(name));
				Ccpo.PreferredLanguage(PreferredLanguage).click();
				Ccpo.Currency(Currency).click();
				Ccpo.Periodicity(Periodicity).click();
				Ccpo.DunningSchedule(DunningSchedule).click();
				Ccpo.BillCycle(BillCycle).click();
				Ccpo.BillCurrency(BillCurrency).click();
				Ccpo.BillingRegions(Region).click();
				Ccpo.UploadDocuments("Proof of Identity");
				Ccpo.UploadLaterReason().sendKeys("Test");
				Ccpo.Save().click();
				Ccpo.UploadDocuments("Proof of Address");
				Ccpo.UploadLaterReason().sendKeys("Test");
				Ccpo.Save().click();
				Ccpo.Proceed().click();
			} else
				log.error("given date is older than the current date");
		} else
			log.error("given date is greater than the current date");
	}
}
