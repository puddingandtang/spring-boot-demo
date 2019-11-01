package com.tcl.demo.boot.service.test.activity;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.sun.tools.javac.util.Assert;
import com.tcl.demo.boot.common.model.BaseRule;
import com.tcl.demo.boot.common.model.RuleEnum;
import com.tcl.demo.boot.common.model.activity.rule.time.ActivityTimeRule;
import com.tcl.demo.boot.common.model.activity.rule.time.TimeFrame;
import com.tcl.demo.boot.common.tool.TimeTool;
import com.tcl.demo.boot.service.activity.context.ActivityContext;
import com.tcl.demo.boot.service.activity.context.ActivityParticipantContext;
import com.tcl.demo.boot.service.activity.context.ActivityResponse;
import com.tcl.demo.boot.service.activity.context.ActivityTraceContext;
import com.tcl.demo.boot.service.activity.engine.component.rule.ActivityCityRuleVerify;
import com.tcl.demo.boot.service.activity.engine.component.rule.ActivityTimeRuleVerify;
import com.tcl.demo.boot.service.activity.type.ActivityResultCodeEnum;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class ActivityTimeRuleVerifyMock {

    private ActivityTimeRule rule1;

    private ActivityTimeRule rule2;

    private ActivityTimeRule rule3;

    @Before
    public void init() {

        rule1 = new ActivityTimeRule();
        rule1.setBegin(TimeTool.toDateTime("2019-10-01 00:00:00", TimeTool.Y_M_D_H_M_S).getTime());
        rule1.setEnd(TimeTool.toDateTime("2019-12-30 00:00:00", TimeTool.Y_M_D_H_M_S).getTime());

        TimeFrame timeFrame1 = new TimeFrame();
        timeFrame1.setBegin("00:00:00");
        timeFrame1.setEnd("07:00:00");
        List<TimeFrame> times = Lists.newArrayList(timeFrame1);
        rule1.setTimes(times);

        HashSet<Integer> weeks = Sets.newHashSet(1, 2, 3, 4, 5);
        rule1.setWeeks(weeks);

        rule1.setDays(Sets.newHashSet(1, 2));


    }

    @Test
    public void caseTime1() {

        Long mockTime = TimeTool.toDateTime("2019-11-01 07:00:00", TimeTool.Y_M_D_H_M_S).getTime();

        ActivityTimeRuleVerify verify = new ActivityTimeRuleVerify();

        ActivityTraceContext traceContext = new ActivityTraceContext();

        /** 构建活动规则*/
        ActivityContext activityContext = new ActivityContext();
        Map<String, BaseRule> activityRules = Maps.newHashMap();
        activityRules.put(RuleEnum.ACTIVITY_TIME_RULE.getRuleCode(), rule1);
        activityContext.setActivityRules(activityRules);

        traceContext.setActivity(activityContext);

        /** 参与参数*/
        ActivityParticipantContext participantContext = new ActivityParticipantContext();
        participantContext.setTime(mockTime);

        traceContext.setParticipant(participantContext);

        ActivityResponse response = new ActivityResponse();

        verify.processComponent(traceContext, response);

        Assert.check(!traceContext.isInterrupt());
        Assert.check(response.getResultCode() == ActivityResultCodeEnum.SUC.getCode());

    }


}
