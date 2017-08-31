package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


import java.util.Iterator;
import java.util.LinkedHashMap;

/*
 * This class has Implementation for reading the InputDataFile.TestcaseList Sheet 
 * and Fetching List of all the Test cases that have to be Executed     
 */
public class TestcaseListReader 
{

	static String SheetName = "TestcaseList";
	
	LinkedHashMap<String,String> Testcase;
	String TestcaseID;
	String TestcaseDesc;
	int TestcaseCount;
	
	public TestcaseListReader() 
	{
		// TODO Auto-generated constructor stub
		
		Testcase = new LinkedHashMap<String,String>();
	}
	 
	/*
	 *  Get the List of all Test cases from the Input Data Excel sheet.sheetName that have to be Executed
	 *  i.e having Execute = Y 
	 *  It Returns a Map (of <String,String>) with Test case ID as a Key 
	 *  and the Test case Description as Value
	 *  
	 *  Usage : getTestcaseListToExecute(InputDataFilePath,"TestcaseList")
	 */
	 public LinkedHashMap<String,String> getTestcaseListToExecute(String ExcelFileName,String SheetName)   
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
			 System.out.println("No of Rows ="+rowCount);
			 while (iterator.hasNext())
			 {
				 	
	                Row currentRow = iterator.next();
	                FirstColumn=currentRow.getCell(0).toString();
	                System.out.println();
	                
	                //System.out.println("currentRow.getRowNum()"+currentRow.getRowNum());
	                
	                //If Current Row No = 0 , its a Header , Hence we should continue to next Row 
	                if(currentRow.getRowNum()==0)
	                {
	                	continue;
	                }
	                //System.out.println("Row.FirstColumn = "+FirstColumn);
	                
	                /*
	                 * If First Column (i.e Execute = YES/NO) of the Row is NO  , 
	                 * then no need to add it to the List   
	                 */
	                if (FirstColumn.equalsIgnoreCase("NO"))
	                {
	                	continue;
	                }
	                
	                Iterator<Cell> cellIterator = currentRow.iterator();
	                
	                //Iterated over Each Column of the Current Row
	                while (cellIterator.hasNext()) 
	                {
				 
	                	Cell currentCell = cellIterator.next();
	                	
	                	/*
	                	 * In the Testcase List Sheet , Testcase ID is the Second Column (Index = 1)		                 	                	
	                	 */
		                if(currentCell.getColumnIndex()==1)
		                {
		                	
		                	TestcaseID = currentCell.getStringCellValue();
		                }

	                	/*
	                	 * In the Testcase List Sheet , Testcase Desc is the Third Column (Index = 2)
	                	 * 		                 	                	
	                	 */
		                
		                if(currentCell.getColumnIndex()==2)
		                {
		                	
		                	TestcaseDesc = currentCell.getStringCellValue();
		                }
		                
	                	
	                }	
	                //In the Map , Put the Testcase ID as Key and Testcase Desc as Value  
	                Testcase.put(TestcaseID, TestcaseDesc);
             }

			 
             TestcaseCount=Testcase.size();
             System.out.println("Map size = "+TestcaseCount);
             workbook.close();
             
		 }
		 catch (FileNotFoundException e)
		 {
	         e.printStackTrace();
	     } 
		 catch (IOException e) 
		 {
	         e.printStackTrace();
	     }
		 return Testcase;
	 }
}
