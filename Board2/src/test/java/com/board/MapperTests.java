package com.board;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import com.board.domain.BoardDTO;
import com.board.mapper.BoardMapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
class MapperTests {

    @Autowired
    private BoardMapper boardMapper;
    //게시글 생성
    @Test
    public void testOfInsert() {
        BoardDTO params = new BoardDTO();
        params.setPostTitle("1번 게시글 제목");
        params.setPostContents("1번 게시글 내용");
        params.setUserNumber(1);
        params.setBoardNumber(2);
        params.setPostViews(2);
        params.setPostState("1");

        int result = boardMapper.insertBoard(params);
        System.out.println("결과는 " + result + "입니다.");
    }
    //게시글 보기
    @Test
    public void testOfSelectDetail() {
        BoardDTO board = boardMapper.selectBoardDetail((long) 1);
        try {
            String boardJson = new ObjectMapper().writeValueAsString(board);

            System.out.println("=========================");
            System.out.println(boardJson);
            System.out.println("=========================");

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
    
    @Test //수정
    public void testOfUpdate() {
        BoardDTO params = new BoardDTO();
        params.setPostTitle("1번 게시글 제목을 수정합니다.");
        params.setPostContents("1번 게시글 내용을 수정합니다.");
        params.setUserNumber(1);
        params.setPostNumber((long) 1);

        int result = boardMapper.updateBoard(params);
        if (result == 1) {
            BoardDTO board = boardMapper.selectBoardDetail((long) 1);
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
    
    @Test //삭제
    public void testOfDelete() {
        int result = boardMapper.deleteBoard((long) 1);
        if (result == 1) {
            BoardDTO board = boardMapper.selectBoardDetail((long) 1);
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
    
    @Test // 포문으로 여러게 만들기
    public void testMultipleInsert() {
        for (int i = 2; i <= 50; i++) {
            BoardDTO params = new BoardDTO();
            params.setPostTitle(i + "번 게시글 제목");
            params.setPostContents(i + "번 게시글 내용");
            params.setUserNumber(i);
            boardMapper.insertBoard(params);
        }
    }
    
    @Test //리스트 배열에 담은거 여러게 꺼내서 보여주기
    public void testSelectList(BoardDTO params) {
        int boardTotalCount = boardMapper.selectBoardTotalCount(params);
        if (boardTotalCount > 0) {
            List<BoardDTO> boardList = boardMapper.selectBoardList(params);
            if (CollectionUtils.isEmpty(boardList) == false) {
                for (BoardDTO board : boardList) {
                    System.out.println("=========================");
                    System.out.println(board.getPostTitle());
                    System.out.println(board.getPostContents());
                    System.out.println(board.getUserNumber());
                    System.out.println("=========================");
                }
            }
        }
    }
}