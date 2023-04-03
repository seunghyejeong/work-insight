package com.opencloudplatform.edm.dao;

import com.opencloudplatform.edm.domain.QnABoard;

import java.util.List;
import java.util.Map;

public interface QnABoardDao {



    int registerPost(QnABoard qnaBoard) throws Exception;   // 게시할때 public or not을 선택할 수 있음

    List<QnABoard> selectPostPublic() throws Exception; // 공개글만 select
    List<QnABoard> selectPostPrivate() throws Exception; // 비공개글만 select
    List<QnABoard> selectAll() throws Exception;    //게시글 모두 select
    List<QnABoard> selectNotReply() throws Exception;   // 답변대기상태 게시글 select
    int deletePost(int idx, String writer) throws Exception; // 게시글 지우기
    int updatePost(QnABoard qnaBoard) throws Exception; // 게시글 수정하기
 // int publicOrNot(int publicOrNot) throws Exception;
    int totalCnt() throws Exception; // 게시글 총 갯수
    int deleteAll() throws Exception; // 게시글 모두 지우기

    QnABoard select(Integer idx) throws Exception; // 하나의 게시글 select
    List<QnABoard> getPage(Map map) throws Exception; //현재 page select
    int count() throws Exception; //
    int increaseViewCnt(Integer idx) throws Exception; //조회수 증가
//    int searchResultCnt(SearchCondition sc) throws Exception;
//    List<QnABoard> searchSelectPage(SearchCondition sc) throws Exception;
}
