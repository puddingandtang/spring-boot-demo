package com.tcl.demo.boot.service.common.processor;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum BizProcessKey {

    BEST_COUPON_FILTER_AVAILABLE(1, "最优优惠券推荐-过程数据-可用券数据", "BEST_COUPON_FILTER_AVAILABLE");

    BizProcessKey(Integer type, String desc, String key) {

        this.type = type;
        this.desc = desc;
        this.key = key;
    }

    private Integer type;

    private String key;

    private String desc;

    public static final BizProcessKey acquireByType(final Integer type) {

        if (null == type) {
            return null;
        }

        return Arrays.stream(BizProcessKey.values())
                .filter(t -> type.intValue() == t.getType())
                .findFirst().orElse(null);
    }
}
