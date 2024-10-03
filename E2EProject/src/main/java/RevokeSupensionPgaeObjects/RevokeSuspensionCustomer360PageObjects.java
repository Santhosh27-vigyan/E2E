package RevokeSupensionPgaeObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RevokeSuspensionCustomer360PageObjects {
	public WebDriver driver;
	private WebDriverWait wait;

	public RevokeSuspensionCustomer360PageObjects(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "client-snackbar")
	private WebElement Authentication;
	@FindBy(xpath = "//span[contains(@class,'body1')]/h6")
	public WebElement Status;
	@FindBy(xpath = "//div[@data-cy='Overviewservice']/div/span")
	private WebElement Product;
	@FindBy(xpath = "//div[@data-cy='moreActions']")
	private WebElement MoreActionsButton;
	@FindBy(xpath = "//div[@class='MuiListItemText-root']/span[text()='Revoke Suspend']")
	private WebElement SuspensionOtion;

	public WebElement CustomerStatus() {
		wait.until(ExpectedConditions.visibilityOf(Authentication));
		return Status;
	}

	public WebElement SelectProduct() {
		wait.until(ExpectedConditions.visibilityOf(Product));
		return Product;
	}

	public WebElement MoreActions() {
		wait.until(ExpectedConditions.visibilityOf(MoreActionsButton));
		return MoreActionsButton;
	}

	public WebElement SelectSuspensionOption() {
		wait.until(ExpectedConditions.visibilityOf(SuspensionOtion));
		return SuspensionOtion;
	}
}
