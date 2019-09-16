package com.tcl.demo.boot.common.base;

public class ErrorCodes {

    // 成功
    public static final ErrorCode SUCCESS = new ErrorCode(ErrorCode.SUCCESS_CODE, "");

    // 系统内部异常，用于非人为主动抛出异常的对外
    public static final ErrorCode CODE_ERROR = new ErrorCode(400, "系统内部异常");

    // 请求参数非法
    public static final ErrorCode PARAMETER_COMMON_ILLEGALITY = new ErrorCode(1_000_001, "请求参数非法");
    // 初始化参数非法
    public static final ErrorCode INIT_PARAM_ILLEGALITY = new ErrorCode(1_000_002, "初始化参数非法");
    // guava cache 返回null错误
    public static final ErrorCode CACHE_CALL_BACK_NULL = new ErrorCode(1_000_003, "缓存回调返回结果为null");
    public static final ErrorCode BIZ_PROCESSOR_IS_EMPTY = new ErrorCode(1_000_004, "BizProcessor不合法");

    // 规则错误码 1_010_000 ~ 1_029_999
    public static final ErrorCode RULE_PARAM_CHECK_ERROR = new ErrorCode(1_010_000, "规则参数非法:{0}");
    public static final ErrorCode RULE_COMMON_ERROR = new ErrorCode(1_010_001, "规则不匹配，通用错误");
    public static final ErrorCode RULE_NOT_EXIST_ERROR = new ErrorCode(1_010_002, "{}-规则不存在");
    public static final ErrorCode RULE_NOT_MATCH_ERROR = new ErrorCode(1_010_003, "{}-规则不满足");

    // 命令错误码 1_030_000 ~ 1_039_999
    public static final ErrorCode COMMAND_PARAM_ILLEGALITY = new ErrorCode(1_030_000, "命令参数非法：{}");

    // Task错误码 1_040_000 ~ 1_049_999
    public static final ErrorCode TASK_COMMON_ERROR = new ErrorCode(1_040_000, "任务非法：{}");

    // 营销用户体系-错误码 2_000_000 ~ 2_099_999
    public static final ErrorCode QUERY_ACCOUNT_PARAM_ILLEGALITY = new ErrorCode(2_000_000, "查询营销账户参数非法");
    public static final ErrorCode IDENTITY_NOT_EXIST = new ErrorCode(2_000_001, "营销账户不存在");


    // 营销券体系-错误码 2_100_000 ~ 2_199_99

    // 营销活动体系-错误码 2_200_000 ~ 2_299_99

    // 营销卡体系-错误码 2_300_000 ~ 2_399_99
}
