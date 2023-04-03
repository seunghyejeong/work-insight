package com.opencloudplatform.edm.dao;

import com.opencloudplatform.edm.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class UserDaoImplTest {
    @Autowired
    UserDao userDao;
    @Test
    public void insertUser()throws Exception {
        userDao.deleteAll();
        User user = new User("지원선","wonsunzz@naver.com");
        assertTrue(userDao.insertUser(user)==1);
    }

    @Test
    public void deleteUser()throws Exception{
        userDao.deleteAll();
        User user = new User ("지원선","wonsun@NAVER.COM");
        assertTrue(userDao.insertUser(user)==1);

        User user2 = userDao.selectUser(user.getUsername());
        assertTrue(user2.getUsername().equals(user.getUsername()));
        //assertTrue(user2.equals(user)); 가 안됐던 이유는 index값이 null이어서.
        assertTrue(userDao.deleteUser(user2.getUsername())==1);
    }

    @Test
    public void deleteAll()throws Exception {
        userDao.deleteAll();
    }

    @Test
    public void selectUser()throws Exception {
        userDao.deleteAll();
        User user = new User("정마비", "bamibam@co.kr");
        assertTrue(userDao.insertUser(user)==1);

        User user2 = userDao.selectUser(user.getUsername());
        assertTrue(user.getUsername().equals(user2.getUsername()));

        user2 = userDao.selectUser("abcd");
        assertTrue(user2==null);
    }
}