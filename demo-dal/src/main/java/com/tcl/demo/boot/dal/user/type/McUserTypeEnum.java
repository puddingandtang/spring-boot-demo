package com.tcl.demo.boot.dal.user.type;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum McUserTypeEnum {

    CUSTOMER(1, "顾客");

    McUserTypeEnum(Integer type, String typeDes) {
        this.type = type;
        this.typeDes = typeDes;
    }

    private Integer type;

    private String typeDes;

    /**
     * 获取状态枚举
     *
     * @param type
     * @return
     */
    public static final McUserTypeEnum acquireByType(final Integer type) {

        if (null == type) {
            return null;
        }

        return Arrays.stream(McUserTypeEnum.values())
                .filter(t -> t.getType().intValue() == type.intValue())
                .findFirst()
                .orElse(null);
    }
}
