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
	
		params.setBoardNumber(2);
		params.setPostTitle("1번 게시글 제목");
		params.setPostContents("1번 게시글 내용");
		params.setPostInputdate(null);
		params.setPostCorrent(null);
		params.setPostRecommend(1);
		params.setPostViews(0);
		params.setPostState("1");
		params.setUserNumber(1);
	
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
			params.setBoardNumber(2);
			// 외래키니까 무조건 넣어줘야함 
			params.setPostTitle(i+ "번 게시글 제목");
			params.setPostContents(i +"번 게시글 제목");
			params.setPostCorrent(null);
			params.setPostDeletedate(null);
			params.setPostRecommend(0);
			params.setPostViews(0);
			params.setPostState("1");
			params.setUserNumber(1);
			
			
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
		params.setPostTitle("수정테스트 .");
		params.setPostContents(" 게시글 수정테스트중이요.");
		params.setPostInputdate("2020-01-11");
		params.setPostRecommend(2);
		params.setPostViews(2);
		params.setUserNumber(1);
	
		params.setPostNumber((long) 21);

		int result = boardMapper.updateBoard(params);
		if (result == 1) {
			BoardDTO board = boardMapper.selectBoardDetail((long) 21);
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
		int result = boardMapper.deleteBoard((long) 19);
		if (result == 1) {
			BoardDTO board = boardMapper.selectBoardDetail((long) 20);
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
					System.out.println(board.getPostTitle());
					System.out.println(board.getPostContents());
					System.out.println(board.getPostNumber());
					System.out.println("=========================");
				}
			}
		}
	}

	
	
	@Test
	public void getBoardList2021() {
		Long boardNum;
		List<BoardDTO> boardList = Collections.emptyList();
		System.out.println("--------------------------------");
		System.out.println(boardList);
		System.out.println("--------------------------------");
		// 비어있는 리스트 생성 

		int boardTotalCount = boardMapper.selectBoardTotalCount();
		System.out.println("total count : " +boardTotalCount);
		System.out.println("--------------------------------");
		
		
			boardList = boardMapper.selectBoardList();
//			selectBoardList 쿼리 실행시 널값으로 출력됨 
			for(int i=0; i<boardList.size(); i++) {
				boardNum = boardList.get(i).getPostNumber();
				System.out.println(boardNum);
			}
			
		}
	
	
	
	
		
	
	@Test
	public void openBoardList() {
		List<BoardDTO> boardList = boardService.getBoardList();
		
		System.out.println(boardList);
		
	}
	

}