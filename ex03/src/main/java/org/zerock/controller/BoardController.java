package org.zerock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criterial;
import org.zerock.domain.pageDTO;
import org.zerock.service.BoardService;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/board/*")
public class BoardController {
	
	@Autowired
	private BoardService service;
	
	@GetMapping("/list")
	public void list(Criterial cri,  Model model) {
		log.info("list==============>");
		int total = service.getTotal(cri);
		
		model.addAttribute("list", service.getList(cri));
		model.addAttribute("pageMaker", new pageDTO(cri, total));
	}
	
	@GetMapping("/register")
	public void register() {
		
	}
	
	@PostMapping("/register")
	public String register(BoardVO board, RedirectAttributes rttr) {
		log.info("register==> " + board);
		service.register(board);
		
		rttr.addFlashAttribute("result", board.getBno());
		return "redirect:/board/list";
	}
	
	
	@PostMapping("/modify")
	public String modify(BoardVO board, @ModelAttribute("cri") Criterial cri, RedirectAttributes rttr) {
		log.info("modify===> ");
		log.info("cri===> " + cri.getPageNum());
		if(service.modify(board)) {
			rttr.addFlashAttribute("result","modify");
		}
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		rttr.addAttribute("type", cri.getType());
		rttr.addAttribute("keyword", cri.getKeyword());
//		return "redirect:/board/list";
		return "abc";
	}
	
	@PostMapping("/remove")
	public String remove(Long bno, @ModelAttribute("cri") Criterial cri, RedirectAttributes rttr) {
		log.info("remove===> ");
		
		if(service.remove(bno)) {
			rttr.addFlashAttribute("result","delete");
		}
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		rttr.addAttribute("type", cri.getType());
		rttr.addAttribute("keyword", cri.getKeyword());
		return "redirect:/board/list";
	}

	@GetMapping({"/get", "/modify"})
	public void get(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criterial cri, Model model) {
		log.info("get or modify ======> ");
		model.addAttribute("board", service.get(bno));
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
