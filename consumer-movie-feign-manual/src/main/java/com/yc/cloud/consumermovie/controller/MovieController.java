package com.yc.cloud.consumermovie.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.feign.FeignClientsConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.yc.cloud.consumermovie.entity.User;
import com.yc.cloud.consumermovie.feign.UserFeignClient;

import feign.Client;
import feign.Contract;
import feign.Feign;
import feign.auth.BasicAuthRequestInterceptor;
import feign.codec.Decoder;
import feign.codec.Encoder;

@Import(FeignClientsConfiguration.class)
@RestController
public class MovieController {
   
    private UserFeignClient userUserFeighClient;
    
    private UserFeignClient adminUserFeighClient;
    @Autowired
    public MovieController(Decoder decoder,Encoder encoder,Client client,Contract contract) {
        this.userUserFeighClient = Feign.builder().client(client).encoder(encoder).decoder(decoder).
        contract(contract).requestInterceptor(new BasicAuthRequestInterceptor("user", "123")).target(UserFeignClient.class,"http://provider-user");
        this.adminUserFeighClient = Feign.builder().client(client).encoder(encoder).decoder(decoder).
        contract(contract).requestInterceptor(new BasicAuthRequestInterceptor("admin", "123456")).target(UserFeignClient.class,"http://provider-user");
        } 
    
    @GetMapping("/user-user/{id}")
    public User findByIdUser(@PathVariable Long id) {
        return this.userUserFeighClient.findById(id);
    }
    
    @GetMapping("/user-admin/{id}")
    public User findByIdAdmin(@PathVariable Long id) {
        return this.adminUserFeighClient.findById(id);
    }
    
}
