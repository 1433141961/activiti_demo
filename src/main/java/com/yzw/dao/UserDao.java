package com.yzw.dao;

import com.yzw.model.User;

import java.util.List;
import java.util.Map;

public interface UserDao {
    List<User> selectAllUser();

    void insert(User user);

    User selectUserByUserNameAndPassword(Map<String,String> map);

    List<String> selectUrlByUserId(int userId);

    User selectUserByUserId(int userId);

}