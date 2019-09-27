package com.yzw.dao.impl;

import com.yzw.dao.RoleDao;
import com.yzw.model.Role;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class RoleDaoImpl extends SqlSessionDaoSupport implements RoleDao {
    String ns = "com.yzw.mapper.RoleDao.";
    @Override
    public void save(Role role) {
        getSqlSession().insert(ns+"insertRole",role);
    }

    @Override
    public List<Role> selectAllRole() {
        List<Role> roles = getSqlSession().selectList(ns+"selectAllRole");
        return roles;
    }

    @Override
    public List<Role> selectRoleByUserId(int userId) {
        List<Role> roles = getSqlSession().selectList(ns+"selectRoleByUserId",userId);
        return roles;
    }
}
