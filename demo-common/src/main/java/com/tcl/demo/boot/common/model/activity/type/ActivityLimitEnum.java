package com.tcl.demo.boot.common.model.activity.type;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum ActivityLimitEnum {

    ALL_NOT_LIMIT(1, "无限制"),

    USABLE_LIMIT(2, "可用限制"),

    DISABLE_LIMIT(3, "不可用限制");

    ActivityLimitEnum(Integer type, String desc) {

        this.type = type;
        this.desc = desc;
    }

    private Integer type;

    private String desc;

    public static final ActivityLimitEnum acquireByType(final Integer type) {

        if (null == type) {
            return null;
        }

        return Arrays.stream(ActivityLimitEnum.values())
                .filter(t -> type.intValue() == t.getType())
                .findFirst().orElse(null);
    }
}