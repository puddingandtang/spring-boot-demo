package com.tcl.demo.boot.common.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Aspect
@Configuration
public class ResponseAopProcess {

    private static final Logger invokedTimeLog = LoggerFactory.getLogger("invokedTime");

    @Pointcut(value = "@annotation(ResponseAop)")
    public void pointCut() {
    }

    @Around(value = "pointCut()")
    public Object logInvokedTime(ProceedingJoinPoint pjp) throws Throwable {

        long start = System.currentTimeMillis();

        try {

            return pjp.proceed();

        } finally {

            long end = System.currentTimeMillis();

            invokedTimeLog.info("{} - {} - 耗时 {} ms", pjp.getSignature().getDeclaringTypeName(), pjp.getSignature().getName(), end - start);

        }
    }
}
