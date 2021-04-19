package com.lx.spring_boot_learn.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class OverEventListener implements ApplicationListener<OverEvent> {

    @Override
    public void onApplicationEvent(OverEvent overEvent) {
        System.out.println("监听到OverEvent事件的发布，发布内容为：" + overEvent.getSource());
    }
}
