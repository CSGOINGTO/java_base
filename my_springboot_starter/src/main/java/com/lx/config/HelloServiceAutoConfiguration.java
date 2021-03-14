package com.lx.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.lx.service"})
public class HelloServiceAutoConfiguration {
}
