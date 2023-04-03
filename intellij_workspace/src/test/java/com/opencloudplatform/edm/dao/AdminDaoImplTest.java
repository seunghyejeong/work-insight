package com.opencloudplatform.edm.dao;

import com.opencloudplatform.edm.domain.Admin;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class AdminDaoImplTest {
    @Autowired
    AdminDao adminDao;
    @Test
    public void insertUser() throws Exception{
        Admin admin = new Admin("paasta-ta","pstamaster19!","관리자");
        assertTrue(adminDao.insertUser(admin)==1);
    }

    @Test
    public void deleteUser() {
    }

    @Test
    public void selectUser() throws Exception {
        assertTrue(adminDao.selectUser("paasta-ta")!=null);
    }

    @Test
    public void deleteAll() {
    }
}