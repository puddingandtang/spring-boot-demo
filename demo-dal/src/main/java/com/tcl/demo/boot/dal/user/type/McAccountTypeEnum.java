package com.tcl.demo.boot.dal.user.type;

import com.google.common.collect.Maps;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public enum McAccountTypeEnum {

    COUPON(1, "券账户"),

    INTEGRAL(2, "积分账户"),

    CARD(3, "卡账户");


    McAccountTypeEnum(Integer typeCode, String typeDesc) {
        this.typeCode = typeCode;
        this.typeDesc = typeDesc;
    }

    private Integer typeCode;

    private String typeDesc;


    public static Map<Integer, McAccountTypeEnum> initConfig = Maps.newHashMap();

    static {
        initConfig = Arrays.stream(McAccountTypeEnum.values()).collect(Collectors.toMap(t -> t.getTypeCode(), t -> t, (t1, t2) -> t1));
    }

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
