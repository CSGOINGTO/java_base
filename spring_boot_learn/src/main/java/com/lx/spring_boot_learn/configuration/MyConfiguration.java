package com.lx.spring_boot_learn.configuration;

import com.lx.spring_boot_learn.entity.MyBean;
import com.lx.spring_boot_learn.entity.MyBeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfiguration {
    @Bean(initMethod = "init", destroyMethod = "destroy", name = "myBean")
    public MyBean myBean() {
        return new MyBean();
    }

    @Bean
    public MyBeanPostProcessor myBeanPostProcessor() {
        return new MyBeanPostProcessor();
    }
}

