package com.lx.spring_boot_learn.entity;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class MyBeanPostProcessor implements BeanPostProcessor {

    // 初始化之前执行
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("初始化之前执行...");
        if (bean instanceof MyBean) {
            ((MyBean) bean).setBeanName("初始化之前的beanName");
        }
        return bean;
    }

    // 初始化之后执行
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("初始化之后执行...");
        if (bean instanceof MyBean) {
            ((MyBean) bean).setBeanName("初始化之后的beanName");
        }
        return bean;
    }
}
