package com.yzw.util;



import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

import java.io.Serializable;
public class LeaveTaskListener implements TaskListener{


    private static final long serialVersionUID = 8186211914894458539L;

    @Override
    public void notify(DelegateTask delegateTask) {
        String taskKey = delegateTask.getTaskDefinitionKey();
        if(taskKey.equals("leave_apply")){
            delegateTask.setAssignee("employee");
        }
        if(taskKey.equals("manager_audit")){
            delegateTask.setAssignee("manager");
        }
        if(taskKey.equals("boss_audit")){
            delegateTask.setAssignee("boss");
        }
    }
}
