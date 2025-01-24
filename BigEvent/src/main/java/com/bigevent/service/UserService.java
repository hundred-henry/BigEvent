package com.bigevent.service;

import com.bigevent.pojo.User;

public interface UserService {
    User findByUserName(String username);

    void register(String username, String password);
}
