package com.myportfolio.web.dao;


import com.myportfolio.web.domain.CommentDTO;

import java.util.List;

public interface CommentDao {
    int count(Integer bno) throws Exception // T selectOne(String statement)
    ;

    int deleteAll(Integer bno) // int delete(String statement)
    ;

    int delete(Integer cno, String commenter) throws Exception // int delete(String statement, Object parameter)
    ;

    int insert(CommentDTO dto) throws Exception // int insert(String statement, Object parameter)
    ;

    List<CommentDTO> selectAll(Integer bno) throws Exception // List<E> selectList(String statement)
    ;

    CommentDTO select(Integer cno) throws Exception // T selectOne(String statement, Object parameter)
    ;

    int update(CommentDTO dto) throws Exception // int update(String statement, Object parameter)
    ;
}
