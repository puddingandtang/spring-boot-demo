package com.tcl.demo.boot.service.common.processor.rule;

import com.tcl.demo.boot.service.common.processor.BizProcessor;
import com.tcl.demo.boot.service.coupon.CouponBO;
import com.tcl.demo.boot.service.coupon.CouponFilterBO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CouponTerminalRuleVerifyProcessor extends BizProcessor<RuleVerifyCondition<CouponFilterBO>, RuleVerifyContent<CouponBO>> {


    @Override
    protected boolean verifyProcess(RuleVerifyCondition<CouponFilterBO> condition, RuleVerifyContent<CouponBO> content) {

        return false;
    }
}
