package com.yzw.model;

import java.io.Serializable;
import java.util.Date;

/**
 * leave_bill
 * @author 
 */
public class LeaveBill implements Serializable {
    private Integer lbId;

    private Integer userId;

    private String lbName;

    private Date beginTime;

    private Integer days;

    private String reason;

    private static final long serialVersionUID = 1L;

    public Integer getLbId() {
        return lbId;
    }

    public void setLbId(Integer lbId) {
        this.lbId = lbId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getLbName() {
        return lbName;
    }

    public void setLbName(String lbName) {
        this.lbName = lbName;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}