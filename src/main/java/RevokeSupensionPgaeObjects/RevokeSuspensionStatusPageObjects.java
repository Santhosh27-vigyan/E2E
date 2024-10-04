package RevokeSupensionPgaeObjects;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import resources.base;

public class RevokeSuspensionStatusPageObjects extends base {
	public WebDriver driver;
	private WebDriverWait wait;
	public RevokeSuspensionStatusPageObjects(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		PageFactory.initElements(driver, this);
	}

	
	@FindBy(xpath = "//h6[contains(@class,'subtitle1')]")
	private WebElement Title;
	@FindBy(xpath = "//p[text()='Status']/parent::div/following-sibling::div[2]/div/span")
	private WebElement StatusObject;
	private By RefreshButton = By.xpath(".//parent::div/button");
	@FindBy(xpath = "//p[text()='Customer ID']/parent::div/following-sibling::div[2]/div/span")
	private WebElement CustomerId;
	@FindBy(xpath = "//button[@data-cy='goBackToDashboard']")
	private WebElement BackToDashboard;

	public String checkStatus(String Type) throws IOException, InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(StatusObject));
		for (int i = 1; i <= 300; i++) {

			if (StatusObject.getText().equalsIgnoreCase("completed")
					|| StatusObject.getText().equalsIgnoreCase("failed")) {
				getScreenshot("Revoke Suspension is "+StatusObject.getText(),Type);
				break;
			} else {
				StatusObject.findElement(RefreshButton).click();
			}
			Thread.sleep(1000);
			if (i == 30) {
				getScreenshot("Revoke Suspension is "+StatusObject.getText(),Type);
			}
		}
		return StatusObject.getText();
	}

	public String GetCustomerID() {
		return CustomerId.getText();
	}

	public WebElement GoBackToDashboard() {
		return BackToDashboard;
	}
}
