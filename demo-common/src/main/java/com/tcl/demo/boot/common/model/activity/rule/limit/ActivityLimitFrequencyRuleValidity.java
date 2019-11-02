package com.tcl.demo.boot.common.model.activity.rule.limit;

import com.tcl.demo.boot.common.model.activity.type.ActivityFrequencyEnum;
import com.tcl.demo.boot.common.tool.CollectionTool;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ActivityLimitFrequencyRuleValidity {

    /**
     * 校验
     *
     * @param rule
     * @return
     */
    public static boolean validityLimitFrequnecyRule(ActivityLimitFrequencyRule rule) {

        if (null == rule) {

            return false;
        }

        List<LimitActivityFrequency> activityFrequencies = rule.getActivity();
        if (!CollectionTool.isEmpty(activityFrequencies)) {

            // 校验
            Map<Integer, LimitActivityFrequency> map = activityFrequencies.stream()
                    .filter(t -> null != ActivityFrequencyEnum.acquireByType(t.getType()) && null != t.getFrequency())
                    .collect(Collectors.toMap(t -> t.getType(), t -> t, (t1, t2) -> t1));
            if (map.size() != activityFrequencies.size()) {

                return false;
            }

        }

        List<LimitAccountFrequency> accountFrequencies = rule.getAccount();
        if (!CollectionTool.isEmpty(accountFrequencies)) {

            Map<Integer, LimitAccountFrequency> map = accountFrequencies.stream()
                    .filter(t -> null != ActivityFrequencyEnum.acquireByType(t.getType()) && null != t.getFrequency())
                    .collect(Collectors.toMap(t -> t.getType(), t -> t, (t1, t2) -> t1));

            if (map.size() != accountFrequencies.size()) {

                return false;
            }
        }

        List<LimitEquipmentFrequency> equipmentFrequencies = rule.getEquipment();
        if (!CollectionTool.isEmpty(equipmentFrequencies)) {

            Map<Integer, LimitEquipmentFrequency> map = equipmentFrequencies.stream()
                    .filter(t -> null != ActivityFrequencyEnum.acquireByType(t.getType()) && null != t.getFrequency())
                    .collect(Collectors.toMap(t -> t.getType(), t -> t, (t1, t2) -> t1));

            if (map.size() != equipmentFrequencies.size()) {

                return false;
            }
        }

        return true;

    }
}
