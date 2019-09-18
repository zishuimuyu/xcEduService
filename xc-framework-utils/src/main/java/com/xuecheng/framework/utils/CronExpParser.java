package com.xuecheng.framework.utils;/*
package com.xuecheng.framework.utils;


import org.quartz.CronExpression;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

*/
/**
 * @Description：将Cron表达式解析成中文
 * @author：MLNSOFT-GJH
 * @createDate：2019/2/22
 * @company：北京木联能软件股份有限公司
 *//*

public class CronExpParser {
    */
/**
     * 解析corn表达式，生成指定日期的时间序列
     *
     * @param cronExpression cron表达式
     * @param cronDate       cron解析日期
     * @param result         crom解析时间序列
     * @return 解析成功或者失败
     *//*

    public static boolean parser(String cronExpression, String cronDate, List<String> result) {
        if (cronExpression == null || cronExpression.length() < 1 || cronDate == null || cronDate.length() < 1) {
            return false;
        } else {
            CronExpression exp = null;
            // 初始化cron表达式解析器
            try {
                exp = new CronExpression(cronExpression);
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return false;
            }

            // 定义生成时间范围
            // 定义开始时间，前一天的23点59分59秒
            Calendar c = Calendar.getInstance();
            String sStart = cronDate + " 00:00:00";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date dStart = null;
            try {
                dStart = sdf.parse(sStart);
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            c.setTime(dStart);
            c.add(Calendar.SECOND, -1);
            dStart = c.getTime();

            // 定义结束时间，当天的23点59分59秒
            c.add(Calendar.DATE, 1);
            Date dEnd = c.getTime();

            // 生成时间序列
            Date dd = dStart;
            dd = exp.getNextValidTimeAfter(dd);
            while ((dd.getTime() >= dStart.getTime()) && (dd.getTime() <= dEnd.getTime())) {
                result.add(sdf.format(dd));
                dd = exp.getNextValidTimeAfter(dd);
            }
            exp = null;
        }
        return true;
    }

    public static String translateToChinese(String cronExp) {
        if (cronExp == null || cronExp.length() < 1) {
            return "corn表达式不正确";
        }
        CronExpression exp = null;
        // 初始化cron表达式解析器
        try {
            exp = new CronExpression(cronExp);
        } catch (ParseException e) {
            return "corn表达式不正确";
        }
        String[] tmpCorns = cronExp.split(" ");
        StringBuffer sBuffer = new StringBuffer();
        if (tmpCorns.length == 6 || tmpCorns.length == 7) {
            //解析月
            if (!tmpCorns[4].equals("*")) {
                sBuffer.append(tmpCorns[4]).append("月");
            } else {
                sBuffer.append("每月");
            }
            //解析周
            if (!tmpCorns[5].equals("*") && !tmpCorns[5].equals("?")) {
                if (tmpCorns[5].contains("-")) {
                    // String[] weekSplit = tmpCorns[5].split("-");
                    ArrayList<String> weekList = new ArrayList<>();
                    weekList.add(tmpCorns[5].substring(0, 3));
                    weekList.add("-");
                    weekList.add(tmpCorns[5].substring(4, 7));
                    for (String week : weekList) {
                        switch (week) {
                            case "SUN":
                                sBuffer.append("星期天");
                                break;
                            case "MON":
                                sBuffer.append("星期一");
                                break;
                            case "TUE":
                                sBuffer.append("星期二");
                                break;
                            case "WED":
                                sBuffer.append("星期三");
                                break;
                            case "THU":
                                sBuffer.append("星期四");
                                break;
                            case "FRI":
                                sBuffer.append("星期五");
                                break;
                            case "SAT":
                                sBuffer.append("星期六");
                                break;
                            case "-":
                                sBuffer.append("至");
                                break;
                            default:
                                sBuffer.append(week);
                                break;
                        }
                    }
                } else {
                    char[] tmpArray = tmpCorns[5].toCharArray();
                    for (char tmp : tmpArray) {
                        switch (tmp) {
                            case '1':
                                sBuffer.append("星期天");
                                break;
                            case '2':
                                sBuffer.append("星期一");
                                break;
                            case '3':
                                sBuffer.append("星期二");
                                break;
                            case '4':
                                sBuffer.append("星期三");
                                break;
                            case '5':
                                sBuffer.append("星期四");
                                break;
                            case '6':
                                sBuffer.append("星期五");
                                break;
                            case '7':
                                sBuffer.append("星期六");
                                break;
                            case '-':
                                sBuffer.append("至");
                                break;
                            default:
                                sBuffer.append(tmp);
                                break;
                        }
                    }
                }
            }

        }

        //解析日
        if (!tmpCorns[3].equals("?")) {
            if (!tmpCorns[3].equals("*")) {
                sBuffer.append(tmpCorns[3]).append("日");
            } else {
                sBuffer.append("每日");
            }
        } else {
            sBuffer.append("每日");
        }

        //解析时
        if (!tmpCorns[2].equals("*")) {
            sBuffer.append(tmpCorns[2]).append("时");
        } else {
            sBuffer.append("每小时");
        }

        //解析分
        if (!tmpCorns[1].equals("*") && !tmpCorns[1].contains("/")) {
            sBuffer.append(tmpCorns[1]).append("分");
        }

        if (tmpCorns[1].contains("/")) {
            sBuffer.append("每隔" + tmpCorns[1].charAt(2));
        } */
/*else {
            sBuffer.append("每分钟");
        }*//*


        //解析秒
        if (!tmpCorns[0].equals("*") && !tmpCorns[0].equals("0")) {
            sBuffer.append(tmpCorns[0]).append("秒" + "执行一次");
        } else if (tmpCorns[0].equals("0")) {
            sBuffer.append("执行一次");
        } else {
            sBuffer.append("每秒" + "执行一次");
        }
        return sBuffer.toString();
    }


    //测试方法
    public static void main(String[] args) {
        String CRON_EXPRESSION = "0 0 0 0 0 ?";
        System.out.println(CRON_EXPRESSION);
        System.out.println(CronExpParser.translateToChinese(CRON_EXPRESSION));

    }

}
*/
