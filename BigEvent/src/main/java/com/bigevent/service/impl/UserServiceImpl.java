package com.bigevent.service.impl;

import com.bigevent.mapper.UserMapper;
import com.bigevent.pojo.User;
import com.bigevent.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bigevent.utils.Md5Util;

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
