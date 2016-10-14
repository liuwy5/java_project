package io;

import java.io.*;

/**
 * Created by ubuntu
 */
public class CopyFile {
    /**
     * 文件复制
     * @param src 源文件
     * @param dstPath 目标文件
     */
    public static void copy(File src, String dstPath) {
        if(src.exists()){
            File fPath = new File(dstPath);
            if (!fPath.exists()) {
                boolean mkdir = fPath.mkdirs();
                System.out.println("目标路径创建结果" + mkdir);
            }
            String dstFileName = dstPath + "/" + src.getName();
            try {
                InputStream in = null;
                OutputStream out = null;
                try {
                    in = new BufferedInputStream(new FileInputStream(src),1024);
                    out = new BufferedOutputStream(new FileOutputStream(dstFileName),1024);
                    byte[] buffer = new byte[1024];
                    int len;
                    while ((len=in.read(buffer))!=-1) {
                        out.write(buffer,0,len);
                    }
                } finally {
                    if (null != in) {
                        in.close();
                    }
                    if (null != out) {
                        out.close();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            System.out.println("源文件不存在。");
        }

    }

    public static void main(String[] args) {
        File file1=new File("/home/ubuntu/Downloads/data.xls");//源文件
        String copyPath = "/home/ubuntu/excel";
        copy(file1, copyPath);
    }
}
