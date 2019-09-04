package com.tcl.demo.boot.common.model.rule.coupon;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum CouponCityTypeEnum {

    ALL_CITY(1, "任意城市可用"),

    USABLE_CITY(2, "可用城市"),

    DISABLE_CITY(3, "不可用城市");


    CouponCityTypeEnum(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    private Integer type;

    private String desc;

    public static final CouponCityTypeEnum acquireByType(final Integer type) {

        if (null == type) {
            return null;
        }

        return Arrays.stream(CouponCityTypeEnum.values())
                .filter(t -> type.intValue() == t.getType())
                .findFirst().orElse(null);
    }
}
