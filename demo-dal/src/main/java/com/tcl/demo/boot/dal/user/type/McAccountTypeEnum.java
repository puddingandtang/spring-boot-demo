package com.tcl.demo.boot.dal.user.type;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum McAccountTypeEnum {

    COUPON(1, "券账户"),

    INTEGRAL(2, "积分账户");


    McAccountTypeEnum(Integer typeCode, String typeDesc) {
        this.typeCode = typeCode;
        this.typeDesc = typeDesc;
    }

    private Integer typeCode;

    private String typeDesc;

    public static final McAccountTypeEnum acquireByTypeCode(final Integer typeCode) {

        if (null == typeCode) {

            return null;
        }

        return Arrays.stream(McAccountTypeEnum.values())
                .filter(t -> t.getTypeCode().intValue() == typeCode.intValue())
                .findFirst()
                .orElse(null);
    }
}
