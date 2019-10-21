package com.tcl.demo.boot.service.activity.type;

import lombok.Getter;

@Getter
public enum ActivityResultCodeEnum {

    SUC(200, "活动请求成功且有活动"),

    SUC_NOT_MATCH_ACTIVITY(201, "活动请求成功但活动规则不符合"),

    FAIL(300, "活动请求失败");


    ActivityResultCodeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private Integer code;

    private String desc;
}
