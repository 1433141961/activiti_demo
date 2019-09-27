package com.yzw.dao;

import com.yzw.model.RoleFun;

import java.util.List;

public interface RoleFunDao {
    void insertRoleFun(RoleFun roleFun);

    void deleteRoleFunByRoleId(int roleId);
}