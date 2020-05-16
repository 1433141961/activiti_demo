package com.yzw.service.impl;

import com.yzw.dao.RoleDao;
import com.yzw.model.Role;
import com.yzw.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleDao roleDao;
    @Override
    public void save(Role role) {
        roleDao.save(role);
    }

    @Override
    public List<Role> selectAllRole() {
        List<Role> roles = roleDao.selectAllRole();
        return roles;
    }

    @Override
    public List<Role> selectRoleByUserId(int userId) {
        List<Role> roles = roleDao.selectRoleByUserId(userId);
        return roles;
    }

    @Override
    public void deleteByRoleId(Integer roleId) {
        roleDao.deleteByRoleId(roleId);
    }
}
