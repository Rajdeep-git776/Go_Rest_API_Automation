package utils;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;

public class TestUtil {

    public  static long PAGE_LOAD_TIMEOUT = 20;
    public static long IMPLICIT_WAIT = 200;

    // Resuable code for reading data from excel file and sending to Data provider
    static XSSFWorkbook workbook;
    static XSSFSheet sheet;

    public TestUtil(String excelPath, String sheetName){
        try {
            workbook = new XSSFWorkbook(System.getProperty("user.dir")+excelPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        sheet =workbook.getSheet(sheetName);
    }

    public  int getRowCount()  {

        int rows=sheet.getPhysicalNumberOfRows();
        //System.out.println("row "+ rows);
        return rows;


    }

    public int getColCount(){
        int columCount=sheet.getRow(0).getPhysicalNumberOfCells();
        //System.out.println("column "+ columCount);
        return columCount;
    }

    public  String  getCellDataString(int rowNum, int colNum){

        String cellData =sheet.getRow(rowNum).getCell(colNum).getStringCellValue();
        // System.out.println(cellData);
        return cellData;

    }

    public  void getCellDataNumeric(int rowNum, int colNum){

        double cellData =sheet.getRow(rowNum).getCell(colNum).getNumericCellValue();
        //System.out.println(cellData);

    }


    public static Object[][] testData(String excelPath, String sheetName) {
        TestUtil tu = new TestUtil(excelPath, sheetName);
        int rowCount = tu.getRowCount();
        int colCount = tu.getColCount();
        Object[][] data = new Object[rowCount-1][colCount];
        tu.getCellDataString(1, 0);
        for (int i = 1; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                String cellData = tu.getCellDataString(i, j);
                System.out.print(cellData+" ");
                data[i-1][j]= cellData;
            }
            System.out.println();
        }
        return data;
    }

}
