package com.tcl.demo.boot.common.aop;

import com.google.common.base.Optional;
import com.tcl.demo.boot.common.base.ErrorCodes;
import com.tcl.demo.boot.common.base.ErrorLv;
import com.tcl.demo.boot.common.exception.BizNormalException;
import com.tcl.demo.boot.common.exception.BizRuntimeException;
import com.tcl.demo.boot.common.result.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Aspect
@Configuration
public class ResponseAopProcess {

    private static final Logger chainLog = LoggerFactory.getLogger("chainLog");

    @Pointcut(value = "@annotation(ResponseAop)")
    public void pointCut() {
    }

    @Around(value = "pointCut()")
    public Object processAop(ProceedingJoinPoint pjp) throws Throwable {

        long start = System.currentTimeMillis();

        try {

            return pjp.proceed();

        } catch (Throwable e) {

            // 处理异常
            return processException(e);

        } finally {

            long end = System.currentTimeMillis();

            // todo 构建一个链路top日志，用于追寻一次请求的
            chainLog.info("{} - {} - 耗时 {} ms", pjp.getSignature().getDeclaringTypeName(), pjp.getSignature().getName(), end - start);

        }
    }

    private Object processException(Throwable e) {

        // 异常封装结果
        ResponseDTO responseDTO;

        // 日志级别默认Warn
        ErrorLv logLv = ErrorLv.WARN;

        // 异常
        BizRuntimeException runtimeException = null;

        if (e instanceof BizNormalException) {

            runtimeException = (BizRuntimeException) e;

            responseDTO = new ResponseDTO().buildFail(runtimeException.getCode(), runtimeException.getMsg());

            // 设置日志级别,默认WARN
            logLv = Optional.fromNullable(runtimeException.getLv()).or(ErrorLv.WARN);

        } else {

            responseDTO = new ResponseDTO().buildFail(ErrorCodes.CODE_ERROR.getCode(), ErrorCodes.CODE_ERROR.getMsg());

            logLv = ErrorLv.ERROR;
        }


        switch (logLv) {

            case INFO: {
                log.info("执行异常，日志级别为Info，异常内容为:", e);
                break;
            }
            case WARN: {
                log.warn("执行异常，日志级别为Warn，异常内容为：", e);
                break;
            }
            case ERROR: {
                log.error("执行异常，日志级别为Error，异常内容为：", e);
                break;
            }
            default: {
                break;
            }
        }


        return responseDTO;
    }
}
