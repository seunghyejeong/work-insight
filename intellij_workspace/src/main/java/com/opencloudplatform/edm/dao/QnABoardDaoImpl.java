package com.opencloudplatform.edm.dao;

import com.opencloudplatform.edm.domain.QnABoard;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class QnABoardDaoImpl implements QnABoardDao {
    @Autowired
    SqlSession sqlSession;
    private static String namespace = "com.opencloudplatform.edm.dao.qnaBoardMapper.";

    @Override
    public int registerPost(QnABoard qnaBoard) throws Exception {
         return sqlSession.insert(namespace+"insert",qnaBoard);
    }
    @Override
    public List<QnABoard> selectAll() throws Exception{
        return sqlSession.selectList(namespace+"select_all");
    }
    @Override
    public List<QnABoard> selectPostPublic() throws Exception {
        return sqlSession.selectList(namespace+"select_public");
    }
    @Override
    public List<QnABoard> selectPostPrivate (){
        return sqlSession.selectList(namespace+"select_private");
    }

    @Override
    public List<QnABoard> selectNotReply() throws Exception {
        return sqlSession.selectList(namespace+"select_notReply");
    }

    @Override
    public QnABoard select(Integer idx) throws Exception{ //하나의 게시글만
        return sqlSession.selectOne(namespace+"select_one",idx);
    }
    @Override
    public List<QnABoard> getPage(Map map) throws Exception{
        return sqlSession.selectList(namespace+"selectNowPage",map);
    }
    @Override
    public int deletePost(int idx, String writer){
        Map map = new HashMap();
        map.put("idx",idx);
        map.put("writer", writer);
        return sqlSession.delete(namespace+"delete",map);
    }

    @Override
    public int updatePost(QnABoard qnaBoard){
        return sqlSession.update(namespace+"update",qnaBoard);
    }
//    @Override
//    public int publicOrNot(int publicOrNot) {
//        return sqlSession.selectOne(namespace+"publicOrNot",publicOrNot);
//        // public == true == 1
//        // private == false == 0
//    }
    @Override
    public int totalCnt(){
        return sqlSession.selectOne(namespace+"totalCnt");
    }
    @Override
    public int deleteAll(){
        return sqlSession.delete(namespace+"deleteAll");
    }

    @Override
    public int count() throws Exception {
        return sqlSession.selectOne(namespace+"count");
    } // T selectOne(String statement)

    @Override
    public int increaseViewCnt(Integer idx) throws Exception {
        return sqlSession.update(namespace+"increaseViewCnt", idx);
    } // int update(String statement, Object parameter)

//    @Override
//    public int searchResultCnt(SearchCondition sc) throws Exception {
//        System.out.println("sc in searchResultCnt() = " + sc);
//        System.out.println("session = " + sqlSession);
//        return sqlSession.selectOne(namespace+"searchResultCnt", sc);
//    } // T selectOne(String statement, Object parameter)
//
//    @Override
//    public List<QnABoard> searchSelectPage(SearchCondition sc) throws Exception {
//        return sqlSession.selectList(namespace+"searchSelectPage", sc);
//    } // List<E> selectList(String statement, Object parameter)
}

