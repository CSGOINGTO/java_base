package com.lx.spring_boot_learn.event;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OverEventPublisherTest {

    @Autowired
    private OverEventPublisher overEventPublisher;

    @Test
    public void publishOverEvent() {
        overEventPublisher.publishOverEvent(new OverEvent("我是新创建的结束任务！"));
    }
}
