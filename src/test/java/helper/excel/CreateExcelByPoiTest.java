package helper.excel;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ubuntu.
 */
public class CreateExcelByPoiTest {
    @Test
    public void createExcelHighVersionTest(){
        List<String[]> list = new ArrayList<String[]>();
        for(int i = 0; i < 6; i++){
            String[] strings = {"a", "b", "c"};
            list.add(strings);
        }
        CreateExcelByPoi.createExcelHighVersion(list, "head", "excel_2007.xlsx");
    }


    @Test
    public void createExcelLowVersionTest(){
        List<String[]> list = new ArrayList<String[]>();
        for(int i = 0; i < 6; i++){
            String[] strings = {"a", "b", "c"};
            list.add(strings);
        }
        CreateExcelByPoi.createExcelLowVersion(list, "head", "excel_2003.xls");
    }


}
