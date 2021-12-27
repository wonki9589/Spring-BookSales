package com.board.service;

import java.util.List;

import com.board.domain.BoardDTO;


public interface BoardService {

    public boolean registerBoard(BoardDTO params);

    public BoardDTO getBoardDetail(Long postNumber);

    public boolean deleteBoard(Long postNumber);

    public List<BoardDTO> getBoardList(BoardDTO params);

}