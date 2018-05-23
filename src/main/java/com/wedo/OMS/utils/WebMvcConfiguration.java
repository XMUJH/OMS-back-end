package com.wedo.OMS.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


/**
 * 自定义配置类实现JavaBean注解形式配置
 */
@Configuration
public class WebMvcConfiguration extends WebMvcConfigurerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebMvcConfiguration.class);

    /**
     * 跨域CORS配置
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        super.addCorsMappings(registry);
        LOGGER.info("WebMVC configuration : addCorsMappings");
        registry.addMapping("/**").allowedOrigins("*")
                .allowedMethods("*").allowedHeaders("*");
    }
}

