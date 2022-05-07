package com.jiawa.wiki.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Value("${test.hello:默认值}")
    private String testHello;
    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String hello(){
        return "Hello World"+testHello;
    }
}
