package com.tcl.demo.boot.common.aop;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import com.tcl.demo.boot.common.base.ErrorCodes;
import com.tcl.demo.boot.common.base.ErrorLv;
import com.tcl.demo.boot.common.exception.BizNormalException;
import com.tcl.demo.boot.common.exception.BizRuntimeException;
import com.tcl.demo.boot.common.result.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * 全局异常捕获aop，作用于最外层，例如controller
 */
@Slf4j
@Aspect
@Configuration
public class ResponseAopProcess {

    private static final Logger chainLog = LoggerFactory.getLogger("chainLog");

    private static final List<Class> filterClass = Lists.newArrayList(Integer.class);

    @Pointcut(value = "@annotation(ResponseAop)")
    public void pointCut() {
    }

    @Around(value = "pointCut()")
    public Object processAop(ProceedingJoinPoint pjp) throws Throwable {

        long start = System.currentTimeMillis();

        Object pjpReturn;

        try {

            // 执行返回结果
            pjpReturn = pjp.proceed();
            return pjpReturn;

        } catch (Throwable e) {

            // 判断pjpReturn结构是否为ResponseDTO
            if (checkClassReturnIfNotResponseDTO(pjp)) {
                throw new Throwable(e);
            }
            // 处理异常
            return processException(e);

        } finally {

            // 构建请求检索日志
            processChainLog(pjp, start);
        }
    }

    private boolean checkClassReturnIfNotResponseDTO(ProceedingJoinPoint pjp) {

        try {
            Signature signature = pjp.getSignature();
            MethodSignature methodSignature = (MethodSignature) signature;

            Class returnClass = methodSignature.getReturnType();

            if (returnClass == ResponseDTO.class) {

                return false;
            }

            return true;
        } catch (Exception e) {
            return true;
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

    private void processChainLog(ProceedingJoinPoint pjp, long requestBeginTime) {

        long requestEndTime = System.currentTimeMillis();

        Object[] requestParams = pjp.getArgs();

        JSONObject paramFirst = new JSONObject();

        if (null != requestParams && requestParams.length > 0) {

            try {
                Object objects = requestParams[0];
                paramFirst = JSONObject.parseObject(JSON.toJSONString(objects));
            } catch (Exception e) {
            }
        }

        Long userNo = Optional.fromNullable(paramFirst.getLong("userNo")).or(0L);
        Integer userType = Optional.fromNullable(paramFirst.getInteger("userType")).or(0);
        Integer bizLine = Optional.fromNullable(paramFirst.getInteger("bizLine")).or(0);
        Integer terminal = Optional.fromNullable(paramFirst.getInteger("terminal")).or(0);

        // todo 构建一个链路top日志，用于追寻一次请求的 -->引入logback 的 MCD
        chainLog.info("{} - {} - [{},{},{},{}] - 耗时 {} ms",
                pjp.getSignature().getDeclaringTypeName(),
                pjp.getSignature().getName(),
                userNo,
                userType,
                bizLine,
                terminal,
                requestEndTime - requestBeginTime);

    }
}
