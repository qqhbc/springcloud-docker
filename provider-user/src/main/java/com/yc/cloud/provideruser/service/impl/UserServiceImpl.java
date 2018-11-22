package com.yc.cloud.provideruser.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.cloud.provideruser.dao.UserDao;
import com.yc.cloud.provideruser.entity.User;
import com.yc.cloud.provideruser.service.UserService;
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;
    
    @Override
    public User findById(Long id) {
        return userDao.findOne(id);
    }

}
