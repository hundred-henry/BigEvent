package com.bigevent.bigeventbackend.controller;

import com.bigevent.bigeventbackend.pojo.Result;
import com.bigevent.bigeventbackend.pojo.User;
import com.bigevent.bigeventbackend.service.UserService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result register(String username, String password) {
        if (username!=null && username.length()>=5 && username.length()<=16 &&
                password!=null && password.length()>=5 && password.length()<=16) {
            // Select User
            User u = userService.findByUserName(username);
            if (u == null) {
                userService.register(username, password);
                return Result.success();
            } else {
                return Result.error("username already exists");
            }
        } else {
            return Result.error("Illegal parameters");
        }
    }
}
