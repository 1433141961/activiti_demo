package com.yzw.controller;

import com.yzw.model.*;
import com.yzw.service.*;
import com.yzw.service.impl.FlowServiceImpl;
import org.apache.axis2.description.Flow;
import org.springframework. beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("leave")
public class LeaveController {
    @Autowired
    FunctionService functionService;

    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;

    @Autowired
    LeaveBillService leaveBillService;


    /**
     * 跳转到index界面
     */
    @RequestMapping("toIndex.do")
    public String toIndex(){
        return "item/index";
    }
    /**
     * 跳转到用户请假界面
     */
    @RequestMapping("listBill.do")
    public String listBill(String assignee, HttpSession session,Integer state, Model model){
        User user = (User) session.getAttribute("user");
        if (user!=null) {
            List<TaskBean> taskBeans = leaveBillService.selectLeaveTaskBean(user.getUserId(), assignee);
            model.addAttribute("taskBeans", taskBeans);

            List<ProcessInstanceBean> piList =  leaveBillService.selectHistoryProcessInstanceBean(user.getUserId());
            model.addAttribute("piList",piList);
            model.addAttribute("state", state);
        }
        return "item/listBill_"+assignee;
    }


    /**
     * 跳转到添加请假单界面
     */
    @RequestMapping("toAddBill.do")
    public String toAddBill(){
        return "item/addBill";
    }

    /**
     * 跳转到添加请假单界面
     */
    @RequestMapping("addBill.do")
    public String addBill(LeaveBill leaveBill,HttpSession session){
        User user = (User) session.getAttribute("user");
        if (user!=null){
            leaveBillService.insert(leaveBill,user.getUserId());
            return "redirect:listBill.do?assignee=employee&state=1";
        }else {
            return "redirect:/ecps/console/login.jsp";
        }
    }

    /**
     * 查看请假单详情
     */
    @RequestMapping("doBillDetail.do")
    public String doBillDetail(String taskId,HttpSession session,Model model){
        User user = (User) session.getAttribute("user");
        if (user!=null){
            //查询任务
            TaskBean taskBean = leaveBillService.selectTaskBeanByTaskId(user.getUserId(),taskId);
            //查询批注信息
            List<CommentBean> commentBeans = leaveBillService.selectCommentList(taskId);
//            List<Date> dates = leaveBillService.selectCommenDate(taskId);
            model.addAttribute("taskBean",taskBean);
            model.addAttribute("commentBeans",commentBeans);

            return "item/billDetail";
        }else {
            return "redirect:/ecps/console/login.jsp";
        }
    }


    @RequestMapping("doTask.do")
    public String doTask(String assignee, String taskId, String comment, String outcome, HttpSession session, Model model){
        User user = (User) session.getAttribute("user");
        if(user != null){
            leaveBillService.completeTask(taskId, comment, user.getUserId(), outcome);
            return "redirect:listBill.do?assignee="+assignee+"&state=1";
        }else{
            return "redirect:/ecps/console/login.jsp";
        }
    }

    @InitBinder
    public void initBind(ServletRequestDataBinder dataBinder){
        dataBinder.registerCustomEditor(Date.class,
                new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
    }




}
