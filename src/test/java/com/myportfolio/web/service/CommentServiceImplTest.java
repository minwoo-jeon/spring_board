package com.myportfolio.web.service;


import com.myportfolio.web.dao.BoardDao;
import com.myportfolio.web.dao.CommentDao;
import com.myportfolio.web.domain.BoardDTO;
import com.myportfolio.web.domain.CommentDTO;
import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.test.context.*;
import org.springframework.test.context.junit4.*;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class CommentServiceImplTest {
    @Autowired
    CommentService commentService;
    @Autowired
    CommentDao commentDao;
    @Autowired
    BoardDao boardDao;

    @Test
    public void remove() throws Exception {
        boardDao.deleteAll();

        BoardDTO boardDto = new BoardDTO("hello", "hello", "asdf");
        assertTrue(boardDao.insert(boardDto) == 1);
        Integer bno = boardDao.selectAll().get(0).getBno();
        System.out.println("bno = " + bno);

        commentDao.deleteAll(bno);
        CommentDTO commentDto = new CommentDTO(bno,0,"hi","qwer");

        assertTrue(boardDao.select(bno).getComment_cnt() == 0);
        assertTrue(commentService.write(commentDto)==1);


        assertTrue(boardDao.select(bno).getComment_cnt() == 1);

        Integer cno = commentDao.selectAll(bno).get(0).getCno();

        // 일부러 예외를 발생시키고 Tx가 취소되는지 확인해야.
        int rowCnt = commentService.remove(cno, bno, commentDto.getCommenter());
        assertTrue(rowCnt==1);
        assertTrue(boardDao.select(bno).getComment_cnt() == 0);
    }

    @Test
    public void write() throws  Exception {
        boardDao.deleteAll();

        BoardDTO boardDto = new BoardDTO("hello", "hello", "asdf");
        assertTrue(boardDao.insert(boardDto) == 1);
        Integer bno = boardDao.selectAll().get(0).getBno();
        System.out.println("bno = " + bno);

        commentDao.deleteAll(bno);
        CommentDTO commentDto = new CommentDTO(bno,0,"hi","qwer");

        assertTrue(boardDao.select(bno).getComment_cnt() == 0);
        assertTrue(commentService.write(commentDto)==1);

        Integer cno = commentDao.selectAll(bno).get(0).getCno();
        assertTrue(boardDao.select(bno).getComment_cnt() == 1);
    }
}