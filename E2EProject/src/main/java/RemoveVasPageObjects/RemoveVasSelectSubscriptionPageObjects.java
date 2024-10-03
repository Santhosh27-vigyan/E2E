package RemoveVasPageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RemoveVasSelectSubscriptionPageObjects {
	public WebDriver driver;
	private WebDriverWait wait;

	public RemoveVasSelectSubscriptionPageObjects(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "client-snackbar")
	private WebElement ProductAdded;
	@FindBy(xpath = "//span[text()='Proceed']")
	private WebElement ProceedButton;

	public WebElement CheckProductSelected() {
		wait.until(ExpectedConditions.visibilityOf(ProductAdded));
		return ProductAdded;
	}

	public WebElement ClickOnProceed() {
		return ProceedButton;
	}
}
