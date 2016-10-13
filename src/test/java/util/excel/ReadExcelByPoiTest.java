package util.excel;

import util.print.PrintHelper;
import org.junit.Test;

/**
 * Created by ubuntu.
 */
public class ReadExcelByPoiTest {
    @Test
    public void readExcelTest(){
        PrintHelper.print(ReadExcelByPoi.readExcel("excel_2003.xls"));
    }
}
