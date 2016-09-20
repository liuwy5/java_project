package helper.excel;

import helper.print.PrintHelper;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ubuntu
 * 只支持excel2003版本
 */
public class ReadExcelByJxl {
    private static List<String[]> ReadExcelFile(String fileName, int skipRow){
        List<String[]> result = new ArrayList<String[]>();
        jxl.Workbook readwb;
        Sheet readsheet = null;
        //构建Workbook对象, 只读Workbook对象
        //直接从本地文件创建Workbook
        try{
            readwb = Workbook.getWorkbook(new File(fileName));
            //Sheet的下标是从0开始
            //获取第一张Sheet表
            readsheet = readwb.getSheet(0);
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (NullPointerException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } catch (BiffException e){
            e.printStackTrace();
        }

        if(readsheet != null){
            //获取Sheet表中所包含的总行数
            int rsRows = readsheet.getRows();
            //获取Sheet表中所包含的总列数
            int rsColumn = readsheet.getColumns();

            if(skipRow < rsRows){
                for (int i = skipRow; i < rsRows; i++)
                {

                    String[] content = new String[rsColumn];
                    for(int j = 0; j < rsColumn; j++){
                        content[j] = readsheet.getCell(j, i).getContents();
                    }
                    result.add(content);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) throws NullPointerException {
        List<String[]> list = ReadExcelByJxl.ReadExcelFile("/home/ubuntu/excel/t1.xls", 1);
        PrintHelper.print(list);
    }
}

