package com.opencloudplatform.edm.dao;

import com.opencloudplatform.edm.domain.Admin;
import com.opencloudplatform.edm.domain.User;

public interface AdminDao {
    int insertUser(Admin admin) throws Exception ;

    int deleteUser(String adminId) throws Exception ;

    Admin selectUser(String adminId) throws Exception ;

    int deleteAll() throws Exception;
}

