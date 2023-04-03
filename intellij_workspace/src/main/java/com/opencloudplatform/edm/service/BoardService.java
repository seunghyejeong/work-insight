package com.opencloudplatform.edm.service;

import com.opencloudplatform.edm.dao.QnABoardDao;
import com.opencloudplatform.edm.domain.QnABoard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface BoardService {
    int getCount() throws Exception;
    int remove(Integer idx, String writer) throws Exception;
    int write(QnABoard qnaBoard) throws Exception;
    List<QnABoard> getList() throws Exception;
    QnABoard read(Integer idx) throws Exception;
    List<QnABoard> getPage(Map map) throws Exception;
    int modify(QnABoard qnaBoard) throws Exception;
//
//    int getSearchResultCnt(SearchCondition sc) throws Exception;
//    List<QnABoard> getSearchResultPage(SearchCondition sc) throws Exception;
}
