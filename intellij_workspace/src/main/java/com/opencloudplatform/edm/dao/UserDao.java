package com.opencloudplatform.edm.dao;


import com.opencloudplatform.edm.domain.User;

public interface UserDao {

    int insertUser(User user) throws Exception ;

    int deleteUser(String userName) throws Exception ;

    User selectUser(String userName) throws Exception ;

    int deleteAll() throws Exception;
}
