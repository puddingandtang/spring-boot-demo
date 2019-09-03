package com.tcl.demo.boot.common.base;

public class ErrorCodes {

    // 成功
    public static final ErrorCode SUCCESS = new ErrorCode(ErrorCode.SUCCESS_CODE, "");

    // 系统内部异常，用于非人为主动抛出异常的对外
    public static final ErrorCode CODE_ERROR = new ErrorCode(400, "系统内部异常");

    // 请求参数非法
    public static final ErrorCode PARAMETER_COMMON_ILLEGALITY = new ErrorCode(1_000_001, "请求参数非法");

    // 营销用户体系-错误码 2_000_000 ~ 2_099_999
    public static final ErrorCode QUERY_ACCOUNT_PARAM_ILLEGALITY = new ErrorCode(2_000_000, "查询营销账户参数非法");
    public static final ErrorCode IDENTITY_NOT_EXIST = new ErrorCode(2_000_001, "营销账户不存在");

    // 营销券体系-错误码 2_100_000 ~ 2_199_99

    // 营销活动体系-错误码 2_200_000 ~ 2_299_99

    // 营销卡体系-错误码 2_300_000 ~ 2_399_99
}
