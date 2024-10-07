package com.myportfolio.web.dao;


import com.myportfolio.web.domain.CommentDTO;
import org.apache.ibatis.session.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public class CommentDaoImpl implements CommentDao {

    private final SqlSession session;
    private static String namespace = "com.myportfolio.web.dao.CommentMapper.";

    public CommentDaoImpl(SqlSession session){
        this.session = session;
    }


    @Override
    public int count(Integer bno) throws Exception {
        return session.selectOne(namespace+"count", bno);
    } // T selectOne(String statement)

    @Override
    public int deleteAll(Integer bno) {
        return session.delete(namespace+"deleteAll", bno);
    } // int delete(String statement)

    @Override
    public int delete(Integer cno, String commenter) throws Exception {
        Map map = new HashMap();
        map.put("cno", cno);
        map.put("commenter", commenter);
        return session.delete(namespace+"delete", map);
    } // int delete(String statement, Object parameter)

    @Override
    public int insert(CommentDTO dto) throws Exception {
        return session.insert(namespace+"insert", dto);
    } // int insert(String statement, Object parameter)

    @Override
    public List<CommentDTO> selectAll(Integer bno) throws Exception {
        return session.selectList(namespace+"selectAll", bno);
    } // List<E> selectList(String statement)

    @Override
    public CommentDTO select(Integer cno) throws Exception {
        return session.selectOne(namespace + "select", cno);
    } // T selectOne(String statement, Object parameter)

    @Override
    public int update(CommentDTO dto) throws Exception {
        return session.update(namespace+"update", dto);
    } // int update(String statement, Object parameter)
}