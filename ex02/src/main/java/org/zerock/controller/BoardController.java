package org.zerock.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
import org.zerock.mapper.BoardMapper;
import org.zerock.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/board/*")
@AllArgsConstructor
@Log4j
public class BoardController {
	
	private final BoardService service;
	/*
	 * private final BoardMapper boardMapper; public BoardController(BoardService
	 * boardService, BoardMapper boardMapper) { this.boardService = boardService;
	 * this.boardMapper = boardMapper; }
	 */
	
	@GetMapping("/list")
	public void list(Model model) {
//		List<BoardVO> list = service.getList();
		model.addAttribute("list", service.getList());
		log.info(service.getList());
	}
	
	@PostMapping("/register")
	public String register(BoardVO board, RedirectAttributes rttr) {
		log.info("register : " + board);
		service.register(board);
		rttr.addFlashAttribute("result", board.getBno());  ///딱 한번만 전송한다.
		return "redirect:/board/list";   //   PRG방식   "/board/list" ==> 포워드. 
	}
	
	
	@GetMapping("/get")
	public void get(@RequestParam("bno") Long bno, Model model) {
		log.info("/get..........");
		model.addAttribute("board", service.get(bno));
		// WEB-INF/views/board/get.jsp 찾아간다.
	}
	
	@PostMapping("/modify")
	public String modify(BoardVO board, RedirectAttributes rttr) {
		log.info("/modify......");
		
		if(service.modify(board)) {
			rttr.addAttribute("result", "success");
		}
		return "redirect:/board/list";  //PRG
	}
	
	
	@PostMapping("/remove")
	public String remove(@RequestParam("bno") Long bno, RedirectAttributes rttr) {
		log.info("/remove......");
		
		if(service.remove(bno)) {
			rttr.addAttribute("result", "success");
		}
		return "redirect:/board/list";  //PRG
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
