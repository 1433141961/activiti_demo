package com.yzw.service;

import com.yzw.model.ProcessInstanceBean;
import com.yzw.model.TaskBean;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.task.Comment;

import java.util.Date;
import java.util.List;

public interface FlowService {
    void deploy();

    /**
     * 删除部署的流程
     */
    void undeploy(String deployId);

    void startProcess(int userId,int billId);

    List<TaskBean> selectTaskByUserIdAndAssignee(String assignee,
                                                        Integer userId);

    String getTaskIncome(String taskId);

    List<String> selectTaskOutcomes(String taskId);

    List<String> selectAcitvityOutcomes(HistoricActivityInstance hai);

    List<String> selectTaskIncomes(String taskId);

    TaskBean selectTaskBeanById(String taskId);

    public void completeTask(String taskId, String comment, Integer userId, String outcome);


    public List<Comment> selectCommentList(String taskId);
//    public List<Date> selectCommentDate(String taskId);

    /**
     * 查询历史的流程实例
     * @param userId
     * @return
     */
    public List<ProcessInstanceBean> selectHistoryProcessInstanceBean(Integer userId);

}
