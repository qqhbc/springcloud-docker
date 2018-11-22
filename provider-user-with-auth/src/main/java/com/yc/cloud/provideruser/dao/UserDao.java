package com.yc.cloud.provideruser.dao;

import org.springframework.data.jpa.repository.JpaRepository;    
import org.springframework.stereotype.Repository;

import com.yc.cloud.provideruser.entity.User;

@Repository
public interface UserDao extends JpaRepository<User,Long> {
    
}
