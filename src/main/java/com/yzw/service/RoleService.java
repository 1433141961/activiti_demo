package com.yzw.service;

import com.yzw.model.Role;

import java.util.List;

public interface RoleService {
    void save(Role role);

    List<Role> selectAllRole();

    List<Role> selectRoleByUserId(int userId);

    void deleteByRoleId(Integer roleId);
}
