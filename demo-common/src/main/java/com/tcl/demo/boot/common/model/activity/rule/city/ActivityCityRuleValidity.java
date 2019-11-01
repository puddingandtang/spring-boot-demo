package com.tcl.demo.boot.common.model.activity.rule.city;

import com.tcl.demo.boot.common.model.activity.type.ActivityLimitEnum;
import com.tcl.demo.boot.common.tool.CollectionTool;

public class ActivityCityRuleValidity {

    /**
     * 校验城市
     *
     * @param rule
     * @return
     */
    public static boolean validityCityRule(ActivityCityRule rule) {

        if (null == rule) {

            return false;
        }

        ActivityLimitEnum limitEnum = ActivityLimitEnum.acquireByType(rule.getLimitType());
        if (null == limitEnum) {

            return false;
        }

        switch (limitEnum) {

            case ALL_NOT_LIMIT: {

                // 校验
                boolean check = CollectionTool.isEmpty(rule.getCityCodes());

                return check;
            }
            case USABLE_LIMIT: {

                boolean check = !CollectionTool.isEmpty(rule.getCityCodes());

                return check;

            }
            case DISABLE_LIMIT: {

                return !CollectionTool.isEmpty(rule.getCityCodes());
            }
            default: {

                return false;
            }


        }

    }
}
