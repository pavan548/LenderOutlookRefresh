package utils;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import java.util.Enumeration;
import java.util.LinkedHashMap;

import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.By;


public class ObjectRepoLoader 
{
	static PropertiesFileLoader propLoader;
	static Properties ObjRepoProp;
	static LinkedHashMap<String,String> classLocatorsObjRepoMap;
	static Set<?> ObjRepoPropKeySet;
	LinkedHashMap<String,String> ObjRepoMap;
	Set<String> ObjRepoKeySet;
	static String pageClassName;
	
	
	public ObjectRepoLoader()
	{
		
	    System.out.println("ObjectRepoLoader Constructor");   
	    propLoader= new PropertiesFileLoader();
	    classLocatorsObjRepoMap = new LinkedHashMap<String,String>();
	 }

	
	public static LinkedHashMap<String,String> loadObjectRepo(String ObjRepoFilePath,String className )
	{	
		ObjRepoProp=propLoader.loadTestConfigProperties(ObjRepoFilePath);
		Enumeration<?> e = ObjRepoProp.propertyNames();
		pageClassName=className;
		while (e.hasMoreElements()) 
		{
		      String key = (String) e.nextElement();
		      System.out.println(key + " -- " + ObjRepoProp.getProperty(key));
		      System.out.println("Inside loadObjectRepo , classname = "+className);
		      if(key.contains(className))
		      {
		    	    
		    	  String loc[]= key.split(className+".");
		    	  String locatorKey;
		    	  int size = loc.length;
		    	  /*
		    	  System.out.println("inside if cond:key.contains(className) ,loc size ="+size);
		    	  System.out.println("inside if cond:key.contains(className) ,loc[0] ="+loc[0]);
		    	  System.out.println("inside if cond:key.contains(className) ,loc[1] ="+loc[1]);
		    	  */
		    	  locatorKey=loc[size-1];
		    	  /*
		    	  System.out.println("inside if cond:key.contains(className) ,locatorKey ="+locatorKey);
		    	  System.out.println("inside if cond:key.contains(className) ,original key ="+key);
		    	  System.out.println("ObjRepoProp.getProperty(key)= "+ObjRepoProp.getProperty(key));
		    	  */
		    	  classLocatorsObjRepoMap.put(locatorKey,ObjRepoProp.getProperty(key));
		      }
		}
		return classLocatorsObjRepoMap;
	}
	
	public By getObjectLocator(String locatorName,LinkedHashMap<String,String> ClassObjRepoMap) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException
	{
		String locatorType = null;
		String locatorValue = null;
		ObjRepoMap =  ClassObjRepoMap;
		//String DynamicLocatorValue[];
		ObjRepoKeySet=ObjRepoMap.keySet();
		//Object pageClassObj; 
		//System.out.println("Inside getObjectLocator Func: locatorName = "+locatorName);
		//Class<?> pageClass; 
		//String MethodName=StepName;
		
		//System.out.println("Inside getObjectLocator Func:  static variable pageClassName ="+pageClassName);
		
		for (String key : ObjRepoMap.keySet())
		{
			
			if (key.contains(locatorName))
			{
				 //System.out.println("Inside IF Cond:key.contains(locatorName) , key ="+key); 
				 locatorValue = ObjRepoMap.get(key);
				 locatorType = key.split("_")[1];
				
				 break;
			}
		    
		    
		}
		
		
		By locator=null;
		//System.out.println("Before Switch(locatorType) ::");
		switch(locatorType)
		{
		case "id":
			//System.out.println("Inside Switch(locatorType)=id ::locatorValue = "+locatorValue);
			locator = By.id(locatorValue);
			
			break;
		case "name":
			locator = By.name(locatorValue);
			break;
		case "cssSelector":
			locator = By.cssSelector(locatorValue);
			break;
		case "linkText":
			locator = By.linkText(locatorValue);
			break;
		case "partialLinkText":
			locator = By.partialLinkText(locatorValue);
			break;
		case "tagName":
			locator = By.tagName(locatorValue);
			break;
		case "xpath":
			locator = By.xpath(locatorValue);
			//System.out.println("Inside Switch(locatorType)=xpath ::locatorValue = "+locatorValue);
			break;
		}
		return locator;
	}
	
	
	public By getObjectDynLocator(String locatorName,LinkedHashMap<String,String> ClassObjRepoMap,Object classObj) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException
	{
		String locatorType = null;
		String locatorValue = null;
		ObjRepoMap =  ClassObjRepoMap;
		//String DynamicLocatorValue[];
		ObjRepoKeySet=ObjRepoMap.keySet();
		Object pageClassObj; 
		System.out.println("Inside getObjectLocator Func: locatorName = "+locatorName);
		//Class<?> pageClass; 
		//String MethodName=StepName;
		//String ClassName = pageClassName;
		pageClassObj=classObj;
		System.out.println("Inside getObjectLocator Func:  static variable pageClassName ="+pageClassName);
		
		Field classDynVariable;
		String classDynStringVariableValue;
		String replacedLocString; 
		
		//Field[] fields = classObj.getClass().getDeclaredFields();
		
		/*
		for( int i = 0 ; i < fields.length ; i++ )
		{
			fields[i].setAccessible(true);
			System.out.println("Field Name-->"+fields[i].getName()+"\t" 
					+"Field Type-->"+ fields[i].getType().getName()+"\t"
					+"Field Value-->"+ fields[i].get(classObj));
		}
		*/
		for (String key : ObjRepoMap.keySet())
		{
			
			if (key.contains(locatorName))
			{
				 //System.out.println("Inside IF Cond:key.contains(locatorName) , key ="+key); 
				 locatorValue = ObjRepoMap.get(key);
				 locatorType = key.split("_")[1];
				
				 break;
			}
		    
		    
		}
		if(locatorValue.contains("+"))
		{	
			
			if(locatorValue.contains("\'\"+"))
			{
				
				//System.out.println("First IF check for Variable in xpath : vehicle name");
				String[] locatorDynString = null;
				int locDynStringcount;
				locatorDynString=locatorValue.split("\'\"\\+");
				locDynStringcount=locatorDynString.length;
				System.out.println("locDynStringcount : "+locDynStringcount);
				System.out.println("locDynStringcount[1] : "+locatorDynString[1]);
				String locDynVariableStringArr[];
				String locDynVariableString;
				locDynVariableStringArr=locatorDynString[1].split("\\+\"\'");
				locDynVariableString=locDynVariableStringArr[0];
				System.out.println("locDynVariableString = " +locDynVariableString);
				
				//classDynVariable=pageObject.getClass().getDeclaredField(locDynVariableString);
							
				classDynVariable=classObj.getClass().getDeclaredField(locDynVariableString);
				
				classDynStringVariableValue = (String)classDynVariable.get(classObj);
				System.out.println("classDynStringVariable = "+classDynStringVariableValue);
				
				replacedLocString=locatorValue.replaceAll("\"\\+" ,"" );
				replacedLocString=replacedLocString.replaceAll("\\+\"","");
				replacedLocString=replacedLocString.replaceAll( locDynVariableString,classDynStringVariableValue);
				locatorValue=replacedLocString;
				System.out.println("replacedLocString = "+replacedLocString);
				
			}
			
			else if (locatorValue.contains("\"+"))
			{
				//System.out.println("Second IF check for Variable in xpath : vehicle seqNo");
			}	
			
		}
		
		By locator=null;
		//System.out.println("Before Switch(locatorType) ::");
		switch(locatorType)
		{
		case "id":
			//System.out.println("Inside Switch(locatorType)=id ::locatorValue = "+locatorValue);
			locator = By.id(locatorValue);
			
			break;
		case "name":
			locator = By.name(locatorValue);
			break;
		case "cssSelector":
			locator = By.cssSelector(locatorValue);
			break;
		case "linkText":
			locator = By.linkText(locatorValue);
			break;
		case "partialLinkText":
			locator = By.partialLinkText(locatorValue);
			break;
		case "tagName":
			locator = By.tagName(locatorValue);
			break;
		case "xpath":
			locator = By.xpath(locatorValue);
			//System.out.println("Inside Switch(locatorType)=xpath ::locatorValue = "+locatorValue);
			break;
		}
		return locator;
	}
	
	
	
}
