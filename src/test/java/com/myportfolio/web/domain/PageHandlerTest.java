package com.myportfolio.web.domain;

import com.myportfolio.web.dao.BoardDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class PageHandlerTest {

    @Autowired
    BoardDao boardDao;

    @Test
    public void test5()throws Exception{
        boardDao.deleteAll();
        for (int i = 1; i <= 250; i++){
            BoardDto boardDto = new BoardDto("test"+i,"test1","asdf");
             int cnt = boardDao.insert(boardDto);
        }
    }

    @Test
    public void test(){
        PageHandler ph = new PageHandler(250, 1 );
        ph.print();
        System.out.println("ph = " + ph);
        assertTrue(ph.beginPage==1);
        assertTrue(ph.endPage == 10);
    }

    @Test
    public void test2(){
        PageHandler ph = new PageHandler(250, 11);
        ph.print();
        System.out.println("ph = " + ph);
        assertTrue(ph.beginPage==11);
        assertTrue(ph.endPage == 20);
    }

    @Test
    public void test3(){
        PageHandler ph = new PageHandler(255, 25);
        ph.print();
        System.out.println("ph = " + ph);
        assertTrue(ph.beginPage==21);
        assertTrue(ph.endPage == 26 );
    }

    @Test
    public void test4(){
        PageHandler ph = new PageHandler(250, 20);
        ph.print();
//        System.out.println("ph = " + ph);
        assertTrue(ph.beginPage==11);
        assertTrue(ph.endPage == 20 );
    }



    /**/
}