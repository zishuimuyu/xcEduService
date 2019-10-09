package com.xuecheng.framework.utils;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * MD5工具类
 * @author：GJH
 * @createDate：2019/10/9
 * @company：洪荒宇宙加力蹲大学
 */
public class MD5Util {

    /**
     * The M d5.
     */
    static MessageDigest MD5 = null;

    /**
     * The Constant HEX_DIGITS.
     */
    private static final char HEX_DIGITS[] = {'0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    static {
        try {
            MD5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ne) {
            ne.printStackTrace();
        }
    }

    /**
     * 获取文件md5值.
     *
     * @param file the file
     * @return md5串
     * @throws IOException
     */
    public static String getFileMD5String(File file) throws IOException {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            byte[] buffer = new byte[8192];
            int length;
            while ((length = fileInputStream.read(buffer)) != -1) {
                MD5.update(buffer, 0, length);
            }

            return new String(encodeHex(MD5.digest()));
        } catch (FileNotFoundException e) {
            throw e;
        } catch (IOException e) {
            throw e;
        } finally {
            try {
                if (fileInputStream != null)
                    fileInputStream.close();
            } catch (IOException e) {
                throw e;
            }
        }
    }

    /**
     * 获取文件md5值.
     *
     * @param data the byte[] data
     * @return md5串
     * @throws IOException
     */
    public static String getFileMD5String(byte[] data) throws IOException {
        MD5.update(data);
        return new String(encodeHex(MD5.digest()));
    }

    /**
     * Encode hex.
     *
     * @param bytes the bytes
     * @return the string
     */
    public static String encodeHex(byte bytes[]) {
        return bytesToHex(bytes, 0, bytes.length);

    }

    /**
     * Bytes to hex.
     *
     * @param bytes the bytes
     * @param start the start
     * @param end   the end
     * @return the string
     */
    public static String bytesToHex(byte bytes[], int start, int end) {
        StringBuilder sb = new StringBuilder();
        for (int i = start; i < start + end; i++) {
            sb.append(byteToHex(bytes[i]));
        }
        return sb.toString();

    }

    /**
     * Byte to hex.
     *
     * @param bt the bt
     * @return the string
     */
    public static String byteToHex(byte bt) {
        return HEX_DIGITS[(bt & 0xf0) >> 4] + "" + HEX_DIGITS[bt & 0xf];

    }

    /**
     * 获取md5值.
     *
     * @param str the string
     * @return md5串
     * @throws IOException
     */
    public static String getStringMD5(String str) {
        StringBuilder sb = new StringBuilder();
        try {
            byte[] data = str.getBytes("utf-8");
            MessageDigest MD5 = MessageDigest.getInstance("MD5");
            MD5.update(data);
            data = MD5.digest();
            for (int i = 0; i < data.length; i++) {
                sb.append(HEX_DIGITS[(data[i] & 0xf0) >> 4] + "" + HEX_DIGITS[data[i] & 0xf]);
            }
        } catch (Exception e) {
        }
        return sb.toString();
    }

    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {

        long beginTime = System.currentTimeMillis();
        File fileZIP = new File("D:\\BaiduNetdiskDownload\\test1.avi");

        String md5 = "";
        try {
            md5 = getFileMD5String(fileZIP);
        } catch (IOException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("MD5:" + md5 + "\n time:" + ((endTime - beginTime)) + "ms");

        System.out.println(getStringMD5("111111"));
    }

    /**
     * 加密字符串 验证
     *
     * @param password 待加密的密码
     * @return
     */
    public static String encrypt(String password) {
        String passwordMd5 = null;
        try {
            // 拿到一个MD5转换器（如果想要SHA1参数换成”SHA1”）
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            // 输入的字符串转换成字节数组
            byte[] passwordByteArray = password.getBytes("utf-8");
            // passwordByteArray是输入字符串转换得到的字节数组
            // 转换并返回结果，也是字节数组，包含16个元素
            byte[] bytes = md5.digest(passwordByteArray);
            // 字符数组转换成字符串返回
            passwordMd5 = toHex(bytes);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return passwordMd5;
    }

    /**
     * 密码验证的方法
     *
     * @param notVerifiedPassWord 前台传来待验证的密码
     * @param encryptedPassword   数据库存储的加密后的密码
     * @return
     */
    public static boolean verification(String notVerifiedPassWord, String encryptedPassword) {
        if (encrypt(notVerifiedPassWord).equals(encryptedPassword)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 下面这个函数用于将字节数组换成成16进制的字符串
     *
     * @param bytes
     * @return
     */
    private static String toHex(byte[] bytes) {
        // 首先初始化一个字符数组，用来存放每个16进制字符
        final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();
        // new一个字符数组，这个就是用来组成结果字符串的（解释一下：一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方））
        StringBuilder ret = new StringBuilder(bytes.length * 2);
        // 遍历字节数组，通过位运算（位运算效率高），转换成字符放到字符数组中去
        for (int i = 0; i < bytes.length; i++) {
            ret.append(HEX_DIGITS[(bytes[i] >> 4) & 0x0f]);
            ret.append(HEX_DIGITS[bytes[i] & 0x0f]);
        }
        // 字符数组组合成字符串返回
        return ret.toString();
    }
}
