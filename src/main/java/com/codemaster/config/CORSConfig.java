package com.codemaster.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * packageName com.codemaster.config
 *
 * @author lyf
 * @version JDK 1.8
 * @className CORSConfig
 * @date 2024/11/28  16:22
 * @description 跨域配置类
 */
@Configuration
public class CORSConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").//允许所有的访问请求（访问路径）
                allowedOrigins("*").//允许所有的请求域名访问我们的跨域资源
                allowedMethods("*").//允许所有的请求方法访问该跨域资源服务器
                allowedHeaders("*");//允许所有的请求header访问

    }
}