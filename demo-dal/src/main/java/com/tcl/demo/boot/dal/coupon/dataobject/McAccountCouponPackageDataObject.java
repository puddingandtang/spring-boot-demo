package com.tcl.demo.boot.dal.coupon.dataobject;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class McAccountCouponPackageDataObject implements Serializable {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 券编号
     */
    private String couponNo;

    /**
     * 账户编号
     */
    private String accountNo;

    /**
     * 账户类型
     */
    private Integer accountType;

    /**
     * 券状态
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
