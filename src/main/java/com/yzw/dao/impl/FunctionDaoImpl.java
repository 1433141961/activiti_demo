package com.yzw.dao.impl;

import com.yzw.dao.FunctionDao;
import com.yzw.model.Function;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class FunctionDaoImpl extends SqlSessionDaoSupport implements FunctionDao {
    String ns = "com.yzw.mapper.FunctionDao.";

    @Override
    public void saveFun(Function function) {
        getSqlSession().insert(ns+"insertFun",function);
    }

    @Override
    public List<Function> selectAllFun() {
        List<Function> functions = getSqlSession().selectList(ns+"selectAllFun");
        return functions;
    }

    @Override
    public List<Function> selectFunByRoleId(Integer roleId) {
        List<Function> functions = getSqlSession().selectList(ns+"selectFunByRoleId",roleId);
        return functions;
    }

    @Override
    public void deleteFunByFunId(int funId) {
        getSqlSession().delete(ns+"deleteFunByFunId",funId);
    }
}
