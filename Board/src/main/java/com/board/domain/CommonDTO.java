package com.board.domain;

import java.time.LocalDateTime;

import com.board.paging.Criteria;
import com.board.paging.PaginationInfo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonDTO extends Criteria {

    /** 페이징 정보 */
    private PaginationInfo paginationInfo;

    private String postState;
    //POST_STATE  게시글 상태 

    private String postInputdate;
    //POST_INPUTDATE 등록일 
    
    private String postCorrent; 
    //POST_CORRENT 수정일 
    
    private String postDeletedate ;
    //POST_DELETEDATE 삭제일 

}