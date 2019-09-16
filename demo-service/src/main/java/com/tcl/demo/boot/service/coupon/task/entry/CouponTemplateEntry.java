package com.tcl.demo.boot.service.coupon.task.entry;

import lombok.Data;

import java.io.Serializable;

@Data
public class CouponTemplateEntry implements Serializable {

    /**
     * 模版编号
     */
    private Long templateNo;

    /**
     * 模版种类
     */
    private Integer templateKind;

    /**
     * 数量
     */
    private Long num;
}
