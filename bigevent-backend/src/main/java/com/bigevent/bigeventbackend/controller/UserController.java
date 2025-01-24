package com.bigevent.bigeventbackend.controller;

import com.bigevent.bigeventbackend.pojo.Result;
import com.bigevent.bigeventbackend.pojo.User;
import com.bigevent.bigeventbackend.service.UserService;
import com.bigevent.bigeventbackend.utils.JwtUtil;
import com.bigevent.bigeventbackend.utils.Md5Util;
import jakarta.validation.constraints.Pattern;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}$")String username,
                           @Pattern(regexp = "^\\S{5,16}$")String password) {
        // Select User
        User u = userService.findByUserName(username);
        if (u == null) {
            userService.register(username, password);
            return Result.success();
        } else {
            return Result.error("username already exists");
        }
    }

    @PostMapping("/login")
    public Result<String> login(@Pattern(regexp = "^\\S{5,16}$")String username,
                                @Pattern(regexp = "^\\S{5,16}$")String password) {
        // search user by username
        User loginUser = userService.findByUserName(username);

        // Determine if the user exists
        if (loginUser == null) {
            return Result.error("username not found");
        }

        // Determine whether the password is correct
        if (Md5Util.getMD5String(password).equals(loginUser.getPassword())) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", loginUser.getId());
            claims.put("username", loginUser.getUsername());

            String token = JwtUtil.genToken(claims);
            return Result.success(token);
        }

        return Result.error("password incorrect");
    }
}
