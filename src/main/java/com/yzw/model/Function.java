package com.yzw.model;

import java.io.Serializable;

/**
 * function
 * @author 
 */
public class Function implements Serializable {
    private Integer funId;

    private String funName;

    private String url;

    private String funDesc;

    private static final long serialVersionUID = 1L;

    public Integer getFunId() {
        return funId;
    }

    public void setFunId(Integer funId) {
        this.funId = funId;
    }

    public String getFunName() {
        return funName;
    }

    public void setFunName(String funName) {
        this.funName = funName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFunDesc() {
        return funDesc;
    }

    public void setFunDesc(String funDesc) {
        this.funDesc = funDesc;
    }
}