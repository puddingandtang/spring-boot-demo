package com.tcl.demo.boot.common.exception;

import com.google.common.base.Optional;
import com.tcl.demo.boot.common.base.ErrorCode;
import com.tcl.demo.boot.common.base.ErrorLv;

import java.text.MessageFormat;

/**
 * 业务主动封装异常,视为正常
 */
public class BizNormalException extends BizRuntimeException {

    /**
     * 异常构建
     * 错误等级：Info
     *
     * @param errorCode
     */
    public BizNormalException(ErrorCode errorCode) {

        this.code = errorCode.getCode();
        this.msg = errorCode.getMsg();
        this.lv = ErrorLv.INFO;
    }

    /**
     * 异常构建
     * 错误等级：Info
     *
     * @param errorCode
     * @param replace   ：替换errMsg的占位符，采用MessageFormat
     */
    public BizNormalException(ErrorCode errorCode, Object... replace) {

        this.code = errorCode.getCode();
        this.msg = MessageFormat.format(Optional.fromNullable(errorCode.getMsg()).or(""), replace);
        this.lv = ErrorLv.INFO;
    }

    /**
     * 构建异常
     *
     * @param errorCode
     * @param errorLv   ：如果为null则默认Info级别
     */
    public BizNormalException(ErrorCode errorCode, ErrorLv errorLv) {

        this.code = errorCode.getCode();
        this.msg = errorCode.getMsg();
        this.lv = Optional.fromNullable(errorLv).or(ErrorLv.INFO);
    }

    /**
     * 构建异常
     *
     * @param errorCode
     * @param errorLv   ：如果为null则默认Info级别
     * @param replace   ：替换errMsg的占位符，采用MessageFormat
     */
    public BizNormalException(ErrorCode errorCode, ErrorLv errorLv, Object... replace) {

        this.code = errorCode.getCode();
        this.msg = MessageFormat.format(Optional.fromNullable(errorCode.getMsg()).or(""), replace);
        this.lv = Optional.fromNullable(errorLv).or(ErrorLv.INFO);
    }

    /**
     * 构建异常
     *
     * @param errorCode
     * @param errorMsg
     * @param errorLv
     * @param replace
     */
    public BizNormalException(int errorCode, String errorMsg, ErrorLv errorLv, Object... replace) {

        this.code = errorCode;
        this.msg = MessageFormat.format(Optional.fromNullable(errorMsg).or(""), replace);
        this.lv = Optional.fromNullable(errorLv).or(ErrorLv.INFO);

    }


}
