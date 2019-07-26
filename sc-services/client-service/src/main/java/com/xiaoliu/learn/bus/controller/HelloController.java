package com.xiaoliu.learn.bus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class HelloController {
    @Value("${demo.name}")
    private String name;
    @Autowired
    private Environment env;

    @PostMapping("/hello")
    public String getName() {
        return name;
    }

    @PostMapping("/env")
    public String getEnv() {
        return env.getProperty("demo.name", "undefined");
    }
}
