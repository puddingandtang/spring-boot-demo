package com.tcl.demo.boot.service.activity.engine.channel.rule;

import com.tcl.demo.boot.service.activity.engine.channel.BaseChannel;
import com.tcl.demo.boot.service.activity.engine.component.BaseComponent;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;

@Slf4j
public class MockRuleChannel extends BaseChannel {

    public MockRuleChannel(LinkedList<BaseComponent> components) {

        super.initComponents(components);
    }
}
