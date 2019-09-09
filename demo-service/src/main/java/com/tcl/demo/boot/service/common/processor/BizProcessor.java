package com.tcl.demo.boot.service.common.processor;

public interface BizProcessor<Condition extends BizProcessCondition, Content extends BizProcessContent> {

    void process(Condition condition, Content content);
}
