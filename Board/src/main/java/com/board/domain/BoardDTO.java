package com.board.domain;



import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDTO extends CommonDTO{

 
    // 게시판 테이블 구조화 역활하는 클래
    // 여기서는 게시글 테이블 구조화해서 매핑  
        
        
            //db칼럼명은 대문자 java 소문자로 
        
        private Long postNumber;    
        //POST_NUMBER 게시번호 
        
        private int boardNumber;  //게시판
        // 게시글 테이블 만들어지면 연결해야함 지금은 강제로 1이라고 저장해놓음 
        //BOARD_NUMBER 게시판번호 
        
        private String postTitle; 
        //POST_TITLE 제목 
        
        private String postContents; 
        //POST_CONTENTS 내용 
        
        
        private int postRecommend;
        //POST_RECOMMEND 추천수 
        private int postViews;
        //POST_VIEWS 조회수 
        
               
        private int  userNumber;
        // 사용자 테이블 만들어지면 연결해야함 지금은 쿼리넣을때 1 이라고 강제로 저장해놓음 
        //USER_NUMBER  게시글 작성자 
     }

 

