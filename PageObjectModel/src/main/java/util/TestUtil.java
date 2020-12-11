package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import base.TestBase;

public class TestUtil extends TestBase{


	public static String path = "C:\\Users\\VICKY\\Desktop\\Liya\\Selenium\\PageObjectModel\\src\\main\\java\\testdata\\Data.xlsx";
	public static  FileInputStream fin;
	public static XSSFWorkbook wk;
	public static XSSFSheet sh;
	

	public static String[][] getData() {

		try {
			fin = new FileInputStream(path);
			wk = new XSSFWorkbook(fin);
			sh = wk.getSheet("sheet1");

		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}

		int rowNum = sh.getLastRowNum();
		int colNum = sh.getRow(0).getLastCellNum();

		String[][] data = new String[rowNum][colNum];
		for(int i=0; i<rowNum; i++) {

			for(int k=0; k<colNum; k++) {

				data[i][k]= sh.getRow(i+1).getCell(k).toString();
			}
		}
		
		return data;
	}
}
