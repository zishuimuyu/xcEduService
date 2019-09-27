package com.xuecheng.framework;

import com.xuecheng.framework.utils.FilePoiUtil;

import java.io.File;
import java.util.List;

/**
 * @Description：
 * @author：GJH
 * @createDate：2019/9/5
 * @company：洪荒宇宙加力蹲大学
 */
public class Testss {
    public static void main(String[] args) throws Exception {
        File file = new File("E:\\mlnsoft\\datafiles\\uploadRootDir");
        List<File> files = FilePoiUtil.getFiles(file, "ini", "2019", true,null);
        System.out.println(files.size());
    }
}
