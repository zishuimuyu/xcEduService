package com.xuecheng.framework.utils;


import org.ini4j.Ini;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 解析文件工具类，可导入txt，csv,ini等
 *
 * @author：gjh
 * @createDate：2019-09
 */
public class FileParseUtil {

    /**
     * @param in        文件流BufferedReader
     * @param spaceMark 分割标记
     * @return
     * @throws Exception
     */
    private List<List<String>> read(BufferedReader in, String spaceMark) throws Exception {
        List<List<String>> dataList = new ArrayList<List<String>>();
        String regExp = getRegExp(spaceMark);
        String strLine;
        String str = "";
        //每一行被分割的个数
        int lineCountByMark = 0;
        while (in != null && (strLine = in.readLine()) != null) {
            lineCountByMark = strLine.split(spaceMark, -1).length;
            Pattern pattern = Pattern.compile(regExp);
            Matcher matcher = pattern.matcher(strLine);
            List<String> listTemp = new ArrayList<String>();
            while (matcher.find() && lineCountByMark > 0
                    && listTemp.size() != lineCountByMark) {
                str = matcher.group();
                str = str.trim();

                if (str.endsWith(spaceMark)) {
                    str = str.substring(0, str.length() - 1);
                    str = str.trim();
                }

                if (str.startsWith("\"") && str.endsWith("\"")) {
                    str = str.substring(1, str.length() - 1);
                    if (FileParseUtil.isExisted("\"\"", str)) {
                        str = str.replaceAll("\"\"", "\"");
                    }
                }
                listTemp.add(str);
            }
            dataList.add(listTemp);
        }
        in.close();
        return dataList;
    }

    /**
     * Regular Expression for parse
     *
     * @return
     */
    private String getRegExp(String spaceMark) {
        final String SPECIAL_CHAR_A = "[^\",\\n 　]";
        final String SPECIAL_CHAR_B = "[^\"" + spaceMark + "\\n]";

        StringBuffer strRegExps = new StringBuffer();
        strRegExps.append("\"((");
        strRegExps.append(SPECIAL_CHAR_A);
        strRegExps.append("*[" + spaceMark + "\\n 　])*(");
        strRegExps.append(SPECIAL_CHAR_A);
        strRegExps.append("*\"{2})*)*");
        strRegExps.append(SPECIAL_CHAR_A);
        strRegExps.append("*\"[ 　]*" + spaceMark + "[ 　]*");
        strRegExps.append("|");
        strRegExps.append(SPECIAL_CHAR_B);
        strRegExps.append("*[ 　]*" + spaceMark + "[ 　]*");
        strRegExps.append("|\"((");
        strRegExps.append(SPECIAL_CHAR_A);
        strRegExps.append("*[" + spaceMark + "\\n 　])*(");
        strRegExps.append(SPECIAL_CHAR_A);
        strRegExps.append("*\"{2})*)*");
        strRegExps.append(SPECIAL_CHAR_A);
        strRegExps.append("*\"[ 　]*");
        strRegExps.append("|");
        strRegExps.append(SPECIAL_CHAR_B);
        strRegExps.append("*[ 　]*");
        return strRegExps.toString();
    }

    /**
     * If argChar is exist in argStr
     *
     * @param argChar
     * @param argStr
     * @return
     */
    private static boolean isExisted(String argChar, String argStr) {

        boolean blnReturnValue = false;
        if ((argStr.indexOf(argChar) >= 0)
                && (argStr.indexOf(argChar) <= argStr.length())) {
            blnReturnValue = true;
        }
        return blnReturnValue;
    }

    /**
     * 判断字符串是否为空
     *
     * @param strVal string
     * @return true 为空 false 不为空
     */
    private boolean isEmpty(String strVal) {
        return strVal == null || "".equals(strVal);
    }

    /**
     * 解析Ini文件,返回map
     *
     * @param file
     * @return
     * @throws IOException
     */
    public static Map<String, String> parseIniFile(File file) throws IOException {
        Map<String, String> resultMap = new HashMap<>();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "GBK"));
        String line = bufferedReader.readLine();
        while (line != null && line != "") {
            line = bufferedReader.readLine().trim();
            if (line.indexOf("End") > -1) {
                break;
            }
            String[] split = line.split("=");
            if (split.length == 1) {
                resultMap.put(split[0], null);
            } else {
                resultMap.put(split[0], split[1]);
            }
        }
        bufferedReader.close();
        return resultMap;
    }

    /**
     * 解析ini文件
     *
     * @param file        文件
     * @param sectionName ini文件建分组的名称
     * @param optionName  键的名
     * @return
     * @throws IOException
     */
    public static String parseIniFile(File file, Object sectionName, Object optionName) throws IOException {

        if (file.isFile()) {
            InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "UTF-8");
            Ini ini = new Ini(isr);
            return ini.get(sectionName, optionName);
        } else {
            return null;
        }
    }

    /**
     * 解析ini文件
     *
     * @param filePath    文件路径
     * @param sectionName ini文件建分组的名称
     * @param optionName  键的名
     * @return
     * @throws IOException
     */
    public static String parseIniFile(String filePath, Object sectionName, Object optionName) throws IOException {
        if (filePath != null && filePath != "") {
            return null;
        }
        InputStreamReader isr = new InputStreamReader(new FileInputStream(filePath), "UTF-8");
        Ini ini = new Ini(isr);
        return ini.get(sectionName, optionName);

    }

    /**
     * 解析ini文件
     *
     * @param fileInputStream 文件流
     * @param sectionName     ini文件建分组的名称
     * @param optionName      键的名
     * @return
     * @throws IOException
     */
    public static String parseIniFile(FileInputStream fileInputStream, Object sectionName, Object optionName) throws IOException {
        InputStreamReader isr = new InputStreamReader(fileInputStream, "UTF-8");
        Ini ini = new Ini(isr);
        return ini.get(sectionName, optionName);

    }
}