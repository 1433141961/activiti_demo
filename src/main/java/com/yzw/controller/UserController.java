package com.yzw.controller;

import com.yzw.model.Function;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("user")
public class UserController  {
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
        return "permission/index";
    }
    @RequestMapping("toInsert.do")
    public String toInsert(){
        return "permission/addUser";
    }
    @RequestMapping("insert.do")
    public String Insert(User user){
        userService.insert(user);
        return "redirect:selectAllUser.do";
    }

    /**
     * 通过ajax查询功能
     * @return
     */
    @RequestMapping("selectAllFunForAjax.do")
    public void selectAllFunForAjax(HttpServletResponse response) throws IOException {
        //处理ajax的乱码问题
        response.setCharacterEncoding("UTF-8");
        List<Function> functions  = functionService.selectAllFun();
        JSONArray jsonArray = JSONArray.fromObject(functions);
        String  jsonText = jsonArray.toString();
        response.getWriter().write(jsonText);

    }

    /**
     * 通过ajax根据roleId查询角色所有的权限
     * @return
     */
    @RequestMapping("selectFunByRoleId.do")
    public void selectFunByRoleId(HttpServletResponse response ,int roleId) throws IOException {
        //处理ajax的乱码问题
        response.setCharacterEncoding("UTF-8");
        List<Function> functions  = functionService.selectFunByRoleId(roleId);
        JSONArray jsonArray = JSONArray.fromObject(functions);
        String  jsonText = jsonArray.toString();
        response.getWriter().write(jsonText);

    }

    /**
     *查询所有用户
     * @return
     */
    @RequestMapping("selectAllUser.do")
    public String selectAllUser(Model model){
        List<User> users = userService.selectAllUser();
        model.addAttribute("users",users);
        return "permission/listUser";
    }
    /**
     *根据用户名和密码查询user
     * @return
     */
    @RequestMapping("selectUserByUserNameAndPassword.do")
    public String selectUserByUserNameAndPassword(String userName, String password, HttpSession session){
        Map<String,String> map = new HashMap<>();
        map.put("userName",userName);
        map.put("password",password);
        User user = userService.selectUserByUserNameAndPassword(map);
        System.out.println(user);
        if (user!=null){
            //查询到url
            List<String> urls = userService.selectUrlByUserId(user.getUserId());
            //吧url设置到user对象里设置的urlList属性中，这个操作是为了能够在session中拿到url
            user.setUrlList(urls);
            //将user添加到session中
            session.setAttribute("user",user);
            return "redirect:/leave/toIndex.do";
        }
        return "redirect:/ecps/console/login.jsp";
    }
}
