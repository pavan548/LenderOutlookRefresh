package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import java.util.Set;

/*
 * This Class has Functions for Getting the Steps for each TestcaseID of a TestcaseID List
 * from the InputData Sheet 
 */

public class RunEngineReader 
{

	static String SheetName = "RunEngine";
	
	LinkedHashMap<String, List<String>> TestcaseStepsList;
	List<String>stepsList;
	String TestcaseID;
	String stepName;
	int TestcaseCount;
	
	public RunEngineReader() 
	{

		TestcaseStepsList =  new LinkedHashMap<String, List<String>>();
	}
	
	/*
	 * Takes Input as Tescase ID List and for each Testcase ID ( as Key) , it Fetches the List of Steps (as Value)
	 * Other Inputs are  Input Data Excel sheet Path and the Work Sheet name from which to Fetch the Steps Data
	 * Usage : getTestcaseSteps("InputDataFilePath", "RunEngine", TC_ID_Set)      
	 */
	 public LinkedHashMap<String, List<String>> getTestcaseSteps(String ExcelFileName,String SheetName,Set<String> TC_ID_Set)   
	 {
		int rowCount;
		String FirstColumn;
		
		 try
		 {
			 FileInputStream excelFile = new FileInputStream(new File(ExcelFileName));
			 Workbook workbook = new XSSFWorkbook(excelFile);
			 Sheet datatypeSheet = workbook.getSheet(SheetName);
			 Iterator<Row> iterator = datatypeSheet.iterator();
			 rowCount=datatypeSheet.getPhysicalNumberOfRows();
			 //System.out.println("No of Rows ="+rowCount);
			 
			 //Iterate over the Rows 
			 while (iterator.hasNext())   
			 {
				    stepsList =new ArrayList<String>();
				    
	                Row currentRow = iterator.next();
	                
	                System.out.println();
	                
	                //System.out.println("currentRow.getRowNum()"+currentRow.getRowNum());
	                
	                //If Current Row No = 0 , its a Header , Hence we should continue to next Row 
	                if(currentRow.getRowNum()==0)
	                {
	                	continue;
	                }
	                
	                //Fetch the First Column(TestcaseID )  of  the current Row(Test case) in the Sheet   
	                FirstColumn=currentRow.getCell(0).toString();
	                
	                //System.out.println("Row.FirstColumn = "+FirstColumn);
	                
	                /*
	                 * If the currentRow.TestcaseID  is NOT contained in the given set of 
	                 * Executable testcase ID's , we should Ignore it and move to Next row 
	                 * in the Sheet     
	                 */
	                
	                if (TC_ID_Set.contains(FirstColumn)== false)
	                {
	                	continue;
	                }
	                
	                
	                Iterator<Cell> cellIterator = currentRow.iterator();
	                
	               //Iterate over the Columns for the current Row 
	                while (cellIterator.hasNext()) 
	                {
				 
	                	Cell currentCell = cellIterator.next();
	                	
	                	 
		                //Testcase ID is always the First Column (index 0 ) 
	                	
		                if(currentCell.getColumnIndex()==0)
		                {
		                	
		                	TestcaseID = currentCell.getStringCellValue();
		                }
		                
		                /* 
		                 * if Column Index is >0 , it means they are steps of the Testcase
		                 * Get all the subsequent Column Values i.e Steps (Till Not Blank)  and 
		                 * Add the Steps to a List      
		                 */
		                else
		                {
		                	stepName= currentCell.getStringCellValue();
		                	stepsList.add(stepName);
		                	
		                }
		                
	                	
	                }	
	                
	                /*
	                 *  In the TestcaseStepsList Map , Put the Testcase ID as the key
	                 *  and the List of Steps as the Value 
	                 */
	                TestcaseStepsList.put(TestcaseID, stepsList);
	               
             }
             System.out.println();
             //TestcaseList.add(Testcase);
			 
             TestcaseCount=TestcaseStepsList.size();
             workbook.close();
             System.out.println("RunEngine testcases with steps  Map(having TC steps) size(no of TC to Execute ) = "+TestcaseCount);
		 }
		 catch (FileNotFoundException e)
		 {
	         e.printStackTrace();
	     } 
		 catch (IOException e) 
		 {
	         e.printStackTrace();
	     }
		 return TestcaseStepsList;
	 }
}
