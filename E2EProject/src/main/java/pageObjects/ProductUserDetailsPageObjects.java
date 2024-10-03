package pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductUserDetailsPageObjects {
	public WebDriver driver;
	public  ProductUserDetailsPageObjects(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	private WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
	@FindBy(xpath = "//h6[contains(@class,'subtitle1')]")
	private WebElement Title;
	@FindBy(xpath = "//span[text()='Proceed']")
	private WebElement ProceedButton;
	public WebElement Proceed()
	{
		wait.until(ExpectedConditions.visibilityOf(Title));
		return ProceedButton;
	}

}
