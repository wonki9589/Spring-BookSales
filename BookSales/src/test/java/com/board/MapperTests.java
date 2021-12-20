package com.board;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;

import com.board.domain.BoardDTO;
import com.board.mapper.BoardMapper;
import com.board.service.BoardService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@SpringBootTest
class MapperTests {

	@Autowired
	private BoardMapper boardMapper;
	private BoardService boardService;

	@Test
	public void testOfInsert() {
		BoardDTO params = new BoardDTO();
	
		params.setBoard_number(3);
		params.setPost_title("3번 게시글 제목");
		params.setPost_contents("3번 게시글 내용");
		params.setPost_inputdate(null);
		params.setPost_corrent(null);
		params.setPost_deletedate(null);
		params.setPost_recommend(1);
		params.setPost_views(0);
		params.setPost_state("1");
		params.setUser_number(3);
	
//   보드넘버랑 유저넘버가 다른테이블에서 참조하는거니까 해당 테이블에 데이터가 있어야 찾아서 넣을수있음 
		//게시글번호에 auto increment 추가안해서 직접 넣어줘야함 이거는 상의하고 변경하던가 해야함 

		int result = boardMapper.insertBoard(params);
		System.out.println("결과는 " + result + "입니다.");
	}
	
	@Test
	public void testMultipleInsert() {
		for (int i = 3; i <= 20; i++) {
			BoardDTO params = new BoardDTO();
			//			params.setPost_number();
			// auto increment 속성 추가로 굳이 안넣어줘도됨 
			params.setBoard_number(1);
			// 외래키니까 무조건 넣어줘야함 
			params.setPost_title(i+ "번 게시글 제목");
			params.setPost_contents(i +"번 게시글 제목");
			params.setPost_corrent(null);
			params.setPost_deletedate(null);
			params.setPost_recommend(0);
			params.setPost_views(0);
			params.setPost_state("1");
			params.setUser_number(i);
			
			
			boardMapper.insertBoard(params);
		}
	}





@Test
public void testOfSelectDetail() {
	BoardDTO board = boardMapper.selectBoardDetail((long) 1);
	try {
		String boardJson =new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(board);
				// 의존성 주입 했는데도 안됨 
				//new ObjectMapper().writeValueAsString(board);
				
		
		
		System.out.println("=========================");
		System.out.println(boardJson);
		System.out.println("=========================");

	} catch (JsonProcessingException e) {
		e.printStackTrace();
	}
}
// 이거 이상하다 그냥 무시 
	
	
	@Test
	public void testOfUpdate() {
		BoardDTO params = new BoardDTO();
		params.setPost_title("2번 게시글 제목을 수정합니다.");
		params.setPost_contents("2번 게시글 내용을 수정합니다.");
		params.setPost_number((long) 2);

		int result = boardMapper.updateBoard(params);
		if (result == 1) {
			BoardDTO board = boardMapper.selectBoardDetail((long) 2);
			try {
				String boardJson = new ObjectMapper().writeValueAsString(board);

				System.out.println("=========================");
				System.out.println(boardJson);
				System.out.println("=========================");

			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
	}
	
//	 업데이트하고싶은거만 setter 	불러와서 수정 


	@Test
	public void testOfDelete() {
		int result = boardMapper.deleteBoard((long) 2);
		if (result == 1) {
			BoardDTO board = boardMapper.selectBoardDetail((long) 2);
			try {
				String boardJson = new ObjectMapper().writeValueAsString(board);

				System.out.println("=========================");
				System.out.println(boardJson);
				System.out.println("=========================");

			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	@Test
	public void testSelectList() {
		int boardTotalCount = boardMapper.selectBoardTotalCount();
		if (boardTotalCount > 0) {
			List<BoardDTO> boardList = boardMapper.selectBoardList();
			if (CollectionUtils.isEmpty(boardList) == false) {
				for (BoardDTO board : boardList) {
					System.out.println("=========================");
					System.out.println(board);
					System.out.println(board.getPost_title());
					System.out.println(board.getPost_contents());
					System.out.println(board.getPost_number());
					System.out.println("=========================");
				}
			}
		}
	}

	
	
	@Test
	public void getBoardList2021() {
		List<BoardDTO> boardList = Collections.emptyList();
		// 비어있는 리스트 생성 

		int boardTotalCount = boardMapper.selectBoardTotalCount();
		System.out.println("total count : " +boardTotalCount);
		
		if (boardTotalCount > 0) {
			boardList = boardMapper.selectBoardList();
			
			if(boardList.isEmpty()) {
				System.out.println("리스트가 비어있다");
			}
			System.out.println( "리스트가 있네 ");
			System.out.println(boardList);
		}
		
	}
	@Test
	public void openBoardList() {
		List<BoardDTO> boardList = boardService.getBoardList();
		
		System.out.println(boardList);
		
	}
	

}