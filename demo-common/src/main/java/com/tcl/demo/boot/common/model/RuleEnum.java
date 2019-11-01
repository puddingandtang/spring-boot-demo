package com.tcl.demo.boot.common.model;

import com.tcl.demo.boot.common.model.activity.rule.city.ActivityCityRule;
import com.tcl.demo.boot.common.model.activity.rule.time.ActivityTimeRule;
import com.tcl.demo.boot.common.model.activity.rule.reward.RewardMatchingRule;
import com.tcl.demo.boot.common.model.coupon.rule.CouponCityRule;
import com.tcl.demo.boot.common.model.coupon.rule.CouponTerminalRule;
import lombok.Getter;

import java.util.List;

@Getter
public enum RuleEnum {

    COUPON_CITY_RULE(1, "COUPON_CITY_RULE", "券城市规则", null, CouponCityRule.class, true),

    COUPON_TERMINAL_RULE(1, "COUPON_TERMINAL_RULE", "券终端规则", null, CouponTerminalRule.class, true),

    ACTIVITY_REWARD_MATCHING_RULE(2, "ACTIVITY_REWARD_MATCHING_RULE", "活动奖励匹配规则", List.class, RewardMatchingRule.class, true),

    ACTIVITY_TIME_RULE(2, "ACTIVITY_TIME_RULE", "活动时间规则", null, ActivityTimeRule.class, true),

    ACTIVITY_CITY_RULE(2, "ACTIVITY_CITY_RULE", "活动城市规则", null, ActivityCityRule.class, true);

    RuleEnum(Integer bizType,
             String ruleCode,
             String desc,
             Class packageClass,
             Class ruleClass,
             Boolean mustCheck) {
        this.bizType = bizType;
        this.ruleCode = ruleCode;
        this.desc = desc;
        this.packageClass = packageClass;
        this.ruleClass = ruleClass;
        this.mustCheck = mustCheck;
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

    /**
     * 是否必须校验
     * 因为有些规则是定制的，一些场景不一定存在
     */
    private boolean mustCheck;

}
