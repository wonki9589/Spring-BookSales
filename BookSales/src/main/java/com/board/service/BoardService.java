package com.board.service;

import java.util.List;

import com.board.domain.BoardDTO;

public interface BoardService {

	public boolean registerBoard(BoardDTO params);

	public BoardDTO getBoardDetail(Long post_number);

	public boolean deleteBoard(Long post_number);

	public List<BoardDTO> getBoardList();

}