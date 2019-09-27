package com.yzw.service.impl;

import com.yzw.dao.FunctionDao;
import com.yzw.model.Function;
import com.yzw.service.FunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FunctionServiceImpl implements FunctionService {
    @Autowired
    FunctionDao functionDao;

    @Override
    public void saveFun(Function function) {
        functionDao.saveFun(function);
    }

    @Override
    public List<Function> selectAllFun() {
        List<Function> functions = functionDao.selectAllFun();
        return functions;
    }

    @Override
    public List<Function> selectFunByRoleId(Integer roleId) {
        List<Function> functions = functionDao.selectFunByRoleId(roleId);
        return functions;
    }

    @Override
    public void deleteFunByFunId(int funId) {
        functionDao.deleteFunByFunId(funId);
    }
}
