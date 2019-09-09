package com.tcl.demo.boot.service.common.command;


import com.tcl.demo.boot.service.common.processor.RuleVerifyCondition;
import com.tcl.demo.boot.service.common.processor.RuleVerifyContent;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class BestRecommendCommand implements BaseCommand<RuleVerifyCondition, RuleVerifyContent> {


    @Override
    public void processCommand(RuleVerifyCondition condition, RuleVerifyContent content) {

    }

}
