package com.yzw.service.impl;

import com.yzw.dao.RoleFunDao;
import com.yzw.model.RoleFun;
import com.yzw.service.RoleFunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.RowId;
import java.util.List;
@Service
public class RoleFunServiceImpl implements RoleFunService {
    @Autowired
    RoleFunDao roleFunDao;
    @Override
    public void grantFun(int roleId, List<RoleFun> roleFuns) {
        roleFunDao.deleteRoleFunByRoleId(roleId);
        if(roleFuns != null && roleFuns.size()>0){
            for (RoleFun roleFun : roleFuns){
                roleFunDao.insertRoleFun(roleFun);
            }

        }
    }

    @Override
    public void insertRoleFun(RoleFun roleFun) {

    }

    @Override
    public void deleteRoleFunByRoleId(int roleId) {
        roleFunDao.deleteRoleFunByRoleId(roleId);
    }

}
