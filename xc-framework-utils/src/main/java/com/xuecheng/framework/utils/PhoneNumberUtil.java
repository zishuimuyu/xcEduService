package com.xuecheng.framework.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * 电话号码工具类
 * @author：GJH
 * @createDate：2019/9/6
 */
public class PhoneNumberUtil {

    /**
     * 大陆号码或香港号码均可
     * @param phoneNumber 电话号码
     * @throws PatternSyntaxException
     */
    public static boolean isPhoneLegal(String phoneNumber)throws PatternSyntaxException {
        return isChinaPhoneLegal(phoneNumber) || isHKPhoneLegal(phoneNumber);
    }

    /**
     * 判断是否是合法的大陆手机号码: 11位数，匹配格式：前三位固定格式+后8位任意数
     * 此方法中前三位格式有：
     * 13+任意数
     * 15+除4的任意数
     * 18+除1和4的任意数
     * 17+除9的任意数
     * 147
     * @param phoneNumber
     * @return
     * @throws PatternSyntaxException
     */
    public static boolean isChinaPhoneLegal(String phoneNumber) throws PatternSyntaxException {
        String regExp = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(phoneNumber);
        return m.matches();
    }

    /**
     * 是否是香港手机号码: 8位数，5|6|8|9开头+7位任意数
     * @param phoneNumber 电话号码
     * @return
     * @throws PatternSyntaxException
     */
    public static boolean isHKPhoneLegal(String phoneNumber)throws PatternSyntaxException {
        String regExp = "^(5|6|8|9)\\d{7}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(phoneNumber);
        return m.matches();
    }

}
