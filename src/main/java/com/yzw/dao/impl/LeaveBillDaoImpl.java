package com.yzw.dao.impl;

import com.yzw.dao.LeaveBillDao;
import com.yzw.model.LeaveBill;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LeaveBillDaoImpl extends SqlSessionDaoSupport implements LeaveBillDao {
   String ns = "com.yzw.mapper.LeaveBillDao.";
   @Override
   public void insert(LeaveBill leaveBill) {
      getSqlSession().insert(ns+"insert",leaveBill);
   }

   @Override
   public LeaveBill selectLeaveBillById(int billId) {
      LeaveBill leaveBill = getSqlSession().selectOne(ns+"selectLeaveBillById",billId);
      return leaveBill;
   }
}