package RevokeSupensionPgaeObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RevokeSuspensionSummaryPageObjects {
	public WebDriver driver;
	private WebDriverWait wait;

	public RevokeSuspensionSummaryPageObjects(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//h2[text()='Revoke Suspend']")
	private WebElement Orderform;
	@FindBy(xpath = "//span[text()='Proceed']")
	private WebElement ProceedButton;

	public WebElement CheckOrderformDisplayed() {
		wait.until(ExpectedConditions.visibilityOf(Orderform));
		return Orderform;
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
