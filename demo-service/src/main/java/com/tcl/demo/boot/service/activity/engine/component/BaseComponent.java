package com.tcl.demo.boot.service.activity.engine.component;

import com.tcl.demo.boot.common.base.ErrorCodes;
import com.tcl.demo.boot.common.base.ErrorLv;
import com.tcl.demo.boot.common.exception.BizRuntimeException;
import com.tcl.demo.boot.common.exception.BizUnNormalException;
import com.tcl.demo.boot.service.activity.context.ActivityResponse;
import com.tcl.demo.boot.service.activity.context.ActivityTraceContext;
import com.tcl.demo.boot.service.activity.type.ActivityResultCodeEnum;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class BaseComponent {

    /**
     * 执行组件
     *
     * @param traceContext
     * @throws BizRuntimeException
     */
    public void processComponent(ActivityTraceContext traceContext, ActivityResponse response) throws BizRuntimeException {

        try {

            this.process(traceContext, response);

        } catch (Exception e) {

            BizRuntimeException changeException = null;

            if (e instanceof BizRuntimeException) {

                changeException = (BizRuntimeException) e;

            } else {

                // 非BizRuntimeException异常
                changeException = new BizUnNormalException(ErrorCodes.CODE_ERROR, ErrorLv.ERROR);
            }

            log.warn("执行类[{}],活动链路上下文[{}],异常内容[{}]", this.getClass().getName(), traceContext, e);

            throw changeException;


        }


    }

    /**
     * 子类组件实现
     *
     * @param traceContext
     */
    protected abstract void process(ActivityTraceContext traceContext, ActivityResponse response);

    /**
     * 校验失败
     *
     * @param traceContext
     */
    public void verifyFail(ActivityTraceContext traceContext, ActivityResponse response, ActivityResultCodeEnum codeEnum) {

        traceContext.setInterrupt(true);
        response.setResultCode(codeEnum.getCode());
    }
}
