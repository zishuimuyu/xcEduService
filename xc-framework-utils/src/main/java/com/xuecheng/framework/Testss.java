package com.xuecheng.framework;

import com.xuecheng.framework.utils.FilePoiUtil;

import java.io.File;
import java.util.List;

/**
 * @Description：TODO
 * @author：GJH
 * @createDate：2019/9/5
 * @company：北京木联能软件股份有限公司
 */
public class Testss {
    public static void main(String[] args) throws Exception {
        File file = new File("E:\\mlnsoft\\datafiles\\uploadRootDir");
        List<File> files = FilePoiUtil.getFiles(file, "ini", "2019", true,null);
        System.out.println(files.size());
    }
}
