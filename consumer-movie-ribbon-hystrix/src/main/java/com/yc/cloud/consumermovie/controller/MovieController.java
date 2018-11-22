package com.yc.cloud.consumermovie.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yc.cloud.consumermovie.entity.User;

@RestController
public class MovieController {
   
    @Autowired
    private RestTemplate restTemplate;
/*    @Autowired
    private DiscoveryClient discoveryClient;*/
    @Autowired
    private LoadBalancerClient loadBalanceClient;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(MovieController.class);
    
    @HystrixCommand(fallbackMethod = "findByIdFallback")
    @GetMapping("/user/{id}")
    public User findById(@PathVariable Long id) {
        return this.restTemplate.getForObject("http://provider-user/"+id, User.class);
    }
    
   /* @GetMapping("/user-instance")
    public List<ServiceInstance> shwInfo(){
        return this.discoveryClient.getInstances("provider-user");
    }*/
    
    @GetMapping("/log-user-instance")
    public void logUserInstance() {
        ServiceInstance serviceInstance = this.loadBalanceClient.choose("provider-user");
        MovieController.LOGGER.info("{}:{}:{}",serviceInstance.getServiceId(),serviceInstance.getHost(),serviceInstance.getPort(),serviceInstance.getMetadata());
    }
    
    public User findByIdFallback(Long id) {
        User user = new User();
        user.setId(-1L);
        user.setUsername("默认用户");
        return user;
    }
}
