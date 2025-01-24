package com.bigevent.bigeventbackend.controller;

import com.bigevent.bigeventbackend.pojo.Result;
import com.bigevent.bigeventbackend.pojo.User;
import com.bigevent.bigeventbackend.service.UserService;
import com.bigevent.bigeventbackend.utils.JwtUtil;
import com.bigevent.bigeventbackend.utils.Md5Util;
import com.bigevent.bigeventbackend.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

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

    @GetMapping("/userInfo")
    public Result<User> userInfo(@RequestHeader(name="Authorization") String token) {
//        Map<String, Object> map = JwtUtil.parseToken(token);

        Map<String, Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User user = userService.findByUserName(username);
        return Result.success(user);
    }

    @PutMapping("/update")
    public Result update(@RequestBody @Validated User user) {
        userService.update(user);
        return Result.success();
    }

    @PatchMapping("updateAvatar")
    public Result patch(@RequestParam @URL String avatarUrl) {
        userService.updateAvatar(avatarUrl);
        return Result.success();
    }

    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String, String> params) {
        String oldPwd = params.get("old_pwd");
        String newPwd = params.get("new_pwd");
        String rePwd = params.get("re_pwd");

        if (!StringUtils.hasLength(oldPwd) || !StringUtils.hasLength(newPwd) || !StringUtils.hasLength(rePwd)) {
            return Result.error("Missing required parameters");
        }

        // check old_pwd
        Map<String, Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User loginUser = userService.findByUserName(username);
        if (!loginUser.getPassword().equals(Md5Util.getMD5String(oldPwd))) {
            return Result.error("Wrong old password");
        }

        if (!rePwd.equals(newPwd)) {
            return Result.error("Different new passwords and re-entered passwords");
        }

        userService.updatePwd(newPwd);
        return Result.success();
    }
}
