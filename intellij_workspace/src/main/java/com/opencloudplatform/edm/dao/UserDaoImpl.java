package com.opencloudplatform.edm.dao;

import com.opencloudplatform.edm.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private SqlSession sqlSession;
    private static String namespace = "com.opencloudplatform.edm.dao.userMapper.";


    @Override
    public int insertUser(User user) throws Exception {
        return sqlSession.insert(namespace + "insert",user);
    }

    @Override
    public int deleteUser(String userName) throws Exception {
        return sqlSession.delete(namespace+"delete", userName);
    }

    @Override
    public int deleteAll() throws Exception {
        return sqlSession.delete(namespace+"deleteAll");
    }

    @Override
    public User selectUser(String userName) throws Exception {
        return sqlSession.selectOne(namespace+"select",userName);
    }
}





