package com.board.domain;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDTO {
// 게시판 테이블 구조화 역활하는 클래
// 여기서는 게시글 테이블 구조화해서 매핑  
	
	
		//db칼럼명은 대문자 java 소문자로 
	private int post_number;    //POST_NUMBER
	private int board_number;  //BOARD_NUMBER
	private String post_title; //POST_TITLE
	private String post_contents; //POST_CONTENTS
	private String post_inputdate; //POST_INPUTDATE
	private String post_corrent; //POST_CORRENT
	private String post_deletedate ;//POST_DELETEDATE
	private String post_recommend;//POST_RECOMMEND
	private String post_views;  //POST_VIEWS
	private String post_state;//POST_STATE
	private int  user_number; //USER_NUMBER 
 }
