package RevokeSupensionPgaeObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RevokeSuspensionCustomerSearchPageObjects {
	public WebDriver driver;
	private WebDriverWait wait;

	public RevokeSuspensionCustomerSearchPageObjects(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//h2[text()='Home']")
	private WebElement Home;
	@FindBy(id = "inputBaseBar")
	private WebElement CustomerSearchSpaceBar;
	@FindBy(xpath = "//div[contains(@class,'MuiGrid-grid-md-11')]/div/div[1]/p[2]")
	private List<WebElement> SearchResults;
	private WebElement SelectedResult;

	public WebElement Search() {
		wait.until(ExpectedConditions.visibilityOf(Home));
		return CustomerSearchSpaceBar;
	}

	public WebElement SelectCustomer(String CustomerID) {
		wait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("(//div[contains(@class,'MuiGrid-grid-md-11')]/div/div[1]/p[2])[1]")));
		System.out.println(SearchResults.size());
		for (int i = 0; i < SearchResults.size(); i++) {
			if (SearchResults.get(i).getText().equals(CustomerID)) {
				SelectedResult = SearchResults.get(i);
				break;
			}
		}
		return SelectedResult;
	}
}
