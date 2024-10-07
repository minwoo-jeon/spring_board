package com.myportfolio.web.service;


import com.myportfolio.web.domain.*;

import java.util.*;

public interface BoardService {
    int getCount() throws Exception;
    int remove(Integer bno, String writer) throws Exception;
    int write(BoardDTO boardDto) throws Exception;
    List<BoardDTO> getList() throws Exception;
    BoardDTO read(Integer bno) throws Exception;
    List<BoardDTO> getPage(Map map) throws Exception;
    int modify(BoardDTO boardDto) throws Exception;

    List<BoardDTO> getSearchResultPage(SearchConditionDTO sc)throws Exception;

    int getSearchResultCnt(SearchConditionDTO sc)throws Exception;
}