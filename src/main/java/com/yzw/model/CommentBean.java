package com.yzw.model;

import org.activiti.engine.task.Comment;

import java.util.Date;
import java.util.List;

public class CommentBean {
	
	String Name;
	
	Comment comment;

	Date finishDate;

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	public Date getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}
}
