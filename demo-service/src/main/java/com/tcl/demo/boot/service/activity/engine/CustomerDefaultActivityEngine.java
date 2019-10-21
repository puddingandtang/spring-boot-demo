package com.tcl.demo.boot.service.activity.engine;

import com.google.common.collect.Lists;
import com.tcl.demo.boot.service.activity.context.*;
import com.tcl.demo.boot.service.activity.engine.channel.BaseChannel;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.List;

@Slf4j
public class CustomerDefaultActivityEngine extends AbstractEngine {

    @Override
    protected ActivityParticipantContext assemblyBizActivityParticipantContext(ActivityRequest request) {

        ActivityParticipantContext context = super.assemblyBizActivityParticipantContext(request);

        context.setScene(request.getScene());

        return context;
    }

    @Override
    protected List<ActivityContext> acquire(ActivityParticipantContext participantContext) {

        // todo 获取活动信息 --> Mock

        List<ActivityContext> activityContexts = Lists.newArrayList();

        ActivityContext activity01 = new ActivityContext();
        activity01.setActivityId(1L);
        activityContexts.add(activity01);


        return activityContexts;
    }


    public CustomerDefaultActivityEngine(LinkedList<BaseChannel> outChannels) {

        super.assemblyChannel(outChannels);
    }

    public CustomerDefaultActivityEngine() {
    }

}
