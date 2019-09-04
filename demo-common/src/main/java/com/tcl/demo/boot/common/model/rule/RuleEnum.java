package com.tcl.demo.boot.common.model.rule;

import com.tcl.demo.boot.common.model.rule.activity.reward.RewardMatchingRule;
import lombok.Getter;

import java.util.List;

@Getter
public enum RuleEnum {

    COUPON_CITY_RULE(1, "COUPON_CITY_RULE", "券城市规则", null, null),

    ACTIVITY_REWARD_MATCHING_RULE(2, "ACTIVITY_REWARD_MATCHING_RULE", "活动奖励匹配规则", List.class, RewardMatchingRule.class);


    RuleEnum(Integer bizType, String ruleCode, String desc, Class packageClass, Class ruleClass) {
        this.bizType = bizType;
        this.ruleCode = ruleCode;
        this.desc = desc;
        this.packageClass = packageClass;
        this.ruleClass = ruleClass;
    }

    /**
     * 1.coupon
     * 2.activity
     */
    private Integer bizType;

    /**
     * 规则编码
     */
    private String ruleCode;

    /**
     * 规则描述
     */
    private String desc;

    /**
     * 规则类被包装，例如List.class
     * 假设 packageClass 为 List.class , 那么这个封装的规则的业务List<ruleClass>
     */
    private Class packageClass;

    /**
     * 规则类
     */
    private Class ruleClass;

}
