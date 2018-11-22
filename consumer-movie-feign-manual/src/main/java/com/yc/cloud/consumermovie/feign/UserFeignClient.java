package com.yc.cloud.consumermovie.feign;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yc.cloud.consumermovie.entity.User;

public interface UserFeignClient {
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public User findById(@PathVariable("id") Long id);
}
