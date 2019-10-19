package com.xuecheng.framework.utils;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.lang.ObjectId;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * id生成器,使用时创建此类的对象即可
 *
 * @author：GJH
 * @createDate：2019/10/9
 * @company：洪荒宇宙加力蹲大学
 */
@Slf4j
public class IdGenerator {
    /**
     * 锁对象，可以为任意对象
     */
    private static Object lockObj = "lockerOrder";
    /**
     * 订单号生成计数器
     */
    private static long orderNumCount = 0L;
    /**
     * 每毫秒生成订单号数量最大值
     */
    private int maxPerMSECSize = 1000;
    /**
     * 初始化的id
     */
    private long workerId = 0;


    /**
     *
     */

    /**
     * 生成非重复订单号，理论上限1毫秒1000个，可扩展
     *
     * @param tname 测试用
     */
    public synchronized String generate(String tname) {
        try {
            // 最终生成的订单号
            String finOrderNum = "";
            synchronized (lockObj) {
                // 取系统当前时间作为订单号变量前半部分，精确到毫秒
                long nowLong = Long.parseLong(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));
                // 计数器到最大值归零，可扩展更大，目前1毫秒处理峰值1000个，1秒100万
                if (orderNumCount >= maxPerMSECSize) {
                    orderNumCount = 0L;
                }
                //组装订单号
                String countStr = maxPerMSECSize + orderNumCount + "";
                finOrderNum = nowLong + countStr.substring(1);
                orderNumCount++;
                //  System.out.println(finOrderNum + "--" + Thread.currentThread().getName() + "::" + tname);
                // Thread.sleep(1000);
                return finOrderNum;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    @PostConstruct
    void init() {
        try {
            workerId = NetUtil.ipv4ToLong(NetUtil.getLocalhostStr());
            log.info("当前机器 workerId: {}", workerId);
        } catch (Exception e) {
            log.warn("获取机器 ID 失败", e);
            workerId = NetUtil.getLocalhost().hashCode();
            log.info("当前机器 workerId: {}", workerId);
        }
    }

    /**
     * 获取一个批次号，形如 2019071015301361000101237
     * 数据库使用 char(25) 存储
     *
     * @param tenantId 租户ID，5 位
     * @param module   业务模块ID，2 位
     * @return 返回批次号
     */
    public synchronized String batchId(int tenantId, int module) {
        String prefix = DateTime.now().toString(DatePattern.PURE_DATETIME_MS_PATTERN);
        return prefix + tenantId + module + RandomUtil.randomNumbers(3);
    }

    @Deprecated
    public synchronized String getBatchId(int tenantId, int module) {
        return batchId(tenantId, module);
    }

    /**
     * 生成的是不带-的UUID(字符串)，类似于：b17f24ff026d40949c85a24f4f375d42
     *
     * @return String
     */
    public String simpleUUID() {
        return IdUtil.simpleUUID();
    }

    /**
     * 生成的UUID是带-的字符串，类似于：a5c8a5e8-df2b-4706-bea4-08d0939410e3
     *
     * @return
     */
    public String randomUUID() {
        return IdUtil.randomUUID();
    }

    private Snowflake snowflake = IdUtil.createSnowflake(workerId, 1);

    public synchronized long snowflakeId() {
        return snowflake.nextId();
    }

    public synchronized long snowflakeId(long workerId, long dataCenterId) {
        Snowflake snowflake = IdUtil.createSnowflake(workerId, dataCenterId);
        return snowflake.nextId();
    }

    /**
     * 生成类似：5b9e306a4df4f8c54a39fb0c
     * ObjectId 是 MongoDB 数据库的一种唯一 ID 生成策略，
     * 是 UUID version1 的变种，详细介绍可见：服务化框架－分布式 Unique ID 的生成方法一览。
     *
     * @return
     */
    public String objectId() {
        return ObjectId.next();
    }


    public static void main(String[] args) {
        // 测试多线程调用订单号生成工具
        /*try {
            for (int i = 0; i < 200; i++) {
                Thread t1 = new Thread(new Runnable() {
                    public void run() {
                        GenerateOrderNum generateOrderNum = new GenerateOrderNum();
                        generateOrderNum.generate("a");
                    }
                }, "at" + i);
                t1.start();

                Thread t2 = new Thread(new Runnable() {
                    public void run() {
                        GenerateOrderNum generateOrderNum = new GenerateOrderNum();
                        generateOrderNum.generate("b");
                    }
                }, "bt" + i);
                t2.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        System.out.println(System.currentTimeMillis());
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        IdGenerator idGenerator = new IdGenerator();
        SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);
        for (int i = 0; i < 5; i++) {
            executorService.execute(() -> {

                System.out.println("分布式 ID: {},:" + idGenerator.snowflakeId());
//                System.out.println("批次号 ID: {},:"+idGenerator.batchId(1001,100));
//                System.out.println("simpleUUID ID: {},:"+idGenerator.simpleUUID());
//                System.out.println("randomUUID ID: {},:"+idGenerator.randomUUID());
//                System.out.println("objectId ID: {},:"+idGenerator.objectId());
//                System.out.println("generate ID: {},:"+idGenerator.generate("tst"));
            });
        }
        executorService.shutdown();

    }

}
