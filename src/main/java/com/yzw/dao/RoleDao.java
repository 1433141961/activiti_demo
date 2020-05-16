package com.yzw.dao;

import com.yzw.model.Role;

import java.util.List;

public interface RoleDao {
    void save(Role role);

    List<Role> selectAllRole();

    List<Role> selectRoleByUserId(int userId);


    void deleteByRoleId(Integer roleId);
}