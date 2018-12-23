package com.itheima.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/jump")
public class UsersJumpPage {
    /**
     * 页面跳转
     * @return
     */
    @RequestMapping("/addUser")
    public String jumpUpdate(){
        return "add";
    }
}
