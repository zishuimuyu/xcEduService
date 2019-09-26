package com.xuecheng.framework.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;


/**
 * 网络链接工具类
 *
 * @author：GJH
 * @createDate：2019/9/26
 * @company：北京木联能软件股份有限公司
 */
public class NetUtil {
    public final static String LOCAL_IP = "127.0.0.1";

    /**
     * 使用Ip判断该Ip对应的计算机网络是否畅通
     *
     * @return Bool
     */
    public static Boolean isConnectedByIp(String ip) {
        boolean reachable = false;
        try {
            InetAddress address = InetAddress.getByName(ip);
            reachable = address.isReachable(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reachable;
    }

    /**
     * 通过Ping  ip/域名 的方式测试网路连接状态
     */
    public static Boolean ping(String ip) {
        //定义其返回的状态，默认为false，网络不正常
        boolean connect = false;

        /**
         * 用Runtime.getRuntime().exec()来调用系统外部的某个程序，
         * 他会生成一个新的进程去运行调用的程序。
         * 此方法返回一个java.lang.Process对象，
         * 该对象可以得到之前开启的进程的运行结果
         * 还可以操作进程的输入输出流
         */
        Runtime runtime = Runtime.getRuntime();
        Process process;
        try {
            process = runtime.exec("ping " + ip);
            InputStream is = process.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, "GBK");
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            StringBuffer sb = new StringBuffer();
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            //System.out.println("返回值为:" + sb);
            is.close();
            isr.close();
            br.close();
            if (null != sb && !sb.toString().equals("")) {
                if (sb.indexOf("TTL") > 0) {
                    //网络畅通
                    connect = true;
                } else {
                    //网络不通
                    connect = false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return connect;
    }

    /**
     * 判断本机网络是否畅通(外网)
     *
     * @return
     */
    public static Boolean isConnectedSelf() throws Exception {
        URL url = new URL("https://www.baidu.com");
        //打开到此 URL 的连接并返回一个用于从该连接读入的 InputStream
        InputStream in = url.openStream();
        in.close();
        return true;

    }

    /**
     * 判断本地端口是否可用(即是否被使用)
     *
     * @param port 端口
     * @return
     */
    public static boolean isUsableLocalPort(int port) {
        if (!isValidPort(port)) {
            // 给定的IP未在指定端口范围中
            return false;
        }
        try {
            new Socket(LOCAL_IP, port).close();
            // socket链接正常，说明这个端口正在使用
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    /**
     * 是否为有效的端口
     *
     * @param port 端口号
     * @return 是否有效
     */
    public static boolean isValidPort(int port) {
        // 有效端口是0～65535
        return port >= 0 && port <= 0xFFFF;
    }
}
