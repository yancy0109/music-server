package com.server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@EnableWebMvc
public class MyWebConfig implements WebMvcConfigurer {

    private String serverLocation;

    public void setServerLocation(String serverLocation) {
        this.serverLocation = serverLocation;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
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
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }
}
