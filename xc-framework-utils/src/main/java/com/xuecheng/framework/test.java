package com.xuecheng.framework;

import cn.hutool.core.io.FileUtil;
import net.lingala.zip4j.core.ZipFile;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;

/**
 * @author：GJH
 * @createDate：2019/9/5
 */
public class test {
    public static void main(String[] args) throws Exception {
        String path = "F:\\项目\\15-学成在线\\day08 课程图片管理 分布式文件系统\\代码\\xcEduService01";
        File file = new File(path);
        if(file.isDirectory()){
            File[] files = file.listFiles();
            for (File file1 : files) {
                if (file1.getName().equals(".idea")){
                    FileUtil.del(file1);
                }
                if (file1.exists()){
                    File[] files1 = file1.listFiles();
                    for (File file2 : files1) {
                        String name = file2.getName();
                        if (name.equals("target")||name.endsWith(".iml")||name.equals(".idea")||name.equals(".gitignore")){
                            FileUtil.del(file2);
                        }
                    }
                }


            }
        }

    }

}
