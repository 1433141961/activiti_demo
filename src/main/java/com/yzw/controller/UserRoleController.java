package com.yzw.controller;

import com.yzw.model.RoleFun;
import com.yzw.model.UserRole;
import com.yzw.service.FunctionService;
import com.yzw.service.RoleFunService;
import com.yzw.service.RoleService;
import com.yzw.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("userRole")
public class UserRoleController {
    @Autowired
    FunctionService functionService;

    @Autowired
    RoleService roleService;

    @Autowired
    RoleFunService roleFunService;

    @Autowired
    UserRoleService userRoleService;

    /**
     * 分配角色：首先删除已有的所有的角色，然后在重新将选中的角色插入（赋予角色）
     * @return
     */
    @RequestMapping("grantRole.do")
    public void grantRole(HttpServletResponse response , int userId,String roleIds) throws IOException {
        //处理ajax的乱码问题
        response.setCharacterEncoding("UTF-8");
        //分割前台传过来的funId字符串数组


        List<UserRole> userRoles = new ArrayList<UserRole>();
    //首先需要判空，如果fundIds是空话，分割了有没有意义
        if (roleIds != null && !"".equals(roleIds.trim())){
        String[] roleIdArr = roleIds.split(",");
        //如果长度为零的话就会报错
        if (roleIdArr.length>0){
            for (String roleId:roleIdArr){
                UserRole userRole = new UserRole();
        /*        //强制转化为int类型
                int funIdNum = Integer.parseInt(funId);*/
                userRole.setRoleId(new Integer(roleId));
                userRole.setUserId(userId);
                userRoles.add(userRole);
            }
            userRoleService.grantRole(userRoles,userId);
            response.getWriter().write("success");
        }
    }else {
            userRoleService.deleteUserRoleByUserId(userId);
            response.getWriter().write("success");
        }


}


}
