package com.tcl.demo.boot.service.activity.engine;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import com.tcl.demo.boot.common.base.ErrorCodes;
import com.tcl.demo.boot.common.exception.BizNormalException;
import com.tcl.demo.boot.common.exception.BizRuntimeException;
import com.tcl.demo.boot.common.tool.CollectionTool;
import com.tcl.demo.boot.service.activity.context.*;
import com.tcl.demo.boot.service.activity.engine.channel.BaseChannel;
import com.tcl.demo.boot.service.activity.type.ActivityResultCodeEnum;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.List;

/**
 * BaseChannel 可以更具化，分为input、rule、reward、output等通用channel。
 * 同理 BaseComponent 也一样。
 * <p>
 * 现在所有的都是基于BaseChannel，BaseComponent，这样功能区分不够分明，后期具现化。
 */
@Slf4j
public abstract class AbstractEngine {

    private LinkedList<BaseChannel> channels;

    /**
     * 执行引擎
     *
     * @param request
     * @return
     */
    public List<ActivityResponse> processEngine(ActivityRequest request) {

        /** 组合活动参与上下文 - 业务*/
        ActivityParticipantContext participantContext =
                Optional.fromNullable(assemblyBizActivityParticipantContext(request)).or(new ActivityParticipantContext());

        /** 组合活动参与上下文 - 基础*/
        participantContext = assemblyCommonActivityParticipantContext(request, participantContext);

        /** 流程组 - 初始化*/
        if (CollectionTool.isEmpty(channels)) {

            throw new BizNormalException(ErrorCodes.ACTIVITY_CHANNEL_NOT_EXIST);
        }

        /** 活动信息 - 获取*/
        List<ActivityContext> activityContexts = acquire(participantContext);

        log.info("获取活动信息,入参[{}],出参[{}]", participantContext, activityContexts);

        if (CollectionTool.isEmpty(activityContexts)) {
            return Lists.newArrayList();
        }

        /** 活动执行*/
        List<ActivityResponse> responses = processActivities(activityContexts, participantContext);

        return responses;
    }

    /**
     * 组装公共活动参与上下文
     *
     * @param request
     * @param context
     * @return
     */
    private ActivityParticipantContext assemblyCommonActivityParticipantContext(ActivityRequest request, ActivityParticipantContext context) {

        context.setAccountNo(request.getAccountNo());
        context.setAccountType(request.getAccountType());
        context.setActivityNo(request.getActivityNo());
        context.setCityCode(request.getCityCode());
        context.setRequestTime(System.currentTimeMillis());
        context.setTime(request.getTime());
        context.setTerminal(request.getTerminal());

        return context;

    }

    /**
     * 组装业务活动参与上下文
     * <p>
     * 子类可选择实现
     *
     * @param request
     * @return
     */
    protected ActivityParticipantContext assemblyBizActivityParticipantContext(ActivityRequest request) {

        ActivityParticipantContext context = new ActivityParticipantContext();
        return context;
    }

    /**
     * 组装流程
     *
     * @return
     */
    protected void assemblyChannel(LinkedList<BaseChannel> outChannels) {

        this.channels = outChannels;
    }

    /**
     * 获取活动信息
     * <p>
     * 子类自行实现
     *
     * @param participantContext
     * @return
     */
    protected List<ActivityContext> acquire(ActivityParticipantContext participantContext) {

        throw new BizNormalException(ErrorCodes.OPT_NOT_SUPPORT);
    }

    /**
     * 多活动执行
     *
     * @param activityContexts
     * @param participantContext
     * @return
     */
    protected List<ActivityResponse> processActivities(List<ActivityContext> activityContexts, ActivityParticipantContext participantContext) {

        if (CollectionTool.isEmpty(activityContexts) || null == participantContext) {

            return Lists.newArrayList();
        }

        List<ActivityTraceContext> traceContexts = Lists.newArrayList();

        for (ActivityContext context : activityContexts) {


            ActivityTraceContext cur = new ActivityTraceContext();
            cur.setActivity(context);

            /* 通过序列化，反序列化去引用，随便写写*/
            ActivityParticipantContext curParticipant = JSON.parseObject(JSON.toJSONString(participantContext), ActivityParticipantContext.class);
            cur.setParticipant(curParticipant);

            cur.setInterrupt(false);

            traceContexts.add(cur);
        }


        List<ActivityResponse> responses = Lists.newArrayList();

        for (ActivityTraceContext traceContext : traceContexts) {

            /** 调用com.tcl.demo.boot.service.activity.engine.AbstractEngine#processActivity(com.tcl.demo.boot.service.activity.context.ActivityTraceContext)*/
            ActivityResponse response = processActivity(traceContext);
            responses.add(response);
        }

        return responses;
    }

    /**
     * 单活动执行
     * <p>
     * 不建议重写该方法。
     * 可重写{@link AbstractEngine#processActivities}，该类是同步校验每个活动，可以异步化，同步等待
     *
     * @param traceContext
     * @return
     */
    protected ActivityResponse processActivity(ActivityTraceContext traceContext) {

        ActivityContext activityContext = traceContext.getActivity();
        ActivityParticipantContext participantContext = traceContext.getParticipant();

        ActivityResponse response = new ActivityResponse();

        boolean interrupt = false;

        for (BaseChannel channel : this.channels) {

            if (interrupt) {

                log.info("执行流程[{}],执行中断", channel.getClass().getName());
                break;
            }

            try {

                /** 执行channel流程*/
                channel.processChannel(traceContext, response);

                /** 设置中断标识*/
                interrupt = traceContext.isInterrupt();

            } catch (Exception e) {

                /** 异常则中断*/
                interrupt = true;

                /** 异常判断*/
                if (e instanceof BizRuntimeException) {

                    response.setResultCode(ActivityResultCodeEnum.SUC_NOT_MATCH_ACTIVITY.getCode());

                    BizRuntimeException bizException = (BizRuntimeException) e;

                    switch (bizException.getLv()) {
                        case ERROR: {

                            log.error("执行流程[" + channel.getClass().getName() + "]," + bizException.getMsg() + ",异常：", e);
                            break;
                        }
                        default: {

                            log.warn("执行流程[" + channel.getClass().getName() + "]," + bizException.getMsg() + ",异常：", e);
                        }
                    }

                } else {

                    response.setResultCode(ActivityResultCodeEnum.FAIL.getCode());
                    log.error("执行流程[" + channel.getClass().getName() + "]异常：", e);
                }
            }
        }

        log.info("执行活动,活动编号[{}],参与账户编号[{}],活动结果[{}]", activityContext.getActivityId(), participantContext.getAccountNo(), response);
        return response;


    }
}
