package com.ssafy.myhouse.service.boardservice;

import com.ssafy.myhouse.mapper.BoardMapper;
import com.ssafy.myhouse.util.PageNavigation;
import com.ssafy.myhouse.vo.Board;
import com.ssafy.myhouse.vo.BoardParameterDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FreeBoardServiceImpl implements FreeBoardService{
    @Autowired
    private SqlSession sqlSession;

    @Override
    public boolean writeArticle(Board boardDto) throws Exception {
        if(boardDto.getTitle() == null || boardDto.getContent() == null) {
            throw new Exception();
        }
        return sqlSession.getMapper(BoardMapper.class).freeWriteArticle(boardDto) == 1;
    }

    @Override
    public List<Board> listArticle(BoardParameterDto boardParameterDto) throws Exception {
        int start = boardParameterDto.getPg() == 0 ? 0 : (boardParameterDto.getPg() - 1) * boardParameterDto.getSpp();
        boardParameterDto.setStart(start);
        return sqlSession.getMapper(BoardMapper.class).freeListArticle(boardParameterDto);
    }

    @Override
    public PageNavigation makePageNavigation(BoardParameterDto boardParameterDto) throws Exception {
        int naviSize = 5;
        PageNavigation pageNavigation = new PageNavigation();
        pageNavigation.setCurrentPage(boardParameterDto.getPg());
        pageNavigation.setNaviSize(naviSize);
        int totalCount = sqlSession.getMapper(BoardMapper.class).getTotalCount(boardParameterDto);//총글갯수  269
        pageNavigation.setTotalCount(totalCount);
        int totalPageCount = (totalCount - 1) / boardParameterDto.getSpp() + 1;//27
        pageNavigation.setTotalPageCount(totalPageCount);
        boolean startRange = boardParameterDto.getPg() <= naviSize;
        pageNavigation.setStartRange(startRange);
        boolean endRange = (totalPageCount - 1) / naviSize * naviSize < boardParameterDto.getPg();
        pageNavigation.setEndRange(endRange);
        pageNavigation.makeNavigator();
        return pageNavigation;
    }

    @Override
    public Board getArticle(int articleno) throws Exception {
        return sqlSession.getMapper(BoardMapper.class).freeGetArticle(articleno);
    }

    @Override
    public void updateHit(int articleno) throws Exception {
        sqlSession.getMapper(BoardMapper.class).freeUpdateHit(articleno);
    }

    @Override
    @Transactional
    public boolean modifyArticle(Board boardDto) throws Exception {
        return sqlSession.getMapper(BoardMapper.class).freeModifyArticle(boardDto) == 1;
    }

    @Override
    @Transactional
    public boolean deleteArticle(int articleno) throws Exception {
        return sqlSession.getMapper(BoardMapper.class).freeDeleteArticle(articleno) == 1;
    }
}
