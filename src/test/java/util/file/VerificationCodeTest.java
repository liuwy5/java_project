package util.file;

import io.CopyFile;
import org.junit.Test;

import java.io.File;

/**
 * Created by ubuntu.
 */
public class VerificationCodeTest {
    @Test
    public void generateTest(){
        File file = VerificationCode.generate("434366");
        CopyFile.copy(file, "/home/ubuntu");
    }
}
