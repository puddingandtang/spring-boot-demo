package com.tcl.demo.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@SpringBootApplication(
        scanBasePackages = {"com.tcl.demo.boot.web","com.tcl.demo.boot.common.aop"}
)
public class SpringBootDemo {

    /**
     * 应用启动入口
     *
     * @param args
     */
    public static void main(String[] args) {

        SpringApplication.run(SpringBootDemo.class, args);

    }
}
