package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;


public class stepLoader {
	
	//LinkedHashMap<String, LinkedHashMap<String,String>> StepDataList;
	LinkedHashMap<String,String>DataList;
	
	public stepLoader() {
		// TODO Auto-generated constructor stub
		DataList = new LinkedHashMap<String,String>();
	}
	 
	 public LinkedHashMap<String, String> getStepDataFromSheet(String ExcelFileName,String SheetName,String StepName)   
	 {
		System.out.println(" At start of getStepDataFromSheet Func ");  
		int rowCount;
		String FirstColumn;
		String FieldValue;
		
		String dataKey;
		String dataValue;
		 try
		 {
			 FileInputStream excelFile = new FileInputStream(new File(ExcelFileName));
			 Workbook workbook = new XSSFWorkbook(excelFile);
			 Sheet datatypeSheet = workbook.getSheet(SheetName);
			 Iterator<Row> iterator = datatypeSheet.iterator();
			 rowCount=datatypeSheet.getPhysicalNumberOfRows();
			 //System.out.println("No of Rows ="+rowCount);
			 while (iterator.hasNext())
			 {
				 	
	                Row currentRow = iterator.next();
	                FirstColumn=currentRow.getCell(0).toString();
	                System.out.println();
	                
	                //System.out.println("currentRow.getRowNum()"+currentRow.getRowNum());
	                
	                if(currentRow.getRowNum()==0)
	                {
	                	continue;
	                }
	                //System.out.println("Row.FirstColumn = "+FirstColumn);
	                //System.out.println("Passed StepName = "+StepName);
	                if (FirstColumn.equalsIgnoreCase(StepName)==false)
	                {
	                	continue;
	                }
	                Iterator<Cell> cellIterator = currentRow.iterator();
	                
	                while (cellIterator.hasNext()) 
	                {
	                	FieldValue=null;
	                	
	                	Cell currentCell = cellIterator.next();
	                	
	                	//System.out.print("currentCell.getColumnIndex()"+currentCell.getColumnIndex()+"###");
	                	// System.out.print("currentCell.getColumnIndex()"+currentCell.getColumnIndex()+"###");
		                if(currentCell.getColumnIndex()>0)
		                {
		                	//System.out.print("Inside currentCell.getColumnIndex()>0 ");
		                	FieldValue = currentCell.getStringCellValue();
		                	//System.out.print(" FieldValue = "+FieldValue);
		                	String[] SplitField = FieldValue.split("=");
		                	dataKey = SplitField[0];
		                	dataValue=SplitField[1];
		                	
		                	DataList.put(dataKey, dataValue);
		                }
		               
	                	
	                }	
             }
             System.out.println();
             //StepDataList.put(StepName, DataList);
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
		 return DataList;
	 }
}
