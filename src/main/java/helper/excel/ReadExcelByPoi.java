package helper.excel;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by ubuntu
 */
public class ReadExcelByPoi {
    /**
     * 读取excel文件内容
     * 每一行中，如果第一列没有值，则为null，直到遇到不为空的列。后面不为空的列为""
     * @param filePath 文件路径
     * @return excel文件内容构成的list
     */
    public static List<String[]> readExcel(String filePath){
        if(filePath.endsWith("xls")){
            return readExcelLowVersion(filePath);
        }else if(filePath.endsWith("xlsx")){
            return readExcelHighVersion(filePath);
        }else{
            System.out.println("该文件类型不支持");
            return null;
        }
    }

    private static List<String[]> readExcelLowVersion(String fileName) {
        // 构造 XSSFWorkbook 对象，strPath 传入文件路径
        HSSFWorkbook hssfWorkbook = null;
        HSSFSheet hssfSheet = null;
        try{
            hssfWorkbook = new HSSFWorkbook(new FileInputStream(fileName));
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }

        if(hssfWorkbook != null){
            // 读取第一章表格内容
            hssfSheet = hssfWorkbook.getSheetAt(0);
        }
        return ReadExcelByPoi.readExcelContent(hssfSheet);
    }

    private static List<String[]> readExcelHighVersion(String fileName) {
        // 构造 XSSFWorkbook 对象，strPath 传入文件路径
        XSSFWorkbook xwb = null;
        XSSFSheet xssfSheet = null;
        try{
            xwb = new XSSFWorkbook(new FileInputStream(fileName));
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }

        if(xwb != null){
            // 读取第一张表格内容
            xssfSheet = xwb.getSheetAt(0);
        }
        return ReadExcelByPoi.readExcelContent(xssfSheet);
    }

    private static List<String[]> readExcelContent(Sheet sheet) {
        List<String[]> list = new LinkedList<String[]>();
        if(sheet != null){
            Row row;
            Cell cell;
            System.out.println("读取office excel内容如下：");
            for (int i = sheet.getFirstRowNum(); i <= sheet.getPhysicalNumberOfRows(); i++) {
                row = sheet.getRow(i);
                if (row != null) {
                    String[] array = new String[row.getLastCellNum()];
                    for (int j = row.getFirstCellNum(); j < row.getLastCellNum(); j++) {
                        cell = row.getCell(j);
                        if (cell != null) {
                            cell.setCellType(Cell.CELL_TYPE_STRING);
                            array[j] = cell.getStringCellValue().trim();
                        }
                    }
                    list.add(array);
                }
            }
        }
        return list;
    }
}
