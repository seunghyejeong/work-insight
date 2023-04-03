package com.opencloudplatform.edm.service;

import com.opencloudplatform.edm.dao.QnABoardDao;
import com.opencloudplatform.edm.domain.QnABoard;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class BoardServiceImpl implements BoardService {
    @Autowired
    QnABoardDao qnaBoardDao;

    @Override
    public int getCount() throws Exception {
        return qnaBoardDao.totalCnt();
    }

    @Override
    public int remove(Integer idx, String writer) throws Exception {
        return qnaBoardDao.deletePost(idx, writer);
    }

    @Override
    public int write(QnABoard qnaBoard) throws Exception {
        return qnaBoardDao.registerPost(qnaBoard);
    }

    @Override
    public List<QnABoard> getList() throws Exception {
        return qnaBoardDao.selectAll();
    }

    @Override
    public QnABoard read(Integer idx) throws Exception {
        QnABoard qnaBoard = qnaBoardDao.select(idx);
        qnaBoardDao.increaseViewCnt(idx);

        return qnaBoard;
    }

    @Override
    public List<QnABoard> getPage(Map map) throws Exception {
        return qnaBoardDao.getPage(map);
    }

    @Override
    public int modify(QnABoard qnaBoard) throws Exception {
        return qnaBoardDao.updatePost(qnaBoard);
    }

//    @Override
//    public int getSearchResultCnt(SearchCondition sc) throws Exception {
//        return qnaBoardDao.searchResultCnt(sc);
//    }
//
//    @Override
//    public List<QnABoard> getSearchResultPage(SearchCondition sc) throws Exception {
//        return qnaBoardDao.searchSelectPage(sc);
//    }
}