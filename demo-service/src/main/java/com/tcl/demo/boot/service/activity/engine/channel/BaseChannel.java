package com.tcl.demo.boot.service.activity.engine.channel;

import com.tcl.demo.boot.common.base.ErrorCodes;
import com.tcl.demo.boot.common.exception.BizNormalException;
import com.tcl.demo.boot.common.tool.CollectionTool;
import com.tcl.demo.boot.service.activity.context.ActivityResponse;
import com.tcl.demo.boot.service.activity.context.ActivityTraceContext;
import com.tcl.demo.boot.service.activity.engine.component.BaseComponent;

import java.util.LinkedList;

public abstract class BaseChannel {

    private LinkedList<BaseComponent> components;

    public void processChannel(ActivityTraceContext traceContext, ActivityResponse response) {

        if (CollectionTool.isEmpty(this.components)) {

            throw new BizNormalException(ErrorCodes.ACTIVITY_CHANNEL_NOT_EXIST);
        }

        for (BaseComponent baseComponent : components) {

            baseComponent.processComponent(traceContext, response);
        }

    }

    /**
     * 初始化
     *
     * @param components
     */
    protected void initComponents(LinkedList<BaseComponent> components) {

        this.components = components;
    }


}
