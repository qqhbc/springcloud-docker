package com.yc.cloud.consumermovie.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.yc.cloud.consumermovie.entity.User;
import com.yc.cloud.consumermovie.feign.UserFeignClient;

@RestController
public class MovieController {
   
    @Autowired
    private UserFeignClient userFeignClient;
    
    @GetMapping("/user/{id}")
    public User findById(@PathVariable Long id) {
        return this.userFeignClient.findById(id);
    }
    
}
