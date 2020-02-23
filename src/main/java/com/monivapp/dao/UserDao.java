package com.monivapp.dao;

import com.monivapp.entity.User;

public interface UserDao {

    void save(User user);
    
    User findByUserName(String userName);
}