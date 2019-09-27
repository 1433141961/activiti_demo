package com.yzw.service;

import com.yzw.model.CommentBean;
import com.yzw.model.LeaveBill;
import com.yzw.model.ProcessInstanceBean;
import com.yzw.model.TaskBean;

import java.util.Date;
import java.util.List;

public interface LeaveBillService {
   void insert(LeaveBill leaveBill ,int userId);

   public List<TaskBean> selectLeaveTaskBean(Integer userId, String assignee);

   public TaskBean selectTaskBeanByTaskId( int userId , String taskId);

   public void completeTask(String taskId, String comment, Integer userId, String outcome);


   public List<CommentBean> selectCommentList(String taskId);

//   public List<Date> selectCommenDate(String taskId);

   List<ProcessInstanceBean> selectHistoryProcessInstanceBean(Integer userId);
}