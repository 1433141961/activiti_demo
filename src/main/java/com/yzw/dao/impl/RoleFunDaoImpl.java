package com.yzw.dao.impl;

import com.yzw.dao.RoleFunDao;
import com.yzw.model.RoleFun;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class RoleFunDaoImpl extends SqlSessionDaoSupport implements RoleFunDao {
    String ns = "com.yzw.mapper.RoleFunDao.";

    /**
     * 插入
     * @param roleFun
     */
    @Override
    public void insertRoleFun(RoleFun roleFun) {
        getSqlSession().insert(ns+"insertRoleFun",roleFun);

    }

    /**
     * 删除
     * @param roleId
     */
    @Override
    public void deleteRoleFunByRoleId(int roleId) {
        getSqlSession().delete(ns+"deleteRoleFunByRoleId",roleId);
    }
}
