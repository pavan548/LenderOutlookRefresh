package pages.vehiclePages;

import java.util.LinkedHashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class VehicleExtendedDetailsScreen {

	WebDriver driver;
	WebDriverWait waiter;
	int vehicleSeqNo;
    String vehicleName;
    String VehicleXpathStringbyName;
    String VehicleXpathStringBySeqNo;
    String VehicleLastReportedOffsetXpath;
    String VehicleLastReportedOffset;
    String VehicleLastReportedDateXpath;
    String VehicleLastReportedDate;
    String VehicleLastReportedLocXpath;
    String VehicleLastReportedLoc;
    String vehicleLocateNowButtonXpath;
    String LocateNowErrorText;
    String LocateNowErrorXpath;
    String VehicleEditPropertiesXpath;
    String VehicleRepossessionXpath;
    String VehicleDetailsButtonXpath;
    String VehicleActionsButtonXpath;
    // By IncludeSubAccToggleButton = By.xpath("//span[@class='Vehicles-filterTxt']/div/input[@name='includeSubAccounts']");
    By IncludeSubAccToggleButton  = By.xpath("//*[@id='app']/descendant::div[text()='Vehicles']/following-sibling::div/descendant::label[contains(text(),'Include Sub Accounts')]");
    //By IncludeSubAccToggleButton  = By.name("includeSubAccounts");
    VehicleCreateRepossessionScreen RepoScreen; 
    
	    public VehicleExtendedDetailsScreen(WebDriver driver2,WebDriverWait waiter2)
	    {

	        this.driver = driver2;
	        this.waiter = waiter2;
	    }
	 
	    public boolean checkExtendedDetailsLoaded(int vehicleSeqNo)
	    {
	    	vehicleLocateNowButtonXpath="//*[@id='app']/descendant::table/tbody//tr["+vehicleSeqNo+"]/td/div/div[5]/descendant::button[1]";
	    	try
	    	{
	    		waiter.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(vehicleLocateNowButtonXpath)));
	    	}
	    	catch (NoSuchElementException e) 
	    	{
	            return false;
	        }
			return true;
	    
	    }
	    
	    public boolean checkExtendedDetailsLoaded(String vehicleName)
	    {
	    	vehicleLocateNowButtonXpath="//*[@id='app']/descendant::table/tbody//descendant::div[@title='"+vehicleName+"']/parent::div/following-sibling::div/following-sibling::div/descendant::button[1]";
	    	try
	    	{
	    		waiter.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(vehicleLocateNowButtonXpath)));
	    	}
	    	catch (NoSuchElementException e) 
	    	{
	            return false;
	        }
			return true;
	    
	    }
	    //Click on DashBoard on the Left Side Panel
	        
	    public void clickVehicleBySeqNo(int i )
	    {
	    	System.out.println("Inside VehicleDetailsScreen --> clickVehicleBySeqNo Func  ");
	    	vehicleSeqNo=i;
	    	VehicleXpathStringBySeqNo = "//*[@id='app']/descendant::table/tbody//tr["+vehicleSeqNo+"]/td/div/div[3]/div[1]";
	    	waiter.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(VehicleXpathStringBySeqNo)));
	    	driver.findElement(By.xpath(VehicleXpathStringBySeqNo)).click();
	    }
	    
	    public void clickVehicleByName(String VName )
	    {
	    	vehicleName = VName;
	         
	    	VehicleXpathStringbyName = "//*[@id='app']/descendant::table/tbody//descendant::div[@title='"+vehicleName+"']";
	    	waiter.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(VehicleXpathStringbyName)));
	    	driver.findElement(By.xpath(VehicleXpathStringbyName)).click();
	    	
	    }
	    
	    public String getVehicleLastReportedOffset(int i)
	    {
	    	vehicleSeqNo=i;
	    	VehicleLastReportedOffsetXpath = "//*[@id='app']/descendant::table/tbody//tr["+vehicleSeqNo+"]/td/div/div[3]/div[2]";
	    	waiter.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(VehicleLastReportedOffsetXpath)));
	    	VehicleLastReportedOffset = driver.findElement(By.xpath(VehicleLastReportedOffsetXpath)).getText();
		    //System.out.println("VehicleLastReported Offset  = " +VehicleLastReportedOffset);
			return VehicleLastReportedOffset;
	    	
	    }	
	    
	    public String getVehicleLastReportedOffset(String VName)
	    {
	    	vehicleName = VName;
	    	VehicleLastReportedOffsetXpath = "//*[@id='app']/descendant::table/tbody//descendant::div[@title='"+vehicleName+"']/following-sibling::div";
	    	waiter.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(VehicleLastReportedOffsetXpath)));
	    	VehicleLastReportedOffset = driver.findElement(By.xpath(VehicleLastReportedOffsetXpath)).getText();
		    //System.out.println("VehicleLastReported Offset  = " +VehicleLastReportedOffset);
			return VehicleLastReportedOffset;
	    	
	    }
	    
	    public String getVehicleLastReportedDate(int i)
	    {
	    	vehicleSeqNo=i;
	    	//VehicleLastReportedDateXpath = "//*[@id='app']/descendant::table/tbody//tr["+vehicleSeqNo+"]/td/div/div[5]/descendant::div";
	    	VehicleLastReportedDateXpath ="//*[@id='app']/descendant::table/tbody//tr["+vehicleSeqNo+"]/td/div/div[5]/descendant::div/span[2]";
	    	//VehicleLastReportedDate = driver.findElement(By.xpath(VehicleLastReportedDateXpath)).getText();
	    	waiter.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(VehicleLastReportedDateXpath)));
	    	List<WebElement> Last_Reported  = driver.findElements(By.xpath(VehicleLastReportedDateXpath));
	    	WebElement reportedDate = Last_Reported.get(0);
	    	VehicleLastReportedDate=reportedDate.getText();
		    //System.out.println("VehicleLastReported Offset  = " +VehicleLastReportedDate);
			return VehicleLastReportedDate;
	    	
	    }	
	    
	    public String getVehicleLastReportedDate(String VName)
	    {
	    	vehicleName = VName;
	    	//VehicleLastReportedDateXpath = "//*[@id='app']/descendant::table/tbody//descendant::div[@title='"+VehicleName+"']/following-sibling::div/descendant::div[contains(text(),'Last Reported Date')]/ancestor::span/following-sibling::span";
	    	
	    	//VehicleLastReportedDateXpath="//*[@id='app']/descendant::table/tbody//descendant::div[@title='Honda_Brio_pavanAir02']/parent::div/following-sibling::div/following-sibling::div/descendant::div/span[2]";
	    	VehicleLastReportedDateXpath="//*[@id='app']/descendant::table/tbody//descendant::div[@title='"+vehicleName+"']/parent::div/following-sibling::div/following-sibling::div/descendant::div/span[2]";
	    	waiter.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(VehicleLastReportedDateXpath)));
	    	List<WebElement> Last_Reported  = driver.findElements(By.xpath(VehicleLastReportedDateXpath));
	    	WebElement reportedDate = Last_Reported.get(0);
	    	VehicleLastReportedDate=reportedDate.getText();
	    	//VehicleLastReportedDate = driver.findElement(By.xpath(VehicleLastReportedDateXpath)).getText();
		    //System.out.println("VehicleLastReported Offset  = " +VehicleLastReportedDate);
			return VehicleLastReportedDate;
	    	
	    }
	    
	    public String getVehicleLastReportedLoc(int i)
	    {
	    	vehicleSeqNo=i;
	    	//VehicleLastReportedDateXpath = "//*[@id='app']/descendant::table/tbody//tr["+vehicleSeqNo+"]/td/div/div[5]/descendant::div";
	    	VehicleLastReportedLocXpath = "//*[@id='app']/descendant::table/tbody//tr["+vehicleSeqNo+"]/td/div/div[5]/descendant::div/span[2]";
	    	//VehicleLastReportedDate = driver.findElement(By.xpath(VehicleLastReportedDateXpath)).getText();
	    	waiter.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(VehicleLastReportedLocXpath)));
	    	List<WebElement> Last_Reported  = driver.findElements(By.xpath(VehicleLastReportedLocXpath));
	    	WebElement reportedLoc = Last_Reported.get(1);
	    	VehicleLastReportedLoc = reportedLoc.getText();
		    //System.out.println("VehicleLastReported Offset  = " +VehicleLastReportedDate);
			return VehicleLastReportedLoc;
	    	
	    }	
	    
	    public String getVehicleLastReportedLoc(String VName)
	    {
	    	vehicleName = VName;
	    	//VehicleLastReportedDateXpath = "//*[@id='app']/descendant::table/tbody//descendant::div[@title='"+VehicleName+"']/following-sibling::div/descendant::div[contains(text(),'Last Reported Date')]/ancestor::span/following-sibling::span";
	    	
	    	VehicleLastReportedLocXpath="//*[@id='app']/descendant::table/tbody//descendant::div[@title='"+vehicleName+"']/parent::div/following-sibling::div/following-sibling::div/descendant::div/span[2]";
	    	waiter.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(VehicleLastReportedLocXpath)));
	    	List<WebElement> Last_Reported  = driver.findElements(By.xpath(VehicleLastReportedLocXpath));
	    	WebElement reportedLoc = Last_Reported.get(1);
	    	VehicleLastReportedDate=reportedLoc.getText();
	    	//VehicleLastReportedDate = driver.findElement(By.xpath(VehicleLastReportedDateXpath)).getText();
		    //System.out.println("VehicleLastReported Offset  = " +VehicleLastReportedDate);
			return VehicleLastReportedDate;
	    	
	    }
	    
	    
	    public String getTotalDistanceDriven(int i)
	    {
	    	vehicleSeqNo=i;
	    	//VehicleLastReportedDateXpath = "//*[@id='app']/descendant::table/tbody//tr["+vehicleSeqNo+"]/td/div/div[5]/descendant::div";
	    	VehicleLastReportedLocXpath = "//*[@id='app']/descendant::table/tbody//tr["+vehicleSeqNo+"]/td/div/div[5]/descendant::div/span[2]";
	    	//VehicleLastReportedDate = driver.findElement(By.xpath(VehicleLastReportedDateXpath)).getText();
	    	waiter.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(VehicleLastReportedLocXpath)));
	    	List<WebElement> Last_Reported  = driver.findElements(By.xpath(VehicleLastReportedLocXpath));
	    	WebElement reportedLoc = Last_Reported.get(1);
	    	VehicleLastReportedLoc = reportedLoc.getText();
		    //System.out.println("VehicleLastReported Offset  = " +VehicleLastReportedDate);
			return VehicleLastReportedLoc;
	    	
	    }	
	    public String  clickVehicleLocateNow(int i)
	    {
	    	 
	    	vehicleSeqNo=i;
	    	vehicleLocateNowButtonXpath="//*[@id='app']/descendant::table/tbody//tr["+vehicleSeqNo+"]/td/div/div[5]/descendant::button[1]";
	    	driver.findElement(By.xpath(vehicleLocateNowButtonXpath)).click();
	    	LocateNowErrorXpath="//*[@id='app']/div/div/div[3]/div/div[2]/span";
	    	waiter.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocateNowErrorXpath)));
	    	LocateNowErrorText=driver.findElement(By.xpath(LocateNowErrorXpath)).getText();		    
		    //System.out.println("LocateNowErrorText = " +LocateNowErrorText );
	    	
		    if(LocateNowErrorText!=null)
		    {
		    	return LocateNowErrorText;
		    }	
		    
		   
		    else
		    {
		    	return "worked";
		    }	
	    }
	    
	    
	    public String  clickVehicleLocateNow(String VName)
	    {
	    	 
	    	vehicleName = VName;
	    	vehicleLocateNowButtonXpath="//*[@id='app']/descendant::table/tbody//descendant::div[@title='"+vehicleName+"']/parent::div/following-sibling::div/following-sibling::div/descendant::button[1]";
	    	driver.findElement(By.xpath(vehicleLocateNowButtonXpath)).click();
	    	LocateNowErrorXpath="//*[@id='app']/div/div/div[3]/div/div[2]/span";
	    	waiter.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocateNowErrorXpath)));
	    	LocateNowErrorText=driver.findElement(By.xpath(LocateNowErrorXpath)).getText();		    
		    //System.out.println("LocateNowErrorText = " +LocateNowErrorText );
		    if(LocateNowErrorText!=null)
		    {
		    	return LocateNowErrorText;
		    }	
		    else
		    {
		    	return "worked";
		    }	
	    }
	    
	    public void  clickVehicleDetailsButton(int i )
	    {
	    	vehicleSeqNo = i;
	    	VehicleDetailsButtonXpath= "//*[@id='app']/descendant::table/tbody//tr["+vehicleSeqNo+"]/td/div/div[5]/descendant::button[5]";
	    	waiter.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(VehicleDetailsButtonXpath)));
	    	driver.findElement(By.xpath(VehicleDetailsButtonXpath)).click();
	    }
	    
	    public void  clickEditPropertiesButton(int i )
	    {
	    	vehicleSeqNo = i;
	    	VehicleEditPropertiesXpath= "//*[@id='app']/descendant::table/tbody//tr["+vehicleSeqNo+"]/td/div/div[5]/descendant::button[2]";
	    	waiter.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(VehicleEditPropertiesXpath)));
	    	driver.findElement(By.xpath(VehicleEditPropertiesXpath)).click();
	    	System.out.println("Inside VDS.clickEditPropertiesButton Func");
	    	
	    }
	    
	    public void  clickRepoButtonByVehicleSeqNo(int i )
	    {
	    	vehicleSeqNo = i;
	    	VehicleRepossessionXpath = "//*[@id='app']/descendant::table/tbody//tr["+vehicleSeqNo+"]/td/div/div[5]/descendant::button[3]";
	    	waiter.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(VehicleRepossessionXpath)));
	    	driver.findElement(By.xpath(VehicleRepossessionXpath)).click();
	    }
	    
	    public void  clickRepoButtonByVehicleName(String VName )
	    {
	    	vehicleName = VName;
	    	//VehicleRepossessionXpath = "//*[@id='app']/descendant::table/tbody//tr["+vehicleSeqNo+"]/td/div/div[5]/descendant::button[3]";
	    	VehicleRepossessionXpath="//*[@id='app']/descendant::table/tbody//descendant::div[@title='"+vehicleName+"']/parent::div/following-sibling::div/following-sibling::div/descendant::button[3]";
	    	waiter.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(VehicleRepossessionXpath)));
	    	driver.findElement(By.xpath(VehicleRepossessionXpath)).click();
	    }
	    
	    public void  clickActionsButton(int i )
	    {
	    	vehicleSeqNo = i;
	    	VehicleActionsButtonXpath = "//*[@id='app']/descendant::table/tbody//tr["+vehicleSeqNo+"]/td/div/div[5]/descendant::button[4]";
	    	waiter.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(VehicleActionsButtonXpath)));
	    	driver.findElement(By.xpath(VehicleActionsButtonXpath)).click();
	    }
	    public void clickIncludeSubAccToggleButton()
	    {
	    	
	    	waiter.until(ExpectedConditions.visibilityOfElementLocated(IncludeSubAccToggleButton));
	    	driver.findElement(IncludeSubAccToggleButton).click();
	    	
	    	
	    }	
	    
	    public boolean RCreateRepossession(LinkedHashMap<String, String> CreateRepoDataMap)
	    {
	    	int vehicleSeqNo;
	    	boolean createRepoPass; 
	    	
	    	System.out.println("Inside VehicleExtendedDetailsScreen --> RCreateRepossession Func  ");
	    	
	    	System.out.println("clickVehicleDataMap.get(vehicleSeqNo) = "+CreateRepoDataMap.get("vehicleSeqNo"));
	    	System.out.println("clickVehicleDataMap.get(vehicleName) = "+CreateRepoDataMap.get("vehicleName"));
	    	if(CreateRepoDataMap.get("vehicleSeqNo")!= null) 
	    	{	
	    		 
	    		 vehicleSeqNo = Integer.parseInt((CreateRepoDataMap.get("vehicleSeqNo")));
	    		 System.out.println("IF loop : Integer.parseInt of vehicleSeqNo = "+ vehicleSeqNo);
	    		 this.clickRepoButtonByVehicleSeqNo(vehicleSeqNo);
	    	     
	    	}
			
	    	else
	    	{	
	    		vehicleName = CreateRepoDataMap.get("vehicleName");
	    		System.out.println("Else Loop :  vehicleName = "+ vehicleName);
	    		this.clickRepoButtonByVehicleName(vehicleName);
	    	} 
	    	
	    	RepoScreen= new VehicleCreateRepossessionScreen(driver, waiter);
	    	createRepoPass=RepoScreen.RCreateRepossession(CreateRepoDataMap);
	    	return createRepoPass;
	    	
	    }
}
