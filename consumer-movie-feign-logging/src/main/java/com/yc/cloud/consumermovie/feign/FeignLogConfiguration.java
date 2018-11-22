package com.yc.cloud.consumermovie.feign;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Logger;

@Configuration
public class FeignLogConfiguration {
    @Bean
    Logger.Level feignLoggerLever(){
        return Logger.Level.BASIC;
    }
}
