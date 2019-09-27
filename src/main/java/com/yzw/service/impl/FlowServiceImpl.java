package com.yzw.service.impl;

import com.yzw.model.ProcessInstanceBean;
import com.yzw.model.TaskBean;
import com.yzw.service.FlowService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FlowServiceImpl implements FlowService {
    @Autowired
    RepositoryService repositoryService;

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    TaskService taskService;

    @Autowired
    HistoryService historyService;

    /**
     * 部署流程
     */
    @Override
    public void deploy() {
        DeploymentBuilder deploymentBuilder = repositoryService.createDeployment();
        deploymentBuilder.addClasspathResource("diagrams/leave_bill.bpmn")
                .addClasspathResource("diagrams/leave_bill.png");
        deploymentBuilder.name("请假单")
                .category("请假的");
        deploymentBuilder.deploy();
    }

    @Override
    public void undeploy(String deployId) {
        repositoryService.deleteDeployment(deployId,true);
    }

    /**
     * userId存入流程变量，billId存入业务key（表act-hi-proccinst中的business_key列）
     * 这样子做是为了让业务和流程关联起来
     *
     * @param userId
     * @param billId
     */
    @Override
    public void startProcess(int userId, int billId) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        runtimeService.startProcessInstanceByKey("leave_bill", billId + "", map);
    }


    public List<TaskBean> selectTaskByUserIdAndAssignee(String assignee,
                                                        Integer userId) {
        List<TaskBean> taskBeanList = new ArrayList<TaskBean>();
        List<Task> taskList =new ArrayList<>();
        //老师这里有误，把employee写错了写成了emplonee，所以导致了查询所有的请假单
        if ("employee".equals(assignee)) {
            taskList = taskService.createTaskQuery()
                    .processDefinitionKey("leave_bill")
                    //.processDefinitionId("leave_manage:2:204")
                    .taskAssignee(assignee)
                    .processVariableValueEquals("userId", userId)
                    .orderByTaskCreateTime()
                    .desc()
                    .list();
        } else {
            taskList = taskService.createTaskQuery()
                    .processDefinitionKey("leave_bill")
                    // .processDefinitionId("leave_manage:2:204")
                    .taskAssignee(assignee)
                    .orderByTaskCreateTime()
                    .desc()
                    .list();
        }

        ProcessInstanceQuery processInstanceQuery = runtimeService
                .createProcessInstanceQuery()
                .processDefinitionKey("leave_bill");

        for (Task task : taskList) {
            TaskBean taskBean = new TaskBean();
            taskBean.setTaskId(task.getId());
            taskBean.setTaskName(task.getName());
            //获得当前任务所在的流程实例
            ProcessInstance pi = processInstanceQuery
                    .processInstanceId(task.getProcessInstanceId()).singleResult();
            //获得到当前流程实例中的业务键（请假单Id），注入到业务Bean中
            taskBean.setBusiessKey(pi.getBusinessKey());

            String income = this.getTaskIncome(task.getId());

            taskBean.setIncomeing(income);
            taskBean.setAssignee(task.getAssignee());

            taskBeanList.add(taskBean);
        }


        return taskBeanList;
    }

    /**
     * 获得当前节点的指定来路
     *
     * @param taskId
     * @return
     */
    public String getTaskIncome(String taskId) {
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        List<HistoricActivityInstance> historicActivityInstanceList =
                historyService
                        .createHistoricActivityInstanceQuery()
                        .processInstanceId(task.getProcessInstanceId())
                        .finished()
                        .orderByHistoricActivityInstanceEndTime()
                        .desc()
                        .list();
        HistoricActivityInstance hai = null;
        List<String> outcomeList = null;
        List<String> incomeList = null;
        if (historicActivityInstanceList.size() > 0) {
            hai = historicActivityInstanceList.get(0);
            outcomeList = this.selectAcitvityOutcomes(hai);

        }
        incomeList = this.selectTaskIncomes(taskId);
        String income = null;
        for (String incomes : incomeList) {
            for (String outcomes : outcomeList) {
                if (incomes.equals(outcomes)) {
                    income = outcomes;
                    break;
                }
            }
        }

        return income;
    }

    /**
     * 获得当前节点的所有出路
     */
    public List<String> selectTaskOutcomes(String taskId) {
        List<String> outcomeList = new ArrayList<String>();
        //根据任务的id来查询任务对象
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        //获得当前流程实例
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processDefinitionKey("leave_bill")
                .processInstanceId(task.getProcessInstanceId()).singleResult();
        //获得流程定义的实体对象
        ProcessDefinitionEntity processDefinitionEntity = (ProcessDefinitionEntity) repositoryService.getProcessDefinition(task.getProcessDefinitionId());
        //获得当前正在活动的节点id
        String activityId = processInstance.getActivityId();
        ActivityImpl ai = processDefinitionEntity.findActivity(activityId);
        List<PvmTransition> pvmList = ai.getOutgoingTransitions();
        for (PvmTransition pvm : pvmList) {
            String name = (String) pvm.getProperty("name");
            outcomeList.add(name);
        }
        return outcomeList;
    }

    /**
     * 获得所有的活动的出路（包括开始节点）
     * 传入的是历史活动节点的实例
     *
     * @param hai
     * @return
     */
    public List<String> selectAcitvityOutcomes(HistoricActivityInstance hai) {
        List<String> outcomeList = new ArrayList<String>();
        //查询流程定义的实体对象
        ProcessDefinitionEntity processDefinitionEntity =
                (ProcessDefinitionEntity) repositoryService
                        .getProcessDefinition(hai.getProcessDefinitionId());
        //获得当前正在活动的节点id
        ActivityImpl ai = processDefinitionEntity.findActivity(hai.getActivityId());
        List<PvmTransition> pvmList = ai.getOutgoingTransitions();
        for (PvmTransition pvm : pvmList) {
            String name = (String) pvm.getProperty("name");
            outcomeList.add(name);
        }
        return outcomeList;
    }

    /**
     * 获得当前任务的来路
     */
    public List<String> selectTaskIncomes(String taskId) {

        List<String> incomingList = new ArrayList<String>();
        //根据任务的id来查询任务对象
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        //获得当前流程实例
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processDefinitionKey("leave_bill")
                .processInstanceId(task.getProcessInstanceId()).singleResult();
        //获得流程定义的实体对象
        ProcessDefinitionEntity processDefinitionEntity = (ProcessDefinitionEntity) repositoryService.getProcessDefinition(task.getProcessDefinitionId());
        //获得当前正在活动的节点id
        String activityId = processInstance.getActivityId();
        ActivityImpl ai = processDefinitionEntity.findActivity(activityId);
        List<PvmTransition> pvmList = ai.getIncomingTransitions();
        for (PvmTransition pvm : pvmList) {
            String name = (String) pvm.getProperty("name");
            incomingList.add(name);
        }
        return incomingList;
    }

    public TaskBean selectTaskBeanById(String taskId) {

        TaskBean taskBean = new TaskBean();
        //根据taskid查询task对象
        Task task = taskService.createTaskQuery()
                .processDefinitionKey("leave_bill")
                .taskId(taskId).singleResult();

        ProcessInstanceQuery processInstanceQuery = runtimeService
                .createProcessInstanceQuery()
                .processDefinitionKey("leave_bill");

        taskBean.setTaskId(task.getId());
        taskBean.setTaskName(task.getName());
        //获得当前任务所在的流程实例
        ProcessInstance pi = processInstanceQuery
                .processInstanceId(task.getProcessInstanceId()).singleResult();
        //获得到当前流程实例中的业务键（请假单Id），注入到业务Bean中
        taskBean.setBusiessKey(pi.getBusinessKey());
        taskBean.setAssignee(task.getAssignee());
        //获得当前任务所有要往出走的线
        List<String> outcomes = this.selectTaskOutcomes(taskId);
        taskBean.setOutcomeList(outcomes);
        return taskBean;
    }

    /**
     * 完成任务
     * @param taskId
     * @param comment
     * @param userId
     * @param outcome
     */

    @Override
    public void completeTask(String taskId, String comment, Integer userId, String outcome) {
        //获得任务对象
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();

        //设置批注的添加人
        Authentication.setAuthenticatedUserId(userId+"");
        taskService.addComment(taskId, task.getProcessInstanceId(), comment);

        if(outcome == null){
            taskService.complete(taskId);
        }else{
            //设置outcome流程执行的路线
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("outcome", outcome);
            taskService.complete(taskId, map);
        }

    }

    @Override
    public List<Comment> selectCommentList(String taskId) {
        List<Comment> commentsList = new ArrayList<Comment>();
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        List<HistoricActivityInstance> haiList = historyService
                .createHistoricActivityInstanceQuery()
                .processInstanceId(task.getProcessInstanceId())
                .activityType("userTask")
                .orderByHistoricActivityInstanceEndTime()
                .desc()
                .list();
        for(HistoricActivityInstance hai : haiList){
            List<Comment> commList = taskService.getTaskComments(hai.getTaskId());
            commentsList.addAll(commList);
        }
        return commentsList;
    }

    /*@Override
    public List<Date> selectCommentDate(String taskId) {
        List<Date> dates = new ArrayList<>();
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        List<HistoricActivityInstance> haiList = historyService
                .createHistoricActivityInstanceQuery()
                .processInstanceId(task.getProcessInstanceId())
                .activityType("userTask")
                .orderByHistoricActivityInstanceEndTime()
                .desc()
                .list();
        for(HistoricActivityInstance hai : haiList){
            dates.add(hai.getEndTime());
        }
        return dates;
    }*/

    @Override
    public List<ProcessInstanceBean> selectHistoryProcessInstanceBean(Integer userId) {
        List<ProcessInstanceBean> fibList = new ArrayList<ProcessInstanceBean>();

        List<HistoricProcessInstance> hpiList = historyService.createHistoricProcessInstanceQuery()
                //.processDefinitionId("leave_manage:2:204")
                .processDefinitionKey("leave_bill")
                .variableValueEquals("userId", userId)
                .finished()
                .orderByProcessInstanceEndTime()
                .desc()
                .list();

        for(HistoricProcessInstance hpi : hpiList){
            String billId = hpi.getBusinessKey();
            ProcessInstanceBean fib = new ProcessInstanceBean();
            fib.setBusinessKey(billId);
            fib.setStartTime(hpi.getStartTime());
            fib.setFinishDate(hpi.getEndTime());
//            fib.setTaskId(new Integer(task.getId()));
            fibList.add(fib);
        }

        return fibList;
    }
}
