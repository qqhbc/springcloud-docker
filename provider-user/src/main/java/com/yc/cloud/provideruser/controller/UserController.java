package com.yc.cloud.provideruser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.yc.cloud.provideruser.entity.User;
import com.yc.cloud.provideruser.service.UserService;

@RestController
public class UserController {
    
    @Autowired
    private UserService UserService;
    
    @GetMapping("/{id}")
    public User findById(@PathVariable Long id) {
        return UserService.findById(id);
    }
    
}
