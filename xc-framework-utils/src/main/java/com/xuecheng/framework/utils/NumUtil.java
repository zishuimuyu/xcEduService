package com.xuecheng.framework.utils;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;

/**
 * 处理数字的工具类类 数字
 *
 * @author gjh
 * @date 2016年11月30日 下午5:58:40
 */
@Slf4j
public class NumUtil {
    /**
     * 汉字
     */
    private static final String[] CHINESE_NUMBER = {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};
    private static final String[] NUMBER_UNIT = {"十", "百", "千", "万", "十", "百", "千", "亿", "十", "百", "千"};
    /**
     * 汉语中数字大写
     */
    private static final String[] CN_UPPER_NUMBER = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};
    /**
     * 汉语中货币单位大写，这样的设计类似于占位符
     */
    private static final String[] CN_UPPER_MONETRAY_UNIT = {"分", "角", "元", "拾", "佰", "仟", "万", "拾", "佰", "仟",
            "亿", "拾", "佰", "仟", "兆", "拾", "佰", "仟"};

    /**
     * 单位
     */
    public static String[] CN_UNITS = new String[]{"个", "十", "百", "千", "万", "十", "百", "千", "亿", "十", "百", "千", "万"};


    /**
     * 单位进位，中文默认为4位即（万、亿）
     */
    public static int UNIT_STEP = 4;
    /**
     * 特殊字符：整
     */
    private static final String CN_FULL = "整";
    /**
     * 特殊字符：负
     */
    private static final String CN_NEGATIVE = "负";
    /**
     * 金额的精度，默认值为2
     */
    private static final int MONEY_PRECISION = 2;
    /**
     * 特殊字符：零元整
     */
    private static final String CN_ZEOR_FULL = "零元" + CN_FULL;


    /**
     * 保留指定位数的小数(少的位数不补零)
     *
     * @param value 待处理的数字
     * @param n     保留的小数位数
     * @return
     */
    public static String keepRandomPoint(Double value, int n) {
        if (value == null) {
            value = 0.00;
        }
        return new BigDecimal(value).setScale(n, RoundingMode.HALF_UP).toString();
    }

    /**
     * 浮点保留任意位小数(少位补零)
     *
     * @param value 待处理的数字
     * @param n     保留的小数位数
     * @return
     */
    public static String keepRandomPointZero(Double value, int n) {
        if (value == null) {
            value = 0.00;
        }
        BigDecimal bigDecimal = new BigDecimal(value);
        String rs = bigDecimal.setScale(n, RoundingMode.HALF_UP).toString();
        DecimalFormat df = new DecimalFormat("#0.00");
        return df.format(Double.valueOf(rs));
    }

    /**
     * 把给的书转为百分比表示,结果保留指定小数位(少位补零)
     *
     * @param value 待处理的数字
     * @param n     保留的小数位数
     * @return
     */
    public static String transDoubleToPercent(double value, int n) {
        NumberFormat percent = NumberFormat.getPercentInstance();
        percent.setGroupingUsed(false);
        percent.setMaximumFractionDigits(n);
        return percent.format(value);
    }


    /***
     * 数据格式化
     * @param o
     * @param fmt
     * @return
     */
    public static double formatDouble(Object o, String fmt) {
        try {
            DecimalFormat df = new DecimalFormat(fmt);
            return Double.parseDouble(df.format(o));
        } catch (Exception ex) {
            log.error("格式化数据失败: " + ex.getMessage() + " 【" + o + "】");
            return 0.0;
        }

    }

    /**
     * 把输入的金额转换为汉语中人民币的大写
     *
     * @param numberOfMoney 输入的金额
     * @return 对应的汉语大写
     */
    public static String transToUppercaseNumbers(BigDecimal numberOfMoney) {
        StringBuffer sb = new StringBuffer();
        // -1, 0, or 1 as the value of this BigDecimal is negative, zero, or
        // positive.
        int signum = numberOfMoney.signum();
        // 零元整的情况
        if (signum == 0) {
            return CN_ZEOR_FULL;
        }
        //这里会进行金额的四舍五入
        long number = numberOfMoney.movePointRight(MONEY_PRECISION)
                .setScale(0, 4).abs().longValue();
        // 得到小数点后两位值
        long scale = number % 100;
        int numUnit = 0;
        int numIndex = 0;
        boolean getZero = false;
        // 判断最后两位数，一共有四中情况：00 = 0, 01 = 1, 10, 11


      /*  if (!(scale > 0)) {
            numIndex = 2;
            number = number / 100;
            getZero = true;
        }
         if ((scale > 0) && (!(scale % 10 > 0))) {
            numIndex = 1;
            number = number / 10;
            getZero = true;
        }*/
        if (scale <= 0) {
            numIndex = 2;
            number = number / 100;
            getZero = true;
        }
        if ((scale > 0) && (scale % 10 <= 0)) {
            numIndex = 1;
            number = number / 10;
            getZero = true;
        }
        int zeroSize = 0;
        while (true) {
            if (number <= 0) {
                break;
            }
            // 每次获取到最后一个数
            numUnit = (int) (number % 10);
            if (numUnit > 0) {
                if ((numIndex == 9) && (zeroSize >= 3)) {
                    sb.insert(0, CN_UPPER_MONETRAY_UNIT[6]);
                }
                if ((numIndex == 13) && (zeroSize >= 3)) {
                    sb.insert(0, CN_UPPER_MONETRAY_UNIT[10]);
                }
                sb.insert(0, CN_UPPER_MONETRAY_UNIT[numIndex]);
                sb.insert(0, CN_UPPER_NUMBER[numUnit]);
                getZero = false;
                zeroSize = 0;
            } else {
                ++zeroSize;
                if (!(getZero)) {
                    sb.insert(0, CN_UPPER_NUMBER[numUnit]);
                }
                if (numIndex == 2) {
                    if (number > 0) {
                        sb.insert(0, CN_UPPER_MONETRAY_UNIT[numIndex]);
                    }
                } else if (((numIndex - 2) % 4 == 0) && (number % 1000 > 0)) {
                    sb.insert(0, CN_UPPER_MONETRAY_UNIT[numIndex]);
                }
                getZero = true;
            }
            // 让number每次都去掉最后一个数
            number = number / 10;
            ++numIndex;
        }
        // 如果signum == -1，则说明输入的数字为负数，就在最前面追加特殊字符：负
        if (signum == -1) {
            sb.insert(0, CN_NEGATIVE);
        }
        // 输入的数字小数点后两位为"00"的情况，则要在最后追加特殊字符：整
       /* if (!(scale > 0)) {
            sb.append(CN_FULL);
        }*/
        if (scale <= 0) {
            sb.append(CN_FULL);
        }

        return sb.toString();
    }

    /**
     * 阿拉伯数字转汉字 ArabicNumerals  转换
     *
     * @param value
     * @return
     */
    public static String transArabicNumerToChinese(String value) {
        if (value == null || value == "") {
            return null;
        }
        //整数部分
        String intNumber = "";
        //小数部分
        String decimal = "";
        if (value.contains(".")) {
            String[] split = value.split("\\.");
            //获取整数部分
            intNumber = split[0];
            //获取小数部分
            decimal = split[1];
        } else {
            intNumber = value;
        }
        String result = "";
        //先转换整数部分
        int n = intNumber.length();
        for (int i = 0; i < n; i++) {
            int num = intNumber.charAt(i) - '0';
            if (i != n - 1 && num != 0) {
                result += CHINESE_NUMBER[num] + NUMBER_UNIT[n - 2 - i];
            } else {
                result += CHINESE_NUMBER[num];
            }
        }
        if (!decimal.isEmpty()) {
            //再转换小数部分
            result += "点";
            String[] split = decimal.split("");

            for (String aChar : split) {
                result += CHINESE_NUMBER[Integer.valueOf(aChar)];
            }
        }
        return result;
    }

    /**
     * 将数值转换为中文
     *
     * @param num          需要转换的数值
     * @param isColloquial 是否口语化。例如12转换为'十二'而不是'一十二'。
     * @return
     */
    public static String convert(long num, boolean isColloquial) {
        if (num < 10) {
            // 10以下直接返回对应汉字
            // ASCII2int
            return CHINESE_NUMBER[(int) num];
        }

        char[] chars = String.valueOf(num).toCharArray();
        if (chars.length > CN_UNITS.length) {
            // 超过单位表示范围的返回空
            return null;
        }
        // 记录上次单位进位
        boolean isLastUnitStep = false;
        // 创建数组，将数字填入单位对应的位置
        ArrayList<String> cnchars = new ArrayList<String>(chars.length * 2);
        // 从低位向高位循环
        for (int pos = chars.length - 1; pos >= 0; pos--) {
            char ch = chars[pos];
            // ascii2int 汉字
            String cnChar = CHINESE_NUMBER[ch - '0'];
            // 对应的单位坐标
            int unitPos = chars.length - pos - 1;
            // 单位
            String cnUnit = CN_UNITS[unitPos];
            // 是否为0
            boolean isZero = (ch == '0');
            // 是否低位为0
            boolean isZeroLow = (pos + 1 < chars.length && chars[pos + 1] == '0');
            // 当前位是否需要单位进位
            boolean isUnitStep = (unitPos >= UNIT_STEP && (unitPos % UNIT_STEP == 0));

            if (isUnitStep && isLastUnitStep) {
                // 去除相邻的上一个单位进位
                int size = cnchars.size();
                cnchars.remove(size - 1);
                if (!CHINESE_NUMBER[0].equals(cnchars.get(size - 2))) {
                    // 补0
                    cnchars.add(CHINESE_NUMBER[0]);
                }
            }
            // 单位进位(万、亿)，或者非0时加上单位
            if (isUnitStep || !isZero) {
                cnchars.add(cnUnit);
                isLastUnitStep = isUnitStep;
            }
            // 当前位为0低位为0，或者当前位为0并且为单位进位时进行省略
            if (isZero && (isZeroLow || isUnitStep)) {
                continue;
            }
            cnchars.add(cnChar);
            isLastUnitStep = false;
        }

        Collections.reverse(cnchars);
        // 清除最后一位的0
        int chSize = cnchars.size();
        String chEnd = cnchars.get(chSize - 1);
        if (CHINESE_NUMBER[0].equals(chEnd) || CN_UNITS[0].equals(chEnd)) {
            cnchars.remove(chSize - 1);
        }

        // 口语化处理
        if (isColloquial) {
            String chFirst = cnchars.get(0);
            String chSecond = cnchars.get(1);
            // 是否以'一'开头，紧跟'十'
            if (chFirst.equals(CHINESE_NUMBER[1]) && chSecond.startsWith(CN_UNITS[1])) {
                cnchars.remove(0);
            }
        }
        String[] result = cnchars.toArray(new String[]{});
        StringBuffer strs = new StringBuffer(32);
        for (String str : result) {
            strs.append(str);
        }
        return strs.toString();
    }


}

