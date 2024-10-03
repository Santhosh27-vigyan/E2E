package RemoveVasPageObjects;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RemoveVasCustomer360PageObjects {
	public WebDriver driver;
	private WebDriverWait wait;
	private JavascriptExecutor js;

	public RemoveVasCustomer360PageObjects(WebDriver driver) {
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
	@FindBy(xpath = "//h2[contains(@class,'noWrap')]")
	private List<WebElement>  VasNames;
	private WebElement VasNamePresent;
	By DeleteButton=By.xpath(".//ancestor::div/following-sibling::div[2]/div/div/button/span[text()='Delete']");
	
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
	public WebElement CheckVasIsPresent(String VasName)
	{
		try {
			wait.until(ExpectedConditions.visibilityOfAllElements(VasNames));
		} catch (TimeoutException e) {
			throw new NoSuchElementException("No Devices found to Delete after waiting for 10secs");
		}
		
		
		System.out.println(VasNames.size());
		System.out.println(VasName);
		for (int i = 0; i < VasNames.size(); i++) {
			
			if(VasNames.get(i).getText().equals(VasName))
			{
				VasNamePresent= VasNames.get(i);
				break;
			}
		}
		return VasNamePresent;
	}
	public WebElement ClickOnDelete()
	{
		
		js.executeScript("window.scrollBy(0,500)");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return VasNamePresent.findElement(DeleteButton);
	}

	
}
