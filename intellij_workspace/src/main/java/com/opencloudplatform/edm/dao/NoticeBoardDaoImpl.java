package com.opencloudplatform.edm.dao;

import com.opencloudplatform.edm.domain.NoticeBoard;
import com.opencloudplatform.edm.domain.QnABoard;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class NoticeBoardDaoImpl implements NoticeBoardDao {

    @Autowired
    SqlSession sqlSession;
    private static String namespace = "com.opencloudplatform.edm.dao.noticeBoardMapper.";
    @Override
    public int registerPost(NoticeBoard noticeBoard) throws Exception {
        return sqlSession.insert(namespace+"insert",noticeBoard);
    }

    @Override
    public List<NoticeBoard> selectAll() throws Exception {
        return sqlSession.selectList(namespace+"select_all");
    }

    @Override
    public int deletePost(int idx) throws Exception {
        return sqlSession.delete(namespace+"delete",idx);
    }

    @Override
    public int updatePost(NoticeBoard noticeBoard) throws Exception {
        return sqlSession.update(namespace+"update",noticeBoard);
    }

    @Override
    public int totalCnt() throws Exception {
        return sqlSession.selectOne(namespace+"totalCnt");
    }

    @Override
    public int deleteAll() throws Exception {
        return sqlSession.delete(namespace+"deleteAll");
    }
    @Override
    public int increaseViewCnt(Integer bno) throws Exception{
        return sqlSession.update(namespace+"viewCnt");
    }
    @Override
    public List<QnABoard> selectNowPage(Map map) throws Exception{
        return sqlSession.selectList(namespace+"selectNowPage");
    }

}
