package com.lx.spring_boot_learn.event;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class OverEventPublisher implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    public void publishOverEvent(OverEvent overEvent) {
        applicationContext.publishEvent(overEvent);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
