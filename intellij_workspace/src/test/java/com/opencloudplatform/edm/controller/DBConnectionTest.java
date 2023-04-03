package com.opencloudplatform.edm.controller;

import com.opencloudplatform.edm.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class DBConnectionTest {
    @Autowired
    DataSource ds;

    @Test
    public void insertUserTest() throws Exception{
        deleteUserAll();
        User user = new User("jeongseunghye", "jsh900924@naver.com");
        int rowCnt = insertUser(user);
        assertTrue(rowCnt==1);
    }
    @Test
    public void deleteUserTest() throws Exception{
        deleteUserAll();
        int rowCnt = deleteUser("");

        assertTrue(rowCnt==0);

        User user = new User("abc", "aaaa@aaa.com");
        rowCnt = insertUser(user);
        assertTrue(rowCnt==1);


        rowCnt=deleteUser(user.getUsername());
        assertTrue(rowCnt==1);


        assertTrue(selectUser(user.getUseridx())==null);
    }
    @Test
    public void selectUserTest() throws Exception{
        User user = new User("정바미","jeongbami@bam.bma");
        int rowCnt = insertUser(user);
        User user2 = selectUser(user.getUseridx());
        assertTrue(user.getUsername().equals("정바미"));

    }







    // CRUD =========================================================
    public int updateUser(User user) throws Exception {
        return 0;
    }

    public User selectUser(int useridx) throws Exception{
        Connection conn = ds.getConnection();
        String sql = "select * from user where useridx = ?";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1,useridx);

        ResultSet rs = ps.executeQuery();

        if(rs.next()){
            User user = new User();
            user.setUsername(rs.getString(2));
            user.setUseremail(rs.getString(3));

            return user;
        }
        return null;

    }
    private int deleteUserAll() throws Exception{
        Connection conn = ds.getConnection();
        String sql = "delete from user";

        PreparedStatement ps = conn.prepareStatement(sql);
        int rowCnt = ps.executeUpdate();

        return rowCnt;
    }
    public int deleteUser(String username) throws Exception {
        Connection conn = ds.getConnection();

        String sql = "delete from user where username = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,username);
//        int rowCnt = ps.executeUpdate(); //insert delete update
//
//        return rowCnt;
        return ps.executeUpdate();
    }

    public int insertUser(User user) throws Exception {
        Connection conn = ds.getConnection();
        deleteUserAll();
        String sql = "insert into user values (?,?,?)";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1,user.getUseridx());
        ps.setString(2,user.getUsername());
        ps.setString(3,user.getUseremail());

        int rowCnt = ps.executeUpdate();


        return rowCnt;
    }

    @Test
    public void springJdbcConnectionTest() throws Exception {
//        ApplicationContext ac = new GenericXmlApplicationContext("file:src/main/webapp/WEB-INF/spring/**/root-context.xml");
//        DataSource ds = ac.getBean(DataSource.class);

        Connection conn = ds.getConnection(); // 데이터베이스의 연결을 얻는다.

        System.out.println("conn = " + conn);
        assertTrue(conn!=null); // 괄호 안의 조건식이 true면, 테스트 성공, 아니면 실패
    }
}