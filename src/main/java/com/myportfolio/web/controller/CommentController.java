package com.myportfolio.web.controller;


import com.myportfolio.web.domain.CommentDto;
import com.myportfolio.web.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class CommentController {
    @Autowired
    CommentService service;

    //댓글을 수정하는 메서드
    @PatchMapping("/comments/{cno}")  // web/comments/70 patch
    public ResponseEntity<String> modify(@PathVariable Integer cno , @RequestBody CommentDto dto, HttpSession session) {
//        String commenter = (String)session.getAttribute("id");
        String commenter = "asdf";
        dto.setCommenter(commenter);
        dto.setCno(cno);
        System.out.println("dto =" + dto );

        try {
            if( service.modify(dto) !=1)
                throw new Exception("Modify failed");

            return new ResponseEntity<>("MOD_OK", HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("MOD_ERR", HttpStatus.BAD_REQUEST);
        }

    }


//    {
//
//        "pcno":0,
//            "comment": "HI",
//
//    }

    //댓글을 저장하는 메서드
    @PostMapping("/comments")  // web/comments?bno=13359 post <-- 삭제할 댓글 번호
    public ResponseEntity<String> write(@RequestBody CommentDto dto, HttpSession session, Integer bno) {
//        String commenter = (String)session.getAttribute("id");
        String commenter = "asdf";
        dto.setCommenter(commenter);
        dto.setBno(bno);
        System.out.println("dto = " + dto);

        try {
            if( service.write(dto) !=1)
                throw new Exception("Write failed");

            return new ResponseEntity<>("WRT_OK", HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("WRT_ERR", HttpStatus.BAD_REQUEST);
        }

    }


    // 지정된 댓글을 삭제하는 메서드
    @DeleteMapping("/comments/{cno}")  // /comments/1?bno=13359 <-- 삭제할 댓글 번호
    public ResponseEntity<String> remove(HttpSession session, @PathVariable Integer cno , Integer bno){
//        String commenter = (String)session.getAttribute("id");
        String commenter = "asdf";

        try {
            int rowCnt = service.remove(cno, bno, commenter);

            if(rowCnt!=1)
                throw  new Exception("Delete Failed");

            return  new ResponseEntity<>("DEL_OK",HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return  new ResponseEntity<>("DEL_ERR",HttpStatus.BAD_REQUEST);
        }
    }


    //지정된 게시물의 모든 갯글을 가져오는 메서드
    @GetMapping("/comments") //coments?bno=13359 get
    public ResponseEntity<List<CommentDto>> list(Integer bno) {
        List<CommentDto> list = null;
        try {
            list = service.getList(bno);
            return new ResponseEntity<List<CommentDto>>(list, HttpStatus.OK); //성공시  200
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<List<CommentDto>>(HttpStatus.BAD_REQUEST); //실패시 상태코드 400번
        }

    }
}
