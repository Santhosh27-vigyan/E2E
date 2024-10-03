package pageObjects;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductSelectionPageObjects {
	public WebDriver driver;

	public ProductSelectionPageObjects(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//h3[text()='All Offerings']")
	private WebElement AllOfferings;
	@FindBy(xpath = "(//div[@role='button']/span[1])[3]")
	private WebElement Header;
	@FindBy(xpath = "//li[@role='menuitem']")
	private List<WebElement> TypeLists;
	private WebElement selectedType;
	@FindBy(xpath = "//input[@Placeholder='Search']")
	private WebElement SearchBar;
	@FindBy(xpath = "//button[@aria-label='search']")
	private WebElement SearchIcon;
	@FindBy(xpath = "//button[@data-cy='addToCart']")
	private WebElement AddToCart;
	@FindBy(xpath = "//h6[contains(@class,'noWrap')]")
	private WebElement SelectedProductName;
	@FindBy(xpath = "//*[@id='client-snackbar']")
	private WebElement AddedProductname;
	@FindBy(xpath = "//span[text()='Proceed']")
	private WebElement ProceedButton;
	private WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	public WebElement SelectTypeDropDown() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		while (true) {
			try {
				wait.until(ExpectedConditions.visibilityOf(AllOfferings));
				AllOfferings.click();
				break;
			} catch (Exception e) {
				js.executeScript("window.scrollBy(0,-500)");
				Thread.sleep(500);
			}
		}
		return Header;
	}

	public WebElement SelectType(String Type) {
		System.out.println(Type);
		for (int i = 0; i < TypeLists.size(); i++) {
			if (TypeLists.get(i).getText().equalsIgnoreCase(Type)) {
				System.out.println(TypeLists.get(i).getText());
				selectedType = TypeLists.get(i);
				break;
			}
		}
		return selectedType;
	}

	public WebElement Search(String Productname) {
		SearchBar.sendKeys(Productname);
		return SearchIcon;
	}

	public WebElement AddProductToTheCart(String Productname) throws InterruptedException {
		Thread.sleep(1000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		wait.until(ExpectedConditions.visibilityOf(SelectedProductName));
		while (true) {
			try {
				if (SelectedProductName.getAttribute("title").equalsIgnoreCase(Productname)) {
					System.out.println(SelectedProductName.getAttribute("title"));
					js.executeScript("window.scrollBy(0,500)");
					Thread.sleep(500);
					break;
				} else
					SearchIcon.click();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return AddToCart;
	}

	public WebElement ProductAddedConfirmation() {
		wait.until(ExpectedConditions.visibilityOf(AddedProductname));
		return AddedProductname;
	}

	public WebElement Proceed() {
		return ProceedButton;
	}
}
