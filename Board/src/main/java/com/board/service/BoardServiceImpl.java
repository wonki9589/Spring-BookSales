package com.board.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.domain.BoardDTO;
import com.board.mapper.BoardMapper;
import com.board.paging.PaginationInfo;


@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardMapper boardMapper;

    @Override
    public boolean registerBoard(BoardDTO params) {
        int queryResult = 0;

        if (params.getPostNumber() == null) {
            queryResult = boardMapper.insertBoard(params);
        } else {
            queryResult = boardMapper.updateBoard(params);
        }
        
        /*
         * BoardDTO board = null; System.out.println(board.getPostTitle());
         */
        return (queryResult == 1) ? true : false;
    }

    @Override
    public BoardDTO getBoardDetail(Long postNumber) {
        return boardMapper.selectBoardDetail(postNumber);
    }

    @Override
    public boolean deleteBoard(Long postNumber) {
        int queryResult = 0;

        BoardDTO board = boardMapper.selectBoardDetail(postNumber);

        if (board != null && "1".equals(board.getPostState())) {
            queryResult = boardMapper.deleteBoard(postNumber);
        }

        return (queryResult == 1) ? true : false;
    }

    @Override
    public List<BoardDTO> getBoardList(BoardDTO params) {
        List<BoardDTO> boardList = Collections.emptyList();

        int boardTotalCount = boardMapper.selectBoardTotalCount(params);
        

        PaginationInfo paginationInfo = new PaginationInfo(params);
        paginationInfo.setTotalRecordCount(boardTotalCount);

        params.setPaginationInfo(paginationInfo);

        if (boardTotalCount > 0) {
            boardList = boardMapper.selectBoardList(params);
        }

        return boardList;
    }

}