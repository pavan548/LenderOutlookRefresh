package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ExcelReader {

	
	List<Map<String,List<String>>>  TestcaseList;
	Map<String, List<String>> Testcase = new HashMap<String, List<String>>();
	
	public ExcelReader() {
		// TODO Auto-generated constructor stub
		TestcaseList = new ArrayList<Map<String,List<String>>>();
	}
	 
	 public void getDataFromExcel(String ExcelFileName,String SheetName)   
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
	                
	                System.out.println("currentRow.getRowNum()"+currentRow.getRowNum());
	                
	                if(currentRow.getRowNum()==0)
	                {
	                	continue;
	                }
	                System.out.println("Row.FirstColumn = "+FirstColumn);
	                if (FirstColumn.equalsIgnoreCase("NO"))
	                {
	                	continue;
	                }
	                Iterator<Cell> cellIterator = currentRow.iterator();
	                
	                while (cellIterator.hasNext()) 
	                {
				 
	                	Cell currentCell = cellIterator.next();
	                	
	                	 
		                System.out.print("currentCell.getColumnIndex()"+currentCell.getColumnIndex()+"###");
	                	//getCellTypeEnum shown as deprecated for version 3.15
	                	//getCellTypeEnum ill be renamed to getCellType starting from version 4.0
	                	if (currentCell.getCellTypeEnum() == CellType.STRING) 
	                	{
	                		System.out.println(currentCell.getStringCellValue() + "--");
	                	} 
	                	else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) 
	                	{
	                		System.out.println(currentCell.getNumericCellValue() + "--");
	                	}
	                }	
             }
             System.out.println();

			 
		 }
		 catch (FileNotFoundException e)
		 {
	         e.printStackTrace();
	     } 
		 catch (IOException e) 
		 {
	         e.printStackTrace();
	     }
	 }
}
