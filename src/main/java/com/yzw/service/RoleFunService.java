package com.yzw.service;

import com.yzw.model.RoleFun;

import java.util.List;

public interface RoleFunService {
    void grantFun(int roleId, List<RoleFun> roleFuns);

    void insertRoleFun(RoleFun roleFun);

    void deleteRoleFunByRoleId(int roleId);
}
