package com.myportfolio.web.service;

import com.myportfolio.web.dao.*;
import com.myportfolio.web.domain.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class BoardServiceImpl implements BoardService {
   private final BoardDao boardDao;

   public BoardServiceImpl(BoardDao boardDao){
       this.boardDao = boardDao;
   }

    @Override
    public int getCount() throws Exception {
        return boardDao.count();
    }

    @Override
    public int remove(Integer bno, String writer) throws Exception {
        return boardDao.delete(bno, writer);
    }

    @Override
    public int write(BoardDTO boardDto) throws Exception {
        return boardDao.insert(boardDto);
    }

    @Override
    public List<BoardDTO> getList() throws Exception {
        return boardDao.selectAll();
    }

    @Override
    public BoardDTO read(Integer bno) throws Exception {
        BoardDTO boardDto = boardDao.select(bno);
        boardDao.increaseViewCnt(bno);

        return boardDto;
    }

    @Override
    public List<BoardDTO> getPage(Map map) throws Exception {
        return boardDao.selectPage(map);
    }

    @Override
    public int modify(BoardDTO boardDto) throws Exception {
        return boardDao.update(boardDto);
    }

    @Override
    public List<BoardDTO> getSearchResultPage(SearchConditionDTO sc)throws Exception{
        return boardDao.searchSelectPage(sc);
    }

    @Override
    public int getSearchResultCnt(SearchConditionDTO sc)throws Exception{
        return boardDao.searchResultCnt(sc);
    }


}