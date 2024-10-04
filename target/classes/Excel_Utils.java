package resources;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel_Utils
{
	private static XSSFWorkbook workbook;
	private static XSSFSheet sheet;
//	public static void main(String[] args) throws Exception {
//		String[][] check=GetDbData(System.getProperty("user.dir")+"\\src\\main\\java\\ExcelSheets\\dashboard_page.xlsx", "dashboard");
//		for (int i = 0; i < check.length; i++) {
//			System.out.println(check[i][i]);
//			
//		}
//	}
	
	public static String[][] GetDbData(String path,String sheetName) throws IOException
	{
		FileInputStream fis=new FileInputStream(path);
		 workbook=new XSSFWorkbook(fis);
		 sheet=workbook.getSheet(sheetName);
		int row=1;
		int col=1;
		int ci,cj;
		int totalRows=sheet.getLastRowNum();
		
		int totalCols=sheet.getRow(0).getLastCellNum();
		
		totalCols=totalCols-1;
		String[][] tableData=new String[totalRows][totalCols];
		ci=0;
		
			for (int i = row; i <= totalRows; i++,ci++) {
				cj=0;
				
				for (int j = col; j <= totalCols; j++,cj++) {
					
					tableData[ci][cj]=gettableData(i,j);
			
				}
					
				}
		return tableData;		
	}

	public static  String gettableData(int i, int j) {
		String Data;
		XSSFCell cell = sheet.getRow(i).getCell(j);
		if(cell.getCellType()==CellType.STRING)
			Data=cell.getStringCellValue();
		else
			Data=String.valueOf(cell.getNumericCellValue());
		
		
		return Data;
	}

	

}
