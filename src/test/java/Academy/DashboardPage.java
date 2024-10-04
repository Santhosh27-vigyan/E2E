package Academy;

import static org.testng.Assert.assertTrue;

import java.util.Random;
import org.apache.logging.log4j.*;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import pageObjects.DashboardPageObjects;

public class DashboardPage 
{
	public WebDriver driver;
	public DashboardPageObjects DPO;
	private static Logger log=LogManager.getLogger(DashboardPage.class.getName());
	public DashboardPage(WebDriver driver)
	{
		this.driver=driver;
	}
	public String dashboard(String phNum,String email,String name,String POA,String nationality) throws Exception
	{
		String Iid = "0123456789";
		int string_length1 = 11;
		StringBuilder identificationID=new StringBuilder("2");
		 Random randomGenerator = new Random();
		for (int i=0; i<string_length1; i++) {        
	       int randomIndex = randomGenerator.nextInt(Iid.length());
	       char randomChar = Iid.charAt(randomIndex); // Get the character at the random index
	       	identificationID.append(randomChar);
	    }
		DPO=new DashboardPageObjects(driver);
		DPO.DocumentType("CIVIL ID").click();
		log.info("DocumentType selected");
		DPO.registrationTypeSelection("Bypass PACI").click();
		//log.info("registrationType selected");
		DPO.civilID().sendKeys(identificationID);
		if(System.getProperty("browser").equalsIgnoreCase("firefox"))
		DPO.phoneNumber().click();
		//log.info("civilID sent");
		DPO.phoneNumber().sendKeys(phNum);
		//log.info("phoneNumber sent");
		DPO.email().sendKeys(email);
		//log.info("email sent");
		DPO.name().sendKeys(name);
		//log.info("name sent");
		DPO.Nationality(nationality).sendKeys(Keys.ENTER);
		//log.info("Nationality selected");
		DPO.powerOfAttorney(POA).click();
		//log.info("powerOfAttorney selected");
		DPO.Proceed();
		DPO.CivilIdError();
		DPO.PhoneNumberError();
		DPO.EmailIdError();
		DPO.NameError();
		assertTrue( DPO.leadCreated().isDisplayed());
		return String.valueOf(identificationID);
	
		
	}

}
