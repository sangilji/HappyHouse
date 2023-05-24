package com.ssafy.myhouse.controller;

import com.ssafy.myhouse.service.boardservice.BoardService;
import com.ssafy.myhouse.service.boardservice.FreeBoardService;
import com.ssafy.myhouse.vo.Board;
import com.ssafy.myhouse.vo.BoardParameterDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Description;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class FreeBoardController {
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    private final FreeBoardService freeboardService;

    @Description("게시판 CREATE")
    @PostMapping("/freeboardwrite")
    public ResponseEntity<String> writeArticle(@RequestBody Board boardDto) throws Exception {
        if (freeboardService.writeArticle(boardDto)) {
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
    }
    @Description("게시글 READ")
    @GetMapping("freeboardlist")
    public ResponseEntity<List<Board>> listArticle(BoardParameterDto boardParameterDto) throws Exception {
        return new ResponseEntity<List<Board>>(freeboardService.listArticle(boardParameterDto), HttpStatus.OK);
    }

    @Description("번호 기준으로 게시글을 얻어온다")
    @GetMapping("freeboardview/{boardid}")
    public ResponseEntity<Board> getArticle(@PathVariable("boardid") int boardid) throws Exception {
        freeboardService.updateHit(boardid);
        return new ResponseEntity<Board>(freeboardService.getArticle(boardid), HttpStatus.OK);
    }

    @Description("게시글 UPDATE")
    @PutMapping("freeboardupdate")
    public ResponseEntity<String> modifyArticle(@RequestBody Board boardDto) throws Exception {
        if (freeboardService.modifyArticle(boardDto)) {
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<String>(FAIL, HttpStatus.OK);
    }

    @Description("게시글 DELETE")
    @DeleteMapping("freeboarddelete/{boardid}")
    public ResponseEntity<String> deleteArticle(@PathVariable("boardid") int boardid) throws Exception {
        if (freeboardService.deleteArticle(boardid)) {
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
    }
}
