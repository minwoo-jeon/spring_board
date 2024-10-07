package com.myportfolio.web.controller;

import com.myportfolio.web.domain.*;
import com.myportfolio.web.service.BoardService;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.*;

import javax.servlet.http.*;
import java.util.*;

@Controller
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService){
        this.boardService = boardService;
    }


    //게시물 읽기
    @GetMapping("/read")
    public String read(Integer bno, Model m, SearchConditionDTO sc ){

        try {
            BoardDTO boardDto = boardService.read(bno);
            m.addAttribute("boardDto", boardDto);
            m.addAttribute("sc",sc);


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
    public String list(HttpServletRequest request, SearchConditionDTO sc, Model m){
        if(!loginCheck(request))
            return  "redirect:/login/login?toURL=" + request.getRequestURL();


        try {
            int totalCnt = boardService.getSearchResultCnt(sc);
//            System.out.println(totalCnt);
            m.addAttribute("totalCnt", totalCnt);
            PageHandlerDTO pageHandler = new PageHandlerDTO(totalCnt,sc);

            List<BoardDTO> list = boardService.getSearchResultPage(sc);
            m.addAttribute("list" ,list);
            m.addAttribute("ph", pageHandler);

        }catch (Exception e){
            e.printStackTrace();
        }


        return "boardList";
    }


    //게시물 작성 페이지 가져오기
    @GetMapping("/write")
    public String write(Model m, SearchConditionDTO sc ){
        m.addAttribute("mode" , "new");
        m.addAttribute("sc",sc);
        return "board";
    }

    //게시글 작성 post
    @PostMapping("/write")
    public String write(BoardDTO boardDto, HttpSession session, RedirectAttributes rattr, Model m){
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

    //게시물 수정
    @PostMapping("/modify")
    public String modify(BoardDTO boardDto, HttpSession session, RedirectAttributes rattr, Model m, Integer page , Integer
            pageSize){
        String id = (String)session.getAttribute("id");
        boardDto.setWriter(id);
        try {
            int cnt = boardService.modify(boardDto);
            if(cnt != 1) {
                throw new Exception("Modify_failed");
            }
            rattr.addFlashAttribute("msg","MOD_OK");
            rattr.addAttribute("page" , page);
            rattr.addAttribute("pageSize", pageSize);
            return "redirect:/board/list";
        } catch (Exception e) {
            e.printStackTrace();
            m.addAttribute("msg","MOD_ERR");
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