package helper.date;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by ubuntu.
 */
public class DateHelper {
    public static String getDateT(){
        Calendar calendar = Calendar.getInstance();
        try{
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = simpleDateFormat.parse("2016-4-4");
            calendar.setTime(date);
        } catch (Exception e){
            e.printStackTrace();
        }
        return calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DATE);
    }

    public static String getDate(String format){
        Date date = new Date();
        return new SimpleDateFormat(format).format(date);
    }
}
