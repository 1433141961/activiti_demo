package com.yzw.service;

import com.yzw.model.UserRole;

import java.util.List;

public interface UserRoleService {
    void insertUserRole(UserRole userRole);

    void deleteUserRoleByUserId(int userId);

    void grantRole(List<UserRole> userRoles, int userId);
}