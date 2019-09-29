package com.xuecheng.framework;

import cn.hutool.core.io.FileUtil;
import net.lingala.zip4j.core.ZipFile;
import org.apache.commons.io.FilenameUtils;

import java.io.File;

/**
 * @author：GJH
 * @createDate：2019/9/5
 */
public class test {
    public static void main(String[] args) throws Exception {
        String path = "F:\\项目\\15-学成在线\\day16 Spring Security Oauth2\\代码\\xcEduService01";
        File file = new File(path);
        File[] files = file.listFiles();


        for (File file1 : files) {
            File[] files1 = file1.listFiles();
            for (File file2 : files1) {
                System.out.println(file2.getName());
                if (file2.getName().endsWith(".iml") || file2.getName() == ".gitignore") {
                    file2.delete();
                }
                if (file2.getName() == "target") {
                    FileUtil.del(file2);

                }
            }

        }

    }

}
