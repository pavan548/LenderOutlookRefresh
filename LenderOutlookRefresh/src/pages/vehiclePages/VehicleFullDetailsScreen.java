package pages.vehiclePages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class VehicleFullDetailsScreen {
	
	WebDriver driver;
	WebDriverWait waiter;
	int vehicleSeqNo;
    String VehicleName;
    
    String DetailsTabXpath;
    String HistoryTabXpath;
    String GeofenceTabXpath;
    String ValidationTabXpath;
    String PaymentReminderTabXpath;
    String AirtimePlanTabXpath;
    
	public VehicleFullDetailsScreen(WebDriver driver2,WebDriverWait waiter2)
	{

	        this.driver = driver2;
	        this.waiter = waiter2;
	}
	
	
	public void ClickHistoryTab()
	{
		
		HistoryTabXpath = "//*[@id='app']/div/div/div[2]/div[1]/div/div[2]/div/div[2]/div[2]/div[2]/div[2]/div[2]/div[1]/div[1]/div/div[1]/button[2]";
		
		waiter.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(HistoryTabXpath)));
    	driver.findElement(By.xpath(HistoryTabXpath)).click();
		
	}
	
	public void ClickGeofenceTab()
	{
		GeofenceTabXpath = "//*[@id='app']/div/div/div[2]/div[1]/div/div[2]/div/div[2]/div[2]/div[2]/div[2]/div[2]/div[1]/div[1]/div/div[1]/button[3]";
		
		waiter.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(GeofenceTabXpath)));
    	driver.findElement(By.xpath(GeofenceTabXpath)).click();
		
	}
	
	public void ClickValidationTab()
	{
		ValidationTabXpath = "//*[@id='app']/div/div/div[2]/div[1]/div/div[2]/div/div[2]/div[2]/div[2]/div[2]/div[2]/div[1]/div[1]/div/div[1]/button[4]";
		
		waiter.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ValidationTabXpath)));
    	driver.findElement(By.xpath(ValidationTabXpath)).click();
		
	}
	
	public void ClickPaymentReminderTab()
	{
		PaymentReminderTabXpath = "//*[@id='app']/div/div/div[2]/div[1]/div/div[2]/div/div[2]/div[2]/div[2]/div[2]/div[2]/div[1]/div[1]/div/div[1]/button[5]";
		
		waiter.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PaymentReminderTabXpath)));
    	driver.findElement(By.xpath(PaymentReminderTabXpath)).click();
		
	}
	
	public void ClickAirtimePlanTab()
	{
		AirtimePlanTabXpath = "//*[@id='app']/div/div/div[2]/div[1]/div/div[2]/div/div[2]/div[2]/div[2]/div[2]/div[2]/div[1]/div[1]/div/div[1]/button[6]";
		
		waiter.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(AirtimePlanTabXpath)));
    	driver.findElement(By.xpath(AirtimePlanTabXpath)).click();
		
	}
	
}
