package com.tcl.demo.boot.common.tool;

import com.google.common.base.Strings;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.MessageFormat;
import java.util.Date;

public class TimeTool {

    public static final String Y_M_D_H_M_S = "yyyy-MM-dd HH:mm:ss";

    public static final String Y_M_D_H_M_S_V2 = "yyyy/MM/dd HH:mm:ss";

    public static final String Y_M_D = "yyyy-MM-dd";

    public static final String YMD = "yyyyMMdd";

    public static final String H_M = "HH:mm";

    public static final String H_M_S = "HH:mm:ss";

    /**
     * 指定日期模式格式化
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String format(Date date, String pattern) {
        if (null == date) {
            return "";
        }
        DateTime dateTime = new DateTime(date);
        return dateTime.toString(pattern);
    }

    public static String formatY_M_D_Special(Date date) {

        String time = format(date, Y_M_D);

        if (Strings.isNullOrEmpty(time)) {

            return null;
        }

        String[] timeSplit = time.split("-");
        if (null == timeSplit || timeSplit.length != 3) {

            return null;
        }

        return MessageFormat.format("{0}年{1}月{2}日", timeSplit[0], timeSplit[1], timeSplit[2]);
    }


    /**
     * 将 字符串类型时间 转为 时间类型时间
     *
     * @param dealTime
     * @param rule
     * @return
     */
    public static Date toDateTime(String dealTime, String rule) {

        DateTimeFormatter format = DateTimeFormat.forPattern(rule);
        DateTime dateTime = DateTime.parse(dealTime, format);

        return dateTime.toDate();
    }

    /**
     * 将 时间类型时间  转为  字符串类型时间
     *
     * @param dealTime
     * @param rule
     * @return
     */
    public static String toDateString(Date dealTime, String rule) {

        DateTimeFormatter format = DateTimeFormat.forPattern(rule);
        DateTime dateTime = new DateTime(dealTime);
        return dateTime.toString(format);
    }

    /**
     * 默认输出格式 :yyyy-MM-dd HH:mm:ss
     *
     * @param dealTime
     * @return
     */
    public static String toDateDefaultString(Date dealTime) {

        DateTimeFormatter format = DateTimeFormat.forPattern(Y_M_D_H_M_S);
        DateTime dateTime = new DateTime(dealTime);
        return dateTime.toString(format);
    }

    /**
     * 获取HH:mm 在一天是多少秒
     *
     * @param hourMinute 小时:分钟
     * @return
     */
    public static int acquireSecondOfDay(String hourMinute, String rule) {

        DateTimeFormatter format = DateTimeFormat.forPattern(rule);

        return DateTime.parse(hourMinute, format).secondOfDay().get();
    }

    /**
     * 获取当掐死你时分 在一天是多少秒
     *
     * @param currentTime
     * @return
     */
    public static int acquireSecondOfDay(Date currentTime) {

        DateTime dateTime = new DateTime(currentTime);

        return dateTime.secondOfDay().get();
    }

    /**
     * 获取时间所在星期
     *
     * @param currentTime
     * @return
     */
    public static int acquireDayOfWeek(Date currentTime) {

        DateTime dateTime = new DateTime(currentTime);

        return dateTime.getDayOfWeek();
    }

    /**
     * 获取时间所在月份的几号
     *
     * @param currentTime
     * @return
     */
    public static int acquireDayOfMonth(Date currentTime) {

        DateTime dateTime = new DateTime(currentTime);

        return dateTime.getDayOfMonth();
    }

    /**
     * 获取时间所在月份
     *
     * @param currentTime
     * @return
     */
    public static int acquireYear(Date currentTime) {

        DateTime dateTime = new DateTime(currentTime);

        return dateTime.getYear();
    }


    /**
     * 将时间戳转换字符串的时间格式
     *
     * @param timestamp 时间戳
     * @param rule      时间格式
     * @return
     */
    public static String toDateString(Long timestamp, String rule) {

        DateTimeFormatter format = DateTimeFormat.forPattern(rule);

        DateTime dateTime = new DateTime(timestamp);


        return dateTime.toString(format);
    }

    /**
     * 将时间戳 转 时间类型
     *
     * @param timestamp
     * @return
     */
    public static Date toDate(Long timestamp) {

        DateTime dateTime = new DateTime(timestamp);
        return dateTime.toDate();
    }


    /**
     * 在指定的时间累加天
     *
     * @param pswBeainTime 需要累加的时间
     * @param pswLife      累加的天数
     * @return
     */
    public static Date addDays(Date pswBeainTime, Integer pswLife) {
        DateTime dateTime = new DateTime(pswBeainTime);
        return dateTime.plusDays(pswLife.intValue()).toDate();
    }

    /**
     * 设置23:59:59
     *
     * @param currentTime
     * @return
     */
    public static Date setHourTimeTo235959(Date currentTime) {

        DateTime dateTime = new DateTime(currentTime);

        return new DateTime(dateTime.getYear(), dateTime.getMonthOfYear(), dateTime.getDayOfMonth(), 23, 59, 59).toDate();
    }

    /**
     * 设置时间的的秒数
     *
     * @param currentTime
     * @param x
     * @return
     */
    public static Date setSecondTimeToX(Date currentTime, Integer x) {

        DateTime dateTime = new DateTime(currentTime);

        return new DateTime(
                dateTime.getYear(),
                dateTime.getMonthOfYear(),
                dateTime.getDayOfMonth(),
                dateTime.getHourOfDay(),
                dateTime.getMinuteOfHour(), x).toDate();
    }


    /**
     * 设置00:00:00
     *
     * @param currentTime
     * @return
     */
    public static Date setHourTimeTo000000(Date currentTime) {

        DateTime dateTime = new DateTime(currentTime);

        return new DateTime(dateTime.getYear(), dateTime.getMonthOfYear(), dateTime.getDayOfMonth(), 0, 0, 0).toDate();
    }

    /**
     * 添加分钟
     *
     * @param time
     * @param addMins
     * @return
     */
    public static Date addMins(Date time, Integer addMins) {

        DateTime dateTime = new DateTime(time);

        return dateTime.plusMinutes(addMins.intValue()).toDate();

    }

    /**
     * 添加秒
     *
     * @param time
     * @param addSeconds
     * @return
     */
    public static Date addSeconds(Date time, Integer addSeconds) {

        DateTime dateTime = new DateTime(time);

        return dateTime.plusSeconds(addSeconds.intValue()).toDate();

    }
}
