package com.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.board.domain.BoardDTO;

@Mapper
public interface BoardMapper {

	public int insertBoard(BoardDTO params);

	public BoardDTO selectBoardDetail(Long post_number);

	public int updateBoard(BoardDTO params);

	public int deleteBoard(Long post_number);

	public List<BoardDTO> selectBoardList();

	public int selectBoardTotalCount();

}