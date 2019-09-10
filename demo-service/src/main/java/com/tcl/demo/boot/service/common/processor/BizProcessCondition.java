package com.tcl.demo.boot.service.common.processor;

import lombok.Data;

import java.io.Serializable;

@Data
public class BizProcessCondition implements Serializable {

    /**
     * 账户编号
     */
    private String accountNo;

    /**
     * 账户类型
     */
    private String accountType;

    /**
     * 城市
     */
    private String cityCode;



}
