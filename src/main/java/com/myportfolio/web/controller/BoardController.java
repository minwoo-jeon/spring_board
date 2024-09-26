package com.myportfolio.web.controller;

import com.myportfolio.web.domain.*;
import com.myportfolio.web.service.*;
import com.myportfolio.web.service.BoardService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.*;

import javax.servlet.http.*;
import java.time.*;
import java.util.*;

@Controller
@RequestMapping("/board")
public class BoardController {
    @Autowired
    BoardService boardService;


    //게시물 읽기
    @GetMapping("/read")
    public String read(Integer bno, Model m, Integer page, Integer pageSize ){

        try {
            BoardDto boardDto = boardService.read(bno);
            m.addAttribute("boardDto", boardDto);
            m.addAttribute("page",page);
            m.addAttribute("pageSize",pageSize);


        } catch (Exception e) {
            e.printStackTrace();
        }


        return "board";
    }

    //게시글 삭제
    @PostMapping("/remove")
    public String remove(HttpSession session, Integer bno, Integer page, Integer pageSize, RedirectAttributes rattr ){
        String writer = (String)session.getAttribute("id");


        try {

            int rowCnt = boardService.remove(bno, writer);
            if(rowCnt!=1)
                throw new Exception("DEL_ERR");


            rattr.addAttribute("page", page);
            rattr.addAttribute("pageSize", pageSize);
            rattr.addFlashAttribute("msg", "DEL_OK");

        }catch(Exception e){
            e.printStackTrace();
            rattr.addFlashAttribute("msg", "DEL_ERR");
        }

        return "redirect:/board/list";

    }


    @GetMapping("/list")
    public String list(Integer page, Integer pageSize,Model m, HttpServletRequest request) {
        if(!loginCheck(request))
            return "redirect:/login/login?toURL="+request.getRequestURL();  // 로그인을 안했으면 로그인 화면으로 이동

        if(page==null) page = 1;
        if(pageSize == null)pageSize=10;

        try {
            Map map = new HashMap();
            map.put("offset", (page - 1) * pageSize);
            map.put("pageSize", pageSize);

            int totalCnt = boardService.getCount();
            PageHandler ph = new PageHandler(totalCnt,page,pageSize);

         List<BoardDto>  list =   boardService.getPage(map);
         m.addAttribute("totalCnt", totalCnt);
         m.addAttribute("list",list);
         m.addAttribute("ph",ph);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "boardList"; // 로그인을 한 상태이면, 게시판 화면으로 이동
    }

    //게시물 작성 페이지 가져오기
    @GetMapping("/write")
    public String write(Model m){
        m.addAttribute("mode" , "new");
        return "board";
    }

    //게시글 작성 post
    @PostMapping("/write")
    public String write(BoardDto boardDto, HttpSession session,RedirectAttributes rattr,Model m){
        String id = (String)session.getAttribute("id");
        boardDto.setWriter(id);
        try {
            int cnt = boardService.write(boardDto);
            if(cnt != 1) {
                throw new Exception("WRT_ERR");
            }
            rattr.addFlashAttribute("msg","WRT_OK");
            return "redirect:/board/list";

        } catch (Exception e) {
            e.printStackTrace();
            m.addAttribute("msg","WRT_ERR");
            return "board";
        }
    }




    //로그인 여부확인
    private boolean loginCheck(HttpServletRequest request) {
        // 1. 세션을 얻어서
        HttpSession session = request.getSession();
        // 2. 세션에 id가 있는지 확인, 있으면 true를 반환
        return session.getAttribute("id")!=null;
    }
}