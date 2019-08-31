package com.tcl.demo.boot.common.base;

public class ErrorCodes {

    // 成功
    public static final ErrorCode SUCCESS = new ErrorCode(ErrorCode.SUCCESS_CODE, "");

    // 系统内部异常，用于非人为主动抛出异常的对外
    public static final ErrorCode CODE_ERROR = new ErrorCode(400, "系统内部异常");

    // 请求参数非法
    public static final ErrorCode PARAMETER_COMMON_ILLEGALITY = new ErrorCode(1_000_000, "请求参数非法");


}
