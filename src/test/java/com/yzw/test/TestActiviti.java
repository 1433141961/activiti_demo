package com.yzw.test;

import com.yzw.service.FlowService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TestActiviti {
    @Autowired
    FlowService flowService;
    @Test
    public void deploy(){
        flowService.deploy();
    }
    @Test
    public void undeploy(){
        flowService.undeploy("1601");
    }
}
