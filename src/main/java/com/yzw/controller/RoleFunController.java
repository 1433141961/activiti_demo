package com.yzw.controller;

import com.yzw.model.Function;
import com.yzw.model.Role;
import com.yzw.model.RoleFun;
import com.yzw.service.FunctionService;
import com.yzw.service.RoleFunService;
import com.yzw.service.RoleService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("roleFun")
public class RoleFunController {
    @Autowired
    FunctionService functionService;

    @Autowired
    RoleService roleService;

    @Autowired
    RoleFunService roleFunService;

    /**
     * 分配权限：首先删除已有的所有权限，然后再重新进行选中（赋权）
     * @return
     */
    @RequestMapping("grantFun.do")
    public void grandFun(HttpServletResponse response , int roleId,String funIds) throws IOException {
        //处理ajax的乱码问题
        response.setCharacterEncoding("UTF-8");
        //分割前台传过来的funId字符串数组


        List<RoleFun> roleFuns = new ArrayList<RoleFun>();
    //首先需要判空，如果fundIds是空话，分割了有没有意义
        if (funIds != null && !"".equals(funIds.trim())){
        String[] funIdArr = funIds.split(",");
        //如果长度为零的话就会报错
        if (funIdArr.length>0){
            for (String funId:funIdArr){
                RoleFun roleFun = new RoleFun();
        /*        //强制转化为int类型
                int funIdNum = Integer.parseInt(funId);*/
                roleFun.setFunId(new Integer(funId));
                roleFun.setRoleId(roleId);
                roleFuns.add(roleFun);
            }
            roleFunService.grantFun(roleId,roleFuns);
            response.getWriter().write("success");
        }
    }else {
            roleFunService.deleteRoleFunByRoleId(roleId);
            response.getWriter().write("success");
        }


}


}
