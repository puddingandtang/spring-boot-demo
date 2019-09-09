package com.tcl.demo.boot.service.common.processor;

import com.tcl.demo.boot.common.model.rule.BaseRule;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
public class RuleVerifyContent extends BizProcessContent implements Serializable {

    private static final long serialVersionUID = 2963299351398009550L;

    /**
     * 整体规则
     */
    private Map<String, BaseRule> rules;

}
