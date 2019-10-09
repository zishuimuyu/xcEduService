package com.xuecheng.framework.utils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * Properties通用解析工具类
 *
 * @author：GJH
 * @createDate：2019/10/9
 * @company：洪荒宇宙加力蹲大学
 */
public class PropertiesUtil {
    /**
     * 根据key读取value
     *
     * @param filePath 文件路径
     * @param key      key值
     * @return value值
     */
    public static String readValue(String filePath, String key) {
        Properties props = new Properties();
        try {
            InputStream in = new BufferedInputStream(new FileInputStream(PropertiesUtil.class.getResource(
                    "/" + filePath).getPath().replace("%20", " ")));
            props.load(in);
            String value = props.getProperty(key);
            return value;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
