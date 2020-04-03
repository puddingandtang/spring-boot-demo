package com.tcl.demo.boot.service.activity.component;

import com.google.common.collect.Lists;
import com.tcl.demo.boot.service.activity.context.ActivityContext;
import com.tcl.demo.boot.service.activity.context.ActivityParticipantContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class ActivityInfoComponent {

    /**
     * 获取活动信息
     *
     * @param participantContext
     * @return
     */
    public List<ActivityContext> acquire(ActivityParticipantContext participantContext) {

        return Lists.newArrayList();
    }
}
