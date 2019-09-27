package com.yzw.dao.impl;

import com.yzw.dao.UserDao;
import com.yzw.model.User;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class UserDaoImpl extends SqlSessionDaoSupport implements UserDao {
    String ns = "com.yzw.mapper.UserDao.";

    @Override
    public List<User> selectAllUser() {
        List<User>  users=getSqlSession().selectList(ns+"selectAllUser");
        return users;
    }

    @Override
    public void insert(User user) {
        getSqlSession().insert(ns+"insert",user);
    }

    @Override
    public User selectUserByUserNameAndPassword(Map<String, String> map) {
        User user = getSqlSession().selectOne(ns+"selectUserByUserNameAndPassword",map);
        return user;
    }

    @Override
    public List<String> selectUrlByUserId(int userId) {
        List<String> urls = getSqlSession().selectList(ns+"selectUrlByUserId",userId);
        return urls;
    }

    @Override
    public User selectUserByUserId(int userId) {
        User user = getSqlSession().selectOne(ns+"selectUserByUserId",userId);
        return user;
    }


}