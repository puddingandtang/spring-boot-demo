package com.tcl.demo.boot.service.activity.engine.component.reward;

import com.tcl.demo.boot.service.activity.context.ActivityResponse;
import com.tcl.demo.boot.service.activity.context.ActivityTraceContext;
import com.tcl.demo.boot.service.activity.engine.component.BaseComponent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MockRewardComponent extends BaseComponent {

    @Override
    protected void process(ActivityTraceContext traceContext, ActivityResponse response) {

        log.info("执行[{}]-> [{}]", this.getClass().getName(), traceContext);

    }
}
