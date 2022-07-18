package com.server.config;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;


@Configuration
public class MyWebConfig extends WebMvcConfigurationSupport {

    private String serverLocation;

    public void setServerLocation(String serverLocation) {
        this.serverLocation = serverLocation;
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/avatorImages/**")
                .addResourceLocations("file:"+serverLocation+"\\img\\avatorImages\\");
        registry.addResourceHandler("/song/**")
                .addResourceLocations("file:"+serverLocation+"\\song\\");
        registry.addResourceHandler("/img/songPic/**")
                .addResourceLocations("file:"+serverLocation+"\\img\\songPic\\");
        registry.addResourceHandler("/img/songListPic/**")
                .addResourceLocations("file:"+serverLocation+"\\img\\songListPic\\");
        registry.addResourceHandler("/img/avatorImages/**")
                .addResourceLocations("file:"+serverLocation+"\\img\\avatorImages\\");
        registry.addResourceHandler("/img/singerPic/**")
                .addResourceLocations("file:"+serverLocation+"\\img\\singerPic\\");
        super.addResourceHandlers(registry);
    }


}
