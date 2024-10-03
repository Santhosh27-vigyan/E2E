package pageObjects;

import java.time.Duration;
import org.apache.logging.log4j.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardPageObjects {
	public WebDriver driver;
	private static Logger log = LogManager.getLogger(DashboardPageObjects.class.getName());

	public DashboardPageObjects(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "root_documentType")
	private WebElement civilID;
	private By documentTypeLists = By.xpath("//ul[@role='listbox']/li");
	private By registrationTypes = By.xpath("//div[@role='radiogroup']/label");
	private WebElement rt = null;
	@FindBy(name = "scannerId")
	private WebElement civilId;
	@FindBy(name = "mobile")
	private WebElement Phone_number;
	@FindBy(name = "email")
	private WebElement email_id;
	@FindBy(name = "givenName")
	private WebElement name;
	@FindBy(id = "nationality")
	private WebElement nationality;
	@FindBy(xpath = "//button[@tile='Clear']")
	private WebElement natinalityClear;
	@FindBy(id = "root_checkForAttorney")
	private WebElement checkForAttorney;
	By checkForAttorneyOptions = By.xpath("//li[@role='option']");
	@FindBy(xpath = "//div[contains(@class,'MuiBox-root css-1yuhvjn')] //span[contains(text(),'Proc')]")
	private WebElement proceed;
	@FindBy(id = "root_scannerId")
	private WebElement civilIdError;
	@FindBy(id = "root_mobile")
	private WebElement phoneNumberError;
	@FindBy(id = "root_email")
	private WebElement emailIdError;
	@FindBy(id = "root_givenName")
	private WebElement nameError;
	@FindBy(id = "client-snackbar")
	private WebElement lead;
	private WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(90));
	private WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(2));

	public WebElement DocumentType(String type) {
		WebElement documentType = null;

		while (true) {
			try {
				wait.until(ExpectedConditions.elementToBeClickable(civilID));
				civilID.click();
				break;
			} catch (Exception e) {
				// System.out.println(e);
			}
		}
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("(//ul[@role='listbox']/li)[1]"))));
		for (int i = 0; i < driver.findElements(documentTypeLists).size(); i++) {
			if (driver.findElements(documentTypeLists).get(i).getText().equalsIgnoreCase(type)) {
				documentType = driver.findElements(documentTypeLists).get(i);
				System.out.println("selected");
				break;
			}
		}
		return documentType;
	}

	public WebElement registrationTypeSelection(String type) throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,400)");
		for (int i = 0; i <= driver.findElements(registrationTypes).size(); i++) {
			if (driver.findElements(registrationTypes).get(i).getText().equalsIgnoreCase(type)) {
				// rt = driver.findElement(radio_group);
				rt = driver.findElements(registrationTypes).get(i);
				break;
			}
			// Thread.sleep(500);
		}
		return rt;
	}

	public WebElement civilID() throws InterruptedException {
		// Thread.sleep(500);
		WebElement id = null;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(90));
		try {
			wait.until(ExpectedConditions.elementToBeClickable(civilID));
			civilId.click();
		} catch (StaleElementReferenceException e) {
			wait.until(ExpectedConditions.elementToBeClickable(civilID));
			civilId.click();
		}
		// Thread.sleep(1000);
		id = civilId;
		return id;
	}

	public WebElement phoneNumber() throws InterruptedException {
		 Thread.sleep(1000);
		return Phone_number;
	}

	public void CivilIdError() throws Exception {
		try {
			wait2.until(ExpectedConditions.visibilityOf(civilIdError));
			if (civilIdError.isDisplayed()) {
				log.error("CivilId " + civilIdError.getText());
				throw new Exception("CivilId " + civilIdError.getText());
			}
		} catch (NoSuchElementException e) {
		} catch (NullPointerException e) {
		}
	}

	public WebElement email() throws InterruptedException {
		// Thread.sleep(1000);
		return email_id;
	}

	public void PhoneNumberError() throws Exception {
		try {
			wait2.until(ExpectedConditions.visibilityOf(phoneNumberError));
			if (phoneNumberError.isDisplayed()) {
				log.error("PhoneNumber " + phoneNumberError.getText());
				throw new Exception("PhoneNumber " + phoneNumberError.getText());
			}
		} catch (NoSuchElementException e) {
		} catch (NullPointerException e) {
		}
	}

	public WebElement name() {
		return name;
	}

	public void EmailIdError() throws Exception {
		try {
			wait2.until(ExpectedConditions.visibilityOf(emailIdError));
			if (emailIdError.isDisplayed()) {
				log.error("emailId " + emailIdError.getText());
				throw new Exception("emailId " + emailIdError.getText());
			}
		} catch (NoSuchElementException e) {
		} catch (NullPointerException e) {
		}
	}

	public WebElement Nationality(String nationalityName) {
		nationality.click();
		int k = nationality.getAttribute("value").length();
		for (int i = 0; i < k; i++) {
			nationality.sendKeys(Keys.BACK_SPACE);
		}
		nationality.sendKeys(nationalityName);
		// natinalityClear.click();
		nationality.sendKeys(Keys.ARROW_DOWN);
		return nationality;
	}

	public void NameError() throws Exception {
		try {
			wait2.until(ExpectedConditions.visibilityOf(nameError));
			if (nameError.isDisplayed()) {
				log.error("Name " + nameError.getText());
				throw new Exception("Name " + nameError.getText());
			}
		} catch (NoSuchElementException e) {
			System.out.println(e);
		} catch (NullPointerException e) {
		}
	}

	public WebElement powerOfAttorney(String poa_value) {
		WebElement poa = null;
		// String POA = checkForAttorney.getText();
		checkForAttorney.click();
		for (int i = 0; i < driver.findElements(checkForAttorneyOptions).size(); i++) {
			if (driver.findElements(checkForAttorneyOptions).get(i).getText().equalsIgnoreCase(poa_value))
				poa = driver.findElements(checkForAttorneyOptions).get(i);
		}
		return poa;
	}

	public void Proceed() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		while (true) {
			try {
				proceed.click();
				break;
			} catch (Exception e) {
				js.executeScript("window.scrollBy(0,100)");
			}
		}
	}

	public WebElement leadCreated() {
		wait.until(ExpectedConditions.visibilityOf(lead));
		return lead;
	}
}
