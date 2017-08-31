package pages.vehiclePages;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.ObjectRepoLoader;

public class vehiclesListScreen 
{
	WebDriver driver;
	WebDriverWait waiter;
	static ObjectRepoLoader ObjRepoLoader;
	LinkedHashMap<String,String> ObjRepoMap;
	static String projectPath;
	static String ObjectRepoFilePath=null;
	static String className;

	public static int vehicleSeqNo;
	public static String vehicleName;
	public String VehicleXpathStringbyName;
	public String VehicleXpathStringBySeqNo;
    By VehicleXpathByName = null;
    By VehicleXpathBySeqNo = null;
    By FirstVehicleReportedOffset=null;
    
    VehicleExtendedDetailsScreen VehExtnDetails;
    
	public vehiclesListScreen(WebDriver driver2,WebDriverWait waiter2) throws ClassNotFoundException, ReflectiveOperationException, SecurityException
	{
			className="pages.vehiclePages.vehiclesListScreen";
			projectPath = System.getProperty("user.dir");
			ObjectRepoFilePath=projectPath+"\\ObjectRepository\\ObjectRepo.properties";
	        this.driver = driver2;
	        this.waiter = waiter2;
	        ObjRepoLoader = new ObjectRepoLoader();
			this.ObjRepoMap=ObjRepoLoader.loadObjectRepo(ObjectRepoFilePath,className);
			//VehicleXpathByName=ObjRepoLoader.getObjectLocator("VehicleXpathByName",ObjRepoMap );
			//VehicleXpathBySeqNo=ObjRepoLoader.getObjectLocator("VehicleXpathBySeqNo",ObjRepoMap );
			FirstVehicleReportedOffset=ObjRepoLoader.getObjectLocator("FirstVehicleReportedOffset",ObjRepoMap );
	}
	
	public boolean checkVehicleListLoaded()
	{
		try
    	{
    		waiter.until(ExpectedConditions.visibilityOfElementLocated(FirstVehicleReportedOffset));
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
    /*
    public void RClickVehicle(LinkedHashMap<String, String> clickVehicleDataMap)
    {
    	int vehicleSeqNo;
    	String vehicleName;
    	
    	System.out.println("Inside vehiclesListScreen --> RClickVehicle Func  ");
    	
    	System.out.println("clickVehicleDataMap.get(vehicleSeqNo) = "+clickVehicleDataMap.get("vehicleSeqNo"));
    	System.out.println("clickVehicleDataMap.get(vehicleName) = "+clickVehicleDataMap.get("vehicleName"));
    	if(clickVehicleDataMap.get("vehicleSeqNo")!= null) 
    	{	
    		 
    		 vehicleSeqNo = Integer.parseInt((clickVehicleDataMap.get("vehicleSeqNo")));
    		 System.out.println("IF loop : Integer.parseInt of vehicleSeqNo = "+ vehicleSeqNo);
    		 VehicleXpathStringBySeqNo = "//*[@id='app']/descendant::table/tbody//tr["+vehicleSeqNo+"]/td/div/div[3]/div[1]";
    		 waiter.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(VehicleXpathStringBySeqNo)));
    	     driver.findElement(By.xpath(VehicleXpathStringBySeqNo)).click();
    	}
		
    	else
    	{	
    		vehicleName = clickVehicleDataMap.get("vehicleName");
    		 System.out.println("Else Loop :  vehicleName = "+ vehicleName);
    		VehicleXpathStringbyName = "//*[@id='app']/descendant::table/tbody//descendant::div[@title='"+vehicleName+"']";
        	waiter.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(VehicleXpathStringbyName)));
        	driver.findElement(By.xpath(VehicleXpathStringbyName)).click();
    	}
    	
    	
    	
    }
    */
    
    public boolean RClickVehicle(LinkedHashMap<String, String> clickVehicleDataMap) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException, CloneNotSupportedException
    {
    	VehExtnDetails=new VehicleExtendedDetailsScreen(driver,waiter);
    	boolean clickVehiclePASS;
    	
    	System.out.println("Inside vehiclesListScreen --> RClickVehicle Func  ");
    	
    	System.out.println("clickVehicleDataMap.get(vehicleSeqNo) = "+clickVehicleDataMap.get("vehicleSeqNo"));
    	System.out.println("clickVehicleDataMap.get(vehicleName) = "+clickVehicleDataMap.get("vehicleName"));
    	if(clickVehicleDataMap.get("vehicleSeqNo")!= null) 
    	{	
    		 
    		 vehicleSeqNo = Integer.parseInt((clickVehicleDataMap.get("vehicleSeqNo")));
    		 System.out.println("IF loop : Integer.parseInt of vehicleSeqNo = "+ vehicleSeqNo);
    		 //VehicleXpathStringBySeqNo = "//*[@id='app']/descendant::table/tbody//tr["+vehicleSeqNo+"]/td/div/div[3]/div[1]";
    		 waiter.until(ExpectedConditions.visibilityOfElementLocated(this.VehicleXpathBySeqNo));
    	     driver.findElement(this.VehicleXpathBySeqNo).click();
    	     clickVehiclePASS=VehExtnDetails.checkExtendedDetailsLoaded(vehicleSeqNo);
    	}
		
    	else
    	{	
    		vehicleName = clickVehicleDataMap.get("vehicleName");
    		 System.out.println("Else Loop :  vehicleName = "+ vehicleName);
    		 VehicleXpathByName=ObjRepoLoader.getObjectDynLocator("VehicleXpathByName",ObjRepoMap,this);
    		//VehicleXpathStringbyName = "//*[@id='app']/descendant::table/tbody//descendant::div[@title='"+vehicleName+"']";
        	//waiter.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(VehicleXpathStringbyName)));
    		 waiter.until(ExpectedConditions.visibilityOfElementLocated(this.VehicleXpathByName));
        	//driver.findElement(By.xpath(VehicleXpathStringbyName)).click();
    		 driver.findElement(this.VehicleXpathByName).click();
    		 clickVehiclePASS=VehExtnDetails.checkExtendedDetailsLoaded(vehicleName);
    	}
    	
    	return clickVehiclePASS;
    	
    }

	
}
