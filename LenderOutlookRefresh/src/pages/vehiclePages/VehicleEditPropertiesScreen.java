package pages.vehiclePages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class VehicleEditPropertiesScreen {

	
	WebDriver driver;
	WebDriverWait waiter;
	By FirstNameEditBox = By.xpath("/html/body/descendant::div/form/descendant::div[2]/descendant::input[contains(@id,'customerName-FirstName-FirstName')]");
	//By EditpropertiesScreenTitleXpath=By.xpath("/html/body/div[2]/descendant::h3[text()='Edit Vehicle Details']");
	//By EditpropertiesScreenTitleXpath=By.xpath("/html/body/div[2]/div/div[1]/div/div/h3");
	By EditpropertiesScreenTitleXpath=By.xpath("/html/body/div[2]/descendant::h3");
	String ScreenTitle;
	public VehicleEditPropertiesScreen(WebDriver driver2,WebDriverWait waiter2)
	{

		this.driver = driver2;
		this.waiter = waiter2;
		
	}
	
	
	public void SetVehicleFirstName(String vFirstName)
	{
		waiter.until(ExpectedConditions.visibilityOfElementLocated(FirstNameEditBox));
		driver.findElement(FirstNameEditBox).sendKeys(vFirstName);
	}
	
	public String getScreenTitle()
	{
		waiter.until(ExpectedConditions.visibilityOfElementLocated(EditpropertiesScreenTitleXpath));
		ScreenTitle=driver.findElement(EditpropertiesScreenTitleXpath).getText();
		if (ScreenTitle!=null)
		{
			return ScreenTitle;
		}
		
		else
		{
			ScreenTitle = "FAILED";
			return ScreenTitle;
		}	
	}
	
}
