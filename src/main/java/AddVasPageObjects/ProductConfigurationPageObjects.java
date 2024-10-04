package AddVasPageObjects;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductConfigurationPageObjects {
	public WebDriver driver;
	private WebDriverWait wait;
	private String VasName;

	public ProductConfigurationPageObjects(WebDriver driver, String VasName) {
		this.driver = driver;
		this.VasName = VasName;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//h2[contains(@class,'gutterBottom')]")
	private WebElement History;
	@FindBy(xpath = "//span[text()='Proceed']")
	private WebElement ProceedButton;

	public WebElement CheckHistoryDisplayed() {
		wait.until(ExpectedConditions.textToBePresentInElement(History, VasName));
		return History;
	}

	public WebElement ClickOnProceed() {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ProceedButton;
	}
}
