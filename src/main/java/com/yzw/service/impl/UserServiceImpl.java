package com.yzw.service.impl;

import com.yzw.dao.UserDao;
import com.yzw.model.User;
import com.yzw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Override
    public List<User> selectAllUser() {
        List<User> users = userDao.selectAllUser();
        return users;
    }

    @Override
    public void insert(User user) {
        userDao.insert(user);
    }

    @Override
    public User selectUserByUserNameAndPassword(Map<String, String> map) {
        User user = userDao.selectUserByUserNameAndPassword(map);
        return user;
    }

    @Override
    public List<String> selectUrlByUserId(int userId) {
        List<String> urls = userDao.selectUrlByUserId(userId);
        return urls;
    }
}