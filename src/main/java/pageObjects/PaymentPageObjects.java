package pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PaymentPageObjects {
	public WebDriver driver;
	public PaymentPageObjects(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	private WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
	@FindBy(xpath = "//h2[contains(text(),'Payment')]")
	private WebElement Title;
	@FindBy(xpath = "//span[contains(text(),'Cash')]")
	private WebElement CashButton;
	@FindBy(xpath = "//button[@data-cy='addPayment']")
	private WebElement Paymentbutton;
	@FindBy(id = "standard-error-helper-text")
	private WebElement CommentsSpace;
	@FindBy(xpath = "//span[contains(text(),'Proceed')]")
	private WebElement ProceedButton;
	public WebElement Cash()
	{
		wait.until(ExpectedConditions.visibilityOf(Title));
		wait.until(ExpectedConditions.elementToBeClickable(CashButton)); 
		return CashButton;
	}
	public WebElement Payment()
	{
		return Paymentbutton;
	}
	public WebElement Comments()
	{
		return CommentsSpace;
	}
	public WebElement Proceed()
	{
		return ProceedButton;
	}
	
			

}
