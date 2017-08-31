package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Dashboard {


    WebDriver driver;
    WebDriverWait waiter;
    String subAccName;
    int subAccSeqNo;
    
    String SwitchAccXPath; 
    String selectSubAccBySeqXPath;
    String selectSubAccByNameXPath;
	public Dashboard(WebDriver driver2,WebDriverWait waiter2)
	{

	        this.driver = driver2;
	        this.waiter = waiter2;
	        
	}
	  
	
	public void SwitchToAccBySeqNo(int seqNo) throws InterruptedException
	{
		subAccSeqNo = seqNo;
		SwitchAccXPath = "//*[@id='app']//div/a[contains(@href,'#/accountDetails')][1]/following-sibling::span";
	    driver.findElement(By.xpath(SwitchAccXPath)).click();
		selectSubAccBySeqXPath="//*[@id='app']/descendant::table/tbody/tr["+subAccSeqNo+"]/td[2]/descendant::span[text()='Select']";
		waiter.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(selectSubAccBySeqXPath)));
		driver.findElement(By.xpath(selectSubAccByNameXPath)).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath(SwitchAccXPath)).click();
		Thread.sleep(2000);
		
	}
	public void SwitchToAccByName(String accName) throws InterruptedException
	{
		subAccName=accName;
		SwitchAccXPath = "//*[@id='app']//div/a[contains(@href,'#/accountDetails')][1]/following-sibling::span";
	    driver.findElement(By.xpath(SwitchAccXPath)).click();
	    selectSubAccByNameXPath="//*[@id='app']/descendant::table/tbody/descendant::span[@title='"+subAccName+"']/ancestor::td/following-sibling::td/descendant::span[text()='Select']";
		waiter.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(selectSubAccByNameXPath)));
		driver.findElement(By.xpath(selectSubAccByNameXPath)).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath(SwitchAccXPath)).click();
		Thread.sleep(2000);
	}
}
