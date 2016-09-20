package helper.excel;

import helper.print.PrintHelper;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by ubuntu
 */
public class ReadExcelByPoi {
    private static List<String[]> readExcel(String filePath){
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

    private static void createExcelLowVersion(){
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(); // 创建 一个excel文档对象
        HSSFSheet sheet = hssfWorkbook.createSheet();// 创建一个工作薄对象
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue("aaaa");
        // 文件输出流

        FileOutputStream os;
        try{
            os = new FileOutputStream("/home/ubuntu/excel/style_2003.xls");
            hssfWorkbook.write(os);// 将文档对象写入文件输出流
            os.close();// 关闭文件输出流
            System.out.println("创建成功 office 2003 excel");
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void main(String[] args){
        List<String[]> lowList = ReadExcelByPoi.readExcel("/home/ubuntu/excel/style_2003.xls");
        PrintHelper.print(lowList);
        List<String[]> highList = ReadExcelByPoi.readExcel("/home/ubuntu/excel/t3.xlsx");
        PrintHelper.print(highList);
        ReadExcelByPoi.createExcelLowVersion();
    }
}
