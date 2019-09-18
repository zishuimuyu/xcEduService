package com.xuecheng.framework.utils;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.*;

/**
 * 集合工具类
 * @author：GJH
 * @createDate：2019/9/5
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public final class CollectionUtil {

    /**
     * 判断Map是否为空
     *
     * @param map 数组
     * @return 为空返回true，不为空返回false
     */
    public static boolean isEmpty(Map map) {
        if (map == null || map.size() == 0) {
            return true;
        }
        return false;
    }

    /**
     * 判断Map是否为空
     *
     * @param map 数组
     * @return 为空返回true，不为空返回false
     */
    public static boolean isNotEmpty(Map map) {
        return !isEmpty(map);
    }

    /**
     * 判断集合是否为空
     *
     * @param collection 数组
     * @return 为空返回true，不为空返回false
     */
    public static boolean isEmpty(Collection collection) {
        if (collection == null || collection.size() == 0) {
            return true;
        }
        return false;
    }

    /**
     * 判断数组是否为空
     *
     * @param objs 数组
     * @return 为空返回true，不为空返回false
     */
    public static boolean isEmpty(Object[] objs) {
        if (objs == null || objs.length == 0) {
            return true;
        }
        return false;
    }

    /**
     * 判断集合是否为空
     *
     * @param collection 集合
     * @return 不为空返回true，为空返回false
     */
    public static boolean isNotEmpty(Collection collection) {
        return !isEmpty(collection);
    }

    /**
     * 判断数组是否为空
     *
     * @param objects 数组
     * @return 不为空返回true，为空返回false
     */
    public static boolean isNotEmpty(Object[] objects) {
        return !isEmpty(objects);
    }

    /**
     * 将数组转成集合
     *
     * @param objs 数组
     * @return lstArray 集合
     */

    public static List arrayToCollection(Object[] objs) {
        if (isEmpty(objs)) {
            return null;
        }
        return new ArrayList(Arrays.asList(objs));
    }

    /**
     * 将集合转成数组
     *
     * @param collection 集合
     * @return 数组
     */
    public static Object[] collectionToArray(Collection collection) {
        Object[] objs = null;
        if (isNotEmpty(collection)) {
            objs = new Object[collection.size()];
            collection.toArray(objs);
        }
        return objs;

    }

    /**
     * 求两个集合合集，只对集合中的元素是基本类型才有效
     * 合并
     *
     * @param c1 集合1
     * @param c2 集合2
     * @return 合集
     */
    public static List mergeCollections(Collection c1, Collection c2) {
        List collection = new ArrayList();
        collection.addAll(c1);
        collection.addAll(c2);
        return collection;
    }

    /**
     * 求两个集合交集，只对集合中的元素是基本类型才有效
     *
     * @param c1 集合1
     * @param c2 集合2
     * @return 交集
     */
    public static List getIntersectOfTwoCollection(Collection c1, Collection c2) {
        List c1_ = new ArrayList();
        List c1__ = new ArrayList();
        if (isNotEmpty(c1)) {
            c1_.addAll(c1);
            c1__.addAll(c1);
            c1__.removeAll(c2);
            c1_.removeAll(c1__);
        }
        return c1_;
    }

    /**
     * 求两个集合差集，只对集合中的元素是基本类型才有效
     *
     * @param c1 集合1
     * @param c2 集合2
     * @return 差集(c1 - c2)
     */
    public static List getDifferenceOfTwoCollections(Collection c1, Collection c2) {
        List c1_ = new ArrayList();
        if (isNotEmpty(c1)) {
            c1_.addAll(c1);
            c1_.removeAll(c2);
        }
        return c1_;

    }

    /**
     * 对集合进行排序(需指定是否升序)，只对集合中的元素是基本类型才有效
     *
     * @param collection 待排序的集合
     * @param isAsc      是否升序
     */
    public static void sort(Collection collection, boolean isAsc) {
        if (isNotEmpty(collection)) {
            Object[] objs = collectionToArray(collection);
            Arrays.sort(objs);
            collection.clear();
            if (isAsc) {
                collection.addAll(arrayToCollection(objs));
            }
            if (!isAsc) {
                for (int i = objs.length - 1; i >= 0; i--) {
                    collection.add(objs[i]);
                }
            }

        }

    }

    /**
     * 对数组进行排序，只对数组中的元素是基本类型才有效
     *
     * @param objs 数组
     */
    public static void sort(Object[] objs, boolean isAsc) {

        if (isNotEmpty(objs)) {
            Arrays.sort(objs);
            if (!isAsc) {
                ArrayList<Object> objects = new ArrayList<>();
                for (int i = objs.length - 1; i >= 0; i--) {
                    objects.add(objs[i]);
                }
                for (int i = 0; i < objects.size(); i++) {
                    objs[i] = objects.get(i);
                }
            }
        }

    }

    /**
     * 将Map对象转成 &KEY=VALUE的格式
     *
     * @param map Map
     * @return &KEY=VALUE结果的字符串
     */
    public static String toKeyValueString(Map map) {
        String keyvalueString = null;
        if (isNotEmpty(map)) {
            StringBuffer sbmap = new StringBuffer();
            List keys = new ArrayList(map.keySet());

            for (int i = 0; i < keys.size(); i++) {
                Object key = keys.get(i);
                Object value = map.get(key);
                if (i != 0) {
                    sbmap.append("&");
                }
                sbmap.append(key);
                sbmap.append("=");
                try {
                    sbmap.append(URLEncoder.encode(String.valueOf(value), "UTF-8"));
                } catch (Exception e) {
                    sbmap.append(value);
                }
            }
            keyvalueString = sbmap.toString();
        }
        return keyvalueString;
    }

    /**
     * 根据对象的属性名，获取对象中值
     *
     * @param form     对象
     * @param property 属性名
     * @return 属性的值
     * @throws Exception 异常信息
     */
    public static Object getObjectByGetMethod(Object form, String property) throws Exception {
        Object value = null;
        // 创建类对象
        Method method = form.getClass().getDeclaredMethod("get" + StringUtil.transFirstLetterToUpperCase(property));
        // 获取值
        value = method.invoke(form, new Object[]{});
        // 返回
        return value;
    }

    /**
     * 将两个数组进行拼接
     *
     * @param ob1 集合1
     * @param ob2 集合2
     * @return 合集
     */
    public static byte[] unionArray(byte[] ob1, byte[] ob2) {
        byte[] obj = new byte[ob1.length + ob2.length];
        System.arraycopy(ob1, 0, obj, 0, ob1.length);
        System.arraycopy(ob2, 0, obj, ob1.length, ob2.length);
        return obj;
    }
    /**
     * 对List对象按照某个成员变量进行排序
     *
     * @param list  List对象
     * @param sortField 排序的属性名称
     * @param sortMode 排序方式：ASC，DESC 任选其一
     */
    public static <T> void sortList(List<T> list, final String sortField, final String sortMode) {
        Collections.sort(list, new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                try {
                    Class clazz = o1.getClass();
                    // 获取成员变量
                    Field field = clazz.getDeclaredField(sortField);
                    // 设置成可访问状态
                    field.setAccessible(true);
                    // 转换成小写
                    String typeName = field.getType().getName().toLowerCase();
                    // 获取field的值
                    Object v1 = field.get(o1);
                    // 获取field的值
                    Object v2 = field.get(o2);

                    boolean ASC_order = (sortMode == null || "ASC".equalsIgnoreCase(sortMode));

                    // 判断字段数据类型，并比较大小
                    if (typeName.endsWith("string")) {
                        String value1 = v1.toString();
                        String value2 = v2.toString();
                        return ASC_order ? value1.compareTo(value2) : value2.compareTo(value1);
                    } else if (typeName.endsWith("short")) {
                        Short value1 = Short.parseShort(v1.toString());
                        Short value2 = Short.parseShort(v2.toString());
                        return ASC_order ? value1.compareTo(value2) : value2.compareTo(value1);
                    } else if (typeName.endsWith("byte")) {
                        Byte value1 = Byte.parseByte(v1.toString());
                        Byte value2 = Byte.parseByte(v2.toString());
                        return ASC_order ? value1.compareTo(value2) : value2.compareTo(value1);
                    } else if (typeName.endsWith("char")) {
                        Integer value1 = (int) (v1.toString().charAt(0));
                        Integer value2 = (int) (v2.toString().charAt(0));
                        return ASC_order ? value1.compareTo(value2) : value2.compareTo(value1);
                    } else if (typeName.endsWith("int") || typeName.endsWith("integer")) {
                        Integer value1 = Integer.parseInt(v1.toString());
                        Integer value2 = Integer.parseInt(v2.toString());
                        return ASC_order ? value1.compareTo(value2) : value2.compareTo(value1);
                    } else if (typeName.endsWith("long")) {
                        Long value1 = Long.parseLong(v1.toString());
                        Long value2 = Long.parseLong(v2.toString());
                        return ASC_order ? value1.compareTo(value2) : value2.compareTo(value1);
                    } else if (typeName.endsWith("float")) {
                        Float value1 = Float.parseFloat(v1.toString());
                        Float value2 = Float.parseFloat(v2.toString());
                        return ASC_order ? value1.compareTo(value2) : value2.compareTo(value1);
                    } else if (typeName.endsWith("double")) {
                        Double value1 = Double.parseDouble(v1.toString());
                        Double value2 = Double.parseDouble(v2.toString());
                        return ASC_order ? value1.compareTo(value2) : value2.compareTo(value1);
                    } else if (typeName.endsWith("boolean")) {
                        Boolean value1 = Boolean.parseBoolean(v1.toString());
                        Boolean value2 = Boolean.parseBoolean(v2.toString());
                        return ASC_order ? value1.compareTo(value2) : value2.compareTo(value1);
                    } else if (typeName.endsWith("date")) {
                        Date value1 = (Date) (v1);
                        Date value2 = (Date) (v2);
                        return ASC_order ? value1.compareTo(value2) : value2.compareTo(value1);
                    } else if (typeName.endsWith("timestamp")) {
                        Timestamp value1 = (Timestamp) (v1);
                        Timestamp value2 = (Timestamp) (v2);
                        return ASC_order ? value1.compareTo(value2) : value2.compareTo(value1);
                    } else {
                        // 调用对象的compareTo()方法比较大小
                        Method method = field.getType().getDeclaredMethod("compareTo", new Class[] { field.getType() });
                        // 设置可访问权限
                        method.setAccessible(true);
                        int result = (Integer) method.invoke(v1, new Object[] { v2 });
                        return ASC_order ? result : result * (-1);
                    }
                } catch (Exception e) {
                    String err = e.getLocalizedMessage();
                    System.out.println(err);
                    e.printStackTrace();
                }
                // 未知类型，无法比较大小
                return 0;
            }
        });
    }
    /**
     * 将数组转化为String
     *
     * @param
     * @return
     */
    public static String arrayToJsString(Object[] objus) {
        String result = "[";
        if (objus != null && objus.length > 0) {
            for (Object obj : objus) {
                result += obj + ",";

            }
            result = result.substring(0, result.length() - 1);
        }
        result += "]";
        return result;
    }
    /**
     * ArrayList数组转换成String数组.
     *
     * @param lstArray List
     * @return String[] strTempArray
     */
    @SuppressWarnings("rawtypes")
    public static String[] arrayListToStringArray(List lstArray) {
        String[] strTempArray = null;
        if (lstArray != null) {
            strTempArray = new String[lstArray.size()];

            for (int i = 0; i < lstArray.size(); i++) {
                strTempArray[i] = (String) lstArray.get(i);
            }
        }

        return strTempArray;
    }
}