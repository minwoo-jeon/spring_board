package com.myportfolio.web.service;


import com.myportfolio.web.dao.BoardDao;
import com.myportfolio.web.dao.CommentDao;
import com.myportfolio.web.domain.CommentDTO;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.*;

@Service
public class CommentServiceImpl implements CommentService {

   private final  BoardDao boardDao;
   private final CommentDao commentDao;



    @Autowired
    public CommentServiceImpl(CommentDao commentDao, BoardDao boardDao) {
        this.commentDao = commentDao;
        this.boardDao = boardDao;
    }

    @Override
    public int getCount(Integer bno) throws Exception {
        return commentDao.count(bno);
    }

    @Override

    @Transactional(rollbackFor = Exception.class)
    public int remove(Integer cno, Integer bno, String commenter) throws Exception {
        int rowCnt = boardDao.updateCommentCnt( -1,bno);
//        System.out.println("updateCommentCnt - rowCnt = " + rowCnt);
//        throw new Exception("test");
        rowCnt = commentDao.delete(cno, commenter);
        System.out.println("rowCnt = " + rowCnt);
        return rowCnt;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int write(CommentDTO commentDto) throws Exception {
        int rowCnt =boardDao.updateCommentCnt( 1,commentDto.getBno());

//                throw new Exception("test");
        return commentDao.insert(commentDto);
    }

    @Override
    public List<CommentDTO> getList(Integer bno) throws Exception {
//        throw new Exception("test");
        return commentDao.selectAll(bno);
    }

    @Override
    public CommentDTO read(Integer cno) throws Exception {
        return commentDao.select(cno);
    }

    @Override
    public int modify(CommentDTO commentDto) throws Exception {
        return commentDao.update(commentDto);
    }
}