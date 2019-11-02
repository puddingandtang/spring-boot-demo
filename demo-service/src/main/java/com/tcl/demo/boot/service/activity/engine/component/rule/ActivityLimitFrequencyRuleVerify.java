package com.tcl.demo.boot.service.activity.engine.component.rule;

import com.tcl.demo.boot.common.model.BaseRule;
import com.tcl.demo.boot.common.model.RuleEnum;
import com.tcl.demo.boot.common.model.activity.rule.limit.ActivityLimitFrequencyRule;
import com.tcl.demo.boot.common.model.activity.rule.limit.LimitAccountFrequency;
import com.tcl.demo.boot.common.model.activity.rule.limit.LimitActivityFrequency;
import com.tcl.demo.boot.common.model.activity.rule.limit.LimitEquipmentFrequency;
import com.tcl.demo.boot.service.activity.context.ActivityResponse;
import com.tcl.demo.boot.service.activity.context.ActivityTraceContext;
import com.tcl.demo.boot.service.activity.engine.component.BaseComponent;
import com.tcl.demo.boot.service.activity.type.ActivityResultCodeEnum;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 *
 */
@Slf4j
public class ActivityLimitFrequencyRuleVerify extends BaseComponent {

    private static RuleEnum ACTIVITY_LIMIT_FREQUENCY_RULE = RuleEnum.ACTIVITY_LIMIT_FREQUENCY_RULE;

    @Override
    protected void process(ActivityTraceContext traceContext, ActivityResponse response) {

        BaseRule baseRule = traceContext.acquireByRuleCode(ACTIVITY_LIMIT_FREQUENCY_RULE.getRuleCode());

        /** 规则不存在*/
        if (null == baseRule) {

            log.info("[{}]校验，规则不存在，校验忽略", this.getClass().getName());
            return;
        }

        ActivityLimitFrequencyRule limitFrequencyRule = (ActivityLimitFrequencyRule) baseRule;

        boolean verifyFail = false;

        verifyFail = verifyLimitActivityFrequency(traceContext, limitFrequencyRule.getActivity());
        if (verifyFail) {

            super.verifyFail(traceContext, response, ActivityResultCodeEnum.SUC_NOT_MATCH_ACTIVITY);
            return;
        }

        verifyFail = verifyLimitEquipmentFrequency(traceContext, limitFrequencyRule.getEquipment());
        if (verifyFail) {

            super.verifyFail(traceContext, response, ActivityResultCodeEnum.SUC_NOT_MATCH_ACTIVITY);
            return;
        }

        verifyFail = verifyLimitAccountFrequency(traceContext, limitFrequencyRule.getAccount());
        if (verifyFail) {

            super.verifyFail(traceContext, response, ActivityResultCodeEnum.SUC_NOT_MATCH_ACTIVITY);
            return;
        }
    }

    private boolean verifyLimitActivityFrequency(ActivityTraceContext traceContext, List<LimitActivityFrequency> activity) {

        // 查询
        // todo 每天

        // todo 每周

        // todo 每月


        return false;
    }

    private boolean verifyLimitEquipmentFrequency(ActivityTraceContext traceContext, List<LimitEquipmentFrequency> equipment) {

        return false;
    }

    private boolean verifyLimitAccountFrequency(ActivityTraceContext traceContext, List<LimitAccountFrequency> account) {


        return false;
    }

}
