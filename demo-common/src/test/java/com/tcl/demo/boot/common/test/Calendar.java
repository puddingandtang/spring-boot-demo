package com.tcl.demo.boot.common.test;

import com.alibaba.excel.util.DateUtils;
import com.google.common.collect.Maps;
import org.joda.time.DateTime;
import org.junit.Test;

import java.util.Date;
import java.util.Map;

import static com.alibaba.excel.util.DateUtils.DATE_FORMAT_19;

public class Calendar {


    public static final String SQL = "insert into `caocao`.`tsp_caocao_calendar` ( `_year`, `_month`, `_day`, `_week`, `_week_num`, `_date`, `_holiday`, `_holiday_name`) values ( '%s', '%s', '%s', '%s', '%s', '%s', '%s', %s);";

    @Test
    public void assemblyCalendar() {

        Map<String, String> holiday = Maps.newHashMap();
        holiday.put("2020-01-01", "元旦");

        holiday.put("2020-01-24", "春节");
        holiday.put("2020-01-25", "春节");
        holiday.put("2020-01-26", "春节");
        holiday.put("2020-01-27", "春节");
        holiday.put("2020-01-28", "春节");
        holiday.put("2020-01-29", "春节");
        holiday.put("2020-01-30", "春节");

        holiday.put("2020-04-04", "清明节");
        holiday.put("2020-04-05", "清明节");
        holiday.put("2020-04-06", "清明节");

        holiday.put("2020-05-01", "劳动节");
        holiday.put("2020-05-02", "劳动节");
        holiday.put("2020-05-03", "劳动节");
        holiday.put("2020-05-04", "劳动节");
        holiday.put("2020-05-05", "劳动节");

        holiday.put("2020-06-25", "端午节");
        holiday.put("2020-06-26", "端午节");
        holiday.put("2020-06-27", "端午节");

        holiday.put("2020-10-01", "国庆中秋节");
        holiday.put("2020-10-02", "国庆中秋节");
        holiday.put("2020-10-03", "国庆中秋节");
        holiday.put("2020-10-04", "国庆中秋节");
        holiday.put("2020-10-05", "国庆中秋节");
        holiday.put("2020-10-06", "国庆中秋节");
        holiday.put("2020-10-07", "国庆中秋节");
        holiday.put("2020-10-08", "国庆中秋节");

        try {

            Date nowTime = DateUtils.parseDate("2019-12-30 00:00:59", DATE_FORMAT_19);
            int addDays = 369;
            for (int idx = 0; idx < addDays; idx++) {

                DateTime curTime = new DateTime(nowTime.getTime());
                DateTime nextTime = curTime.plusDays(1);

                String YMD = DateUtils.format(nextTime.toDate(), "yyyy-MM-dd");
                // 年
                String year = String.valueOf(nextTime.getYear());
                // 月
                String month = String.valueOf(nextTime.getMonthOfYear());
                // 日
                String day = String.valueOf(nextTime.getDayOfMonth());
                // 周几
                String dayOfWeek = String.valueOf(nextTime.getDayOfWeek());
                // 第几周
                String weekOfYear = String.valueOf(nextTime.getWeekOfWeekyear());

                String holidayFlag = "1";
                String holidayName = "NULL";
                if (null != holiday.get(YMD)) {
                    holidayFlag = "0";
                    holidayName = "'" + holiday.get(YMD) + "'";
                }

                String initSQL = String.format(SQL, year, month, day, dayOfWeek, weekOfYear, YMD, holidayFlag, holidayName);

                System.out.println(initSQL);

                nowTime = nextTime.toDate();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
