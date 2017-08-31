package testClasses;


import static org.testng.Assert.assertEquals;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import pages.Dashboard;
import pages.login;
import pages.userHomePage;
import pages.vehiclePages.VehicleCreateRepossessionScreen;
import pages.vehiclePages.VehicleEditPropertiesScreen;
import pages.vehiclePages.VehicleExtendedDetailsScreen;
import pages.vehiclePages.VehicleFullDetailsScreen;
import utils.ObjectRepoLoader;
import utils.PropertiesFileLoader;
import utils.RunEngineReader;
import utils.TestcaseListReader;
import utils.stepLoader;

/*
 * This is the main class where the Flow starts
 * We Read all the config File and input data file 
 * and call Step Handler for Handling Each Step 
 * of the Input Data Test cases
 */

public class RunEngine 
  {

	public static  WebDriver driver;
	public static WebDriverWait waiter;
	public static FirefoxProfile profile;
	public static String userName;
	
	// Different Class Objects Declaration  
	static login objLogin;
	static userHomePage objHomePage;
	static VehicleExtendedDetailsScreen VEDS;
	static Dashboard dash;
	static VehicleFullDetailsScreen VFDS;
	static VehicleEditPropertiesScreen VEPS;
	static VehicleCreateRepossessionScreen VCRS;
	static TestcaseListReader TCListReader;
	static RunEngineReader  RunEngineStepsReader;
	static stepLoader stepReader;
	static StepHandler actionHandler;
	
	//Declaring propLoader (Object of PropertiesFileLoader class) For Handling Various Properties File  
	static PropertiesFileLoader propLoader;
	static ObjectRepoLoader ObjRepoLoader;  
	
	static File file ;
	static String GeckoDriverPath;
	static String ChromeDriverPath;
	static String EdgeDriverPath;
	static String IEDriverPath;
	
	static Map<String,String>TC_List;
	static Set<String>TC_IDs;
	static LinkedHashMap<String, List<String>> TCwithSteps;
	static LinkedHashMap<String,String> StepData;
	static LinkedHashMap<String,String> ObjRepoMap;
	List<String>stepsList;

		 	
	 
	 
	 static String URL ; 		
	 static String Username ;  	
	 static String Password ; 		
	 
	  	  
	 /*
	  *  To get Different File Paths i.e Selenium Project Path ,Input Data  File Paths
	  */
	   
	 static String projectPath = null;
	 static String initConfigFilePath = null;
	 static String InputDataFilePath = null;
	 static String ObjectRepoFilePath=null;
	 
	 static String Browser=null;
	
	 
	 static int tcPASS=0;
	 static int tcFAIL=0;
	 static int tcTOTAL=0;
	 
	 
	 public static void main(String[] args) throws InterruptedException, ClassNotFoundException 
	 {
		    //setup() --- START
		 	List<String>stepsList;
		 	String[] SplitStep;  
		 	String SheetName;
		 	String SplitStepName;
		 	String PageClassName;
		 	String MethodToPageMapFilePath;
		 	Properties testConfigProp;
		 	Properties MethodToPageMappingProp;
		 	boolean stepSuccess = false;
		 	boolean TestcasePASS=true;
		 	//Get the  LOR Automation Directory/Folder  Path   
		 	projectPath = System.getProperty("user.dir");
		 	
		 	//From the LOR Directory , traverse to config Folder to get the Config File Path 
		 	initConfigFilePath = projectPath+"\\Config\\testConfig.properties";
		 	MethodToPageMapFilePath=projectPath+"\\Config\\MethodToPageMapping.properties";
		 	ObjectRepoFilePath = projectPath+"\\ObjectRepository\\ObjectRepo.properties";
		 	InputDataFilePath = projectPath+"\\TestData\\InputTestData.xlsx";
		 	
		 	
		 	testConfigProp=propLoader.loadTestConfigProperties(initConfigFilePath);
		 	MethodToPageMappingProp=propLoader.loadTestConfigProperties(MethodToPageMapFilePath);
		 	System.out.println("MethodToPageMappingProp = "+MethodToPageMappingProp.toString());
	
		 	
		 	TCListReader = new TestcaseListReader();
		 	
		 	/*
		 	 *TCListHandler.getTestcaseListToExecute Function Returns a list  of Test cases from
		 	 *  Input Data Sheet-> TestcaseList Tab having Execute = Y
		 	 */
		 	 
		 	TC_List=TCListReader.getTestcaseListToExecute(InputDataFilePath, "TestcaseList");
		 	/*
		 	 * From the Map of Executable testcases , get just the TestcaseID's(TC_list.Keys)List
		 	 *   for Further Processing  
		 	 */
		 	TC_IDs=TC_List.keySet();
		 	
		 	int TC_IDs_size;
		 	TC_IDs_size = TC_IDs.size();
		 	//System.out.println("size of TestcaseSet  Retruned by TCListReader  = "+TC_IDs_size);
		 	
		 	RunEngineStepsReader = new RunEngineReader();
		 	
		 	/*
		 	 * Invoke Func getTestcaseSteps() and Pass the Excel sheet Path,Sheet Name  ,Set of SetCaseId's
		 	 * as Input  for Getting the Steps for all Testcase ID's in the TestcaseID Set 
		 	 *  It Returns a Map with Testcase ID  and the List of Steps as Value
		 	 */
		 	TCwithSteps=RunEngineStepsReader.getTestcaseSteps(InputDataFilePath, "RunEngine", TC_IDs);
		 	System.out.println("TCwithSteps="+TCwithSteps);
		 	Browser = testConfigProp.getProperty("Browser");
		 	URL = testConfigProp.getProperty("URL");
			if(Browser.equalsIgnoreCase("Firefox"))
			{
				GeckoDriverPath = testConfigProp.getProperty("GeckoDriverPath");
				System.setProperty("webdriver.gecko.driver",GeckoDriverPath);
				driver = new  FirefoxDriver();
				
			}
			else if(Browser.equalsIgnoreCase("Chrome"))
			{
				ChromeDriverPath= testConfigProp.getProperty("ChromeDriverPath");
				System.setProperty("webdriver.chrome.driver",ChromeDriverPath);
				driver = new ChromeDriver();
			}
			else if(Browser.equalsIgnoreCase("Edge"))
			{
				EdgeDriverPath=testConfigProp.getProperty("EdgeDriverPath");
				System.setProperty("webdriver.edge.driver",EdgeDriverPath);
				driver = new EdgeDriver();
			}
			else
			{
				IEDriverPath= testConfigProp.getProperty("IEDriverPath");
				System.setProperty("webdriver.ie.driver",IEDriverPath);
				driver = new InternetExplorerDriver();
			}
			waiter = new WebDriverWait(driver, 40);
   	        
   	        driver.get(URL);
   	        driver.manage().window().maximize();
   	        //StepHandler Object for Handling Each step of the Testcase
			actionHandler = new StepHandler(driver,waiter,testConfigProp);
			ObjRepoLoader = new ObjectRepoLoader();
			
			//For Each Testcase ID  in the List of Test cases  to be Executed  
		 	for( String TCid : TCwithSteps.keySet() )
		 	{
		 		Thread.sleep(3000);
		 		stepsList =new ArrayList<String>();
		 		//Get the Steps List  for the current TestcaseID
		 		stepsList=TCwithSteps.get(TCid);
		 		System.out.println("TC Id= "+TCid);
		 		System.out.println("stepsList= "+stepsList);
		 		System.out.println();
		 		System.out.println();
		 		System.out.println("stepsList.size = "+stepsList.size());
		 		
		 		//For each Step in the Steps List of the current Testcase(Testcase ID)
				for (int i = 0; i < stepsList.size(); i++) 
				{
					String StepName;
					
					//Get the Current StepName
					StepName=stepsList.get(i);
				    
					//If the Current Step Name Starts with NavigateTo then It has to be Handled Differently
					if(StepName.startsWith("NavigateTo"))
					{
						
						System.out.println("MOST REQUIRED Inside If StepName.startsWith(NavigateTo) ");
						System.out.println("NavigateToStepName = "+StepName);
						
						//Get the Class Name to which the Steps Belongs to   from the MethodToPageMappingPropertes   
						PageClassName=MethodToPageMappingProp.getProperty(StepName);
						
						System.out.println("MOST REQUIRED IF navigate loop PageClassName="+PageClassName);
						//Get the ObjRepo's  for the specified ClassName  
						ObjRepoMap=ObjRepoLoader.loadObjectRepo(ObjectRepoFilePath, PageClassName);
						
						//Invoke the InvokeNavigateMethodForStep Method for Handling the Navigate Method
						stepSuccess=actionHandler.InvokeNavigateMethodForStep(StepName,PageClassName);
						
					}
					
					//If the Step is not a NavigateTo Step i.e Other Normal Step 
					else
					{	
						System.out.println("MOST REQUIRED Inside Else of StepName.startsWith(NavigateTo) ");
						
						//Split the StepName Ex RloginToLOR_01 to Fetch the sheet/Func Name i.e RloginToLOR  
						SplitStep=StepName.split("_");
						
						SheetName=SplitStep[0];
						
						System.out.println("Step No"+i+" : SheetName="+SheetName);
						//Get the Class Name to which the Steps Belongs to   from the MethodToPageMappingPropertes 
						PageClassName=MethodToPageMappingProp.getProperty(SheetName);
						System.out.println("MOST REQUIRED Else  navigate loop PageClassName="+PageClassName);
						stepReader=new stepLoader();
						/*
						 * Get the Data For the Step  using getStepDataFromSheet
						 * Pass Inputs : Input Data Sheet Name , Sheet Name : RloginToLOR, Step Name : RloginToLOR_01 
						 * getStepDataFromSheet : It Returns a Map of input data for the step in key value format
						 * Ex: for   RloginToLOR  It returns Map of key=username value=aricent_qa ,key = password ,value=Ar1cent!  
						 */
						StepData=stepReader.getStepDataFromSheet(InputDataFilePath, SheetName, StepName);
						
						System.out.println("StepData for Step No "+ (i+1)+" -->  " +StepData);
						
						ObjRepoMap=ObjRepoLoader.loadObjectRepo(ObjectRepoFilePath, PageClassName);
						
						stepSuccess=actionHandler.InvokeMethodForStep(SheetName,PageClassName,StepData);
					}	
				 	Thread.sleep(3000);
				 	//Assert.assertEquals(stepSuccess, true);
				 	
				 	if(stepSuccess!=true)
				 	{
				 		
				 		break;
				 	}
				}
		 		TestcasePASS=TestcasePASS && stepSuccess;
		 		//Assert.assertEquals(TestcasePASS, true);
		 		tcTOTAL=tcTOTAL+1;
		 		if(TestcasePASS==true)
		 		{
		 			tcPASS=tcPASS+1;
		 		}
		 		else
		 		{
		 			tcFAIL=tcFAIL+1;
		 		}
		 		
		 	}
		 	System.out.println("********* TC EXECUTION REPORT ************* ");
		 	System.out.println("TOTAL TC EXECUTED = "+tcTOTAL);
		 	System.out.println("TOTAL TC PASSED = "+tcPASS);
		 	System.out.println("TOTAL TC FAILED = "+tcFAIL);
	 }
	
	 

}	 
		   
