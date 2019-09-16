package com.tcl.demo.boot.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceFactoryConfig {

    @Bean
    public ServiceFactory serviceFactory() {

        return new ServiceFactory();
    }
}
