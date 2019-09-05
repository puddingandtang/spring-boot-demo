package com.tcl.demo.boot.dal.user.type;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum McValidConfigEnum {

    VALID_OPEN(1, "有效"),

    VALID_CLOSE(2, "无效");

    McValidConfigEnum(Integer type, String desc) {

        this.type = type;
        this.desc = desc;
    }

    private Integer type;

    private String desc;

    public static final McValidConfigEnum acquireByType(final Integer type) {

        if (null == type) {
            return null;
        }

        return Arrays.stream(McValidConfigEnum.values())
                .filter(t -> type.intValue() == t.getType())
                .findFirst().orElse(null);
    }
}
