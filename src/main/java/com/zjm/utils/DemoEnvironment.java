package com.zjm.utils;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component("demoEnvironment")
public class DemoEnvironment implements EnvironmentPostProcessor {
    private ConfigurableEnvironment environment;
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {

    /*    String name = "applicationConfig: [classpath:/application.properties]";
        MapPropertySource propertySource = (MapPropertySource) environment.getPropertySources().get(name);
        Map<String, Object> source = propertySource.getSource();
        Map map = new HashMap();
        source.forEach((k, v) -> {
            map.put(k , v);
        });
        // 将配置的端口号修改为 8022
        map.replace("server.port", 8022);
        environment.getPropertySources().replace(name, new MapPropertySource(name, map));
        this.environment = environment;*/
    }

    public ConfigurableEnvironment getProcessEnvironment(){
        return this.environment;
    }

}