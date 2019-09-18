package com.xuecheng.framework.utils;

import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 比较含有相同字段的两个对象之间数据字段值的变更
 *
 * @author gjh
 * @createDate：2018-05
 */
public class CompareObject {

    /**
     * 比较含有相同字段的两个对象之间数据字段值的变更
     *
     * @param <T>
     * @param obj1
     * @param Obj2
     * @return
     * @throws Exception
     */
    public static <T> Map<String, String> compareObject(T obj1, T Obj2) throws Exception {

        Map<String, String> result = new HashMap<String, String>();

        Field[] fs = obj1.getClass().getDeclaredFields();
        for (Field f : fs) {
            f.setAccessible(true);
            Object v1 = f.get(obj1);
            Object v2 = f.get(Obj2);
            result.put(f.getName(), String.valueOf(equals(v1, v2)));
        }
        return result;
    }

    /**
     * 获取含有相同字段的两个对象之间数据字段值的变更
     *
     * @param <T>
     * @param obj1
     * @param Obj2
     * @return
     * @throws Exception
     */
    public static <T> List<String> getCompareField(T obj1, T Obj2) throws Exception {

        List<String> result = new ArrayList<String>();

        Field[] fs = obj1.getClass().getDeclaredFields();
        for (Field f : fs) {
            f.setAccessible(true);
            Object v1 = f.get(obj1);
            Object v2 = f.get(Obj2);
            DecimalFormat df = new DecimalFormat("0.####"); // ##表示2位小数

            if (!equals(v1, v2)) {
                if (v1 instanceof Double && v2 instanceof Double) {

                    String d1 = df.format(Double.parseDouble(String.valueOf(v1)));
                    String d2 = df.format(Double.parseDouble(String.valueOf(v2)));
                    result.add(f.getName() + ":" + d1 + ":" + d2);
                } else {
                    result.add(f.getName() + ":" + v1 + ":" + v2);
                }
            }
        }
        return result;
    }

    /**
     * 比较对象相同字段
     *
     * @param obj1
     * @param obj2
     * @return
     */
    public static boolean equals(Object obj1, Object obj2) {

        if (obj1 == obj2) {
            return true;
        }
        if (obj1 == null || obj2 == null) {
            return false;
        }
        return obj1.equals(obj2);
    }


}
