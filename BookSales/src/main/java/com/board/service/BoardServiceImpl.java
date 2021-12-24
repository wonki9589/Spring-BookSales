package com.board.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.domain.BoardDTO;
import com.board.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardMapper boardMapper;

	@Override
	public boolean registerBoard(BoardDTO params) {
		int queryResult = 0;

	if (params.getPostNumber() == null) {

			queryResult = boardMapper.insertBoard(params);

		} 
	else {
			queryResult = boardMapper.updateBoard(params);
		}
			// if else 문에서는 params의 idx가 null이면,
		//   MySQL의 AUTO_INCREMENT 속성에 의해
		//   PK(post_number)가 자동 증가되어 게시글을 생성하고,
		//   post_number가 포함되어 있으면 게시글을 수정합니다.
		return (queryResult == 1) ? true : false;
	}

	@Override
	public BoardDTO getBoardDetail(long postNumber) {
		return boardMapper.selectBoardDetail(postNumber);
	}
	// 하나의 게시을 조회하는 결과값 반환 추후에 조회수 증기사키는 로직 필요 

	@Override
	public boolean deleteBoard(long postNumber) {
		int queryResult = 0;

		BoardDTO board = boardMapper.selectBoardDetail(postNumber);

		if (board != null && "1".equals(board.getPostState())) {
			queryResult = boardMapper.deleteBoard(postNumber);
		}
//없는 게시글이거나, 삭제 여부(delete_yn) 컬럼의 상태 값이 '0'인 경우에는 삭제가 실행 x
		return (queryResult == 1) ? true : false;
	}

	@Override
	public List<BoardDTO> getBoardList() {
		List<BoardDTO> boardList = Collections.emptyList();
		
		// 비어있는 리스트 생성 

		int boardTotalCount = boardMapper.selectBoardTotalCount();
		
		
		if (boardTotalCount > 0) {
			boardList = boardMapper.selectBoardList();
		
		}
		
		return boardList;
	}

}