package com.tcl.demo.boot.service.test.activity;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.sun.tools.javac.util.Assert;
import com.tcl.demo.boot.common.model.BaseRule;
import com.tcl.demo.boot.common.model.RuleEnum;
import com.tcl.demo.boot.common.model.activity.rule.city.ActivityCityRule;
import com.tcl.demo.boot.common.model.activity.type.ActivityLimitEnum;
import com.tcl.demo.boot.service.activity.context.ActivityContext;
import com.tcl.demo.boot.service.activity.context.ActivityParticipantContext;
import com.tcl.demo.boot.service.activity.context.ActivityResponse;
import com.tcl.demo.boot.service.activity.context.ActivityTraceContext;
import com.tcl.demo.boot.service.activity.engine.component.rule.ActivityCityRuleVerify;
import com.tcl.demo.boot.service.activity.type.ActivityResultCodeEnum;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

public class ActivityCityRuleVerifyMock {

    private ActivityCityRule rule1;

    private ActivityCityRule rule2;

    private ActivityCityRule rule3;

    @Before
    public void init() {

        rule1 = new ActivityCityRule();
        rule1.setLimitType(ActivityLimitEnum.ALL_NOT_LIMIT.getType());

        rule2 = new ActivityCityRule();
        rule2.setLimitType(ActivityLimitEnum.USABLE_LIMIT.getType());
        rule2.setCityCodes(Sets.newHashSet("0571", "0572"));

        rule3 = new ActivityCityRule();
        rule3.setLimitType(ActivityLimitEnum.DISABLE_LIMIT.getType());
        rule3.setCityCodes(Sets.newHashSet("0571", "0572"));
    }

    @Test
    public void verifyNotLimit() {

        ActivityCityRuleVerify verify = new ActivityCityRuleVerify();

        ActivityTraceContext traceContext = new ActivityTraceContext();

        /** 构建活动规则*/
        ActivityContext activityContext = new ActivityContext();
        Map<String, BaseRule> activityRules = Maps.newHashMap();
        activityRules.put(RuleEnum.ACTIVITY_CITY_RULE.getRuleCode(), rule1);
        activityContext.setActivityRules(activityRules);

        traceContext.setActivity(activityContext);

        /** 参与参数*/
        ActivityParticipantContext participantContext = new ActivityParticipantContext();
        participantContext.setCityCode("0571");

        traceContext.setParticipant(participantContext);

        ActivityResponse response = new ActivityResponse();

        verify.processComponent(traceContext, response);

        Assert.check(!traceContext.isInterrupt());
        Assert.check(response.getResultCode() == ActivityResultCodeEnum.SUC.getCode());
    }

    @Test
    public void verifyUsableLimit() {

        ActivityCityRuleVerify verify = new ActivityCityRuleVerify();

        ActivityTraceContext traceContext = new ActivityTraceContext();

        /** 构建活动规则*/
        ActivityContext activityContext = new ActivityContext();
        Map<String, BaseRule> activityRules = Maps.newHashMap();
        activityRules.put(RuleEnum.ACTIVITY_CITY_RULE.getRuleCode(), rule2);
        activityContext.setActivityRules(activityRules);

        traceContext.setActivity(activityContext);

        /** 参与参数*/
        ActivityParticipantContext participantContext = new ActivityParticipantContext();
        participantContext.setCityCode("0571");

        traceContext.setParticipant(participantContext);

        ActivityResponse response = new ActivityResponse();

        verify.processComponent(traceContext, response);

        Assert.check(!traceContext.isInterrupt());
        Assert.check(response.getResultCode() == ActivityResultCodeEnum.SUC.getCode());

    }

    @Test
    public void verifyDisableLimit() {


        ActivityCityRuleVerify verify = new ActivityCityRuleVerify();

        ActivityTraceContext traceContext = new ActivityTraceContext();

        /** 构建活动规则*/
        ActivityContext activityContext = new ActivityContext();
        Map<String, BaseRule> activityRules = Maps.newHashMap();
        activityRules.put(RuleEnum.ACTIVITY_CITY_RULE.getRuleCode(), rule3);
        activityContext.setActivityRules(activityRules);

        traceContext.setActivity(activityContext);

        /** 参与参数*/
        ActivityParticipantContext participantContext = new ActivityParticipantContext();
        participantContext.setCityCode("0571");

        traceContext.setParticipant(participantContext);

        ActivityResponse response = new ActivityResponse();

        verify.processComponent(traceContext, response);

        Assert.check(traceContext.isInterrupt());
        Assert.check(response.getResultCode() != ActivityResultCodeEnum.SUC.getCode());
        Assert.check(response.getResultCode() == ActivityResultCodeEnum.SUC_NOT_MATCH_ACTIVITY.getCode());
    }

}
