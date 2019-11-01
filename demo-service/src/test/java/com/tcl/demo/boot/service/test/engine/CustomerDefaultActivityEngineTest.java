package com.tcl.demo.boot.service.test.engine;

import com.google.common.collect.Lists;
import com.tcl.demo.boot.service.activity.context.ActivityRequest;
import com.tcl.demo.boot.service.activity.context.ActivityResponse;
import com.tcl.demo.boot.service.activity.engine.CustomerDefaultActivityEngine;
import com.tcl.demo.boot.service.activity.engine.channel.BaseChannel;
import com.tcl.demo.boot.service.activity.engine.channel.input.MockInputChannel;
import com.tcl.demo.boot.service.activity.engine.channel.output.MockOutputChannel;
import com.tcl.demo.boot.service.activity.engine.channel.reward.MockRewardChannel;
import com.tcl.demo.boot.service.activity.engine.channel.rule.MockRuleChannel;
import com.tcl.demo.boot.service.activity.engine.component.BaseComponent;
import com.tcl.demo.boot.service.activity.engine.component.input.MockInputComponent;
import com.tcl.demo.boot.service.activity.engine.component.rule.MockCityRuleComponent;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

public class CustomerDefaultActivityEngineTest {


    @Test
    public void process() {


        /** 构建Channel*/
        LinkedList<BaseChannel> outChannels = Lists.newLinkedList();


        /** input*/
        LinkedList<BaseComponent> inputs = Lists.newLinkedList();
        inputs.add(new MockInputComponent());
        MockInputChannel mockInputChannel = new MockInputChannel(inputs);

        /** rule*/
        LinkedList<BaseComponent> rules = Lists.newLinkedList();
        rules.add(new MockCityRuleComponent());
        MockRuleChannel mockRuleChannel = new MockRuleChannel(rules);

        outChannels.add(mockInputChannel);
        outChannels.add(mockRuleChannel);


        // outChannels.add(new MockRewardChannel());
        // outChannels.add(new MockOutputChannel());

        CustomerDefaultActivityEngine engine = new CustomerDefaultActivityEngine(outChannels);

        ActivityRequest request = new ActivityRequest();

        List<ActivityResponse> responses = engine.processEngine(request);


    }
}