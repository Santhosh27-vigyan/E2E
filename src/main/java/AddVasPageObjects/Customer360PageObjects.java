package AddVasPageObjects;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Customer360PageObjects {
	public WebDriver driver;
	private WebDriverWait wait;
	private JavascriptExecutor js;

	public Customer360PageObjects(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		js = (JavascriptExecutor) driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "client-snackbar")
	private WebElement Authentication;
	@FindBy(xpath = "//span[contains(@class,'body1')]/h6")
	private WebElement Status;
	@FindBy(xpath = "//div[@data-cy='Overviewservice']/div/span")
	private WebElement Product;
	@FindBy(xpath = "//span[text()='VAS']")
	private WebElement VASTab;
	@FindBy(xpath = "//span[contains(text(),'VAS ')]")
	private WebElement VasOption;
	@FindBy(xpath = "//h1[contains(text(),'New')]")
	private WebElement NewVas;
	@FindBy(xpath = "//h2[contains(text(),'Customer')]")
	private WebElement AuthenticalPage;
	@FindBy(xpath = "//input[@value='byPassPACI']")
	private WebElement Type;
	@FindBy(xpath = "//span[text()='Proceed']")
	private WebElement ProceedButton;

	public WebElement CustomerStatus() {
		wait.until(ExpectedConditions.visibilityOf(Authentication));
		return Status;
	}

	public WebElement SelectProduct() {
		wait.until(ExpectedConditions.visibilityOf(Product));
		return Product;
	}

	public WebElement SelectVasTab() {
		wait.until(ExpectedConditions.visibilityOf(VASTab));
		return VASTab;
	}

	public WebElement SelectVasOption() {
		wait.until(ExpectedConditions.visibilityOf(VasOption));
		return VasOption;
	}

	public void AddNewVas() {
		while (true) {
			try {
				NewVas.click();
				break;
			} catch (Exception e) {
				js.executeScript("window.scrollBy(0,300)");
				try {
					Thread.sleep(500);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					// e1.printStackTrace();
				}
			}
		}
	}

	public WebElement SelectRegistrationType() {
		wait.until(ExpectedConditions.visibilityOf(AuthenticalPage));
		return Type;
	}

	public WebElement ClickOnProceed() {
		return ProceedButton;
	}
}
