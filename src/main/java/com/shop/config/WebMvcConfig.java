package com.shop.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${uploadPath}") //application.properties 설정한 uploadPath
    String uploadPath;
    //uploadPath = "C:/shop
    // images/item/xxx.jps

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/images/**").addResourceLocations(uploadPath);
        //images로 시작하는 경우 uploadPath에 설정한 폴더를 기준으로 파일을
        //읽어 오도록 설정
        //로컬 컴퓨터에서 root 경로를 설정
    }

}
