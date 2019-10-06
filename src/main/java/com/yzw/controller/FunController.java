package com.yzw.controller;

import com.yzw.model.Function;
import com.yzw.model.Role;
import com.yzw.model.User;
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
@RequestMapping("fun")
public class FunController {
    @Autowired
    FunctionService functionService;

    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;

    /**
     * 列出所有角色
     * @return
     */
    @RequestMapping("selectAllFun.do")
    public String toListRole(Model model){
        List<Function> funs = functionService.selectAllFun();
        model.addAttribute("funs",funs);
        return "permission/listFun";
    }
    /**
     * 跳转到添加角色页面
     * @return
     */
    @RequestMapping("toAddFun.do")
    public String toAddFun(){
        return "permission/addFun";
    }
    /**
     * 添加角色
     * @return
     */
    @RequestMapping("addFun.do")
    public String addFun(Function function){
        functionService.saveFun(function);
        return "redirect:selectAllFun.do";
    }
    /**
     * 根据id删除角色
     * @return
     */
    @RequestMapping("deleteFunByFunId.do")
    public String deleteFunByFunId(int funId){
        functionService.deleteFunByFunId(funId);
        return "redirect:selectAllFun.do";
    }

}
