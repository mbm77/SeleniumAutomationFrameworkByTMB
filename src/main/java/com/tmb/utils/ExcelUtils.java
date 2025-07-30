package com.tmb.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.tmb.constants.FrameworkConstants;
import com.tmb.exceptions.FrameworkException;
import com.tmb.exceptions.InvalidPathForExcelException;

/**
 * Utility class to read or write to excel.
 * 
 * <pre>
 * <b>
 * <a href="https://www.youtube.com/channel/UC6PTXUHb6j4Oxf0ccdRI11A">Testing Mini Bytes Youtube channel</a>
 * </b>
 * </pre>
 * 
 * Jan 22, 2021 
 * @author Amuthan Sakthivel
 * @version 1.0
 * @since 1.0
 * @see com.tmb.listeners.MethodInterceptor
 * @see DataProviderUtils
 */
public final class ExcelUtils {

	/**
	 * Private constructor to avoid external instantiation
	 */
	private ExcelUtils() {}


	/**
	 * Encapsulates all the value from the mentioned excel sheet and store it in
	 * List holding HashMaps. Key for the map in the column header in the excel sheet.
	 * 
	 * @author Amuthan Sakthivel
	 * Jan 22, 2021
	 * @param sheetname Excel sheetname to read the value from
	 * @return List where each index holds a map and Each map holds the details about the test
	 * TODO create reusable methods
	 */
	public static List<Map<String,String>> getTestDetails(String sheetname){
		List<Map<String,String>> list = new ArrayList<>();

		try(FileInputStream fs = new FileInputStream(FrameworkConstants.getExcelpath());
				XSSFWorkbook workbook = new XSSFWorkbook(fs)) {

			XSSFSheet sheet = workbook.getSheet(sheetname);

			int lastrownum = sheet.getLastRowNum();
			int lastcolnum = sheet.getRow(0).getLastCellNum();

			Map<String,String> map =null;
			//list = new ArrayList<>();
			
			DataFormatter dataFormatter = new DataFormatter();
			for(int i=1; i<=lastrownum;i++) { 
				map = new HashMap<>(); 
				for(int j=0;j<lastcolnum;j++) {
					XSSFCell keyCell= sheet.getRow(0).getCell(j);
					String key = dataFormatter.formatCellValue(keyCell);
					XSSFCell valueCell = sheet.getRow(i).getCell(j);
					String value = dataFormatter.formatCellValue(valueCell);
					map.put(key, value);
				}
				list.add(map);
			}

		} catch (FileNotFoundException e) {
			throw new InvalidPathForExcelException("Excel File you trying to read is not found");
		} catch (IOException e) {
			throw new FrameworkException("Some io exception happened  while reading excel file");
		}
		return list;
	}
	
	

}
