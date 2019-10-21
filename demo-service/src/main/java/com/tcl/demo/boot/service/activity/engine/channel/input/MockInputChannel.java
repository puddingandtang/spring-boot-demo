package com.tcl.demo.boot.service.activity.engine.channel.input;

import com.tcl.demo.boot.service.activity.engine.channel.BaseChannel;
import com.tcl.demo.boot.service.activity.engine.component.BaseComponent;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;

@Slf4j
public class MockInputChannel extends BaseChannel {


    public MockInputChannel(LinkedList<BaseComponent> components) {
        super.initComponents(components);
    }
}
