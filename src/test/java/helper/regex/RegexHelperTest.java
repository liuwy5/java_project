package helper.regex;

import org.junit.Test;

/**
 * Created by ubuntu.
 */
public class RegexHelperTest {
    @Test
    public void isChineseCharTest(){
        System.out.println(RegexHelper.isChineseChar("ererer", 2, 4));
        System.out.println(RegexHelper.isChineseChar("发射的发射的发", 2, 4));
        System.out.println(RegexHelper.isChineseChar("是否", 2, 4));
        System.out.println(RegexHelper.isChineseChar("是否是否", 2, 4));
    }

    @Test
    public void isIdNumberTest(){
        System.out.println(RegexHelper.isIdNumber("13112519931127162X"));
        System.out.println(RegexHelper.isIdNumber("330103199011240715"));
        System.out.println(RegexHelper.isIdNumber("131125951234567"));
        System.out.println(RegexHelper.isIdNumber("131125951205567"));
    }
}
