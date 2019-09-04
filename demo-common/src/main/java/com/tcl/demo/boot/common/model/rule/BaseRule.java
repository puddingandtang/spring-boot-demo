package com.tcl.demo.boot.common.model.rule;

import lombok.Data;

import java.io.Serializable;

/**
 * 基础规则
 * 所有规则需要继承该类
 */
@Data
public class BaseRule implements Serializable {

    private static final long serialVersionUID = 8831368483399044697L;

    /**
     * 规则编码
     */
    private String ruleCode;

    /**
     * 规则版本
     */
    private String version;

    /**
     * 规则有效标志
     */
    private Boolean valid;

    public BaseRule(String ruleCode, String version, Boolean valid) {
        this.ruleCode = ruleCode;
        this.version = version;
        this.valid = valid;
    }
}
