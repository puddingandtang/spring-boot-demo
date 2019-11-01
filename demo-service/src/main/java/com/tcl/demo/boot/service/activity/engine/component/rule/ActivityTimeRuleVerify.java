package com.tcl.demo.boot.service.activity.engine.component.rule;

import com.google.common.base.Optional;
import com.google.common.collect.Range;
import com.tcl.demo.boot.common.model.BaseRule;
import com.tcl.demo.boot.common.model.RuleEnum;
import com.tcl.demo.boot.common.model.activity.rule.time.ActivityTimeRule;
import com.tcl.demo.boot.common.model.activity.rule.time.TimeFrame;
import com.tcl.demo.boot.common.tool.CollectionTool;
import com.tcl.demo.boot.common.tool.TimeTool;
import com.tcl.demo.boot.service.activity.context.ActivityParticipantContext;
import com.tcl.demo.boot.service.activity.context.ActivityResponse;
import com.tcl.demo.boot.service.activity.context.ActivityTraceContext;
import com.tcl.demo.boot.service.activity.engine.component.BaseComponent;
import com.tcl.demo.boot.service.activity.type.ActivityResultCodeEnum;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class ActivityTimeRuleVerify extends BaseComponent {

    private static RuleEnum ACTIVITY_TIME_RULE = RuleEnum.ACTIVITY_TIME_RULE;

    @Override
    protected void process(ActivityTraceContext traceContext, ActivityResponse response) {

        BaseRule baseRule = traceContext.acquireByRuleCode(ACTIVITY_TIME_RULE.getRuleCode());
        if (null == baseRule) {

            log.info("[{}]校验，规则不存在，校验忽略", this.getClass().getName());
            return;
        }

        ActivityParticipantContext participantContext = traceContext.getParticipant();
        Long time = participantContext.getTime();
        Long reqTime = Optional.fromNullable(participantContext.getRequestTime()).or(System.currentTimeMillis());

        ActivityTimeRule timeRule = (ActivityTimeRule) baseRule;

        /** 活动有效期*/
        Long begin = timeRule.getBegin();
        Long end = timeRule.getEnd();
        Range<Long> rangeTime = Range.closed(begin, end);
        boolean verify = rangeTime.contains(time);
        log.info("[{}]校验，规则活动有效期，校验结果[{}]", this.getClass().getName(), verify);
        if (!verify) {

            super.verifyFail(traceContext, response, ActivityResultCodeEnum.SUC_NOT_MATCH_ACTIVITY);
            return;
        }

        /** 时间*/
        List<TimeFrame> timeFrames = timeRule.getTimes();
        if (!CollectionTool.isEmpty(timeFrames)) {

            int outTimeFrame = TimeTool.acquireSecondOfDay(new Date(time));

            List<Range<Integer>> timeFrameRange = timeFrames.stream().map(t -> {
                String curBegin = t.getBegin();
                String curEnd = t.getEnd();

                int first = TimeTool.acquireSecondOfDay(curBegin, TimeTool.H_M_S);
                int last = TimeTool.acquireSecondOfDay(curEnd, TimeTool.H_M_S);

                return Range.closed(first, last);

            }).collect(Collectors.toList());

            verify = timeFrameRange.stream().filter(t-> t.contains(outTimeFrame) ).findFirst().isPresent();

            log.info("[{}]校验，规则活动时段，校验结果[{}]", this.getClass().getName(), verify);

            if (!verify) {

                super.verifyFail(traceContext, response, ActivityResultCodeEnum.SUC_NOT_MATCH_ACTIVITY);
                return;
            }

        }

        /** 周*/
        HashSet<Integer> weeks = timeRule.getWeeks();
        if (!CollectionTool.isEmpty(weeks)) {

            int curWeek = TimeTool.acquireDayOfWeek(new Date(time));
            verify = weeks.contains(curWeek);
            log.info("[{}]校验，规则活动周配置，校验结果[{}]", this.getClass().getName(), verify);
            if (!verify) {

                super.verifyFail(traceContext, response, ActivityResultCodeEnum.SUC_NOT_MATCH_ACTIVITY);
                return;
            }
        }

        /** 月*/
        HashSet<Integer> days = timeRule.getDays();
        if (!CollectionTool.isEmpty(days)) {

            int curMonth = TimeTool.acquireDayOfMonth(new Date(time));

            verify = days.contains(curMonth);
            log.info("[{}]校验，规则活动月配置，校验结果[{}]", this.getClass().getName(), verify);

            if (!verify) {

                super.verifyFail(traceContext, response, ActivityResultCodeEnum.SUC_NOT_MATCH_ACTIVITY);
                return;
            }
        }

    }

}
