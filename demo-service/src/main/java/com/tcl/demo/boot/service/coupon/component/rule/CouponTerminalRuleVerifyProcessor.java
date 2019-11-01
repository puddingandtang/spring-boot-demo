package com.tcl.demo.boot.service.coupon.component.rule;

import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import com.tcl.demo.boot.common.base.ErrorCodes;
import com.tcl.demo.boot.common.model.BaseRule;
import com.tcl.demo.boot.common.model.RuleEnum;
import com.tcl.demo.boot.common.model.coupon.rule.CouponTerminalRule;
import com.tcl.demo.boot.common.model.coupon.type.CouponLimitTypeEnum;
import com.tcl.demo.boot.common.model.coupon.type.CouponTerminalEnum;
import com.tcl.demo.boot.service.common.processor.BizProcessor;
import com.tcl.demo.boot.service.common.processor.rule.RuleVerifyCondition;
import com.tcl.demo.boot.service.common.processor.rule.RuleVerifyContent;
import com.tcl.demo.boot.service.coupon.CouponBO;
import com.tcl.demo.boot.service.coupon.CouponFilterBO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class CouponTerminalRuleVerifyProcessor extends BizProcessor<RuleVerifyCondition<CouponFilterBO>, RuleVerifyContent<CouponBO>> {

    @Override
    protected boolean verifyProcess(RuleVerifyCondition<CouponFilterBO> condition, RuleVerifyContent<CouponBO> content) {

        CouponBO couponBO = content.getVerifyContent();
        Map<String, BaseRule> ruleMap = couponBO.getCouponRules();

        CouponTerminalRule rule = (CouponTerminalRule) ruleMap.get(RuleEnum.COUPON_TERMINAL_RULE.getRuleCode());

        if (null == rule) {

            log.info("券[{}]-券规则校验-终端规则-规则不存在，视为不校验通过", couponBO.getCouponNo());
            content.setCode(super.buildErrorMsg(ErrorCodes.RULE_NOT_EXIST_ERROR, "券终端规则"));
            return false;
        }

        CouponLimitTypeEnum limitType = CouponLimitTypeEnum.acquireByType(rule.getType());
        if (null == limitType) {

            log.info("券[{}]-券规则校验-终端规则-规则限制类型存在，视为不校验通过", couponBO.getCouponNo());
            content.setCode(super.buildErrorMsg(ErrorCodes.RULE_NOT_MATCH_ERROR, "券终端规则"));
            return false;
        }

        // 外部传入终端
        Integer outTerminal = condition.getTerminal();

        switch (limitType) {
            case ALL_NOT_LIMIT: {

                log.info("券[{}]-券规则校验-终端规则-规则不限制，视为校验通过", couponBO.getCouponNo());
                return true;
            }
            case USABLE_LIMIT: {

                List<Integer> expectConfig = Optional.fromNullable(rule.getTerminals()).or(Lists.newArrayList());
                Boolean expectMatch = null != CouponTerminalEnum.acquireByType(outTerminal) &&
                        expectConfig.contains(outTerminal);

                log.info("券[{}]-券规则校验-终端规则-可用限制，校验结果[{}]", couponBO.getCouponNo(), expectMatch);

                if (!expectMatch) {
                    content.setCode(super.buildErrorMsg(ErrorCodes.RULE_NOT_MATCH_ERROR, "券终端规则"));
                    return false;
                }

                return true;

            }
            case DISABLE_LIMIT: {

                List<Integer> notExpectConfig = Optional.fromNullable(rule.getTerminals()).or(Lists.newArrayList());
                Boolean notExpectMatch = null == CouponTerminalEnum.acquireByType(outTerminal) ||
                        notExpectConfig.contains(outTerminal);

                if (notExpectMatch) {

                    content.setCode(super.buildErrorMsg(ErrorCodes.RULE_NOT_MATCH_ERROR, "券终端规则"));
                    return false;
                }

                return true;
            }
            default: {

                content.setCode(super.buildErrorMsg(ErrorCodes.RULE_NOT_MATCH_ERROR, "券终端规则"));
                return false;
            }
        }

    }
}
