package com.opencloudplatform.edm.dao;

import com.opencloudplatform.edm.domain.NoticeBoard;
import com.opencloudplatform.edm.domain.QnABoard;

import java.util.List;
import java.util.Map;

public interface NoticeBoardDao {
    int registerPost(NoticeBoard noticeBoard) throws Exception;
    List<NoticeBoard> selectAll() throws Exception;
    int deletePost(int idx) throws Exception;
    int updatePost(NoticeBoard noticeBoard) throws Exception;
    int totalCnt() throws Exception;
    int deleteAll() throws Exception;
    List<QnABoard> selectNowPage(Map map) throws Exception; //현재 page select


    int increaseViewCnt(Integer bno) throws Exception;
}
