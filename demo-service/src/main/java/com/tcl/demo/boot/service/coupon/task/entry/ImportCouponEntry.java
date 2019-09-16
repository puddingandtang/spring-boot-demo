package com.tcl.demo.boot.service.coupon.task.entry;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ImportCouponEntry implements Serializable {

    private List<Long> userNo;

    private Integer userType;

    private List<CouponTemplateEntry> coupons;

}
