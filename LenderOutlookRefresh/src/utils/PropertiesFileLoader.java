package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


public class PropertiesFileLoader 
{
	 public PropertiesFileLoader()
	 {

	     System.out.println("PropertiesFileLoader Constructor");   
	 }

	public static Properties loadTestConfigProperties(String PropertiesFilePath)
	 {

		    Properties prop = new Properties();
		    File file ;
		 	//file = new File("C:\\laptop\\prep\\New_Eclipse_Workspace\\LOR\\Config\\init.properties");
		 	file = new File(PropertiesFilePath); 
		 	
		    FileInputStream fileInput = null;
		    
			try 
			{
				fileInput = new FileInputStream(file);
			}
			catch (FileNotFoundException e) 
			{
				e.printStackTrace();
			}
			
							
			try 
			{
				prop.load(fileInput);
			}
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			return prop;
		}

}
