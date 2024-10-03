package pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SummayPageObjects {
	public WebDriver driver;
	public  SummayPageObjects(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	private WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(30));
	@FindBy(xpath  = "//h2[contains(text(),'Information')]")
	private WebElement Title;
	@FindBy(xpath = "//span[text()='Proceed']")
	private WebElement ProceedButton;
	public WebElement Proceed() throws InterruptedException
	{
		wait.until(ExpectedConditions.visibilityOf(Title));
		Thread.sleep(500);
		return ProceedButton;
	}
}
