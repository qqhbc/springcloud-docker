package com.yc.cloud.consumermovie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.yc.cloud.consumermovie.filter.PreRequestLogFilter;

@SpringBootApplication
@EnableZuulProxy
public class GatewayZuulApplication {
    
    @Bean
    public PreRequestLogFilter preRequestLogFilter() {
        return new PreRequestLogFilter();
    }
    
    public static void main(String[] args) {
        SpringApplication.run(GatewayZuulApplication.class, args);
    }
}
