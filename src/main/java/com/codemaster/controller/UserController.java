package com.codemaster.controller;


import com.codemaster.entity.User;
import com.codemaster.service.UserService;
import com.codemaster.utils.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/api/user")
public class UserController {

    @Resource
    private UserService userService;
    @GetMapping("/list")
    public Result list() {
        List<User> list = userService.list();
        return Result.ok(list);
    }
}

