package util.excel;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.util.List;

/**
 * Created by ubuntu.
 */
public class CreateExcelByPoi {
    public static void createExcelHighVersion(List<String[]> list, String head, String fileName){
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(); // 创建 一个excel文档对象
        createExcelBody(xssfWorkbook, list, head, fileName);
    }

    public static void createExcelLowVersion(List<String[]> list, String head, String fileName){
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(); // 创建 一个excel文档对象
        createExcelBody(hssfWorkbook, list, head, fileName);
    }

    private static CellStyle getCellStyle(Workbook workbook){
        CellStyle style = workbook.createCellStyle();// 创建样式对象

        // 设置字体
        Font font = workbook.createFont();// 创建字体对象
        font.setFontHeightInPoints((short) 15);// 设置字体大小
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 设置粗体
        font.setFontName("黑体");// 设置为黑体字
        style.setFont(font);// 将字体加入到样式对象

        // 设置对齐方式
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER_SELECTION);// 水平居中
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直居中

        // 设置边框
        style.setBorderTop(HSSFCellStyle.BORDER_THICK);// 顶部边框粗线
        style.setTopBorderColor(HSSFColor.RED.index);// 设置为红色
        style.setBorderBottom(HSSFCellStyle.BORDER_DOUBLE);// 底部边框双线
        style.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);// 左边边框
        style.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);// 右边边框
        return style;
    }

    private static void createExcelBody(Workbook workbook, List<String[]> list, String head, String fileName){
        Sheet sheet = workbook.createSheet(); // 创建一个工作薄对象

        //设置列宽
        sheet.setColumnWidth(0, 10000);

        Row row;
        Cell cell;

        // 表头
        row = sheet.createRow(0);
        cell = row.createCell(0);
        cell.setCellStyle(getCellStyle(workbook));
        cell.setCellValue(head);
        for(int i = 1; i < 3; i++){
            row.createCell(i);
        }
        sheet.addMergedRegion(new CellRangeAddress(0, (short)0, 0, (short)2));  //要先创建，才能合并

        // 表格主体
        for(int i = 0; i < list.size(); i++){
            row = sheet.createRow(i + 1);
            for(int j = 0; j < list.get(i).length; j++){
                cell = row.createCell(j);
                cell.setCellStyle(getCellStyle(workbook));
                cell.setCellValue(list.get(i)[j]);
            }
        }

        // 文件输出流
        FileOutputStream os;
        try{
            os = new FileOutputStream(fileName);
            workbook.write(os);// 将文档对象写入文件输出流
            os.close();// 关闭文件输出流
            System.out.println("创建成功 office excel");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
