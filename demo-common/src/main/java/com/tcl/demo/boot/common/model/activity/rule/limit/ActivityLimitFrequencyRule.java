package com.tcl.demo.boot.common.model.activity.rule.limit;

import com.tcl.demo.boot.common.model.BaseRule;
import com.tcl.demo.boot.common.model.RuleEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class ActivityLimitFrequencyRule extends BaseRule implements Serializable {

    /**
     * 活动
     */
    private List<LimitActivityFrequency> activity;

    /**
     * 设备
     */
    private List<LimitEquipmentFrequency> equipment;

    /**
     * 账户
     */
    private List<LimitAccountFrequency> account;

    public ActivityLimitFrequencyRule() {

        super(RuleEnum.ACTIVITY_LIMIT_FREQUENCY_RULE.getRuleCode(), "1.0", true);
    }
}
