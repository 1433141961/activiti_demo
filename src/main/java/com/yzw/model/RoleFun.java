package com.yzw.model;

import java.io.Serializable;

/**
 * role_fun
 * @author 
 */
public class RoleFun implements Serializable {
    private Integer roleId;

    private Integer funId;

    private static final long serialVersionUID = 1L;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getFunId() {
        return funId;
    }

    public void setFunId(Integer funId) {
        this.funId = funId;
    }
}