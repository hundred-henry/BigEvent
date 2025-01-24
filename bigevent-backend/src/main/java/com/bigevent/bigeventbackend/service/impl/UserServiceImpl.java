package com.bigevent.bigeventbackend.service.impl;

import com.bigevent.bigeventbackend.pojo.User;
import com.bigevent.bigeventbackend.service.UserService;
import com.bigevent.bigeventbackend.mapper.UserMapper;
import com.bigevent.bigeventbackend.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByUserName(String username) {
        User u = userMapper.findByUserName(username);
        return u;
    }

    @Override
    public void register(String username, String password) {
        // encrypt password
        String md5String = Md5Util.getMD5String(password);
        // add new user
        userMapper.add(username, md5String);
    }
}
