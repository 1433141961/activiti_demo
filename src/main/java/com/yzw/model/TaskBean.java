package com.yzw.model;

import java.util.List;

public class TaskBean {
	
	
	private String taskId;
	
	private String taskName;
	
	/**
	 * 任务的办理人
	 */
	private String assignee;
	/**
	 * 业务键，存储的是请假单的id
	 */
	private String busiessKey;
	/**
	 * 当前任务的来路
	 */
	private String incomeing;
	/**
	 * 当前任务的所有出路
	 */
	private List<String> outcomeList;
	/**
	 * 业务的对象
	 */
	private LeaveBill leaveBill;

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getBusiessKey() {
		return busiessKey;
	}

	public void setBusiessKey(String busiessKey) {
		this.busiessKey = busiessKey;
	}

	public String getIncomeing() {
		return incomeing;
	}

	public void setIncomeing(String incomeing) {
		this.incomeing = incomeing;
	}

	public List<String> getOutcomeList() {
		return outcomeList;
	}

	public void setOutcomeList(List<String> outcomeList) {
		this.outcomeList = outcomeList;
	}

	public LeaveBill getLeaveBill() {
		return leaveBill;
	}

	public void setLeaveBill(LeaveBill leaveBill) {
		this.leaveBill = leaveBill;
	}
	
	
	

}
