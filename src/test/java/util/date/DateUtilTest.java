package util.date;

import org.junit.Test;

/**
 * Created by ubuntu.
 */
public class DateUtilTest {
    @Test
    public void getDateTTest(){
        String date = DateUtil.getDateT();
        System.out.println(date);
    }

    @Test
    public void getDateTest(){
        String date = DateUtil.getDate("yyyy-MM-dd");
        System.out.println(date);
    }
}
