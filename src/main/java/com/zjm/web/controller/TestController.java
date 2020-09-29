package com.zjm.web.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class TestController {
    @Value("${server.port}")
    private String port;

    @RequestMapping("/handler")
    public String handler(HttpServletRequest request) {

        return "ok";
    }

    @RequestMapping("/getname")
    public String getName() {
        return "myname";
    }

}