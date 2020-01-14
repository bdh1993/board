package com.example.demo.board.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.board.domain.BoardVO;
import com.example.demo.board.service.BoardService;
import com.example.demo.board.service.CommentService;

@Controller
public class BoardController {

	@Resource(name="com.example.demo.board.service.BoardService")
	BoardService mBoardService;
	
	@Resource(name="com.example.demo.board.service.CommentService")
	CommentService mCommentService;
	
	@RequestMapping("/list") //게시판 리스트 화면 호출
	private String boardList(Model model) throws Exception {
		
		model.addAttribute("list", mBoardService.boardListService());
		
		return "list";
	}
	
	@RequestMapping("/detail/{bno}")
	private String boardDetail(@PathVariable int bno, Model model) throws Exception {
		
		model.addAttribute("detail", mBoardService.boardDetailService(bno));
		
		return "detail";
	}
	
	@RequestMapping("/insert") //게시글 작성폼 호출
	private String boardInsertForm() {
		
		return "insert";
	}
	
	@RequestMapping("/insertProc")
	private String boardInsertProc(HttpServletRequest request) throws Exception {
	
		BoardVO board = new BoardVO();
		
		board.setSubject(request.getParameter("subject"));
		board.setContent(request.getParameter("content"));
		board.setWriter(request.getParameter("writer"));
		
//		System.out.println(request.getParameter("subject"));
//		System.out.println(request.getParameter("content"));
//		System.out.println(request.getParameter("writer"));
		
		mBoardService.boardInsertService(board);
		
		return "redirect:/list";
	}
	
	@RequestMapping("/update/{bno}") //게시글 수정폼 호출
	private String boardUpdateForm(@PathVariable int bno, Model model) throws Exception {
		
		model.addAttribute("detail", mBoardService.boardDetailService(bno));
		
		return "update";
	}
	
	@RequestMapping("/updateProc")
	private String boardUpdateProc(HttpServletRequest request) throws Exception {
		
		BoardVO board = new BoardVO();
		board.setSubject(request.getParameter("subject"));
		board.setContent(request.getParameter("content"));
		board.setBno(Integer.parseInt(request.getParameter("bno")));
		
		mBoardService.boardUpdateService(board);
		
		return "redirect:/detail/"+request.getParameter("bno");
	}
	
	@RequestMapping("/delete/{bno}")
	private String boardDelete(@PathVariable int bno) throws Exception {
		
		mBoardService.boardDeleteService(bno);
		mCommentService.commentAllDeleteService(bno); //댓글 전체 삭제 메소드
		/*
		 * @Resource(name="com.example.demo.board.service.CommentService")
		 * CommentService mCommentService;
		 * 
		 * CommentMapper.java 인터페이스
		 * CommentService.java 인터페이스 구현
		 * CommentMapper.xml 쿼리문
		 */ 
		
		
		return "redirect:/list";
	}
}
