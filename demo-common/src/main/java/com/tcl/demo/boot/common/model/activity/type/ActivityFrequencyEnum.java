package com.tcl.demo.boot.common.model.activity.type;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum ActivityFrequencyEnum {

    DAY(1, "每天"),

    WEEK(2, "每周"),

    MONTH(3, "每月");

    ActivityFrequencyEnum(Integer type, String desc) {

        this.type = type;
        this.desc = desc;
    }

    private Integer type;

    private String desc;

    public static final ActivityFrequencyEnum acquireByType(final Integer type) {

        if (null == type) {
            return null;
        }

        return Arrays.stream(ActivityFrequencyEnum.values())
                .filter(t -> type.intValue() == t.getType())
                .findFirst().orElse(null);
    }
}
