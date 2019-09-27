package com.yzw.service;

import com.yzw.model.Function;

import java.util.List;

public interface FunctionService {
    void saveFun(Function function);
    List<Function> selectAllFun();

    List<Function> selectFunByRoleId(Integer roleId);

    void deleteFunByFunId(int funId);
}
