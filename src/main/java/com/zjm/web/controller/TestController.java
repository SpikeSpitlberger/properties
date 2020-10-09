package com.zjm.web.controller;

import com.zjm.utils.SafeProperties;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.context.support.XmlWebApplicationContext;

import javax.servlet.http.HttpServletRequest;
import java.io.*;

@RestController
public class TestController implements ApplicationContextAware {
    @Value("${server.port}")
    private String port;
    @Value("${myproperties.name}")
    private String myname;

    ApplicationContext applicationContext;

    private String profilePath = TestController.class.getResource("/").getPath() + "application.properties";

    @RequestMapping("/handler")
    public String handler(HttpServletRequest request) {
        OutputStream fos = null;
        SafeProperties properties = new SafeProperties();
        File fileStre = new File(this.profilePath);


        try {
            fos = new FileOutputStream(this.profilePath);
            properties.load(fileStre);
            properties.setProperty("server.port", "80", "ddd");
            properties.setProperty("myproperties.name", "Spike", "cccc");
            properties.store(new OutputStreamWriter(fos, "UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }


//        AnnotationConfigServletWebServerApplicationContext context =
//                (AnnotationConfigServletWebServerApplicationContext) WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
        AnnotationConfigServletWebServerApplicationContext context =
                (AnnotationConfigServletWebServerApplicationContext) applicationContext;
        context.refresh();
        return "ok";
    }

    @RequestMapping("/getname")
    public String getName() {
        return myname;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}