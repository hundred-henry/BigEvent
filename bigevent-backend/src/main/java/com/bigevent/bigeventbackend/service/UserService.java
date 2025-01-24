package com.bigevent.bigeventbackend.service;


import com.bigevent.bigeventbackend.pojo.User;

public interface UserService {
    User findByUserName(String username);

    void register(String username, String password);
}