package com.xuecheng.framework.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件工具类
 *
 * @author：gjh
 * @createDate：2019-09
 */
public class FilePoiUtil {

    /**
     * 获取文件
     *
     * @param path      目录文件夹
     * @param suffixs   读取的文件后缀名 逗号连接
     * @param startWith 文件名起始字符串
     * @param count     每次获取的文件数量 迭代
     * @param iteration boolean值,表示是否递归传入目录的下级目录筛选
     * @param count     获取文件总数,如果为null表示不限制
     * @return
     */
    public static List<File> getFiles(File path, String suffixs, String startWith, boolean iteration, Integer count) {
        // 如果文件存在，则继续循环过滤
        List<File> fileList = new ArrayList<File>();
        if (StringUtil.isNotEmpty(suffixs)) {
            String[] suffix = suffixs.split(",");
            for (String suf : suffix) {
                //过滤出指定文本格式的文件（此处全部是.txt格式的文件）
                File files[] = path.listFiles(new ExtFileFilter(suf, startWith));
                if (fileList != null) {
                    for (File file : files) {
                        // 文件夹中是否有文件夹
                        if (file.isDirectory() && iteration) {
                            fileList.addAll(getFiles(file, suf, startWith, iteration, count));
                        } else if (count != null && count > 0) {
                            if (fileList.size() <= count) {
                                fileList.add(file);
                            } else {
                                break;
                            }
                        } else if (file.isFile()) {
                            fileList.add(file);
                        }
                    }
                }
            }
        }
        return fileList;
    }

    /**
     * 文件过滤器：选中文件夹下所有指定后缀的文件
     *
     * @author gjh
     */
    static class ExtFileFilter implements FileFilter {
        private String extName;
        private String startWith;

        public ExtFileFilter(String extName, String startWith) {
            this.extName = extName;
            this.startWith = startWith;
        }

        @Override
        public boolean accept(File f) {
            boolean result = true;
            if (f.isFile()) {
                if (result && StringUtil.isNotEmpty(extName) && !f.getName().endsWith(extName)) {
                    result = false;
                }
                if (result && StringUtil.isNotEmpty(startWith) && !f.getName().startsWith(startWith)) {
                    result = false;
                }
            }
            return result;
        }
    }

    /**
     * 文件夹过滤器：选择指定文件下所有的文件夹
     *
     * @author gjh
     */
    static class ExtDirFilter implements FileFilter {
        @Override
        public boolean accept(File arg) {
            if (arg.isDirectory()) {
                return true;
            } else {
                return false;
            }
        }
    }


    /**
     * 读入文件流
     * @param file 文件流
     * @return String
     */
    public static String readFile(File file) {
        try {
            FileInputStream fis = new FileInputStream(file);
            // 声明一个数组，写入文件流（即缓存）
            byte[] bytIn = new byte[(int) file.length()];
            for (int i = 0; i < file.length(); i++) {
                bytIn[i] = (byte) fis.read();
            }
            // 关闭流
            fis.close();
            // 将字节数组转换成字符串
            String data = new String(bytIn);
            return data;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 按行读取文件内容,返回String[]
     *
     * @param file 文件
     * @param encoding 支付及编码,如果不指定,默认为 UTF-8
     * @return
     */
    public static String[] readFileByLine(File file, String encoding) {
        try {
            if (encoding == null || encoding == "") {
                encoding = "UTF-8";
            }
            List<String> result = new ArrayList<String>();
            InputStreamReader isr = new InputStreamReader(new FileInputStream(file), encoding);
            BufferedReader br = new BufferedReader(isr);
            String line = br.readLine();
            while (line != null) {
                result.add(line);
                line = br.readLine();
            }
            br.close();
            return (String[]) CollectionUtil.collectionToArray(result);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 按行读取文件内容,返回List<String[]>
     *
     * @param file 文件
     * @param encoding 支付及编码,如果不指定,默认为 UTF-8
     * @return
     */
    public static List<String[]> readFileByLineList(File file, String encoding) {
        try {
            if (encoding == null || encoding == "") {
                encoding = "UTF-8";
            }
            List<String[]> result = new ArrayList<String[]>();
            InputStreamReader isr = new InputStreamReader(new FileInputStream(file), encoding);
            BufferedReader br = new BufferedReader(isr);
            String line = br.readLine();
            while (line != null) {
                result.add(line.split(","));
                line = br.readLine();
            }
            br.close();
            return result;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将文件保存到path目录下面
     *
     * @param file 文件
     * @param path 存储路径
     * @return
     */
    public static boolean saveFile(File file, String path) {
        File targerDir = new File(path + (path.endsWith(getSystemDelimiter()) ? "" : getSystemDelimiter()));
        if (!targerDir.exists()) {
            targerDir.mkdirs();
        }
        try {
            FileInputStream fis = new FileInputStream(file);
            byte[] bytIn = new byte[(int) file.length()];
            for (int i = 0; i < file.length(); i++) {
                bytIn[i] = (byte) fis.read();
            }
            fis.close();
            File saveFile = new File(targerDir.getPath() + getSystemDelimiter() + file.getName());
            saveFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(saveFile);
            for (int i = 0; i < bytIn.length; i++) {
                fos.write((int) bytIn[i]);
            }
            fos.close();
            file.delete();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 获得操作系统的路径分隔符号
     *
     * @return
     */
    public static String getSystemDelimiter() {
        // 默认为windows
        String delimiter = "\\";
        // 如果当前操作系统不是windows则把符号换成/
        if (System.getProperty("os.name").indexOf("Windows") < 0) {
            delimiter = "/";
        }
        return delimiter;
    }

    /**
     * 获取一个文件夹的子文件（包括文件夹）
     *
     * @param path 文件夹的路径
     * @return
     */
    public static String[] getFileName(String path) {
        File file = new File(path);
        String[] fileName = file.list();
        return fileName;
    }

    /**
     * 获取一个文件夹下所有的文件的名字（递归到最后一层，不包含文件夹）
     *
     * @param path     文件夹路径
     * @param fileName
     */
    public static void getAllFileName(String path, ArrayList<String> fileName) {
        // 获取文件夹
        File file = new File(path);
        // 获取文件夹下所有的子文件
        File[] files = file.listFiles();
        for (File a : files) {
            // 如果a是一个文件夹，则循环下一层，遍历文件夹
            if (a.isDirectory()) {
                getAllFileName(a.getAbsolutePath(), fileName);
            } else {
                fileName.add(a.getName());
            }
        }
    }


}