package com.yzw.service.impl;

import com.yzw.dao.UserRoleDao;
import com.yzw.model.UserRole;
import com.yzw.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    UserRoleDao userRoleDao;

    @Override
    public void insertUserRole(UserRole userRole) {
        userRoleDao.insertUserRole(userRole);
    }

    @Override
    public void deleteUserRoleByUserId(int userId) {
        userRoleDao.deleteUserRoleByUserId(userId);
    }

    @Override
    public void grantRole(List<UserRole> userRoles, int userId) {
        userRoleDao.deleteUserRoleByUserId(userId);
        if (userRoles!=null && userRoles.size()>0){
            for (UserRole userRole : userRoles){
                userRoleDao.insertUserRole(userRole);
            }
        }
    }
}