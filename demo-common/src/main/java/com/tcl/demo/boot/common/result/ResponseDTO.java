package com.tcl.demo.boot.common.result;

import com.sun.tools.javac.util.Assert;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class ResponseDTO<T> implements Serializable {

    public static final int SUCCESS_CODE = 200;

    /**
     * 响应码
     */
    public int code;

    /**
     * 响应是否成功
     */
    public boolean success;

    /**
     * 响应错误信息
     */
    public String errMsg;

    /**
     * 响应数据
     */
    private T data;

    /**
     * 构造函数
     */
    public ResponseDTO() {
    }

    /**
     * 构造函数
     *
     * @param code
     * @param errMsg
     * @param data
     */
    public ResponseDTO(int code, String errMsg, T data) {
        this.code = code;
        this.success = (code == SUCCESS_CODE);
        this.errMsg = errMsg;
        this.data = data;
    }

    /**
     * 构建成功
     *
     * @param inputData
     */
    public ResponseDTO<T> buildSuccess(T inputData) {

        this.code = SUCCESS_CODE;
        this.success = Boolean.TRUE;
        this.data = inputData;
        return this;
    }

    /**
     * 构建失败
     *
     * @param errorCode
     * @param errMsg
     * @return
     */
    public ResponseDTO<T> buildFail(int errorCode, String errMsg) {

        Assert.check(errorCode != SUCCESS_CODE, "错误码设置非法");
        this.code = errorCode;
        this.success = Boolean.FALSE;
        this.errMsg = errMsg;

        return this;
    }

}
