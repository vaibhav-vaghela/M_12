package TestNG;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.EmptyFileException;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbookFactory;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class Parameterization {
	@BeforeTest
public void createfile() {
	 //declare file name to be create   
	 String filename = "C:\\Users\\vaibhav.vaghela\\Desktop\\Training\\product.xlsx";  
	 try (//creating an instance of HSSFWorkbook class  
	HSSFWorkbook workbook = new HSSFWorkbook()) {
		//invoking creatSheet() method and passing the name of the sheet to be created   
		 HSSFSheet sheet = workbook.createSheet("Product");   
		 //creating the 0th row using the createRow() method  
		 HSSFRow rowhead = sheet.createRow((short)0);  
		 //creating cell by using the createCell() method and setting the values to the cell by using the setCellValue() method  
		 rowhead.createCell(0).setCellValue("P_ID");  
		 rowhead.createCell(1).setCellValue("Product Name");   
		 rowhead.createCell(2).setCellValue("Price");  
		 rowhead.createCell(3).setCellValue("Quantity");
		 FileOutputStream fileOut = new FileOutputStream(filename);  
		 workbook.write(fileOut);  
		 //closing the Stream  
		 fileOut.close();  
		 //closing the workbook  
		 workbook.close();  
		 //prints the message on the console  
		 System.out.println("Excel file has been generated successfully.");}
	 catch (IOException e) {
		
		e.printStackTrace();
	} }
	
@Test(priority = 1, groups = "crud")
@Parameters("FPath")
public void addData(String FPath) throws EncryptedDocumentException,EmptyFileException, IOException {
	  File xlsxFile = new File(FPath);
	 
	  Object[][] newStudents = {
             {"1", "Puma Shoes", "7000", 1},
             {"2", "Bata Shoes", "6500", 2}
     };
	  
	  try {
         //Creating input stream
         FileInputStream inputStream = new FileInputStream(xlsxFile);
          
         //Creating workbook from input stream
         HSSFWorkbook workbook = (HSSFWorkbook) HSSFWorkbookFactory.create(inputStream);

         //Reading first sheet of excel file
         HSSFSheet sheet = (HSSFSheet) workbook.getSheetAt(0);

         //Getting the count of existing records
         int rowCount = sheet.getLastRowNum();

         //Iterating new students to update
         for (Object[] student : newStudents) {
              
             //Creating new row from the next row count
             Row row = sheet.createRow(++rowCount);

             int columnCount = 0;

             //Iterating student informations
             for (Object info : student) {
                  
                 //Creating new cell and setting the value
                 Cell cell = row.createCell(columnCount++);
                 if (info instanceof String) {
                     cell.setCellValue((String) info);
                 } else if (info instanceof Integer) {
                     cell.setCellValue((Integer) info);
                 }
             }
         }
         //Close input stream
         inputStream.close();
         
         //Creating output stream and writing the updated workbook
         FileOutputStream outputStream = new FileOutputStream(xlsxFile);
         workbook.write(outputStream);
          
         //Close the workbook and output stream
         workbook.close();
         outputStream.close();
          
         System.out.println("Excel file has been updated successfully.");
          
     } catch (EncryptedDocumentException | IOException e) {
         System.err.println("Exception while updating an existing excel file.");
         e.printStackTrace();
     }
 }
 
 @Test(priority = 2, groups = "crud")
public void readData(){
	  try  
	  {  
	  File file = new File("C:\\Users\\vaibhav.vaghela\\Desktop\\Training\\Product.xlsx");     
	  FileInputStream fis = new FileInputStream(file);   

	  HSSFWorkbook wb = new HSSFWorkbook(fis);   
	  HSSFSheet sheet = wb.getSheetAt(0);     
	  Iterator<Row> itr = sheet.iterator();    
	  while (itr.hasNext())                 
	  {  
	  Row row = itr.next();  
	  Iterator<Cell> cellIterator = row.cellIterator();  
	  while (cellIterator.hasNext())   
	  {  
	  Cell cell = cellIterator.next();  
	  switch (cell.getCellType())               
	  {  
	  case STRING:    
	  System.out.print(cell.getStringCellValue() + "\t\t\t");  
	  break;  
	  case NUMERIC:  
	  System.out.print(cell.getNumericCellValue() + "\t\t\t");  
	  break;  
	  default:  
	  }  
	  }  
	  System.out.println("");  
	  }  
	  wb.close();
	  }  
	  catch(Exception e)  
	  {  
	  e.printStackTrace();  
	  } 
	  }
}
