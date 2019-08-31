package com.tcl.demo.boot.common.exception;

import com.tcl.demo.boot.common.base.ErrorCode;
import com.tcl.demo.boot.common.base.ErrorLv;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 封装业务异常
 */
@Data
@EqualsAndHashCode(callSuper = false)
public abstract class BizRuntimeException extends RuntimeException {

    /**
     * 错误码
     *
     * @see ErrorCode#code
     */
    protected int code;

    /**
     * 错误信息
     */
    protected String msg;

    /**
     * 默认为Info级别
     */
    protected ErrorLv lv = ErrorLv.INFO;

}
