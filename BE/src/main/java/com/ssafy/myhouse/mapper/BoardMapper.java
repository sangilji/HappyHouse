package com.ssafy.myhouse.mapper;

import com.ssafy.myhouse.vo.Board;
import com.ssafy.myhouse.vo.BoardParameterDto;

import java.sql.SQLException;
import java.util.List;

public interface BoardMapper {
    public int writeArticle(Board boardDto) throws SQLException;
    public List<Board> listArticle(BoardParameterDto boardParameterDto) throws SQLException;
    public int getTotalCount(BoardParameterDto boardParameterDto) throws SQLException;
    public Board getArticle(int articleno) throws SQLException;
    public void updateHit(int articleno) throws SQLException;
    public int modifyArticle(Board boardDto) throws SQLException;
    public int deleteArticle(int articleno) throws SQLException;
    List<Board> listFreeRead(BoardParameterDto boardParameterDto);
        // free board mapper
public int freeWriteArticle(Board boardDto) throws SQLException;
    public List<Board> freeListArticle(BoardParameterDto boardParameterDto) throws SQLException;
    public int freeGetTotalCount(BoardParameterDto boardParameterDto) throws SQLException;
    public Board freeGetArticle(int articleno) throws SQLException;
    public void freeUpdateHit(int articleno) throws SQLException;
    public int freeModifyArticle(Board boardDto) throws SQLException;
    public int freeDeleteArticle(int articleno) throws SQLException;
    List<Board> freeListFreeRead(BoardParameterDto boardParameterDto);
}
