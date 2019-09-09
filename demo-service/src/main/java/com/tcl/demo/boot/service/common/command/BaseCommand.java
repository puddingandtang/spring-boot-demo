package com.tcl.demo.boot.service.common.command;

import com.tcl.demo.boot.service.common.processor.BizProcessCondition;
import com.tcl.demo.boot.service.common.processor.BizProcessContent;
import com.tcl.demo.boot.service.common.processor.BizProcessor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

public interface BaseCommand<Condition extends BizProcessCondition, Content extends BizProcessContent> {

    public void processCommand(Condition condition, Content content);
}
