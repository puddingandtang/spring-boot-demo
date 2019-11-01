package com.tcl.demo.boot.service.activity.engine.component.rule;

import com.google.common.base.Optional;
import com.google.common.base.Strings;
import com.google.common.collect.Sets;
import com.tcl.demo.boot.common.model.BaseRule;
import com.tcl.demo.boot.common.model.RuleEnum;
import com.tcl.demo.boot.common.model.activity.rule.city.ActivityCityRule;
import com.tcl.demo.boot.common.model.activity.type.ActivityLimitEnum;
import com.tcl.demo.boot.service.activity.context.ActivityResponse;
import com.tcl.demo.boot.service.activity.context.ActivityTraceContext;
import com.tcl.demo.boot.service.activity.engine.component.BaseComponent;
import com.tcl.demo.boot.service.activity.type.ActivityResultCodeEnum;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;


/**
 * 活动城市配置校验
 * <p>
 * 请勿牵扯上下规则，城市规则是独立的，不能依赖其他规则
 */
@Slf4j
public class ActivityCityRuleVerify extends BaseComponent {

    private static RuleEnum ACTIVITY_CITY_RULE = RuleEnum.ACTIVITY_CITY_RULE;

    @Override
    protected void process(ActivityTraceContext traceContext, ActivityResponse response) {

        BaseRule baseRule = traceContext.acquireByRuleCode(ACTIVITY_CITY_RULE.getRuleCode());

        /** 规则不存在*/
        if (null == baseRule) {

            log.info("[{}]校验，规则不存在，校验忽略", this.getClass().getName());
            return;
        }

        ActivityCityRule cityRule = (ActivityCityRule) baseRule;

        ActivityLimitEnum limitEnum = ActivityLimitEnum.acquireByType(cityRule.getLimitType());
        if (null == limitEnum) {

            log.error("[{}]校验,limit类型非法，校验失败", this.getClass().getName());
            super.verifyFail(traceContext, response, ActivityResultCodeEnum.SUC_NOT_MATCH_ACTIVITY);
            return;
        }

        String outCityCode = traceContext.getParticipant().getCityCode();
        if (Strings.isNullOrEmpty(outCityCode)) {
            super.verifyFail(traceContext, response, ActivityResultCodeEnum.SUC_NOT_MATCH_ACTIVITY);
            return;
        }

        HashSet<String> configCityCodes = Optional.fromNullable(cityRule.getCityCodes()).or(Sets.newHashSet());

        switch (limitEnum) {
            case ALL_NOT_LIMIT: {

                log.info("[{}]校验，规则无限制，校验通过", this.getClass().getName());
                break;
            }
            case USABLE_LIMIT: {

                boolean verify = configCityCodes.contains(outCityCode);
                log.info("[{}]校验，规则可用限制，校验结果[{}]", this.getClass().getName(), verify);

                if (!verify) {
                    super.verifyFail(traceContext, response, ActivityResultCodeEnum.SUC_NOT_MATCH_ACTIVITY);
                }
                break;

            }
            case DISABLE_LIMIT: {

                boolean verify = !configCityCodes.contains(outCityCode);
                log.info("[{}]校验，规则不可用限制，校验结果[{}]", this.getClass().getName(), verify);

                if (!verify) {
                    super.verifyFail(traceContext, response, ActivityResultCodeEnum.SUC_NOT_MATCH_ACTIVITY);
                }
                break;
            }
            default: {

                super.verifyFail(traceContext, response, ActivityResultCodeEnum.SUC_NOT_MATCH_ACTIVITY);
                break;
            }
        }

    }
}
