package pages;


import java.util.LinkedHashMap;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.ObjectRepoLoader;

public class login
{
	WebDriver driver;
	WebDriverWait waiter;
	static ObjectRepoLoader ObjRepoLoader;
	static userHomePage homePage;
	
	By LORusername=null;
	By LORpassword=null;
	By LORsignInButton=null;
	By LORSignUpLink=null;
	
	String username;
	String password;
	String signInButton;
	String locator;
	static String projectPath;
	static String ObjectRepoFilePath=null;
	static String className;
	
	LinkedHashMap<String,String> ObjRepoMap;
	
	public login(WebDriver driver2,WebDriverWait waiter2) throws RuntimeException, IllegalArgumentException, ReflectiveOperationException
	{
		
		className="pages.login";
		projectPath = System.getProperty("user.dir");
		ObjectRepoFilePath=projectPath+"\\ObjectRepository\\ObjectRepo.properties";
		System.out.println("From Reflection: Login constructor called");
		this.driver = driver2;
		this.waiter = waiter2;
		ObjRepoLoader = new ObjectRepoLoader();
		this.ObjRepoMap=ObjRepoLoader.loadObjectRepo(ObjectRepoFilePath,className);
		LORusername=ObjRepoLoader.getObjectLocator("username",ObjRepoMap );
		LORpassword=ObjRepoLoader.getObjectLocator("password",ObjRepoMap );
		LORsignInButton=ObjRepoLoader.getObjectLocator("signInButton",ObjRepoMap );
		LORSignUpLink=ObjRepoLoader.getObjectLocator("signUpLink",ObjRepoMap );
		homePage=new userHomePage(driver2, waiter2);		
	}
	//Set user name in textbox

	public void setUserName(String strUserName)
	{
		System.out.println("Login.setUserName  called");
		waiter.until(ExpectedConditions.visibilityOfElementLocated(LORusername));
		driver.findElement(LORusername).sendKeys(strUserName);

	}
	//Set password in password textbox

	public void setPassword(String strPassword)
	{
		System.out.println("Login.setPassword  called");
		driver.findElement(LORpassword).sendKeys(strPassword);

	}
	//Click on login button

	public void clickLogin()
	{

        driver.findElement(LORsignInButton).click();

	}

	public void clickSignUp()
	{

        driver.findElement(LORSignUpLink).click();

	}
	
	
	//Get the title of Login Page

    public String getLoginTitle(){

     return    driver.getTitle();

    }

	/**

 	* This POM method will be exposed in test case to login in the application

 	* @param strUserName

 	* @param strPasword

 	* @return

 	*/

	public void loginToLOR(String strUserName,String strPasword)
	{
		
		 System.out.println("Login.loginToLOR  called"); 
		// waiter.until(ExpectedConditions.visibilityOfElementLocated(LORusername));
		//Fill user name

		this.setUserName(strUserName);

		//Fill password

		this.setPassword(strPasword);

		//Click Login button

		this.clickLogin();        

    

	}
	
	public boolean RloginToLOR(LinkedHashMap<String, String> loginDataMap)
	{
		
		String strUserName = loginDataMap.get("username");
		String strPasword = loginDataMap.get("password");
		boolean loginSuccess;
		System.out.println("Login.loginToLOR  called"); 
		// waiter.until(ExpectedConditions.visibilityOfElementLocated(LORusername));
		//Fill user name

		this.setUserName(strUserName);

		//Fill password

		this.setPassword(strPasword);

		//Click Login button

		this.clickLogin();        

		loginSuccess=homePage.checkIfLoggedInUserDisplayed();
    
		return  loginSuccess;

	}


}

