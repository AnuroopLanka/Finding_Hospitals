package com.practo.pom;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelWrite {
	
	public void writeToExcel(Map<String, String> list) throws IOException {
		
		XSSFWorkbook workbook = new XSSFWorkbook();		
		XSSFSheet sheet = workbook.createSheet("Data");
		Map<String, Object[]> details = new LinkedHashMap<String, Object[]>();

		int i = 2; // Variable to maintain index and serial number of data values.
		
		//Adding header row.
		details.put("1", new Object[] { "S.No.", "HOSPITAL NAME", "STAR RATING" });

		for (Map.Entry<String, String> m : list.entrySet()) {
			details.put(Integer.toString(i), new Object[] { (i - 1),m.getKey(), m.getValue()});
			i++;
		}

		// Creating and inserting rows for every data set.
		Set<String> keyset = details.keySet();
		int rowNum = 0;
		for (String key : keyset) {
			Row row = sheet.createRow(rowNum++);
			Object[] objArr = details.get(key);
			int cellnum = 0;
			for (Object obj : objArr) {
				Cell cell = row.createCell(cellnum++);
				if (obj instanceof String)
					cell.setCellValue((String) obj);
				else if (obj instanceof Integer)
					cell.setCellValue((Integer) obj);
			}
		}

		// Auto-resizing column width for all columns.
		sheet.autoSizeColumn(0);
		sheet.autoSizeColumn(1);
		sheet.autoSizeColumn(2);

		// Try-catch block to generated excel file and write in it and save it.
		try {

			// Location to store excel file.
			String storage = System.getProperty("user.dir")+ "\\Hospital_Details.xlsx";

			// Creating and storing the final excel file overwriting any
			// existing file with same name.
			FileOutputStream out = new FileOutputStream(new File(storage));
			workbook.write(out);
			out.close();			
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Closing the created workbook.
		workbook.close();
	}		
	
}


