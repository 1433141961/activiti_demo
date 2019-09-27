package com.yzw.controller;

import com.yzw.service.FunctionService;
import com.yzw.service.RoleService;
import com.yzw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("activiti")
public class activitiController {
    @Autowired
    FunctionService functionService;

    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;

    /**
     * 跳转到index界面
     */
    @RequestMapping("toIndex.do")
    public String toIndex(){
        return "activiti/index";
    }



}
