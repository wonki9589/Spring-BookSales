package com.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.board.domain.BoardDTO;
import com.board.service.BoardService;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;

	@GetMapping(value = "/board/write.do")
	public String openBoardWrite(@RequestParam(value = "postNumber", required = false) Long postNumber, Model model) {
		if (postNumber == null) {
			model.addAttribute("board", new BoardDTO());
		} else {
			BoardDTO board = boardService.getBoardDetail(postNumber);
			if (board == null) {
				return "redirect:/board/list.do";
			}
			model.addAttribute("board", board);
		}

		return "board/write";
	}
	//컨트롤러에서 화면(HTML)으로 데이터를 전달하는 데 사용되는 Model 인터페이스입니다.
	// 여기서 model은 자체적으로 만들어져있는 거 

	
	@PostMapping(value = "/board/register.do")
	public String registerBoard(final BoardDTO params) {
		boolean isRegistered = boardService.registerBoard(params);
		try {
			if (isRegistered == false) {
				System.out.println("게시글 등록에 실패하였다는 메시지를 전달");
				// TODO => 게시글 등록에 실패하였다는 메시지를 전달
			}
		} catch (DataAccessException e) {
			System.out.println("데이터베이스 오류");
			System.out.println(isRegistered);
			
			// TODO => 데이터베이스 처리 과정에 문제가 발생하였다는 메시지를 전달

		} catch (Exception e) {
			System.out.println("ㅔ데이터베이스 오류");
			// TODO => 시스템에 문제가 발생하였다는 메시지를 전달
		}

		return "redirect:/board/list.do";
	}
	
	@GetMapping(value = "/board/list.do")
	public String openBoardList(Model model) {
		List<BoardDTO> boardList = boardService.getBoardList();
		model.addAttribute("boardList", boardList);
	
		return "board/list";
	}
	
	@GetMapping(value = "/board/view.do")
	public String openBoardDetail(@RequestParam(value = "postNumber", required = false) Long postNumber, Model model) {
		if (postNumber == null) {
			// TODO => 올바르지 않은 접근이라는 메시지를 전달하고, 게시글 리스트로 리다이렉트
			return "redirect:/board/list.do";
		}

		BoardDTO board = boardService.getBoardDetail(postNumber);
		if (board == null || "0".equals(board.getPostCorrent())) {
			// TODO => 없는 게시글이거나, 이미 삭제된 게시글이라는 메시지를 전달하고, 게시글 리스트로 리다이렉트
			return "redirect:/board/list.do";
		}
		model.addAttribute("board", board);

		return "board/view";
	}
	
	
	@PostMapping(value = "/board/delete.do")
	public String deleteBoard(@RequestParam(value = "postNumber", required = false) Long postNumber) {
		if (postNumber == null) {
			// TODO => 올바르지 않은 접근이라는 메시지를 전달하고, 게시글 리스트로 리다이렉트
			return "redirect:/board/list.do";
		}

		try {
			boolean isDeleted = boardService.deleteBoard(postNumber);
			if (isDeleted == false) {
				System.out.println(isDeleted);
				// TODO => 게시글 삭제에 실패하였다는 메시지를 전달
			}
		} catch (DataAccessException e) {
			System.out.println("delete 데이터베이스 오류");
			// TODO => 데이터베이스 처리 과정에 문제가 발생하였다는 메시지를 전달

		} catch (Exception e) {
			System.out.println("delete 데이터베이스 오류2");
			// TODO => 시스템에 문제가 발생하였다는 메시지를 전달
		}

		return "redirect:/board/list.do";
	}
	 

}