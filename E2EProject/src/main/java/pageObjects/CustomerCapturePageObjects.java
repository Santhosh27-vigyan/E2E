package pageObjects;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import resources.PreConditions;

public class CustomerCapturePageObjects extends PreConditions {
	public WebDriver driver;

	public CustomerCapturePageObjects(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	private WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	@FindBy(name = "givenName")
	private WebElement givenName;
	@FindBy(name = "fullNameAR")
	private WebElement fullName;
	@FindBy(id = "root_documentType")
	private WebElement idtype;
	@FindBy(xpath = "//li[@role='option']")
	private List<WebElement> idtypes;
	@FindBy(name = "uniqueIdentificationNumber")
	private WebElement idNumber;
	@FindBy(xpath = "//div[@data-cy='dob']/div")
	private WebElement Dob;
	@FindBy(xpath = "//button[contains(@aria-label,'switch to year view')]")
	private WebElement OpenYearView;
	@FindBy(xpath = "//button[contains(@class,'yearButton')]")
	private List<WebElement> DobYears;
	@FindBy(xpath = "//div[contains(@class,'MuiYearPicker-root')]")
	private WebElement DobYearsContainer;
	private WebElement Selectedyear;
	@FindBy(xpath = "//div[contains(@class,'MuiPickersCalendarHeader-labelContainer')]/div/div")
	private WebElement Month;
	@FindBy(xpath = "//button[@title='Next month']")
	private WebElement Next;
	@FindBy(xpath = "//button[@title='Previous month']")
	private WebElement Previous;
	@FindBy(xpath = "//button[@role='gridcell']")
	private List<WebElement> Day;
	private WebElement SelectedDay;
	@FindBy(id = "root_residentialType")
	private WebElement ResiType;
	@FindBy(id = "root_customerCategory")
	private WebElement CustCate;
	@FindBy(id = "root_customerSubCategory")
	private WebElement SubCustCate;
	@FindBy(id = "root_nationality")
	private WebElement NationalityType;
	@FindBy(xpath = "//div[@data-cy='govIdExpiry']/div")
	private WebElement Gov;
	@FindBy(name = "POState")
	private WebElement GovernateName;
	@FindBy(name = "POCity")
	private WebElement AreaName;
	@FindBy(name = "POBox")
	private WebElement PaciNumber;
	@FindBy(id = "root_type")
	private WebElement BuildingType;
	@FindBy(name = "buildingName")
	private WebElement Buildingname;
	@FindBy(name = "levelNumber")
	private WebElement levelNumber;
	@FindBy(name = "streetNr")
	private WebElement StreetNr;
	@FindBy(name = "subUnitNumber")
	private WebElement SubUnitNumber;
	@FindBy(name = "streetName")
	private WebElement StreetName;
	@FindBy(name = "subUnitType")
	private WebElement SubunitType;
	@FindBy(name = "changeAddress")
	private WebElement ChangeAddress;
	@FindBy(name = "billingName")
	private WebElement BillingNameValue;
	@FindBy(id = "root_language")
	private WebElement BillingLanguage;
	@FindBy(id = "root_currencyCode")
	private WebElement BillingCurrency;
	@FindBy(id = "root_dunningSchedule")
	private WebElement BillingDunningSchedule;
	@FindBy(id = "root_periodicity")
	private WebElement BillingPeriodicity;
	@FindBy(id = "root_billCycle")
	private WebElement BillingBillCycle;
	@FindBy(id = "root_presentationCurrencyCode")
	private WebElement BillingBillCurrency;
	@FindBy(id = "root_billingRegions")
	private WebElement BillingBillingRegions;
	@FindBy(xpath = "//div[contains(@class,'MuiTypography-button')]")
	private List<WebElement> UploadDoc;
	private WebElement Docs;
	@FindBy(name = "reason")
	private WebElement Reason;
	@FindBy(xpath = "//span[text()='Save']")
	private WebElement SaveButton;
	@FindBy(xpath = "//button[@type='submit']")
	private WebElement Proceed;

	public WebElement NameCheck() throws InterruptedException {
		try {
			wait.until(ExpectedConditions.visibilityOf(givenName));
			System.out.println(givenName.getAttribute("value"));
		} catch (Exception e) {
			wait.until(ExpectedConditions.visibilityOf(givenName));
			System.out.println(givenName.getAttribute("value"));
		}
		return givenName;
	}

	public WebElement fullNameAr() {
		wait.until(ExpectedConditions.textToBePresentInElement(fullName, ""));
		return fullName;
	}

	public WebElement IdType(String typeName) {
		try {
			idtype.click();
			// System.out.println(idtypes.size());
			if (idtypes.size() == 0)
				throw new Exception();
		} catch (Exception e) {
			idtype.click();
			// System.out.println(idtypes.size());
		}
		for (int i = 0; i < idtypes.size(); i++) {
			if (idtypes.get(i).getText().equalsIgnoreCase(typeName)) {
				idtype = idtypes.get(i);
				break;
			}
		}
		return idtype;
	}

	public WebElement idnumber() {
		return idNumber;
	}

	public void DOB(String dobyear, int dobmonthInt, String dobdate, String dobMonthString) throws InterruptedException {
		Dob.click();
		Calendar(dobyear, dobmonthInt, dobdate, dobMonthString, "dob");
	}

	public WebElement ResidentialType(String type) throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		while (true) {
			try {
				ResiType.click();
				break;
			} catch (Exception e) {
				js.executeScript("window.scrollBy(0,-500)");
				Thread.sleep(500);
			}
		}

		for (int i = 0; i < idtypes.size(); i++) {
			if (idtypes.get(i).getText().equalsIgnoreCase(type)) {
				ResiType = idtypes.get(i);
				break;
			}
		}
		return ResiType;
	}

	public WebElement CustomerCategory(String Categoryname) {
		CustCate.click();
		for (int i = 0; i < idtypes.size(); i++) {
			if (idtypes.get(i).getText().equalsIgnoreCase(Categoryname)) {
				CustCate = idtypes.get(i);
				break;
			}
		}
		return CustCate;
	}

	public WebElement SubCustomerCategory(String SubCategoryname) {
		SubCustCate.click();
		for (int i = 0; i < idtypes.size(); i++) {
			if (idtypes.get(i).getText().equalsIgnoreCase(SubCategoryname)) {
				SubCustCate = idtypes.get(i);
				break;
			}
		}
		return SubCustCate;
	}

	public WebElement Nationality(String Nationality) {
		NationalityType.click();
		for (int i = 0; i < idtypes.size(); i++) {
			// System.out.println(idtypes.get(i).getText());
			if (idtypes.get(i).getText().equalsIgnoreCase(Nationality)) {
				NationalityType = idtypes.get(i);
				break;
			}
		}
		return NationalityType;
	}

	public void GovId(String dobyear, int dobmonthInt, String dobdate, String dobMonthString) throws InterruptedException {
		Gov.click();
		Calendar(dobyear, dobmonthInt, dobdate, dobMonthString, "gov");
	}

	public void Calendar(String dobyear, int dobmonthInt, String dobdate, String dobMonthString, String Option) throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		OpenYearView.click();
		// main code
//	for (int i = 0; i < DobYears.size(); i++) {
//		System.out.println(DobYears.get(i).getText() + " "+i);
//		if (DobYears.get(i).getText().equals(dobyear)) {
//			Selectedyear = DobYears.get(i);	
//			break;
//		} 
//	}
		// alternative
		Selectedyear = DobYears.get(YearNum(dobyear, Option));
		while (true) {
			try {
				Selectedyear.click();
				break;
			} catch (Exception e) {
				js.executeScript("arguments[0].scrollTop = arguments[0].scrollHeight", DobYearsContainer);
			}
		}
		String[] monthsSpli = Month.getText().split("\\s+");
		int monthNum = month_check(monthsSpli[0]);
		System.out.println(monthNum + " from UI" + Option);
		System.out.println(dobmonthInt + " from excel" + Option);
		int months = 0;
		if (monthNum == dobmonthInt && monthsSpli[0].equalsIgnoreCase(dobyear))
			months = button(Option);
		else
			months = dobmonthInt - monthNum;
		System.out.println(months + " " + Option);
		if (months > 0) {
			for (int i = 1; i <= 12; i++) {
				// System.out.println(Month.getText().contains(dobMonthString));
				if (Month.getText().contains(dobMonthString)) {
					break;
				}
				Next.click();
			}
		} else {
			for (int i = 1; i <= 12; i++) {
				// System.out.println(Month.getText().contains(dobMonthString));
				if (Month.getText().contains(dobMonthString)) {
					break;
				}
				Previous.click();
			}
		}
		for (int i = 0; i < Day.size(); i++) {
//		System.out.println(Day.get(i).getText());
//		System.out.println(dobdate);
			wait.until(ExpectedConditions.visibilityOf(Day.get(i)));
			if (Day.get(i).getText().equals(dobdate)) {
				System.out.println(Day.get(i).getText());
				SelectedDay = Day.get(i);
				break;
			}
		}
		int scroll = 0;
		while (true) {
			try {
				SelectedDay.click();
				break;
			} catch (Exception e) {
				System.out.println("SelectedDay.click()");
				js.executeScript("window.scrollBy(0,scroll +=50)");
				Thread.sleep(500);
			}
		}
		int scrollMinus = -scroll;
		js.executeScript("window.scrollBy(0," + scrollMinus + ")");
	}

	public WebElement Governate() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		while (true) {
			try {
				GovernateName.click();
				break;
			} catch (Exception e) {
				js.executeScript("window.scrollBy(0,500)");
				Thread.sleep(500);
			}
		}
		return GovernateName;
	}

	public WebElement Area() {
		return AreaName;
	}

	public WebElement Paci() {
		return PaciNumber;
	}

	public WebElement Building(String type) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		while (true) {
			try {
				BuildingType.click();
				break;
			} catch (Exception e) {
				js.executeScript("window.scrollBy(0,100)");
			}
		}
		for (int i = 0; i < idtypes.size(); i++) {
			if (idtypes.get(i).getText().equalsIgnoreCase(type)) {
				BuildingType = idtypes.get(i);
				break;
			}
		}
		return BuildingType;
	}

	public WebElement buildingNumber() {
		return Buildingname;
	}

	public WebElement FloorNumber() {
		return levelNumber;
	}

	public WebElement BlockNumber() {
		return StreetNr;
	}

	public WebElement UnitNumber() {
		return SubUnitNumber;
	}

	public WebElement StreetName() {
		return StreetName;
	}

	public WebElement UnitType() {
		return SubunitType;
	}

	public void SameAsResidentialAddress() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		while (true) {
			try {
				if (!ChangeAddress.isSelected()) {
					ChangeAddress.click();
					break;
				}
			} catch (Exception e) {
				js.executeScript("window.scrollBy(0,200)");
				Thread.sleep(500);
			}
		}
	}

	public WebElement BillingName() {
		return BillingNameValue;
	}

	public WebElement PreferredLanguage(String PreferredLanguage) throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		while (true) {
			try {
				BillingLanguage.click();
				break;
			} catch (Exception e) {
				js.executeScript("window.scrollBy(0,200)");
				Thread.sleep(500);
			}
		}
		for (int i = 0; i < idtypes.size(); i++) {
			if (idtypes.get(i).getText().equalsIgnoreCase(PreferredLanguage)) {
				BillingLanguage = idtypes.get(i);
				break;
			}
		}
		return BillingLanguage;
	}

	public WebElement Currency(String Currency) throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		while (true) {
			try {
				BillingCurrency.click();
				break;
			} catch (Exception e) {
				js.executeScript("window.scrollBy(0,200)");
				Thread.sleep(500);
			}
		}
		for (int i = 0; i < idtypes.size(); i++) {
			if (idtypes.get(i).getText().contains(Currency)) {
				BillingCurrency = idtypes.get(i);
				break;
			}
		}
		return BillingCurrency;
	}

	public WebElement DunningSchedule(String DunningSchedule) throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		while (true) {
			try {
				BillingDunningSchedule.click();
				break;
			} catch (Exception e) {
				js.executeScript("window.scrollBy(0,200)");
				Thread.sleep(500);
			}
		}
		for (int i = 0; i < idtypes.size(); i++) {
			if (idtypes.get(i).getText().contains(DunningSchedule)) {
				BillingDunningSchedule = idtypes.get(i);
				break;
			}
		}
		return BillingDunningSchedule;
	}

	public WebElement Periodicity(String periodicity) throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		while (true) {
			try {
				BillingPeriodicity.click();
				break;
			} catch (Exception e) {
				js.executeScript("window.scrollBy(0,200)");
				Thread.sleep(500);
			}
		}
		for (int i = 0; i < idtypes.size(); i++) {
			if (idtypes.get(i).getText().equalsIgnoreCase(periodicity)) {
				BillingPeriodicity = idtypes.get(i);
				break;
			}
		}
		return BillingPeriodicity;
	}

	public WebElement BillCycle(String BillCycle) throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		while (true) {
			try {
				BillingBillCycle.click();
				break;
			} catch (Exception e) {
				js.executeScript("window.scrollBy(0,200)");
				Thread.sleep(500);
			}
		}
		for (int i = 0; i < idtypes.size(); i++) {
			if (idtypes.get(i).getText().equalsIgnoreCase(BillCycle)) {
				BillingBillCycle = idtypes.get(i);
				break;
			}
		}
		return BillingBillCycle;
	}

	public WebElement BillCurrency(String BillCurrency) throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		while (true) {
			try {
				BillingBillCurrency.click();
				break;
			} catch (Exception e) {
				js.executeScript("window.scrollBy(0,200)");
				Thread.sleep(500);
			}
		}
		for (int i = 0; i < idtypes.size(); i++) {
			if (idtypes.get(i).getText().contains(BillCurrency)) {
				BillingBillCurrency = idtypes.get(i);
				break;
			}
		}
		return BillingBillCurrency;
	}

	public WebElement BillingRegions(String BillingRegions) throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		while (true) {
			try {
				BillingBillingRegions.click();
				break;
			} catch (Exception e) {
				js.executeScript("window.scrollBy(0,200)");
				Thread.sleep(500);
			}
		}
		for (int i = 0; i < idtypes.size(); i++) {
			if (idtypes.get(i).getText().equalsIgnoreCase(BillingRegions)) {
				BillingBillingRegions = idtypes.get(i);
				break;
			}
		}
		return BillingBillingRegions;
	}

	public void UploadDocuments(String Type) throws InterruptedException {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		for (int i = 0; i < UploadDoc.size(); i++) {
			// System.out.println(UploadDoc.get(i).getText()+" "+Type);
			if (UploadDoc.get(i).getText().equals(Type)) {
				// System.out.println(i);
				Docs = UploadDoc.get(i)
						.findElement(By.xpath(".//parent::div/following-sibling::div[2]/div/div/button/span[1]"));
				// System.out.println(Docs.findElement(By.xpath(".//parent::button/parent::div/parent::div/parent::div/parent::div/div[1]/div")).getText()+"
				// "+Type);
				break;
			}
		}

		while (true) {
			try {
				Thread.sleep(500);
				Docs.click();
				break;
			} catch (Exception e) {
				js.executeScript("window.scrollBy(0,500)");
				Thread.sleep(500);
			}
		}

	}

	public WebElement UploadLaterReason() throws InterruptedException {
		// Thread.sleep(2000);
		return Reason;

	}

	public WebElement Save() throws InterruptedException {
		// Thread.sleep(2000);
		return SaveButton;
	}

	public WebElement Proceed() {
		return Proceed;
	}

}
