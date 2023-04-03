 package com.opencloudplatform.edm.dao;

import com.mysql.cj.protocol.x.Notice;
import com.opencloudplatform.edm.domain.NoticeBoard;
import com.opencloudplatform.edm.domain.QnABoard;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class NoticeBoardDaoImplTest {
    @Autowired
    NoticeBoardDao noticeBoardDao;


    @Test
    public void insertTest() throws Exception{
        noticeBoardDao.deleteAll();
        for (int i = 1; i < 220; i++) {
            NoticeBoard noticeBoard = new NoticeBoard("notice1" + i,
                    "notice content", "admin", "1234");
            noticeBoardDao.registerPost(noticeBoard);
        }

    }
    @Test
    public void registerPost() throws Exception {
        NoticeBoard noticeBoard = new NoticeBoard("notice1", "notice content", "admin", "1234");
        assertTrue(noticeBoardDao.registerPost(noticeBoard)==1);
    }

    @Test
    public void selectAll() throws Exception{
        noticeBoardDao.deleteAll();
        List<NoticeBoard> list = noticeBoardDao.selectAll();
        assertTrue(list.size()==0);

    }

    @Test
    public void deletePost() throws Exception{
        noticeBoardDao.deleteAll();
//        NoticeBoard noticeBoard = new NoticeBoard("delete","delete content","seunghye","1313");
//        assertTrue(noticeBoardDao.registerPost(noticeBoard)==1);
        NoticeBoard noticeBoard2 = new NoticeBoard("delete2","delete content2","seunghye2","1313");
        assertTrue(noticeBoardDao.registerPost(noticeBoard2)==1);
        List<NoticeBoard> list = noticeBoardDao.selectAll();
        Integer idx = list.get(0).getIdx();

//        for(int i=0; i< list.size(); i++){
//            result = list.get(i).getIdx();
//            System.out.println("index = " + result);
//        }

//        for(int i = 0; i < list.size(); i++) {
//            if(list.get(i).getIdx() == indexToDelete) {
//                list.remove(i);
//                break;
//            }
//        }
        assertTrue(noticeBoardDao.deletePost(idx)==1);

    }

    @Test
    public void updatePost() throws Exception {
        noticeBoardDao.deleteAll();
        NoticeBoard noticeBoard = new NoticeBoard("notice", "notice", "notice", "1234");
        assertTrue(noticeBoardDao.registerPost(noticeBoard)==1);

// These two lines are likely getting the index (or ID) of a board from a database.
// The first line seems to be selecting all boards from the table and then getting the index of the first board in the list.
// The second line is likely a custom method in the qnaBoardDao class that directly retrieves the index of the board.
// If the qnaBoardDao class already has a method to retrieve the index of a board,
// it is generally more efficient to use that method instead of fetching all the boards from the table and then getting the index of the first board.

//      Integer boardIdx = qnaBoardDao.selectAll().get(0).getIdx();
//      board.setIdx(boardIdx);
        Integer index = noticeBoard.getIdx();
        noticeBoard.setIdx(index);
        noticeBoard.setTitle("notice modify");
        noticeBoard.setWriter("admin");
        assertTrue(noticeBoardDao.updatePost(noticeBoard)==1);

    }
    @Test
    public void totalCnt() throws Exception {
        noticeBoardDao.deleteAll();
        NoticeBoard noticeBoard = new NoticeBoard("notice", "notice", "notice", "1234");
        assertTrue(noticeBoardDao.registerPost(noticeBoard)==1);
        NoticeBoard noticeBoard2 = new NoticeBoard("notice", "notice", "notice", "1234");
        assertTrue(noticeBoardDao.registerPost(noticeBoard2)==1);
        NoticeBoard noticeBoard3 = new NoticeBoard("notice", "notice", "notice", "1234");
        assertTrue(noticeBoardDao.registerPost(noticeBoard3)==1);

        assertTrue(noticeBoardDao.totalCnt()==3);
    }
}