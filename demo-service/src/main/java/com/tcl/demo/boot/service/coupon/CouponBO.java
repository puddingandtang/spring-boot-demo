package com.tcl.demo.boot.service.coupon;

import com.tcl.demo.boot.common.model.rule.BaseRule;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
public class CouponBO implements Serializable {

    private static final long serialVersionUID = 8132697657614286693L;

    /**
     * 账户编号
     */
    private String accountNo;

    /**
     * 账户类型
     */
    private String accountType;

    /**
     * 券编号
     */
    private String couponNo;

    /**
     * 模版编号
     */
    private Long templateNo;

    /**
     * 模版种类
     */
    private Integer kind;

    /**
     *
     */
    private Integer type;

    /**
     * 规则
     */
    private Map<String, BaseRule> couponRules;

    /**
     * 是否可用
     */
    private boolean disable = false;

    /**
     * 不可用原因
     * disable == true
     */
    private String disableRes;
}
