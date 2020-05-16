package com.yzw.service;

import com.yzw.model.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    List<User> selectAllUser();

    void insert(User user);

    User selectUserByUserNameAndPassword(Map<String,String> map);

    List<String> selectUrlByUserId(int userId);

    void deleteByUserId(Integer id);
}