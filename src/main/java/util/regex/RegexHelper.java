package util.regex;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * Created by ubuntu.
 */
public class RegexHelper {
    //判断是否为汉字, 左右都为闭区间
    public static Boolean isChineseChar(String value, int min, int max){
        String regEx = "^[\\u4e00-\\u9fa5]{" + min + "," + max + "}$";
        return Pattern.compile(regEx).matcher(value).matches();
    }

    //判断是否为正确的身份证号
    public static Boolean isIdNumber(String value){
        String idNumber = value.toUpperCase();
        String regEx = "^([0-9]{17}([0-9]|X))|([0-9]{15})$";

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if(!Pattern.compile(regEx).matcher(idNumber).matches())
            return false;
        else{
            //校验位按照ISO 7064:1983.MOD 11-2的规定生成，X可以认为是数字10。
            //下面分别分析出生日期和校验位
            int len = idNumber.length();
            if(len == 15){
                Calendar calendar = Calendar.getInstance();
                Date dtmBirth = null;
                try{
                    dtmBirth = simpleDateFormat.parse("19" + idNumber.substring(6, 8) + "-" + idNumber.substring(8, 10) + "-" + idNumber.substring(10, 12));
                } catch (Exception e){
                    e.printStackTrace();
                }
                if(dtmBirth != null)
                    calendar.setTime(dtmBirth);
                Boolean bGoodDay =
                        calendar.get(Calendar.YEAR) == (Integer.parseInt(idNumber.substring(6, 8)) + 1900) &&
                                (calendar.get(Calendar.MONTH) + 1) == Integer.parseInt(idNumber.substring(8, 10)) &&
                                calendar.get(Calendar.DATE) == Integer.parseInt(idNumber.substring(10, 12));
                if(!bGoodDay)
                    return false;
                else{  ////将15位身份证转成18位 //校验位按照ISO 7064:1983.MOD 11-2的规定生成，X可以认为是数字10。
                    int[] arrInt = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
                    char[] arrCh = {'1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'};
                    int nTemp = 0;
                    String numString1 = idNumber.substring(0, 6) + "19" + idNumber.substring(6, idNumber.length());
                    for(int i = 0; i < 17; i++){
                        nTemp += Integer.parseInt(numString1.substring(i, i + 1)) * arrInt[i];
                    }
                    numString1 += arrCh[nTemp % 11];
                    return true;
                }
            } else if(len == 18) {
                Calendar calendar = Calendar.getInstance();
                Date dtmBirth = null;
                try{
                    String date = idNumber.substring(6, 10) + "-" + idNumber.substring(10, 12) + "-" + idNumber.substring(12, 14);
                    dtmBirth = simpleDateFormat.parse(date);
                } catch (Exception e){
                    e.printStackTrace();
                }
                if(dtmBirth != null)
                    calendar.setTime(dtmBirth);
                Boolean bGoodDay =
                        calendar.get(Calendar.YEAR) == Integer.parseInt(idNumber.substring(6, 10)) &&
                                (calendar.get(Calendar.MONTH) + 1) == Integer.parseInt(idNumber.substring(10, 12)) &&
                                calendar.get(Calendar.DATE) == Integer.parseInt(idNumber.substring(12, 14));
                if(!bGoodDay)
                    return false;
                else{  //校验位按照ISO 7064:1983.MOD 11-2的规定生成，X可以认为是数字10。
                    int[] arrInt = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
                    char[] arrCh = {'1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'};
                    int nTemp = 0;
                    for(int i = 0; i < 17; i++){
                        nTemp += Integer.parseInt(idNumber.substring(i, i + 1)) * arrInt[i];
                    }
                    char valnum = arrCh[nTemp % 11];
                    return idNumber.substring(17, 18).equals(String.valueOf(valnum));
                }
            } else
                return false;
        }
    }
}
