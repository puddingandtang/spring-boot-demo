package com.tcl.demo.boot.common.model.activity.rule.time;

import lombok.Data;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.Serializable;
import java.util.Date;

@Data
public class TimeFrame implements Serializable {

    /**
     * 开始
     * HH:mm:ss
     */
    private String begin;

    /**
     * 结束
     * HH:mm:ss
     */
    private String end;

    /**
     * 时间格式化
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
     * 一天的多少秒
     *
     * @param time
     * @return
     */
    public static int secondOfDay(Date time) {

        return new DateTime(time).secondOfDay().get();
    }
}
