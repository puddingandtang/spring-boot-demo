package com.tcl.demo.boot.common.tool;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.SecureRandom;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Twitter_Snowflake<br>
 * SnowFlake的结构如下(每部分用-分开):<br>
 * 0 - 0000000000 0000000000 0000000000 0000000000 0 - 00000 - 0000000000 - 0000000 <br>
 * 1位标识，由于long基本类型在Java中是带符号的，最高位是符号位，正数是0，负数是1，所以id一般是正数，最高位是0<br>
 * 41位时间截(毫秒级)，注意，41位时间截不是存储当前时间的时间截，而是存储时间截的差值（当前时间截 - 开始时间截)
 * 得到的值），这里的的开始时间截，一般是我们的id生成器开始使用的时间，由我们程序来指定的（如下下面程序IdWorker类的startTime属性）。41位的时间截，可以使用69年，年T = (1L << 41) / (1000L * 60 * 60 * 24 * 365) = 69<br>
 * 10位的数据机器位，可以部署在1024个节点，包括5位datacenterId和5位workerId<br>
 * 12位序列，毫秒内的计数，12位的计数顺序号支持每个节点每毫秒(同一机器，同一时间截)产生4096个ID序号<br>
 * 加起来刚好64位，为一个Long型。<br>
 * SnowFlake的优点是，整体上按照时间自增排序，并且整个分布式系统内不会产生ID碰撞(由数据中心ID和机器ID作区分)，并且效率较高，经测试，SnowFlake每秒能够产生26万ID左右。
 *
 * @description copy from caocao-util:0.9 修改部分为开始时间戳，
 */
public class SnowflakeIdTool {

    // ==============================Fields===========================================
    /**
     * 开始时间截 (2017/3/10 10:6:50)
     */
    private static final long twepoch = 1489111610226L;

    /**
     * 机器标识位数
     */
    private static final long workerIdBits = 10L;

    /**
     * 数据中心标识位数
     */
    private static final long datacenterIdBits = 0L;

    /**
     * 机器ID最大值
     */
    private static final long maxWorkerId = -1L ^ (-1L << workerIdBits);

    /**
     * 数据中心ID最大值
     */
    private static final long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);

    /**
     * 毫秒内自增位
     */
    private static final long sequenceBits = 12L;

    /**
     * 机器ID偏左移12位
     */
    private static final long workerIdShift = sequenceBits;

    /**
     * 数据中心ID左移17位
     */
    private static final long datacenterIdShift = sequenceBits + workerIdBits;

    /**
     * 时间毫秒左移22位
     */
    private static final long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;

    private static final long sequenceMask = -1L ^ (-1L << sequenceBits);

    private static long workerId;

    private static long datacenterId;

    private static long sequence = 0L;

    private static long lastTimestamp = -1L;

    static Pattern pattern = Pattern.compile("\\d+");

    static {
        String hostname;
        try {
            hostname = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            hostname = maxWorkerId + "";
        }

        Matcher matcher = pattern.matcher(hostname);
        while (matcher.find()) {
            workerId = Long.parseLong(matcher.group(0));
        }
        if (workerId > maxWorkerId || workerId < 0) {
            workerId = new SecureRandom().nextInt((int) maxWorkerId + 1);
            //			throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
        }
    }

    public static synchronized long nextId() {
        long timestamp = timeGen();
        //时间错误
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }
        if (lastTimestamp == timestamp) {
            //当前毫秒内，则+1
            sequence = (sequence + 1) & sequenceMask;
            //当前毫秒内计数满了，则等待下一秒
            if (sequence == 0L) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0L;
        }

        lastTimestamp = timestamp;
        //ID偏移组合生成最终的ID，并返回ID
        return ((timestamp - twepoch) << timestampLeftShift) | (datacenterId << datacenterIdShift) | (workerId << workerIdShift) | sequence;
    }

    /**
     * 等待下一个毫秒的到来
     *
     * @param lastTimestamp
     * @return
     */
    protected static long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    protected static long timeGen() {
        return System.currentTimeMillis();
    }

    public Date getDateById(long id) {
        String s = Long.toBinaryString(id);
        s = String.format("%1$0" + (64 - s.length()) + "d", 0) + s;
        return new Date(Long.parseLong(s.substring(1, 42), 2) + twepoch);
    }

    public long getDatacenterId(long id) {
        String s = Long.toBinaryString(id);
        s = String.format("%1$0" + (64 - s.length()) + "d", 0) + s;
        return Long.parseLong(s.substring(42, 47), 2);
    }

    public long getWorkerIdById(long id) {
        String s = Long.toBinaryString(id);
        s = String.format("%1$0" + (64 - s.length()) + "d", 0) + s;
        return Long.parseLong(s.substring(47, 52), 2);
    }

    public long getSequenceById(long id) {
        String s = Long.toBinaryString(id);
        s = String.format("%1$0" + (64 - s.length()) + "d", 0) + s;
        return Long.parseLong(s.substring(52), 2);
    }


}