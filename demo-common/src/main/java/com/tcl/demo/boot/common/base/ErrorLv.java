package com.tcl.demo.boot.common.base;

import lombok.Getter;

/**
 * 错误等级
 */
@Getter
public enum ErrorLv {

    INFO(1, "正常等级"),

    WARN(2, "警告等级"),

    ERROR(3, "错误等级");


    private int lv;

    private String msg;

    ErrorLv(int lv, String msg) {
        this.lv = lv;
        this.msg = msg;
    }

}
