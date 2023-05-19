package com.ssafy.myhouse.controller;

import com.ssafy.myhouse.service.boardservice.BoardService;
import com.ssafy.myhouse.vo.Board;
import com.ssafy.myhouse.vo.BoardParameterDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class BoardController {
    private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    private final BoardService boardService;

    @Description("게시판 CREATE")
    @PostMapping("/boardwrite")
    public ResponseEntity<String> writeArticle(@RequestBody Board boardDto) throws Exception {
        if (boardService.writeArticle(boardDto)) {
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
    }
    @Description("게시글 READ")
    @GetMapping("boardlist")
    public ResponseEntity<List<Board>> listArticle(BoardParameterDto boardParameterDto) throws Exception {
        return new ResponseEntity<List<Board>>(boardService.listArticle(boardParameterDto), HttpStatus.OK);
    }

    @Description("번호 기준으로 게시글을 얻어온다")
    @GetMapping("boardview/{announcementid}")
    public ResponseEntity<Board> getArticle(@PathVariable("announcementid") int announcementid) throws Exception {
        boardService.updateHit(announcementid);
        return new ResponseEntity<Board>(boardService.getArticle(announcementid), HttpStatus.OK);
    }

    @Description("게시글 UPDATE")
    @PutMapping("boardupdate")
    public ResponseEntity<String> modifyArticle(@RequestBody Board boardDto) throws Exception {
        if (boardService.modifyArticle(boardDto)) {
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<String>(FAIL, HttpStatus.OK);
    }

    @Description("게시글 DELETE")
    @DeleteMapping("boarddelete/{announcementid}")
    public ResponseEntity<String> deleteArticle(@PathVariable("announcementid") int announcementid) throws Exception {
        if (boardService.deleteArticle(announcementid)) {
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
    }
}
