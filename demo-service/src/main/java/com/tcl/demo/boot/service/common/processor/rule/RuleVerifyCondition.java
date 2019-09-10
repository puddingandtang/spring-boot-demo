package com.tcl.demo.boot.service.common.processor.rule;

import com.tcl.demo.boot.service.common.processor.BizProcessCondition;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
public class RuleVerifyCondition<T> extends BizProcessCondition implements Serializable {

    /**
     * 校验条件
     */
    private T verifyCondition;
}
