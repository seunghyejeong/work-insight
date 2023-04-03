package com.opencloudplatform.edm.dao;

import com.opencloudplatform.edm.domain.QnABoard;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class QnABoardDaoImplTest {
    @Autowired
    QnABoardDao qnaBoardDao;

    @Test
    public void insertTest() throws Exception {
        qnaBoardDao.deleteAll();
        for (int i = 1; i < 220; i++) {
        QnABoard qnaBoard = new QnABoard("title" + i,"content","bami","",0,0);
        qnaBoardDao.registerPost(qnaBoard);
        }

    }

    @Test
    public void registerPost() throws Exception {
        qnaBoardDao.deleteAll();
        QnABoard qnaBoard = new QnABoard("title","content","bami","",0,0);
        assertTrue(qnaBoardDao.registerPost(qnaBoard)==1);
        QnABoard qnaBoard2 = new QnABoard("titlee","contentt","bamii","1234",0,1);
        assertTrue(qnaBoardDao.registerPost(qnaBoard2)==1);
        QnABoard qnaBoard3 = new QnABoard("title3","content3","bami3","4321",0,1);
        assertTrue(qnaBoardDao.registerPost(qnaBoard3)==1);
        QnABoard qnaBoard4 = new QnABoard("title4","content4","bami4","",0,0);
        assertTrue(qnaBoardDao.registerPost(qnaBoard4)==1);
    }

    @Test
    public void selectPostPublic() throws Exception {
        qnaBoardDao.deleteAll();
        assertTrue(qnaBoardDao.totalCnt()==0);

        List<QnABoard> list = new ArrayList<>();
        assertTrue(list.size()==0);

        QnABoard qnaBoard = new QnABoard("title2", "content2", "jeong","1234", 0,1);
        assertTrue(qnaBoardDao.registerPost(qnaBoard)==1);
        QnABoard qnaBoard2 = new QnABoard("title4","content4","bami4","",0,0);
        assertTrue(qnaBoardDao.registerPost(qnaBoard2)==1);
        QnABoard qnaBoard3 = new QnABoard("title3","content3","bami3","4321",0,1);
        assertTrue(qnaBoardDao.registerPost(qnaBoard3)==1);

        list = qnaBoardDao.selectPostPublic();
        assertTrue(list.size()==2);
    }

    @Test
    public void selectPostPrivate() throws Exception {
        qnaBoardDao.deleteAll();
        assertTrue(qnaBoardDao.totalCnt()==0);

        List<QnABoard> list = qnaBoardDao.selectAll();
        assertTrue(list.size()==0);

        QnABoard qnaBoard = new QnABoard("title2", "content2", "jeong","1234", 0,0);
        assertTrue(qnaBoardDao.registerPost(qnaBoard)==1);
        QnABoard qnaBoard2 = new QnABoard("title4","content4","bami4","",0,1);
        assertTrue(qnaBoardDao.registerPost(qnaBoard2)==1);
        QnABoard qnaBoard3 = new QnABoard("title3","content3","bami3","3214",0,0);
        assertTrue(qnaBoardDao.registerPost(qnaBoard3)==1);

        list = qnaBoardDao.selectPostPrivate();

        assertTrue(list.size()==2);
    }

    @Test
    public void selectNotReply() throws Exception {
        qnaBoardDao.deleteAll();
        QnABoard Board = new QnABoard("title2", "content2", "jeong","1234", 0,0);
        assertTrue(qnaBoardDao.registerPost(Board)==1);
        QnABoard qnaBoard2 = new QnABoard("title4","content4","bami4","",1,1);
        assertTrue(qnaBoardDao.registerPost(qnaBoard2)==1);
        QnABoard qnaBoard3 = new QnABoard("title3","content3","bami3","3214",0,0);
        assertTrue(qnaBoardDao.registerPost(qnaBoard3)==1);

        List<QnABoard>  list = qnaBoardDao.selectNotReply();
        System.out.println(list);

        assertTrue(list.size()==2);

        }
    @Test
    public void selectAll() throws Exception{
        List<QnABoard> list = qnaBoardDao.selectAll();
        System.out.println(list);
        assertTrue(list.size()==3);
    }

//    @Test
//    public void getPage() throws Exception{
//        List<QnABoard> list = qnaBoardDao.getPage();
//    }


    @Test
    public void deletePost() throws Exception{
        qnaBoardDao.deleteAll();
        QnABoard qnaBoard = new QnABoard("delete post","delete content","delete","1234",0,0);
        assertTrue(qnaBoardDao.registerPost(qnaBoard)==1);
//        QnABoard qnaBoard2 = new QnABoard("new post","don't delete", "new","",0,0);
//        assertTrue(qnaBoardDao.registerPost(qnaBoard2)==1);

        qnaBoardDao.selectAll();
        Integer idx = qnaBoardDao.selectAll().get(0).getIdx();
        //The get(0) method call is then used to retrieve the first element in that list, as the list is zero-indexed.
        System.out.println(idx);
        assertTrue(qnaBoardDao.deletePost(idx,"delete")==1);


    }

    @Test
    public void updatePost() throws Exception {
        qnaBoardDao.deleteAll();
        QnABoard board = new QnABoard("update post", "update content", "update","",0,0);
        assertTrue(qnaBoardDao.registerPost(board)==1);

        Integer boardIdx = qnaBoardDao.selectAll().get(0).getIdx();
        board.setIdx(boardIdx);
        board.setTitle("new title");
        board.setContent("new content");
        assertTrue(qnaBoardDao.updatePost(board)==1);

        qnaBoardDao.deleteAll();

        QnABoard Board1 = new QnABoard("title2", "content2", "jeong","1234", 0,0);
        assertTrue(qnaBoardDao.registerPost(Board1)==1);
        QnABoard qnaBoard2 = new QnABoard("title4","content4","bami4","",0,0);
        assertTrue(qnaBoardDao.registerPost(qnaBoard2)==1);
        QnABoard qnaBoard3 = new QnABoard("title3","content3","bami3","3214",0,0);
        assertTrue(qnaBoardDao.registerPost(qnaBoard3)==1);

        Integer boardIdx1 = qnaBoardDao.selectAll().get(0).getIdx();
        qnaBoard3.setIdx(boardIdx1);
        qnaBoard3.setReplyOrNot(1);
        assertTrue(qnaBoardDao.updatePost(qnaBoard3)==1);

        List<QnABoard> list = qnaBoardDao.selectAll();
        assertTrue(list.size()==3);
    }

//    @Test
//    public void publicOrNot() throws Exception {
//        // public == true == 1
//        // private == false == 0
//        QnABoard qnaBoard = new QnABoard("public", "public","public","",1);
//        assertTrue(qnaBoardDao.registerPost(qnaBoard)==1);
//        QnABoard qnaBoard2 = new QnABoard("private","private","private","1234",0);
//        assertTrue(qnaBoardDao.registerPost(qnaBoard2)==1);
//        QnABoard qnaBoard3 = new QnABoard("private2","private2","private2","12342",0);
//        assertTrue(qnaBoardDao.registerPost(qnaBoard3)==1);
//
//        List<QnABoard> list = qnaBoardDao.selectAll();
//
//        for(QnABoard privatePost : list ){
//            int result = privatePost.getPublicOrNot();
//            System.out.println(result);
//        }
//
//    }

    @Test
    public void totalCnt() throws Exception {
        qnaBoardDao.deleteAll();

        QnABoard qnaBoard = new QnABoard("public", "public","public","",0,1);
        assertTrue(qnaBoardDao.registerPost(qnaBoard)==1);
        QnABoard qnaBoard2 = new QnABoard("private","private","private","1234",0,0);
        assertTrue(qnaBoardDao.registerPost(qnaBoard2)==1);
        QnABoard qnaBoard3 = new QnABoard("private2","private2","private2","12342",0,0);
        assertTrue(qnaBoardDao.registerPost(qnaBoard3)==1);

        assertTrue(qnaBoardDao.totalCnt()==3);
    }

}