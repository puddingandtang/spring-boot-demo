package com.tcl.demo.boot.common.base;

import lombok.Data;

import java.io.Serializable;

@Data
public class ErrorCode implements Serializable {

    /**
     * 该code为成功，请勿占用
     */
    public static final int SUCCESS_CODE = 200;

    /**
     * 错误码
     */
    private int code;

    /**
     * 错误码信息
     */
    private String msg;

    public ErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
