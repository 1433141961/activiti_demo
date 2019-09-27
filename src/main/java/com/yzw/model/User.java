package com.yzw.model;

import java.io.Serializable;
import java.util.List;

/**
 * user
 * @author 
 */
public class User implements Serializable {
    private Integer userId;

    private String userName;

    private String password;

    private Integer telNum;

    private String eMaol;

    private List<String> urlList;

    public List<String> getUrlList() {
        return urlList;
    }

    public void setUrlList(List<String> urlList) {
        this.urlList = urlList;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    private static final long serialVersionUID = 1L;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getTelNum() {
        return telNum;
    }

    public void setTelNum(Integer telNum) {
        this.telNum = telNum;
    }

    public String geteMaol() {
        return eMaol;
    }

    public void seteMaol(String eMaol) {
        this.eMaol = eMaol;
    }
}