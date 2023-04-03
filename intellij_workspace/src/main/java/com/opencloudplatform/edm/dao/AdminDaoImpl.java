package com.opencloudplatform.edm.dao;

import com.opencloudplatform.edm.domain.Admin;
import com.opencloudplatform.edm.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AdminDaoImpl implements AdminDao {
    @Autowired
    SqlSession sqlSession;
    private static String namespace = "com.opencloudplatform.edm.dao.adminMapper.";
    @Override
    public int insertUser(Admin admin) throws Exception {
        return sqlSession.insert(namespace + "insert",admin);
    }

    @Override
    public int deleteUser(String adminId) throws Exception {
        return sqlSession.delete(namespace+"delete", adminId);
    }

    @Override
    public Admin selectUser(String adminId) throws Exception {
        return sqlSession.selectOne(namespace+"select",adminId);
    }

    @Override
    public int deleteAll() throws Exception {
        return sqlSession.delete(namespace+"deleteAll");
    }
}
