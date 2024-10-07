package com.myportfolio.web.dao;

import com.myportfolio.web.domain.*;

import java.util.*;

public interface BoardDao {
    BoardDTO select(Integer bno) throws Exception;
    int delete(Integer bno, String writer) throws Exception;
    int insert(BoardDTO dto) throws Exception;
    int update(BoardDTO dto) throws Exception;
    int increaseViewCnt(Integer bno) throws Exception;

    List<BoardDTO> selectPage(Map map) throws Exception;
    List<BoardDTO> selectAll() throws Exception;
    int deleteAll() throws Exception;
    int count() throws Exception;
    public int updateCommentCnt(Integer cnt, Integer bno)throws Exception;

    List<BoardDTO> searchSelectPage(SearchConditionDTO sc)throws Exception;

    int searchResultCnt(SearchConditionDTO sc)throws Exception;


}