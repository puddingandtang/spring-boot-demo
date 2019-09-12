package com.tcl.demo.boot.common.model.rule.coupon.type;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum CouponTerminalEnum {

    A(1, "A终端");

    CouponTerminalEnum(Integer type, String desc) {

        this.type = type;
        this.desc = desc;
    }

    private Integer type;

    private String desc;

    public static final CouponTerminalEnum acquireByType(final Integer type) {

        if (null == type) {
            return null;
        }

        return Arrays.stream(CouponTerminalEnum.values())
                .filter(t -> type.intValue() == t.getType())
                .findFirst().orElse(null);
    }
}
