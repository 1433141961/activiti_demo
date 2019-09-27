package com.yzw.dao;

import com.yzw.model.UserRole;

public interface UserRoleDao {
    void insertUserRole(UserRole userRole);

    void deleteUserRoleByUserId(int userId);
}