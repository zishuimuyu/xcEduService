package com.xuecheng.framework.utils;

import com.alibaba.fastjson.JSONArray;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

/**
 * 字符串工具类
 *
 * @author：GJH
 * @createDate：2019/9/6
 */
public final class StringUtil<T> {
    /**
     * 类标识
     */
    private static int serial = 0;

    /**
     * 判断字符串是否为空
     *
     * @param strVal string
     * @return true 为空 false 不为空
     */
    public static boolean isEmpty(String strVal) {
        return strVal == null || "".equals(trim(strVal));
    }

    /**
     * 判断字符串是否为空
     *
     * @param strVal string
     * @return true 不为空 false 为空
     */
    public static boolean isNotEmpty(String strVal) {
        return strVal != null && !"".equals(trim(strVal));
    }

    /**
     * 去除左右边全、半角空格
     *
     * @param strValue 字符串
     * @return String 去除左右边全、半角空格字符串
     */
    public static String trim(String strValue) {

        if ((strValue != null) && (!strValue.equals(""))) {
            StringBuffer sb = new StringBuffer();
            StringBuffer sb1 = new StringBuffer();
            String strRet = "";
            sb.append(rTrim(strValue));
            // 反转后去掉右空格
            strRet = rTrim(sb.reverse().toString());
            sb1.append(strRet);
            strRet = sb1.reverse().toString();
            return strRet.trim();
        } else {
            return "";
        }
    }

    /**
     * 如果对象不为空则将对象转换成字符串返回,否则返回空
     *
     * @param obj 对象
     * @return 返回非空字符串
     */
    public static String nullToString(Object obj) {
        String ret = "";
        if (obj != null) {
            ret = String.valueOf(obj);
        }
        return ret;
    }


    /**
     * 转换对象为字符串,如果是空字符串为null.
     *
     * @param str String
     * @return String 字符串为null返回空格；否则返回原字符串.
     */
    public static String emptyStringToNull(Object str) {
        String result = null;
        String val = str != null ? String.valueOf(str) : null;
        if (!"".equals(trim(val))) {
            result = str.toString();
        }

        return result;
    }

    /**
     * 检查字符串中是否包含某个字符串
     *
     * @param str     需要检查的字符
     * @param compare 字符串
     * @return true 包含改字符 false 不包含改字符
     */
    public static boolean contains(String str, String compare) {
        boolean bool = true;
        if (isEmpty(str)) {
            bool = false;
        } else {
            if (str.indexOf(compare) < 0) {
                bool = false;
            }
        }
        return bool;
    }

    /**
     * 将首字符大写
     *
     * @param str 字符串
     * @return 首字符大写的字符串
     */
    public static String transFirstLetterToUpperCase(String str) {
        if (StringUtil.isNotEmpty(str)) {
            String firstLetter = str.substring(0, 1).toUpperCase();
            return firstLetter + str.substring(1, str.length());
        } else {
            return str;
        }
    }

    /**
     * 判断字符串是否为数字(包括小数)
     *
     * @param str String
     * @return 数字返回true 非数字返回false
     */
    public static boolean isNumber(String str) {
        if (StringUtil.isEmpty(str)) {
            return false;
        }
        String mstr = str.toString();
        boolean isInt = compile("^-?[1-9]\\d*$").matcher(mstr).matches();
        boolean isDouble = compile("^-?([1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*|0?\\.0+|0)$").matcher(mstr).matches();
        return isInt || isDouble;
    }

    /**
     * 判断字符串中是否包含数字
     *
     * @param str
     * @return
     */
    public static boolean isContainsNumber(String str) {
        Pattern p = compile("[0-9]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }

    /**
     * 转换字符串为Short
     *
     * @param value String
     * @return 转换后的Short，失败是返回0
     */
    public static Short toShort(Object value) throws Exception {

        String val = value != null ? String.valueOf(value) : null;
        if (isNotEmpty(val)) {
            return Short.valueOf(trim(val));
        }
        return null;
    }

    /**
     * 转换字符串为Integer
     *
     * @param value String
     * @return 转换后的Integer，失败是返回0
     */
    public static Integer toInteger(Object value) throws Exception {
        String val = value != null ? String.valueOf(value) : null;
        if (isNotEmpty(val)) {
            return Integer.valueOf(trim(val));
        }
        return null;
    }

    /**
     * 转换字符串为Long
     *
     * @param value String
     * @return 转换后的Long，失败是返回0
     */
    public static Long toLong(Object value) throws Exception {
        String val = value != null ? String.valueOf(value) : null;
        if (isNotEmpty(val)) {
            return Long.valueOf(trim(val));
        }
        return null;
    }

    /**
     * 转换字符串为Float
     *
     * @param value String
     * @return 转换后的Double，失败是返回0
     */
    public static Float toFloat(Object value) throws Exception {
        String val = value != null ? String.valueOf(value) : null;
        if (isNotEmpty(val)) {
            return Float.valueOf(trim(val));
        }
        return null;
    }

    /**
     * 转换字符串为Double
     *
     * @param value String
     * @return 转换后的Double，失败是返回0
     */
    public static Double toDouble(Object value) {
        String val = value != null ? String.valueOf(value) : null;
        if (isNotEmpty(val) && !val.equals("null")) {
            return Double.valueOf(trim(val));
        }
        return 0.0;
    }

    /**
     * 转换字符串为BigDecimal
     *
     * @param value String
     * @return BigDecimal 转换后的BigDecimal，失败是返回0
     */
    public static BigDecimal toBigDecimal(Object value) {
        String val = value != null ? String.valueOf(value) : null;
        if (isNotEmpty(val)) {
            return new BigDecimal(trim(val));
        }
        return null;
    }

    /**
     * 去除右边全半角空格
     *
     * @param strValue String
     * @return String String
     */
    private static String rTrim(String strValue) {

        if ((strValue != null) && (!strValue.equals(""))) {
            char[] cValue = strValue.toCharArray();
            int nCur = 0;

            for (int i = cValue.length - 1; i > -1; i--) {
                if ((cValue[i] != '\u0020') && (cValue[i] != '\u3000')) {
                    nCur = i;
                    break;
                }
            }

            if ((nCur == 0) && ((cValue[0] == '\u0020') || (cValue[0] == '\u3000'))) {
                return "";
            }

            return strValue.substring(0, nCur + 1);
        } else {
            return "";
        }
    }

    /**
     * 根据传入的url地址得到JSP的名称
     *
     * @param strUrl 请求的URL地址
     * @return String String
     */
    public static String getPageNameByUrl(String strUrl) {
        String strResult = null;
        if (!isEmpty(strUrl)) {
            int beginIndex = strUrl.lastIndexOf("/");
            int endIndex = strUrl.lastIndexOf("_");
            int endPointIndex = strUrl.lastIndexOf(".");
            String type = null;
            if (endPointIndex != -1) {
                type = strUrl.substring(endPointIndex + 1);
            }
            if (!isEmpty(type) && type.equalsIgnoreCase("jsp")) {
                if (beginIndex > 0 && endPointIndex > 0) {
                    strResult = strUrl.substring(beginIndex + 1, endPointIndex);
                }
            } else if (beginIndex > 0 && endIndex > 0) {
                strResult = strUrl.substring(beginIndex + 1, endIndex);
            }
        }
        return strResult;
    }

    /**
     * 把数组转换成字符串,中间用逗号分隔
     *
     * @param objs 数组
     * @return 特殊字符分割的字符串
     */
    public static String getStringByArray(Object[] objs) {

        if (objs != null && objs.length > 0) {
            StringBuilder stringBuilder = new StringBuilder("");
            for (Object obj : objs) {
                stringBuilder.append(obj + ",");
            }
            return stringBuilder.substring(0, stringBuilder.length() - 1);
        } else {
            return null;
        }
    }

    /**
     * 计算两个String的商值
     *
     * @param div1              被除数
     * @param div2              除数
     * @param nValuePointLength 保留小数点几位,如果为空,有效小数点为div1原保留小数与除数的位数之和
     * @return String 结果
     */
    public static String caculateDiv(String div1, String div2, int nValuePointLength) {
        if (!StringUtil.isEmpty(div1) && !StringUtil.isEmpty(div2)) {
            BigDecimal div1Decimal = new BigDecimal(div1);
            BigDecimal div1Decima2 = new BigDecimal(div2);
            div1Decimal = div1Decimal.divide(div1Decima2, nValuePointLength, BigDecimal.ROUND_HALF_EVEN);
            return div1Decimal.toString();
        }
        return "";
    }


    /**
     * 拼写标签的属性值
     *
     * @param name  属性名称
     * @param value 属性值
     * @return 属性字符串
     */
    public static StringBuffer setAtrribute(String name, String value) {
        // 返回生成的属性字符串
        StringBuffer sbHtml = new StringBuffer();
        if (StringUtil.isNotEmpty(name)) {
            sbHtml.append(" ");
            sbHtml.append(name);
            sbHtml.append(" = \"");
            sbHtml.append(value);
            sbHtml.append("\"");
        }
        return sbHtml;
    }

    /**
     * 将数组转化为Echarts中data的json字符串，其中空值用"-"代替
     *
     * @param objs
     * @return
     */
    public static String arrayToEchartsString(Object[] objs) {
        StringBuilder stringBuilder = new StringBuilder("[");
        if (objs != null && objs.length > 0) {
            for (Object obj : objs) {
                if (obj == null || obj == "") {
                    stringBuilder.append("'-',");
                }
                stringBuilder.append(obj + ",");

            }
            stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(","));
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    /**
     * trim数字最后的0
     *
     * @param num      数字
     * @param piontNum 小数点位数
     * @return 属性字符串
     */
    public static String trimEndZero(double num, int piontNum) {
        String strNum = "" + num;
        if (isNotEmpty(strNum) && strNum.indexOf(".") > -1) {
            num = num * Math.pow(10, piontNum + 1);
            num = (num + 5) / Math.pow(10, piontNum + 1);
            strNum = "" + new BigDecimal("" + num);
            String val1 = strNum.substring(0, strNum.indexOf("."));
            String val2 = strNum.substring(strNum.indexOf(".") + 1);
            if (isNotEmpty(val2)) {
                while (val2.endsWith("0")) {
                    val2 = val2.substring(0, val2.length() - 1);
                }
            }
            if (isNotEmpty(val2)) {
                if (val2.length() > piontNum) {
                    val2 = val2.substring(0, piontNum);
                }
                strNum = val1 + "." + val2;
            } else {
                strNum = val1;
            }
        }
        return strNum;
    }

    /**
     * 数字转化成大写人名币
     *
     * @param m 传入数值（单位：元）
     * @return 大写人民币
     */
    public static String getNumberToRMB(String m) {
        String num = "零壹贰叁肆伍陆柒捌玖";
        String dw = "圆拾佰仟万亿";
        String mm[] = null;
        mm = m.split("\\.");
        String money = mm[0];

        String result = num.charAt(Integer.parseInt("" + mm[1].charAt(0))) + "角"
                + num.charAt(Integer.parseInt("" + mm[1].charAt(1))) + "分";

        for (int i = 0; i < money.length(); i++) {
            String str = "";
            int n = Integer.parseInt(money.substring(money.length() - i - 1, money.length() - i));
            str = str + num.charAt(n);
            if (i == 0) {
                str = str + dw.charAt(i);
            } else if ((i + 4) % 8 == 0) {
                str = str + dw.charAt(4);
            } else if (i % 8 == 0) {
                str = str + dw.charAt(5);
            } else {
                str = str + dw.charAt(i % 4);
            }
            result = str + result;
        }
        result = result.replaceAll("零([^亿万圆角分])", "零");
        result = result.replaceAll("亿零+万", "亿零");
        result = result.replaceAll("零+", "零");
        result = result.replaceAll("零([亿万圆])", "$1");
        result = result.replaceAll("壹拾", "拾");

        return result;
    }

    /**
     * 将阿拉伯数字转化成中文数字，处理百位以下部分，超过99的，以传入数值返回
     *
     * @param num 传入要转换的数字
     * @return 返回转换后的中文字符串
     */
    public static String numToStr(String num) {
        // 去除空格
        num = StringUtil.trim(num);
        String strNum = "一二三四五六七八九";
        String strShi = "十";
        // 返回值
        String result = "";
        // 判断为数字
        if (compile("^\\d+$").matcher(num).find()) {
            if (num.length() == 2) {
                int n = Integer.parseInt(num.substring(0, 1)) - 1;
                int n1 = Integer.parseInt(num.substring(1)) - 1;
                if (n == 0) {
                    result += strShi;
                } else if (n > 0) {
                    result += strNum.charAt(n) + strShi;
                }
                if (n1 > -1) {
                    result += strNum.charAt(n1);
                }
            } else if (num.length() == 1) {
                int n = Integer.parseInt(num);
                if (n > -1) {
                    result += strNum.charAt(n);
                }
            }
        }
        if (StringUtil.isEmpty(result)) {
            result = num;
        }
        return result;
    }

    /**
     * 产生通用惟一标识符
     *
     * @return 通用惟一标识符（字符串）
     */
    public static String makeUUID() {
        StringBuffer ret = new StringBuffer();
        SimpleDateFormat dfDate = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        dfDate.setLenient(false);
        ret.append(dfDate.format((new GregorianCalendar()).getTime()));

        DecimalFormat dfNum = new DecimalFormat("000");
        ret.append(dfNum.format(serial++));
        if (serial > 999) {
            serial = 0;
        }
        UUID uuid = UUID.randomUUID();
        ret.append(uuid.toString().replace("-", "").subSequence(0, 12));

        return ret.toString();
    }

    /**
     * 将文本域中的字符\r\n,转化成html<br/>
     * 标签
     *
     * @param str 参数
     * @return HTML字符串
     */
    public static String changeToHtml(String str) {
        String html = null;
        if (str != null) {
            html = str.replaceAll("\r\n", "<br/>");
        }
        return html;
    }

    /**
     * 处理静态SQL的特殊字符
     *
     * @param strValue 参数
     * @return 处理后的SQL字符串
     */
    public static String encodeForSql(String strValue) {
        if (strValue == null) {
            return null;
        }
        String strSrc = strValue.trim();
        String strTemp = "";
        for (int k = 0; k < strSrc.length(); k++) {
            char chrTemp = strSrc.charAt(k);
            if (chrTemp == '\'') {
                strTemp += "\'\'";
            } else {
                strTemp += chrTemp;
            }
        }
        return strTemp;
    }


    /**
     * urlStr转化为Set<String>
     *
     * @param urlStr String
     * @return Set<String>
     */
    public static Set<String> parseUrl(String urlStr) {
        Set<String> urlSet = new TreeSet<String>();

        if (urlStr != null) {
            String[] urlArray = urlStr.split(",");
            if (urlArray != null && urlArray.length > 0) {
                for (String url : urlArray) {
                    urlSet.add(url.trim());
                }
            }
        }

        return urlSet;
    }

    /**
     * 将单引号字符串转化成数组
     *
     * @param ids id字符串
     * @return 整数数组
     */
    public static Integer[] parseStringToIntegerArr(String ids) throws Exception {
        String[] idArrS = ids.split(",");
        if (CollectionUtil.isNotEmpty(idArrS)) {
            Integer[] idsArr = new Integer[idArrS.length];
            for (int i = 0; i < idArrS.length; i++) {
                idsArr[i] = StringUtil.toInteger(idArrS[i]);
            }
            return idsArr;
        }
        return null;
    }

    /**
     * 将数字用0补充相应的位数
     *
     * @param val 补充的值
     * @param num 补充位数
     * @return 补充后的字符串
     */
    public static String fillInInStringTOString(String val, Integer num) {
        String retVal = val;
        if (isNotEmpty(val) && num > 0) {
            for (int i = val.length(); i < num; i++) {
                retVal = "0" + retVal;
            }
        }
        return retVal;
    }

    /**
     * 复制文字进入文件
     *
     * @param filePath 文件
     * @param text     文本
     * @throws IOException 异常
     */
    public static void copyToFile(String filePath, String text) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
        }
        FileOutputStream out = new FileOutputStream(file, false);
        StringBuffer sb = new StringBuffer();
        sb.append(text.replace(";", "\n"));
        out.write(sb.toString().getBytes("utf-8"));
        out.close();
    }


    /**
     * 4舍5入,保留指定位数的小数(默认保留两位)
     *
     * @param value      源数据
     * @param decimalNum 指定保留的小数位数,默认保留两位
     * @return
     */
    public static String formatDouble(Double value, Integer decimalNum) {
        // 拼写格式化的模板
        String f_value = "0.";
        // 如果保留的位数小于0，则默认保留2位
        if (null == decimalNum || decimalNum < 0) {
            decimalNum = 2;
        }
        // 若保留位数为0，则四舍五入取整
        if (null != decimalNum && decimalNum == 0) {
            return Math.round(value) + "";
        }
        for (int i = 0; i < decimalNum; i++) {
            f_value += "0";
        }
        DecimalFormat df = new DecimalFormat(f_value);
        df.setRoundingMode(RoundingMode.HALF_UP);
        if (null == value) {
            return f_value;
        } else {
            return df.format(toDouble(value));
        }

    }

    /**
     * 判断字符串中是否包含中文
     *
     * @param str
     * @return
     */
    public static boolean isContainsChinese(String str) {
        String regEx = "[\u4e00-\u9fa5]";
        Pattern pat = compile(regEx);
        Matcher matcher = pat.matcher(str);
        boolean flg = false;
        if (matcher.find()) {
            flg = true;
        }
        return flg;
    }

    /**
     * java去除字符串中的空格、回车、换行符、制表符
     *
     * @param str 源字符串
     * @return
     */
    public static String replaceBlank(String str) {
        String dest = "";
        if (null != str) {
            Pattern p = compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

    /**
     * 判断字符串是否为整数数字
     */
    public static boolean strIsDigit(String str) {
        try {
            Double.parseDouble(str);// 把字符串强制转换为数字
            return true;// 如果是数字，返回True
        } catch (Exception e) {
            return false;// 如果抛出异常，返回False}
        }
    }

    /**
     * 判断字符串是否为数字
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 数字返回汉字
     *
     * @param num
     * @return
     */
    public static String getBigNum(String num) {
        // 大写数字
        String[] arrNum = {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};
        if (num == null || "".equals(num) || "null".equals(num)) {
            return "";
        }
        int n = Integer.parseInt(num);
        String retnNum = arrNum[n];
        return retnNum;
    }


    /**
     * 去掉数字串中小数点及其小数部分
     *
     * @param str
     * @return
     */
    public static String removeDot(String str) {
        String val = "";
        int index = str.indexOf(".");
        if (index > -1) {
            val = str.substring(0, index).trim();
        } else {
            val = str.trim();
        }
        if (val.length() == 0) {
            return null;
        } else {
            return val;
        }
    }

    /**
     * 将字符串类型转换为Long、Integer、Double
     *
     * @param cz
     * @param valStr
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static Object cast(Class cz, String valStr) throws Exception {
        if (isEmpty(valStr)) {
            return null;
        }
        Object result = null;
        if (Long.class == cz) {
            result = toLong(valStr);
        } else if (Integer.class == cz) {
            result = toInteger(valStr);
        } else if (Double.class == cz) {
            result = toDouble(valStr);
        }
        return result;
    }

    /***
     * 数据格式化
     *
     * @param object
     * @param count  保留几位小数(>=0)
     * @return String
     */
    public static String formatDoubleToStr(Object object, int count) {
        // 返回Double格式化字符串
        String result = "";
        String fmt = "";
        try {
            if (object == null) {
                return result;
            }
            if (count == 0) {
                fmt = "0";
            } else if (count > 0) {
                fmt = "0.";
                for (int i = 0; i < count; i++) {
                    fmt += "0";
                }
            } else {
                return result;
            }
            DecimalFormat df = new DecimalFormat(fmt);
            df.setRoundingMode(RoundingMode.HALF_UP);
            return df.format(toDouble(object));
        } catch (Exception ex) {
            return result;
        }
    }


    /**
     * 根据查询年将12个月的空值用""代替
     *
     * @param dataMap <Object, Double> dataMap
     * @param count   替换总数
     * @return String 返回""替换空值后结果
     */
    public static String joinEmptyStr(Map<Object, String> dataMap, int count) {
        String result = "";
        if (count > 0) {
            for (int k = 1; k <= count; k++) {
                if (dataMap.get(k) == null) {
                    dataMap.put(k, "");
                }
            }
            result = dataMap.values().toString().replaceAll("\\s*", "");
            return result.substring(1, result.length() - 1);
        }
        return result;
    }

    /**
     * 排序
     *
     * @param jsonarr   排序对象
     * @param orderby   排序根据
     * @param ordertype 排序
     */
    public static void compareSortList(List<Map<String, Double>> jsonarr, final String orderby, final String ordertype) {
        Collections.sort(jsonarr, new Comparator<Map<String, Double>>() {
            @Override
            public int compare(Map<String, Double> map1, Map<String, Double> map2) {
                int ret = 0;
                if (map1.get(orderby) == null || map2.get(orderby) == null) {
                    return 0;
                }

                if (map1.get(orderby) > map2.get(orderby)) {
                    ret = 1;
                } else if (map1.get(orderby) < map2.get(orderby)) {
                    ret = -1;
                }
                if ("desc".equalsIgnoreCase(ordertype)) {
                    ret = -ret;
                }
                return ret;
            }
        });
    }

    /**
     * 截取数组(JSONArray)
     *
     * @param jsonArray 目标数组
     * @param beginNum  开始元素
     * @param endNum    结束元素
     * @return 截取的数组(JSONArray)
     */
    public static JSONArray jsonArraySlice(JSONArray jsonArray, int beginNum, int endNum) {
        JSONArray resultArray = new JSONArray();
        for (int i = beginNum; i < endNum && i < jsonArray.size(); i++) {
            resultArray.add(jsonArray.get(i));
        }
        return resultArray;
    }
}
