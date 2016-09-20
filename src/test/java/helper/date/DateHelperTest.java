package helper.date;

import org.junit.Test;

/**
 * Created by ubuntu.
 */
public class DateHelperTest {
    @Test
    public void getDateTTest(){
        String date = DateHelper.getDateT();
        System.out.println(date);
    }

    @Test
    public void getDateTest(){
        String date = DateHelper.getDate("yyyy-MM-dd");
        System.out.println(date);
    }
}
