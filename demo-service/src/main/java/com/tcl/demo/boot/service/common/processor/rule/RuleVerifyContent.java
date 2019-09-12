package com.tcl.demo.boot.service.common.processor.rule;

import com.tcl.demo.boot.service.common.processor.BizProcessContent;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class RuleVerifyContent<T> extends BizProcessContent implements Serializable {

    private static final long serialVersionUID = 2963299351398009550L;


    /**
     * 校验数据
     */
    private T verifyContent;

}
