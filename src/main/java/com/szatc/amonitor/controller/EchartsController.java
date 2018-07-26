package com.szatc.amonitor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/chart")
public class EchartsController {


    @RequestMapping(value = "/echarts")
    public ModelAndView index(ModelAndView modelAndView) {
        modelAndView.setViewName("echarts");

        List<String> userList=new ArrayList<String>();
        userList.add("admin");
        userList.add("user1");
        userList.add("user2");

        modelAndView.addObject("userList", userList);
        return modelAndView;
    }

}
