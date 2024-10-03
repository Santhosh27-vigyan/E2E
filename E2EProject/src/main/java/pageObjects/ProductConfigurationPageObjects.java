package pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class ProductConfigurationPageObjects {
	public WebDriver driver;
	public ProductConfigurationPageObjects(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	private WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(30));
	private WebDriverWait wait2=new WebDriverWait(driver, Duration.ofSeconds(10));
	@FindBy(xpath = "//input[contains(@name,'orderItem[1].product.characteristic[0].')]")
	private WebElement MsisdnInput;
	@FindBy(xpath = "//label[contains(@class,'required')]")
	private List<WebElement> SearchIcons;
	private WebElement SelectedSearchIcon;
	@FindBy(id =  "client-snackbar")
	private WebElement ErrorOrReserved;
	@FindBy(xpath = "//li[@role='menuitem']")
	private WebElement MsisdnLists;
	@FindBy(xpath = "//input[contains(@name,'orderItem[0].product.characteristic[0].')]")
	private WebElement SimInput;
	@FindBy(xpath = "//span[text()='Proceed']")
	private WebElement ProceedButton;
	public WebElement Msisdn()
	{
		wait.until(ExpectedConditions.visibilityOf(MsisdnInput));
		return MsisdnInput;
	}
	public WebElement SearchIcon(String Type)
	{
		System.out.println(SearchIcons.size());
		for (int i = 0; i < SearchIcons.size(); i++) {
			System.out.println(SearchIcons.get(i).getText());
			if(SearchIcons.get(i).getText().contains(Type))
			{
				SelectedSearchIcon=SearchIcons.get(i).findElement(By.xpath(".//parent::div/following-sibling::button"));
			break;
			}
		}
		return SelectedSearchIcon;
	}
	public WebElement SelectGivenMsisdn()
	{
		try {
			wait2.until(ExpectedConditions.visibilityOf(ErrorOrReserved));
			if(ErrorOrReserved.isDisplayed())
				throw new NoSuchElementException(ErrorOrReserved.getText());
		} catch (NullPointerException  e) {
			
		}
		return MsisdnLists;
	}
	public WebElement IsReserved()
	{
		wait.until(ExpectedConditions.visibilityOf(ErrorOrReserved));
		System.out.println(ErrorOrReserved.isDisplayed());
		return ErrorOrReserved;
	}
	public WebElement Sim()
	{
		return SimInput;
	}
	public WebElement Proceed()
	{
		return ProceedButton;
	}
	

}
