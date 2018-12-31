package com.baigehuidi.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.baigehuidi.demo.controller","com.baigehuidi.demo.handler","com.baigehuidi.demo.loader","com.baigehuidi.demo.service",
"com.baigehuidi.demo.weixin4j.spi","com.baigehuidi.demo.weixin4j.model.message.normal","com.baigehuidi.demo.weixin4j.model.message.output","com.baigehuidi.demo.weixin4j.model.message",
"com.baigehuidi.demo.weixin4j.component","com.baigehuidi.demo.weixin4j","com.baigehuidi.demo.weixin4j.controller","com.baigehuidi.demo.weixin4j.model.pay"})
@EnableCaching
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
