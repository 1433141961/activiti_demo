package com.yzw.service.impl;

import com.yzw.dao.LeaveBillDao;
import com.yzw.dao.UserDao;
import com.yzw.model.*;
import com.yzw.service.FlowService;
import com.yzw.service.LeaveBillService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.task.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class LeaveBillServiceImpl implements LeaveBillService {
   @Autowired
   LeaveBillDao leaveBillDao;

   @Autowired
   FlowService flowService;
   @Autowired
   UserDao userDao;

   @Autowired
   HistoryService historyService;
   @Override
   public void insert(LeaveBill leaveBill,int userId) {
      //将用户id设置到请假单中去
      leaveBill.setUserId(userId);
      leaveBillDao.insert(leaveBill);
      //获得返回的主键（请假单id）
      int billId = leaveBill.getLbId();
      //开启流程实例
      flowService.startProcess(userId,billId);
   }

   public List<TaskBean> selectLeaveTaskBean(Integer userId, String assignee) {
      //获得到不包含业务对象的taskBean
      List<TaskBean> taskBeanList = flowService.selectTaskByUserIdAndAssignee(assignee, userId);
      if (taskBeanList.size() > 0) {
         //把业务对象注入到taskBean中
         for (TaskBean tb : taskBeanList) {
            //获得业务key
            String billId = tb.getBusiessKey();
            LeaveBill bl = leaveBillDao.selectLeaveBillById(Integer.parseInt(billId));
            tb.setLeaveBill(bl);

         }
      }
      return taskBeanList;
   }


   public TaskBean selectTaskBeanByTaskId(int userId,String taskId) {
      TaskBean tb = flowService.selectTaskBeanById(taskId);
      String billId = tb.getBusiessKey();
      LeaveBill bl = leaveBillDao.selectLeaveBillById(Integer.parseInt(billId));
      tb.setLeaveBill(bl);
      return tb;
   }

   @Override
   public void completeTask(String taskId, String comment, Integer userId, String outcome) {
      flowService.completeTask(taskId,comment,userId,outcome);
   }

   /**
    * 查询批注信息
    * @param taskId
    * @return
    */
   @Override
   public List<CommentBean> selectCommentList(String taskId) {
      //获得批注内容
      List<Comment> commentList = flowService.selectCommentList(taskId);
       //获得流程中的历史流程实例信息
      List<CommentBean> commBeanList = new ArrayList<CommentBean>();
      for(Comment comm :commentList){
         CommentBean cb = new CommentBean();
         String userId = comm.getUserId();
            //获得流程中的历史流程实例信息
          List<ProcessInstanceBean> processInstanceBeanList = flowService.selectHistoryProcessInstanceBean(new Integer(userId));
         User user = userDao.selectUserByUserId(new Integer(userId));
         //设置批注信息到自定义的commentBean中
         cb.setComment(comm);
         cb.setName(user.getUserName());;
         commBeanList.add(cb);
      }
      return commBeanList;
   }

    @Override
    public List<ProcessInstanceBean> selectHistoryProcessInstanceBean(Integer userId) {
       //用于存储流程和业务的信息
       List<ProcessInstanceBean> pilist = new ArrayList<>();
       //获得流程中的历史流程实例信息
       List<ProcessInstanceBean> processInstanceBeanList = flowService.selectHistoryProcessInstanceBean(userId);
       for (ProcessInstanceBean processInstanceBean : processInstanceBeanList){
           //获得业务key,也就是billId
           String businessKey = processInstanceBean.getBusinessKey();
           LeaveBill leaveBill = leaveBillDao.selectLeaveBillById(new Integer(businessKey));
           processInstanceBean.setLeaveBill(leaveBill);
           pilist.add(processInstanceBean);
       }
       return pilist;
    }

  /* @Override
   public List<Date> selectCommenDate(String taskId) {
      List<Date> dates = flowService.selectCommentDate(taskId);
      return dates;
   }*/
}