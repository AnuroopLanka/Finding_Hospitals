package com.practo.report;
import java.io.*;


import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadFromExcel {
	
public static String[][] getExcelData() {
		
		String[][] arrayExcelData = null;
		
		try {
			String excelLocation = System.getProperty("user.dir") + "\\Alert_Test.xlsx";
			FileInputStream file = new FileInputStream(new File(excelLocation));
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheet("TestData");
			XSSFRow row = sheet.getRow(0);
			
			DataFormatter formatter = new DataFormatter();
			
			int totalRows = sheet.getLastRowNum();
			int totalCols = row.getLastCellNum();

			arrayExcelData = new String[totalRows][totalCols - 1];
			
			for (int i = 1; i <= totalRows; i++) {
				row = sheet.getRow(i);
				
				for (int j = 1; j < totalCols; j++) {
					XSSFCell cell = row.getCell(j);
					arrayExcelData[i - 1][j - 1] = formatter.formatCellValue(cell);
				}
			}
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return arrayExcelData;
	}

}
