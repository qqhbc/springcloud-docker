package com.yc.cloud.consumermovie.feign;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Contract;

@Configuration
public class FeignConfiguration {
    
    @Bean
    public Contract feginContract() {
        return new feign.Contract.Default();
    }
}
