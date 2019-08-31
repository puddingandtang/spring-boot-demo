package com.tcl.demo.boot.common.aop;

import com.tcl.demo.boot.common.result.ResponseDTO;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.annotation.*;

/**
 * 该注解作用于返回结果为 {@link ResponseDTO}
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@ResponseBody
@Documented
public @interface ResponseAop {
}
