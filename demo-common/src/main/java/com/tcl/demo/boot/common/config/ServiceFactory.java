package com.tcl.demo.boot.common.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ServiceFactory implements ApplicationContextAware {


    private static ApplicationContext context = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        context = applicationContext;
    }

    /**
     * 通过class获取实例化bean
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBeanByClass(Class<T> clazz) {
        return context.getBean(clazz);
    }

    /**
     * 通过beanName获取实例化bean
     *
     * @param beanName
     * @return
     */
    public static Object getBeanByName(String beanName) {
        try {
            return context.getBean(beanName);
        } catch (BeansException e) {
            return null;
        }
    }

    /**
     * 通过beanName及类型获取实例化bean
     *
     * @param beanName
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBeanByNameAndClass(String beanName, Class<T> clazz) {
        return context.getBean(beanName, clazz);
    }

    /***
     * 获取上下文容器
     * @return
     */
    public static ApplicationContext getContext() {
        return context;
    }

}
