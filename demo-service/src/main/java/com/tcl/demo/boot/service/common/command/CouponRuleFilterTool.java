package com.tcl.demo.boot.service.common.command;

import com.tcl.demo.boot.service.common.processor.rule.RuleVerifyCondition;
import com.tcl.demo.boot.service.common.processor.rule.RuleVerifyContent;
import com.tcl.demo.boot.service.common.processor.rule.CouponCityRuleVerifyProcessor;
import com.tcl.demo.boot.service.common.processor.rule.CouponTerminalRuleVerifyProcessor;
import com.tcl.demo.boot.service.coupon.CouponBO;
import com.tcl.demo.boot.service.coupon.CouponFilterBO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component
public class CouponRuleFilterTool {

    @Resource
    CouponCityRuleVerifyProcessor couponCityRuleVerifyProcessor;

    @Resource
    CouponTerminalRuleVerifyProcessor couponTerminalRuleVerifyProcessor;

    /**
     * 区分校验
     *
     * @param condition
     * @param content
     * @return
     */
    public boolean baseRuleVerify(RuleVerifyCondition<CouponFilterBO> condition, RuleVerifyContent<CouponBO> content) {

        // 终端校验&&其他1&&其他2
        return couponTerminalRuleVerifyProcessor.process(condition, content);
    }

    /**
     * 通用校验
     *
     * @param condition
     * @param content
     * @return
     */
    public boolean customRuleVerify(RuleVerifyCondition<CouponFilterBO> condition, RuleVerifyContent<CouponBO> content) {

        // 城市校验&&其他校验1&&其他校验2
        return couponCityRuleVerifyProcessor.process(condition, content);
    }
}
