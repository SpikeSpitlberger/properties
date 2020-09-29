package com.zjm.web.controller;

import com.zjm.utils.DemoEnvironment;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContextExtensionsKt;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class TestController implements ApplicationContextAware {
    private ApplicationContext context;
    @Value("${server.port}")
    private String port;
    @Value("${myname}")
    private String myname;

    @Autowired
    private ConfigurableEnvironment environment;
    
    @RequestMapping("/handler")
    public String handler(HttpServletRequest request){
        String name = "applicationConfig: [classpath:/application.properties]";
        MapPropertySource propertySource = (MapPropertySource) environment.getPropertySources().get(name);
        Map<String, Object> source = propertySource.getSource();
        Map map = new HashMap();
        source.forEach((k, v) -> {
            map.put(k , v);
        });
        // 将配置的端口号修改为 8022
        map.replace("name", "Spike");
        environment.getPropertySources().replace(name, new MapPropertySource(name, map));
        AnnotationConfigServletWebServerApplicationContext aca = (AnnotationConfigServletWebServerApplicationContext) WebApplicationContextUtils
                .getWebApplicationContext(request.getServletContext());
        aca.refresh();

        return this.myname;
    }

    @RequestMapping("/getname")
    public String getName(){
        return myname;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}