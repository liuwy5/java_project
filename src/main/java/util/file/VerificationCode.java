package util.file;


import com.github.cage.GCage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * Created by ubuntu.
 */
public class VerificationCode {
    /**
     * 生成验证码图片
     * @param text 验证码内容
     * @return 文件流
     */
    public static File generate(String text){
        File temp = null;
        OutputStream os = null;
        try{
            temp = File.createTempFile("ez_captcha_", ".jpg");
            os = new FileOutputStream(temp);
            new GCage().draw(text, os);
            temp.deleteOnExit();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if(os != null){
                try{
                    os.close();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        return temp;
    }
}
