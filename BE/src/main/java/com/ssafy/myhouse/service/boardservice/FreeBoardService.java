package com.ssafy.myhouse.service.boardservice;

import com.ssafy.myhouse.util.PageNavigation;
import com.ssafy.myhouse.vo.Board;
import com.ssafy.myhouse.vo.BoardParameterDto;

import java.util.List;

public interface FreeBoardService {
    public boolean writeArticle(Board boardDto) throws Exception;
    public List<Board> listArticle(BoardParameterDto boardParameterDto) throws Exception;
    public PageNavigation makePageNavigation(BoardParameterDto boardParameterDto) throws Exception;

    public Board getArticle(int articleno) throws Exception;
    public void updateHit(int articleno) throws Exception;
    public boolean modifyArticle(Board boardDto) throws Exception;
    public boolean deleteArticle(int articleno) throws Exception;
}
