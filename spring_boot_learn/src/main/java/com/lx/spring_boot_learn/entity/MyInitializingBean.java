package com.lx.spring_boot_learn.entity;

import org.springframework.beans.factory.InitializingBean;

//@Component
public class MyInitializingBean extends MyBean implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Initializing...");
        this.setBeanName("initializing myBean...");
    }
}
