package com.szatc.amonitor.controller;

import com.szatc.amonitor.ibatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/user")
public class UserController {


    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping("/getUser")
    public String getUser(){

        return userService.getUser().getName();
    }
}
