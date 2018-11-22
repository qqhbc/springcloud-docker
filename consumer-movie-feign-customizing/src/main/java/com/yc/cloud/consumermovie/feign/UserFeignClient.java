package com.yc.cloud.consumermovie.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yc.cloud.consumermovie.entity.User;

import feign.Param;

@FeignClient(name="provider-user",configuration=FeignConfiguration.class)
public interface UserFeignClient {
    
    @RequestMapping("GET /{id}")
    public User findById(@Param("id") Long id);
}
