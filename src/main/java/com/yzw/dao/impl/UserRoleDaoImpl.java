package com.yzw.dao.impl;

import com.yzw.dao.UserRoleDao;
import com.yzw.model.UserRole;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class UserRoleDaoImpl extends SqlSessionDaoSupport implements UserRoleDao {

    String ns = "com.yzw.mapper.UserRoleDao.";
    @Override
    public void insertUserRole(UserRole userRole) {
        getSqlSession().insert(ns+"insertUserRole",userRole);
    }

    @Override
    public void deleteUserRoleByUserId(int userId) {
        getSqlSession().delete(ns+"deleteUserRoleByUserId",userId);
    }
}