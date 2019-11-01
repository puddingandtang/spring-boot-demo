package com.tcl.demo.boot.common.model.activity.rule.time;

public class ActivityTimeRuleValidity {

    /**
     * 校验时间
     *
     * @param rule
     * @return
     */
    public static boolean validityTimeRule(ActivityTimeRule rule) {

        if (null == rule) {

            return false;
        }

        Long begin = rule.getBegin();
        Long end = rule.getEnd();

        if (null == begin || null == end || begin >= end) {

            return false;
        }



        return true;


    }
}
