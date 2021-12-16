package com.board.mapper;

import com.board.domain.BoardDTO;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import com.board.domain.BoardDTO;

@Mapper
public class BoardMapper {
//데이터베이스와 통신하는 역활 인터페이스임 쿼리호출하는 용도 
	public int insertBoard(BoardDTO params);

	public BoardDTO selectBoardDetail(Long idx);

	public int updateBoard(BoardDTO params);

	public int deleteBoard(Long idx);

	public List<BoardDTO> selectBoardList();

	public int selectBoardTotalCount();

	
	
}
