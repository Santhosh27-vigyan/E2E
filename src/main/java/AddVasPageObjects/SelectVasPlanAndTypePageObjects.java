package AddVasPageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SelectVasPlanAndTypePageObjects {
	public WebDriver driver;
	private WebDriverWait wait;
	private JavascriptExecutor js;

	public SelectVasPlanAndTypePageObjects(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		js = (JavascriptExecutor) driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//h3[contains(text(),'Balance')]")
	private WebElement Balance;
	@FindBy(xpath = "//input[@placeholder='Search']")
	private WebElement SearchBox;
	@FindBy(xpath = "//button[@aria-label='search']")
	private WebElement SearchIcon;
	By SelectedProductName = By.xpath("//h6[contains(@class,'MuiTypography-alignCenter')]");
	@FindBy(xpath = "//span[contains(text(),'Cart')]")
	private WebElement AddToCartButton;
	By AddToCartButtonXpath = By.xpath("//span[contains(text(),'Cart')]");
	@FindBy(id = "client-snackbar")
	private WebElement ProductAdded;
	@FindBy(xpath = "//span[text()='Proceed']")
	private WebElement ProceedButton;

	public WebElement BalnceDispled() {
		wait.until(ExpectedConditions.visibilityOf(Balance));
		return Balance;
	}

	public WebElement ProductNameToSearch() {
		wait.until(ExpectedConditions.presenceOfElementLocated(SelectedProductName));
		js.executeScript("arguments[0].scrollIntoView(true);", SearchBox);
//		while (true) {
//			try {
//				SearchBox.click();
//				break;
//			} catch (Exception e) {
//				js.executeScript("window.scrollBy(0,300)");
//				try {
//					Thread.sleep(500);
//				} catch (InterruptedException e1) {
//					// TODO Auto-generated catch block
//					//e1.printStackTrace();
//				}
//			}
//		}
		return SearchBox;
	}

	public WebElement ClickOnSearchIcon() {
		return SearchIcon;
	}

	public WebElement AddProductToCart() {
		wait.until(ExpectedConditions.presenceOfElementLocated(AddToCartButtonXpath));
		js.executeScript("window.scrollBy(0,500)");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		while (true) {
//			try {
//				AddToCartButton.click();
//				break;
//			} catch (Exception e) {
//				js.executeScript("window.scrollBy(0,300)");
//				try {
//					Thread.sleep(500);
//				} catch (InterruptedException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//			}
//		}
		return AddToCartButton;
	}

	public WebElement ProductAddedCheck() {
		wait.until(ExpectedConditions.visibilityOf(ProductAdded));
		return ProductAdded;
	}

	public WebElement ClickOnProceed() {
		wait.until(ExpectedConditions.visibilityOf(ProductAdded));
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ProceedButton;
	}

}
