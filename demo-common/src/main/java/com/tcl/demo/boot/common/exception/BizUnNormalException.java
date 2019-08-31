package com.tcl.demo.boot.common.exception;

import com.google.common.base.Optional;
import com.sun.tools.javac.util.Assert;
import com.tcl.demo.boot.common.base.ErrorCode;
import com.tcl.demo.boot.common.base.ErrorLv;

import java.text.MessageFormat;

/**
 * 非人为异常采用该异常封装
 */
public class BizUnNormalException extends BizRuntimeException {


    /**
     * 异常构建
     * 错误等级：Error
     *
     * @param errorCode
     */
    public BizUnNormalException(ErrorCode errorCode) {

        // Assert.check(null != errorCode);

        this.code = errorCode.getCode();
        this.msg = errorCode.getMsg();
        this.lv = ErrorLv.ERROR;
    }

    /**
     * 异常构建
     * 错误等级：Error
     *
     * @param errorCode
     * @param replace   ：替换errMsg的占位符，采用MessageFormat
     */
    public BizUnNormalException(ErrorCode errorCode, Object... replace) {

        // Assert.check(null != errorCode);

        this.code = errorCode.getCode();
        this.msg = MessageFormat.format(Optional.fromNullable(errorCode.getMsg()).or(""), replace);
        this.lv = ErrorLv.ERROR;
    }

    /**
     * 构建异常
     *
     * @param errorCode
     * @param errorLv   ：如果为null则默认Error级别
     */
    public BizUnNormalException(ErrorCode errorCode, ErrorLv errorLv) {

        // Assert.check(null != errorCode);

        this.code = errorCode.getCode();
        this.msg = errorCode.getMsg();
        this.lv = Optional.fromNullable(errorLv).or(ErrorLv.ERROR);
    }

    /**
     * 构建异常
     *
     * @param errorCode
     * @param errorLv   ：如果为null则默认Error级别
     * @param replace   ：替换errMsg的占位符，采用MessageFormat
     */
    public BizUnNormalException(ErrorCode errorCode, ErrorLv errorLv, Object... replace) {

        // Assert.check(null != errorCode);
        this.code = errorCode.getCode();
        this.msg = MessageFormat.format(Optional.fromNullable(errorCode.getMsg()).or(""), replace);
        this.lv = Optional.fromNullable(errorLv).or(ErrorLv.ERROR);
    }

    /**
     * 构建异常
     *
     * @param errorCode
     * @param errorMsg
     * @param errorLv
     * @param replace
     */
    public BizUnNormalException(int errorCode, String errorMsg, ErrorLv errorLv, Object... replace) {

        this.code = errorCode;
        this.msg = MessageFormat.format(Optional.fromNullable(errorMsg).or(""), replace);
        this.lv = Optional.fromNullable(errorLv).or(ErrorLv.ERROR);

    }
}
