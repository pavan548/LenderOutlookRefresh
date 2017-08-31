package pages;

import java.util.LinkedHashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.vehiclePages.vehiclesListScreen;
import utils.ObjectRepoLoader;

public class userHomePage 
{

	    WebDriver driver;
	    WebDriverWait waiter;
	    static ObjectRepoLoader ObjRepoLoader;
	    LinkedHashMap<String,String> ObjRepoMap;
	    static String projectPath;
		static String ObjectRepoFilePath=null;
		static String className;
		vehiclesListScreen vehList;
		boolean navigateToVehicleSuccess;
	 
	    By homePageUserName =  null;
	    By DashBoardOption  =  null;
	    By VehiclesOption   =  null;
	    By ReportsOption    =  null; 
	    By AlertsOption     =  null;
	    By DevicesOption    =  null; 
        By AdminOption      =  null;
        By IncludeSubAccToggleButton= null;
        
        
        //Vehicles SubMenus
        By vRepoSubOption      =   null;
        By vScheduleActionsSubOption = null;
        By vDetailsSubOption		= 	null;		
		
	    public userHomePage(WebDriver driver2,WebDriverWait waiter2) throws ClassNotFoundException, ReflectiveOperationException, SecurityException
	    {
	    	className="pages.userHomePage";
	        this.driver = driver2;
	        this.waiter = waiter2;
	        vehList=new vehiclesListScreen(driver2,waiter2);
	        projectPath = System.getProperty("user.dir");
			ObjectRepoFilePath=projectPath+"\\ObjectRepository\\ObjectRepo.properties";
			ObjRepoLoader = new ObjectRepoLoader();
			this.ObjRepoMap=ObjRepoLoader.loadObjectRepo(ObjectRepoFilePath,className);
			
			homePageUserName =  ObjRepoLoader.getObjectLocator("homePageUserName",ObjRepoMap );
		    DashBoardOption  =  ObjRepoLoader.getObjectLocator("DashBoardOption",ObjRepoMap );
		    VehiclesOption   =  ObjRepoLoader.getObjectLocator("VehiclesOption",ObjRepoMap );
		    ReportsOption    =  ObjRepoLoader.getObjectLocator("ReportsOption",ObjRepoMap ); 
		    AlertsOption     =  ObjRepoLoader.getObjectLocator("AlertsOption",ObjRepoMap );
		    DevicesOption    =  ObjRepoLoader.getObjectLocator("DevicesOption",ObjRepoMap ); 
	        AdminOption      =  ObjRepoLoader.getObjectLocator("AdminOption",ObjRepoMap );
	        IncludeSubAccToggleButton= ObjRepoLoader.getObjectLocator("IncludeSubAccToggleButton",ObjRepoMap );
	        //By IncludeSubAccToggleButton = By.xpath("html/body/div[1]/div/form/div/label");
	        
	        //Vehicles SubMenus
	        vRepoSubOption      =   ObjRepoLoader.getObjectLocator("vRepoSubOption",ObjRepoMap );
	        vScheduleActionsSubOption = ObjRepoLoader.getObjectLocator("vScheduleActionsSubOption",ObjRepoMap );
	        vDetailsSubOption		= 	ObjRepoLoader.getObjectLocator("vDetailsSubOption",ObjRepoMap );		
			
	        
	    }

	    //Get the title of Dashboard Page

	    public String getLoginTitle(){

	     return    driver.getTitle();

	    }
	    

	    //Get the User name from Home Page

	    public String getHomePageDashboardUserName()
	    {
	    	waiter.until(ExpectedConditions.visibilityOfElementLocated(VehiclesOption));
	         return    driver.findElement(homePageUserName).getText();

	    }
	        
	    //Click on DashBoard on the Left Side Panel
	        
	    public void clickDashBoardOption()
	    {
	    		waiter.until(ExpectedConditions.visibilityOfElementLocated(IncludeSubAccToggleButton));
	            driver.findElement(DashBoardOption).click();

	    }
	        
	    //Click on Vehicles on the Left Side Panel
	        
	    public void clickVehiclesOption()
	    {
	    		waiter.until(ExpectedConditions.visibilityOfElementLocated(IncludeSubAccToggleButton));
	            driver.findElement(VehiclesOption).click();

	    }  
	    
	    //Click on Reports on the Left Side Panel
        
	    public void clickReportsOption()
	    {
	    		waiter.until(ExpectedConditions.visibilityOfElementLocated(IncludeSubAccToggleButton));
	            driver.findElement(ReportsOption).click();

	    } 
	    
	    //Click on Alerts on the Left Side Panel
        
	    public void clickAlertsOption()
	    {
	    		waiter.until(ExpectedConditions.visibilityOfElementLocated(IncludeSubAccToggleButton));
	            driver.findElement(AlertsOption).click();

	    } 
	    
	    //Click on Devices on the Left Side Panel
        
	    public void clickDevicesOption()
	    {
	    		waiter.until(ExpectedConditions.visibilityOfElementLocated(IncludeSubAccToggleButton));
	            driver.findElement(DevicesOption).click();

	    } 
	    
	    //Click on Admin on the Left Side Panel
        
	    public void clickAdminOption()
	    {
	    	 	waiter.until(ExpectedConditions.visibilityOfElementLocated(IncludeSubAccToggleButton));
	            driver.findElement(AdminOption).click();

	    } 
	    
	    public void clickIncludeSubAccToggleButton()
	    {
	    	
	    		waiter.until(ExpectedConditions.visibilityOfElementLocated(IncludeSubAccToggleButton));
	    		driver.findElement(IncludeSubAccToggleButton).click();
	    	
	    	
	    }	
	    
	    
	    public boolean checkIfLoggedInUserDisplayed()
	    {
	    	
	    	try
	    	{
	    		waiter.until(ExpectedConditions.visibilityOfElementLocated(homePageUserName));
	    	}
	    	catch (NoSuchElementException e) 
	    	{
	            return false;
	        }
	    	return true;
	    }
	    
	    //Click on Vehicles on the Left Side Panel
	        
	    public Boolean NavigateToVehiclesPage()
	    {
	    		waiter.until(ExpectedConditions.visibilityOfElementLocated(VehiclesOption));
	            driver.findElement(VehiclesOption).click();
	            navigateToVehicleSuccess=vehList.checkVehicleListLoaded();
	            return navigateToVehicleSuccess;

	    }  
}
