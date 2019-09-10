package com.tcl.demo.boot.service.common.processor;

import com.google.common.collect.Maps;
import com.tcl.demo.boot.common.base.ErrorCode;
import com.tcl.demo.boot.common.base.ErrorCodes;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
public class BizProcessContent implements Serializable {

    /**
     * 中断标志，默认为false
     */
    private boolean interrupt = false;

    /**
     * 校验结果
     * 成功通过 ：{@link ErrorCodes#SUCCESS}
     * 失败：规则错误码,{@link ErrorCodes#RULE_*}
     */
    private ErrorCode code;

    /**
     * 外部追溯编号
     */
    private String outTraceNo;

    /**
     * 过程数据集合
     */
    private Map<String, Object> processData = Maps.newHashMap();

}
