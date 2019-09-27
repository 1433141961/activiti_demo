package com.yzw.controller;

import com.yzw.model.Role;
import com.yzw.service.FunctionService;
import com.yzw.service.RoleService;
import com.yzw.service.UserService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@Controller
@RequestMapping("role")
public class RoleController {
    @Autowired
    FunctionService functionService;

    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;

    /**
     * 查询所有角色
     * @return
     */
    @RequestMapping("selectAllRole.do")
    public String toListRole(Model model){
        List<Role> roles = roleService.selectAllRole();
        model.addAttribute("roles",roles);
        return "permission/listRole";
    }
    /**
     * 跳转到添加角色
     * @return
     */
    @RequestMapping("toAddRole.do")
    public String toAddRole(){
       return "permission/addRole";
    }
    /**
     * 添加角色
     * @return
     */
    @RequestMapping("addRole.do")
    public String addRole(Role role){
        roleService.save(role);
       return "redirect:selectAllRole.do";
    }



    /**
     * 通过ajax查询角色
     * @return
     */
    @RequestMapping("selectAllRoleForAjax.do")
    public void selectAllRoleForAjax(HttpServletResponse response) throws IOException {
        //处理ajax的乱码问题
        response.setCharacterEncoding("UTF-8");
        List<Role> roles = roleService.selectAllRole();
        JSONArray jsonArray = JSONArray.fromObject(roles);
        String  jsonText = jsonArray.toString();
        response.getWriter().write(jsonText);

    }

    /**
     * 通过ajax根据roleId查询角色所有的权限
     * @return
     */
    @RequestMapping("selectRoleByUserId.do")
    public void selectRoleByUserId(HttpServletResponse response ,int userId) throws IOException {
        //处理ajax的乱码问题
        response.setCharacterEncoding("UTF-8");
        List<Role> roles = roleService.selectRoleByUserId(userId);
        JSONArray jsonArray = JSONArray.fromObject(roles);
        String  jsonText = jsonArray.toString();
        response.getWriter().write(jsonText);
    }



}
