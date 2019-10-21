package com.tcl.demo.boot.service.activity.engine.component;

import com.tcl.demo.boot.common.exception.BizRuntimeException;
import com.tcl.demo.boot.service.activity.context.ActivityContext;
import com.tcl.demo.boot.service.activity.context.ActivityResponse;
import com.tcl.demo.boot.service.activity.context.ActivityTraceContext;
import com.tcl.demo.boot.service.activity.engine.component.BaseComponent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component
public class ActivityInfoInjectComponent extends BaseComponent {
    @Override
    protected void process(ActivityTraceContext traceContext, ActivityResponse response) {

        // todo 查询活动数据

        ActivityContext activityContext = ActivityContext.builder().build();
        traceContext.setActivity(activityContext);
    }
}
