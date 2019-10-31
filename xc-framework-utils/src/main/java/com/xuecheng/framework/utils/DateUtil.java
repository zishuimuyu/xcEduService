package com.xuecheng.framework.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 日期时间工具类
 * @author gjh
 * @createDate：2019-09
 */
public final class DateUtil {

    /**
     * yyyy-MM-dd.
     */
    public static final String DATE_FORMAT_ONE = "yyyy-MM-dd";

    /**
     * yyyy/MM/dd.
     */
    public static final String DATE_FORMAT_TWO = "yyyy/MM/dd";

    /**
     * yyyyMMdd.
     */
    public static final String DATE_FORMAT_THREE = "yyyyMMdd";

    /**
     * yyyyMM.
     */
    public static final String DATE_FORMAT_FOUR = "yyyyMM";

    /**
     * yyyy-MM-dd HH:mm:ss.
     */
    public static final String DATE_FORMAT_FIVE = "yyyy-MM-dd HH:mm:ss";

    /**
     * yyyy-MM-dd HH:mm:ss.SSS.
     */
    public static final String DATE_FORMAT_UPDATE = "yyyy-MM-dd HH:mm:ss.SSS";

    /**
     * yyyy.
     */
    public static final String DATE_FORMAT_SIX = "yyyy";

    /**
     * MM.
     */
    public static final String DATE_FORMAT_SEVEN = "MM";

    /**
     * yyyy/MM/dd HH:mm:ss.
     */
    public static final String DATE_FORMAT_EIGHT = "yyyy/MM/dd HH:mm:ss";

    /**
     * HH:mm:ss.
     */
    public static final String DATE_FORMAT_NINE = "HH:mm:ss";

    /**
     * yy/MM/dd.
     */
    public static final String DATE_FORMAT_TEN = "yy/MM/dd";

    /**
     * yyyy/M/d.
     */
    public static final String DATE_FORMAT_ELEVEN = "yyyy/M/d";

    /**
     * yyyy/MM.
     */
    public static final String DATE_FORMAT_TWELEVE = "yyyy/MM";

    /**
     * yyyyMMddHHmmssSSS.
     */
    public static final String DATE_FORMAT_THIRTEEN = "yyyyMMddHHmmssSSS";

    /**
     * yyMMdd.
     */
    public static final String DATE_FORMAT_FOURTEEN = "yyMMdd";

    /**
     * yyyy.MM.dd.
     */
    public static final String DATE_FORMAT_SIXTEEN = "yyyy.MM.dd";

    /**
     * yyyyMMddHHmmss.
     */
    public static final String DATE_FORMAT_SEVENTEEN = "yyyyMMddHHmmss";

    /**
     * yyyy年MM月dd日
     */
    public static final String DATE_FORMAT_EIGHTEEN = "yyyy年MM月dd日";

    /**
     * HH:mm.
     */
    public static final String DATE_FORMAT_NINETEEN = "HH:mm";

    /**
     * HHmm.
     */
    public static final String DATE_FORMAT_TWENTY = "HHmm";

    /**
     * MMM d'th', yyyy EEE'.'
     */
    public static final String DATE_FORMAT_THIRTY = "EEE', 'MMM d'th', yyyy'.'";

    /**
     * yyyy'年'MM'月'dd日， EEE
     */
    public static final String DATE_FORMAT_FORTY = "yyyy'年'MM'月'dd'日， 'EEE";

    /**
     * yyyy-MM
     */
    public static final String DATE_FORMAT_SIXTY = "yyyy-MM";

    /**
     * yyyy'年'MM'月'
     */
    public static final String DATE_FORMAT_SEVENTY = "yyyy年MM月";

    /**
     * yyyy/MM/dd HH:mm
     */
    public static final String DATE_FORMAT_EIGHTY = "yyyy-MM-dd HH:mm";
    /**
     * dd
     */
    public static final String DATE_FORMAT_NINETY = "dd";

    /**
     * INT_4.
     */
    private static final int INT_4 = 4;

    /**
     * INT_6.
     */
    private static final int INT_6 = 6;

    /**
     * INT_8.
     */
    private static final int INT_8 = 8;

    /**
     * .
     */
    public static final String YEAR_FORMAT = "[12][0-9]{3}";

    /**
     * 年.
     */
    public static final int YEAR = 1;

    /**
     * 月.
     */
    public static final int MONTH = 2;

    /**
     * 日.
     */
    public static final int DAY = 3;

    /**
     * 获得系统时间
     *
     * @return 系统时间
     */
    public static Date getSysDate() {

        return new Date(System.currentTimeMillis());
    }

    /**
     * 获得系统时间
     *
     * @return 系统时间
     */
    public static Timestamp getTimeStamp() {
        Date date = new Date();
        return new Timestamp(date.getTime());
    }

    /**
     * 获得String类型系统时间yyyy-MM-dd
     *
     * @return String类型系统时间
     */
    public static String getCurFormatDate() {
        Date curDate = new Date();
        return dateToString(curDate);
    }

    /**
     * 获得String类型系统时间yyyy-MM-dd HH:mm:ss
     *
     * @return String系统时间
     */
    public static String getCurFormatTime() {
        return dateToString(getSysDate(), DATE_FORMAT_FIVE);
    }

    /**
     * 获得当前年
     *
     * @return 当前年
     */
    public static int getCurYear() {
        int i_year = 0;
        Date curDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(curDate);
        i_year = cal.get(1);
        return i_year;
    }

    /**
     * 按指定格式转化字符串为Date
     *
     * @param strDate   String
     * @param strFormat String
     * @return FormatDate
     */
    public static Date getFormatDate(String strDate, String strFormat) {
        if (StringUtil.isEmpty(strDate) || StringUtil.isEmpty(strFormat)) {
            return null;
        }
        SimpleDateFormat df = new SimpleDateFormat(strFormat);
        df.setLenient(false);
        Date date = null;
        try {
            date = df.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 获得月末日期
     *
     * @param strDate 年月（2017-05）
     * @return 2017-05-31
     */
    public static String getLastMonthDate(String strDate) {
        if (StringUtil.isEmpty(strDate)) {
            return null;
        }
        Date date = getFormatDate(strDate, DATE_FORMAT_SIXTY);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cale = Calendar.getInstance();
        cale.setTime(date);
        cale.set(Calendar.DATE, cale.getActualMaximum(Calendar.DATE));
        String lastday = format.format(cale.getTime());
        return lastday;
    }

    /**
     * 按yyyy-MM-dd格式转化字符串为Date
     *
     * @param strDate String
     * @return FormatDate
     */
    public static Date getFormatDate(String strDate) {
        return getFormatDate(strDate, DATE_FORMAT_ONE);
    }

    /**
     * 按指定格式转化Date为字符串
     *
     * @param date     Date
     * @param toFormat String
     * @return FormatDate String
     */
    public static String dateToString(Date date, String toFormat) {

        if (date == null) {
            return "";
        }
        SimpleDateFormat df = new SimpleDateFormat(toFormat);
        df.setLenient(false);
        return df.format(date);
    }

    /**
     * 时间格式化为yyyy-MM-dd的字符串
     *
     * @param datetime 日期参数
     * @return 格式化后的日期字符串
     */
    public static String dateToString(Date datetime) {
        return dateToString(datetime, DATE_FORMAT_ONE);
    }

    /**
     * 按yyyyMMddHHmmss格式转化Date为字符串
     *
     * @param datetime Date
     * @return FormatDateString
     */
    public static String dateTimeToString(Date datetime) {
        return dateToString(datetime, DATE_FORMAT_SEVENTEEN);
    }

    /**
     * 按yyyyMMddHHmmss格式转化字符串为Date
     *
     * @param datetime String
     * @return FormatDateString
     */
    public static Date getFormatDateTime(String datetime) {
        return getFormatDate(datetime, DATE_FORMAT_SEVENTEEN);
    }

    /**
     * 按指定格式格式化Date
     *
     * @param date      Date
     * @param strFormat String
     * @return FormatDate Date
     */
    public static Date formatDate(Date date, String strFormat) {
        if (date == null) {
            return null;
        }
        Date reDate = getFormatDate(dateToString(date, strFormat), strFormat);
        return reDate;
    }

    /**
     * "yyyy-MM-dd HH:mm:ss"转为timestamp格式
     *
     * @param date
     * @return
     */
    public static Timestamp formatTimestamp(String date) {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        try {
            ts = Timestamp.valueOf(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ts;
    }

    /**
     * 比较两个日期
     *
     * @param day1 String
     * @param day2 String
     * @return int day1>day2返回1，day1<day2返回-1，day1=day2返回0
     */
    public static int compareDate(String day1, String day2) {

        String date1 = "";
        String date2 = "";
        int isEarly = -1;

        if (day1 == null || day1.trim().equals("") || day2 == null || day2.trim().equals("")) {
            isEarly = -1;
        } else {
            date1 = day1.replaceAll("-", "");
            date2 = day2.replaceAll("-", "");
            int year1 = Integer.parseInt(date1.substring(0, INT_4));
            int month1 = Integer.parseInt(date1.substring(INT_4, INT_6)) - 1;
            int day = Integer.parseInt(date1.substring(INT_6, INT_8));
            Calendar cal1 = Calendar.getInstance();
            cal1.set(year1, month1, day);

            int year2 = Integer.parseInt(date2.substring(0, INT_4));
            int month2 = Integer.parseInt(date2.substring(INT_4, INT_6)) - 1;
            int day22 = Integer.parseInt(date2.substring(INT_6, INT_8));
            Calendar cal2 = Calendar.getInstance();
            cal2.set(year2, month2, day22);

            if (cal1.getTime().compareTo(cal2.getTime()) > 0) {
                isEarly = 1;
            } else if (cal1.getTime().compareTo(cal2.getTime()) == 0) {
                isEarly = 0;
            }
        }
        return isEarly;
    }



    /**
     * 日期追加的计算
     *
     * @param date    原日期
     * @param AddNum 追加的年月日的大小
     * @param addType   追加的类型 1:年,2:月,3:日,
     * @return 追加后的新日期
     */
    public static Date addDate(Date date, int AddNum, int addType) {
        GregorianCalendar objCal = new GregorianCalendar();
        objCal.setTime(date);
        switch (addType) {
            case 1: {
                objCal.add(GregorianCalendar.YEAR, AddNum);
                break;
            }
            case 2: {
                objCal.add(GregorianCalendar.MONTH, AddNum);
                break;
            }
            case 3: {
                objCal.add(GregorianCalendar.DATE, AddNum);
                break;
            }
            default: {
                break;
            }
        }
        return objCal.getTime();
    }

    /**
     * 转换给定日期为星期显示
     * @param date        日期
     * @param isReturnChinese 是否返回中文
     * @return 显示中文化的星期X
     */
    public static String getCnEnDayOfWeek(Date date, boolean isReturnChinese) {
        String strDayOfWeek = "星期";
        String strEnDayOfWeek = "";
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == 1) {
            strDayOfWeek += "一";
            strEnDayOfWeek += "Monday";
        } else if (dayOfWeek == 2) {
            strDayOfWeek += "二";
            strEnDayOfWeek += "Tuesday";
        } else if (dayOfWeek == 3) {
            strDayOfWeek += "三";
            strEnDayOfWeek += "Wednesday";
        } else if (dayOfWeek == 4) {
            strDayOfWeek += "四";
            strEnDayOfWeek += "Thursday";
        } else if (dayOfWeek == 5) {
            strDayOfWeek += "五";
            strEnDayOfWeek += "Friday";
        } else if (dayOfWeek == 6) {
            strDayOfWeek += "六";
            strEnDayOfWeek += "Saturday";
        } else {
            strDayOfWeek += "日";
            strEnDayOfWeek += "Sunday";
        }
        if (isReturnChinese) {
            return strDayOfWeek;
        }
        return strEnDayOfWeek;

    }

    /**
     * 把给定的日期转换为年月日  星期 的显示方式
     * @param date        日期
     * @param isReturnChinese 是否返回中文格式
     * @return 返回的结果String类型
     */
    public static String getCurrentDayOfWeek(Date date, boolean isReturnChinese) {
        SimpleDateFormat sdf = null;

        if (isReturnChinese) {
            sdf = new SimpleDateFormat(DATE_FORMAT_FORTY, Locale.CHINESE);
        } else {
            sdf = new SimpleDateFormat(DATE_FORMAT_THIRTY, Locale.ENGLISH);
        }
        String strDate = sdf.format(Calendar.getInstance().getTime());
        return strDate;

    }

    /**
     * 获取给定日期所在年的一年最后一天
     * @param date 日期
     * @return 一年最后一天 yyyy-MM-dd
     * @throws ParseException 异常
     */
    public static Date getYearLastDay(Date date) throws ParseException {
        String lastDay = new SimpleDateFormat(DATE_FORMAT_SIX).format(date) + "-12-31";
        return new SimpleDateFormat(DATE_FORMAT_ONE).parse(lastDay);
    }

    /**
     * 获取两个日期之间间隔天数
     * @param startDay 开始日期
     * @param endDay 结束日期
     * @return 日期相隔天数
     */
    public static int getDiffDays(Date startDay, Date endDay) {
        Calendar d1 = Calendar.getInstance();
        Calendar d2 = Calendar.getInstance();
        d1.setFirstDayOfWeek(Calendar.SUNDAY);
        d2.setFirstDayOfWeek(Calendar.SUNDAY);
        d1.setTime(startDay);
        d2.setTime(endDay);
        // swap dates so that d1 is start and d2 is end
        if (d1.after(d2)) {
            Calendar swap;
            swap = d1;
            d1 = d2;
            d2 = swap;
        }
        int days = d2.get(Calendar.DAY_OF_YEAR) - d1.get(Calendar.DAY_OF_YEAR);
        int y2 = d2.get(Calendar.YEAR);
        if (d1.get(Calendar.YEAR) != y2) {
            d1 = (Calendar) d1.clone();
            do {
                days += d1.getActualMaximum(Calendar.DAY_OF_YEAR);
                d1.add(Calendar.YEAR, 1);
            } while (d1.get(Calendar.YEAR) != y2);
        }
        return days;
    }

    /**
     * 获取两个日期之间间隔月数
     * @param startMonth 开始日期
     * @param endMonth 结束日期
     * @return 日期相隔天数
     */
    public static int getDiffMonth(String startMonth, String endMonth) {
        Date d1 = getFormatDate(endMonth, DATE_FORMAT_SIXTY);
        Date d2 = getFormatDate(startMonth,DATE_FORMAT_SIXTY);
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(d1);
        c2.setTime(d2);
        int year1 = c1.get(Calendar.YEAR);
        int year2 = c2.get(Calendar.YEAR);
        int month1 = c1.get(Calendar.MONTH);
        int month2 = c2.get(Calendar.MONTH);
        int day1 = c1.get(Calendar.DAY_OF_MONTH);
        int day2 = c2.get(Calendar.DAY_OF_MONTH);
        // 获取年的差值 假设 startMonth = 2015-8-16  endMonth = 2011-9-30
        int yearInterval = year1 - year2;
        // 如果 startMonth的 月-日 小于 endMonth的 月-日 那么 yearInterval-- 这样就得到了相差的年数
        if (month1 < month2 || month1 == month2 && day1 < day2) {
            yearInterval--;
        }
        // 获取月数差值
        int monthInterval = (month1 + 12) - month2;
        if (day1 < day2) {
            monthInterval--;
        }
        monthInterval %= 12;
        return yearInterval * 12 + monthInterval;
    }



    /** 获取日期相隔周数
     * @param startDate  开始日期
     * @param endDate 结束日期
     * @return 日期相隔周数
     */
    public static int getDiffWeek(Date startDate, Date endDate) {
        return (int) ((getNextMonday(endDate).getTime()-getNextMonday(startDate).getTime()) / (1000 * 60 * 60 * 3600 * 7));
    }

    /**
     * 获取给定日期所在周的周一的日期
     * @param date 日期
     * @return 获得周一的日期
     */
    public static Date getMonday(Date date) {
        Calendar c = Calendar.getInstance();
        c.setFirstDayOfWeek(Calendar.SUNDAY);
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return c.getTime();
    }

    /**
     * 获得给定日期所在周的周六的日期
     * @param date 日期
     * @return 获得周六的日期
     */
    public static Date getSaturday(Date date) {
        Calendar c = Calendar.getInstance();
        c.setFirstDayOfWeek(Calendar.SUNDAY);
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
        return c.getTime();
    }

    /**
     * 获得某日为所在该周第几天(星期日为第一天)
     *
     * @param date 日期
     * @return 获得某日为该周第几天
     */
    public static int getDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.SUNDAY);
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }


    /**
     * 获得给定月第一天日期
     *
     * @param date
     * @return Date
     */
    public static Date getFirstDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.SUNDAY);
        calendar.setTime(date);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.add(Calendar.DATE, 1 - dayOfMonth);
        return calendar.getTime();
    }

    /**
     * 取得某天是一年中的多少周
     *
     * @param date date
     * @return 某天是一年中的多少周
     */
    public static int getWeekOfYear(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int currMonth = c.get(Calendar.MONTH);
        if (currMonth == 11 && c.get(Calendar.WEEK_OF_YEAR) == 1) {
            // 取上周周一
            c.add(Calendar.DATE, -7);
            return c.get(Calendar.WEEK_OF_YEAR) + 1;

        } else {
            return c.get(Calendar.WEEK_OF_YEAR);
        }
    }

    /**
     * 得到给定年周的总数
     *
     * @param year year
     * @return 某一年周的总数
     */
    public static int getMaxWeekNumOfYear(int year) {
        Calendar c = new GregorianCalendar();
        c.set(year, Calendar.DECEMBER, 31, 23, 59, 59);
        return getWeekOfYear(c.getTime());
    }

    /**
     * 获取给定日期所在周是当月第几周
     * @param date 日期
     * @return 该月第几周
     */
    public static int getWeekOfMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setFirstDayOfWeek(Calendar.SUNDAY);
        c.setTime(date);
        return c.get(Calendar.WEEK_OF_MONTH);
    }

    /**
     * 获得给定日期的下一个星期一的日期
     *
     * @param day day
     * @return 日期的下一个星期一的日期
     */
    public static Date getNextMonday(Date day) {
        Calendar date = Calendar.getInstance();
        date.setFirstDayOfWeek(Calendar.SUNDAY);
        date.setTime(day);
        Calendar result = null;
        result = date;
        do {
            result = (Calendar) result.clone();
            result.add(Calendar.DATE, 1);
        } while (result.get(Calendar.DAY_OF_WEEK) != 2);

        return result.getTime();
    }

    /**
     * 获得给定日期所在周第一天的日期(星期天为第一天)
     *
     * @param day day
     * @return 本周第一天的日期(星期天为第一天)
     */
    public static Date getFirstDayOfWeek(Date day) {
        Calendar date = Calendar.getInstance();
        date.setFirstDayOfWeek(Calendar.SUNDAY);
        date.setTime(day);
        date.set(Calendar.DAY_OF_WEEK, date.getFirstDayOfWeek());
        return date.getTime();
    }


    /**
     * 获取给定时间所在的年值(即年份)
     * @param date
     * @return
     */
    public static Integer getYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.YEAR);
    }
    /**
     * 获取给定时间所在的月数值(即处于当年的第几月)
     * @param date
     * @return
     */
    public static Integer getMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MONTH) + 1;
    }
    /**
     * 获取给定时间所在的天数值(即处于当年的第几天)
     * @param date
     * @return
     */
    public static Integer getDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_YEAR);
    }
    /**
     * 获取给定时间所在的周值(即处于当年的第几周)
     * @param date
     * @return
     */
    public static Integer getWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.WEEK_OF_YEAR);
    }
    /**
     * 获取指定时间的小时值
     * @param date
     * @return
     */
    public static Integer getHour(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.HOUR_OF_DAY);
    }
    /**
     * 获取指定时间的分钟值
     * @param date
     * @return
     */
    public static Integer getMinute(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MINUTE);
    }

    /**
     * 获取指定时间的秒值
     * @param date
     * @return
     */
    public static Integer getSecond(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.SECOND);
    }

    /**
     * String类型时间转Date
     * @param time String类型时间
     * @return 返回Date类型yyyy-MM-dd格式日期
     */
    public static Date getDateFromStrFull(String time) throws ParseException {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sf.parse(time);

    }

    /**
     * 时间转化为年份
     * @param datetime 时间
     * @return String类型的年份
     */
    public static String formatDateToYear(Date datetime) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy");
        return sf.format(datetime);
    }

    /**
     * Data类型时间转String
     * @param datetime Date类型
     * @return 返回String类型yyyy-MM-dd格式日期
     */
    public static String getFormatDate(Date datetime) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        return sf.format(datetime);
    }

    /**
     * Data类型时间转String
     * @param datetime Date类型
     * @return 返回String类型yyyy-MM-dd HH:mm:ss格式时间
     */
    public static String getFormatDateFull(Date datetime) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return  sf.format(datetime);

    }

    /**
     * 获取指定年月份的天数
     *
     * @param date 月份 yyyy-MM格式
     * @return
     */
    public static int getDaysOfMonth(String date) throws Exception {
        SimpleDateFormat sf = new SimpleDateFormat(DATE_FORMAT_SIXTY);
        Date time = sf.parse(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        int num2 = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        return num2;
    }

  /*  public static int getDaysOfMonth(String month) throws ParseException {
        month = month.trim();
        if (month.length()>5&&month.length()<=7){
            month = month + "-01";
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(month);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }*/


    /**
     * 得到月天数封装Map
     *
     * @param dayNum 月的天数
     * @return dayMap(dd, " ")
     */
    public static Map<String, String> getDayOfMonth(int dayNum) {
        Map<String, String> dayMap = new TreeMap<String, String>();
        for (int i = 1; i <= dayNum; i++) {
            dayMap.put(i <= 9 ? "0" + i : "" + i, "");
        }
        return dayMap;
    }


    /**
     * 得到时间封装Map
     *
     * @param selday 选择日期
     * @param sTime  开始小时:00
     * @param eTime  结束小时:period
     * @return 开始小时整点时刻到结束小时整点时刻map
     */
    public static Map<String, Double> getAnyTimeMap(String selday, int sTime, int eTime, int period) {
        Map<String, Double> timeMap = new TreeMap<String, Double>();

        for (int i = (sTime * 60 - period); i < (eTime * 60); i += period) {
            String hourAndMinute = (((i + period) / 60) > 9 ? ((i + period) / 60) : "0" + ((i + period) / 60)) + ":"
                    + (((i + period) % 60) > 9 ? ((i + period) % 60) : "0" + ((i + period) % 60));
            String time = selday + " " + hourAndMinute;
            if (sTime == 0 && "24:00".equals(hourAndMinute)) {
                continue;
            }
            timeMap.put(time, null);
        }
        return timeMap;
    }

    /**
     * 得到时间封装Map
     *
     * @param selectedMonth 选择月份
     * @param sTime    开始日期
     * @param eTime    结束日期
     * @return
     */
    public static Map<String, Double> getMonthMap(String selectedMonth, int sTime, int eTime) {
        Map<String, Double> timeMap = new TreeMap<String, Double>();

        for (int i = sTime; i < eTime; i++) {
            String time = selectedMonth + "-" + (i > 9 ? i : "0" + i);
            timeMap.put(time, null);
        }
        return timeMap;
    }


    /**
     * 获取所查询年月的环比年月
     *
     * @return
     */
    public static String getHbYearMonth(String proMonth) {
        String hbYear = "";
        String hbMonth = "";
        if (Integer.parseInt(proMonth.split("-")[1]) == 1) {
            hbYear = (Integer.parseInt(proMonth.split("-")[0]) - 1) + "";
            hbMonth = "" + 12;
        } else if (Integer.parseInt(proMonth.split("-")[1]) <= 10) {
            hbYear = proMonth.split("-")[0];
            hbMonth = "0" + (Integer.parseInt(proMonth.split("-")[1]) - 1);
        } else {
            hbYear = proMonth.split("-")[0];
            hbMonth = "" + (Integer.parseInt(proMonth.split("-")[1]) - 1);
        }

        return hbYear + "-" + hbMonth;
    }


    /**
     * 获取同比年月
     *
     * @param proMonth
     * @return
     */
    public static String getTbYearMonth(String proMonth) {
        String tbYear = (Integer.parseInt(proMonth.split("-")[0]) - 1) + "";
        String tbMonth = proMonth.split("-")[1];
        return tbYear + "-" + tbMonth;
    }

    /**
     * 得到昨天日期
     *
     * @return String
     */
    public static String getYesterday() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        String date = dateToString(cal.getTime());
        return date;
    }

    /**
     * 得到上月月份
     *
     * @return 返回String类型 YYYY-MM格式字符串
     */
    public static String getLastMonth() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        String date = dateToString(cal.getTime(), DATE_FORMAT_SIXTY);
        return date;
    }

    /**
     * 得到去年年份
     *
     * @return String
     */
    public static String getLastYear() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -1);
        String date = dateToString(cal.getTime(), DATE_FORMAT_SIX);
        return date;
    }

    /**
     * 获取指定时间间隔(以秒为单位)后的另一个时间
     *
     * @param startTime 起始时间 必须是yyyy-MM-dd HH:mm:ss格式
     * @param second    时间间隔,正值表示向前推(时间增加),负值表示向后推移(时间减少)
     * @return
     */
    public static String getIntervalTime(String startTime, int second) throws ParseException {
        Date dateFromStrFull = getDateFromStrFull(startTime);
        long time = dateFromStrFull.getTime();
        long nextTime = time + second * 1000;
        SimpleDateFormat dates = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dates.format(nextTime);
    }
    /**
     * 获得该月第一天
     * @param year
     * @param month
     * @return
     */
    public static String getFirstDayOfMonth(int year,int month){
        Calendar cal = Calendar.getInstance();
        cal.clear();
        //设置年份
        cal.set(Calendar.YEAR,year);
        //设置月份
        cal.set(Calendar.MONTH, month-1);
        //获取某月最小天数
        int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最小天数
        cal.set(Calendar.DAY_OF_MONTH, firstDay);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String firstDayOfMonth = sdf.format(cal.getTime());
        return firstDayOfMonth;
    }

    /**
     * 获得该月最后一天
     * @param year
     * @param month
     * @return
     */
    public static String getLastDayOfMonth(int year,int month){
        Calendar cal = Calendar.getInstance();
        cal.clear();
        //设置年份
        cal.set(Calendar.YEAR,year);
        //设置月份
        cal.set(Calendar.MONTH, month-1);
        //获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String lastDayOfMonth = sdf.format(cal.getTime());
        return lastDayOfMonth;
    }
}
