package com.lx.spring_boot_learn.event;

import org.springframework.context.ApplicationEvent;

public class OverEvent extends ApplicationEvent {

    public OverEvent(Object source) {
        super(source);
    }
}
