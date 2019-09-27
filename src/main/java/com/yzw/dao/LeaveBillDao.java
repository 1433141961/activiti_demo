package com.yzw.dao;

import com.yzw.model.LeaveBill;

import java.util.List;

public interface LeaveBillDao {
   void insert(LeaveBill leaveBill);

   LeaveBill selectLeaveBillById(int billId);
}