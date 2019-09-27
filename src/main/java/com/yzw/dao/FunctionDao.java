package com.yzw.dao;

import com.yzw.model.Function;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface FunctionDao {
    void saveFun(Function function);

     List<Function> selectAllFun();


     List<Function> selectFunByRoleId(Integer roleId);

     void deleteFunByFunId(int funId);
}