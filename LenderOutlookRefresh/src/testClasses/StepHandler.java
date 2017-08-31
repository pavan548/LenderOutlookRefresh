package testClasses;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


/*
 * This Class is For Handling Each Step of a Test case 
 * This class is called from RunEngine
 */

public class StepHandler 
{
	static LinkedHashMap<String,String> StepData;
	
	static WebDriver driver;
	static WebDriverWait waiter;
	static Properties configProp; 
	 // To get Selenium Project Path 
	static String projectPath = null;
	static String initConfigFilePath = null;
	
	
	
	public StepHandler(WebDriver driver2,WebDriverWait waiter2,Properties prop)
	{
		// TODO Auto-generated constructor stub
		StepData = new LinkedHashMap<String,String>();
		this.driver = driver2;
		this.waiter = waiter2;
		projectPath = System.getProperty("user.dir");
	 	//System.out.println("projectPath = "+projectPath);
	 	initConfigFilePath = projectPath+"\\Config\\testConfig.properties";
	 	
	}

	/*
	 * This Method is for Handling Normal Steps (Functions)  of a test case 
	 */
	public boolean InvokeMethodForStep(String StepName,String pageClassName,LinkedHashMap<String,String> StepInputData) throws InterruptedException, ClassNotFoundException
	{
		//StepData = StepInputData;
		boolean stepSuccess = false;
		String ClassName = pageClassName;
		System.out.println("StepHandler.InvokeMethodForStep().ClassName ="+ClassName);
		Class<?> pageClass;  // convert string classname to class
		
		String MethodName=StepName;
		System.out.println("InvokeMethodForStep Func,StepNameMethodName= "+MethodName);
		System.out.println("InvokeMethodForStep Func,ClassName= "+ClassName);
		try
		{
			pageClass = Class.forName(pageClassName);
			System.out.println("Before Constructor");
			Constructor<?> pageCons = pageClass.getConstructor(org.openqa.selenium.WebDriver.class,waiter.getClass());
			System.out.println("After Constructor , Before Object ");
			Object pageObject = pageCons.newInstance(driver,waiter);
			System.out.println(" After Object , pageObject.getClass()= " +pageObject.getClass().toString());
			System.out.println("After Constructor , After Object , Before Method declaration");
			
			System.out.println("pageObject.getClass()="+pageObject.getClass());
			Method[] loginMethods = pageClass.getDeclaredMethods();
			
			System.out.println("Declared Methods are: " + Arrays.toString(loginMethods));
			 
			Method pageMethod1=((Class<?>) pageObject.getClass()).getDeclaredMethod(MethodName,new Class[]{java.util.LinkedHashMap.class});
			//Method method1=
			System.out.println("After Object , After Method declaration,Before Method Invocation");
			stepSuccess=(boolean) pageMethod1.invoke(pageObject,StepInputData);
			
		}
		catch (ClassNotFoundException e) 
		{
			stepSuccess=false;
            e.printStackTrace();
        } 
		catch (NoSuchMethodException nsme)
		{
			stepSuccess=false;
            nsme.printStackTrace();
        } 
		catch (IllegalAccessException iae) 
		{
			stepSuccess=false;
            iae.printStackTrace();
        }
		catch (InstantiationException ie)
		{
			stepSuccess=false;
            ie.printStackTrace();
        } 
		catch (InvocationTargetException ite) 
		{
			stepSuccess=false;
            ite.printStackTrace();
        }
		return stepSuccess;
	

	}
	
	/*
	 * This Method is for Handling Navigation Related Steps (Functions)  of a test case 
	 */
	
	public boolean InvokeNavigateMethodForStep(String StepName,String pageClassName) throws InterruptedException, ClassNotFoundException
	{
		boolean stepSuccess = false;
		Class<?> pageClass;  // convert string classname to class
			
		String MethodName=StepName;
		String ClassName = pageClassName;
		
		System.out.println("InvokeMethodForStep Func,StepNameMethodName= "+MethodName);
		System.out.println("InvokeMethodForStep Func,ClassName= "+ClassName);
		try
		{
			pageClass = Class.forName(pageClassName);
			System.out.println("Before Constructor");
			Constructor<?> pageCons = pageClass.getConstructor(org.openqa.selenium.WebDriver.class,waiter.getClass());
			System.out.println("After Constructor , Before Object ");
			Object pageObject = pageCons.newInstance(driver,waiter);
			System.out.println(" After Object , pageObject.getClass()= " +pageObject.getClass().toString());
			System.out.println("After Constructor , After Object , Before Method declaration");
			
			System.out.println("pageObject.getClass()="+pageObject.getClass());
			Method[] loginMethods = pageClass.getDeclaredMethods();
			
			System.out.println("Declared Methods are: " + Arrays.toString(loginMethods));
			 
			Method pageMethod1=((Class<?>) pageObject.getClass()).getDeclaredMethod(MethodName,new Class[]{});
			//Method method1=
			System.out.println("After Object , After Method declaration,Before Method Invocation");
			stepSuccess=(boolean) pageMethod1.invoke(pageObject);
			return stepSuccess;
		}
		catch (ClassNotFoundException e) 
		{
            e.printStackTrace();
            stepSuccess=false;
        } 
		catch (NoSuchMethodException nsme)
		{
            nsme.printStackTrace();
            stepSuccess=false;
        } 
		catch (IllegalAccessException iae) 
		{
            iae.printStackTrace();
            stepSuccess=false;
        }
		catch (InstantiationException ie)
		{
			stepSuccess=false;
            ie.printStackTrace();
            
        } 
		catch (InvocationTargetException ite) 
		{
			stepSuccess=false;
            ite.printStackTrace();
        }
		return stepSuccess;
	

	}
	
}
