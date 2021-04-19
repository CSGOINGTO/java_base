package com.lx.spring_boot_learn.entity;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class MyBean implements BeanNameAware, BeanFactoryAware, InitializingBean, DisposableBean {

    private BeanFactory beanFactory;

    private String beanName;

    public MyBean() {
        System.out.println("我是构造器...");
    }

    @PostConstruct
    private void initMethod() {
        System.out.println("PostConstruct...");
    }

    private void init() {
        System.out.println("init method...");
    }

    public void destroy() {
        System.out.println("destroy method...");
    }

    @PreDestroy
    private void destroyMethod() {
        System.out.println("PreDestroy...");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setBeanName(String s) {
        this.beanName = s;
    }

    @Override
    public String toString() {
        return "MyBean{" +
                "beanFactory=" + beanFactory +
                ", beanName='" + beanName + '\'' +
                '}';
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean afterPropertiesSet方法");
    }

}
