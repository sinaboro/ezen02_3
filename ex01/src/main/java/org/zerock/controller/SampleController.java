package org.zerock.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.SampleDTO;
import org.zerock.domain.TodoDTO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import oracle.jdbc.proxy.annotation.Post;

@Controller
//@RestController
@RequestMapping("/sample/*")
@Log4j
public class SampleController {
	
	@PostMapping("/exUploadPost")
	public void exUploadPost(ArrayList<MultipartFile> files) {
		files.forEach(file -> {
			log.info("----------------");
			log.info("name : " + file.getOriginalFilename());
			log.info("size : " + file.getSize());
		});
	}
	
	@GetMapping("/exUpload")
	public void exUpload() {
		log.info("exUpload========>");
	}
	
	
	@GetMapping("/ex07")
	public ResponseEntity<String> ex07(){
		String msg = "{\"name\" : \"홍길동\"}";
		HttpHeaders header = new HttpHeaders();
		header.add("Content-Type", "application/json;charset=utf-8");
		
		return new ResponseEntity<String>(msg, header, HttpStatus.OK);
	}
	
	
	@GetMapping("/ex06")
	public @ResponseBody SampleDTO ex06(){
		SampleDTO dto = new SampleDTO();
		dto.setName("홍길동");
		dto.setAge(20);
		return dto;
	}

	@GetMapping("/ex06_1")
	public @ResponseBody String ex06_1(){
		int num = 2/0;
		return "ok";
	}
	
	@GetMapping("/ex05")
	public String ex05(RedirectAttributes rttr) {
		rttr.addAttribute("name", "AAA");
		rttr.addAttribute("age", 10);
		return "redirect:/";
	}
	
	@GetMapping("/ex04")
	public String ex04(SampleDTO dto  
			, @ModelAttribute("page") int page) {
		log.info(dto);
		log.info(page);
		//model.addAttribute("dto",dto);
		
		return "/sample/ex04";
	}
	// /WEB-INF/views//sample/ex04.jsp
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(dateFormat, false));
	}
	
	@GetMapping("/ex03")
	public @ResponseBody String ex03(TodoDTO todo) {
		log.info(todo.getDueDate());
		return "ex03";
	}
	
	@GetMapping("/todo")
	public String todo(TodoDTO dto) {
		log.info(dto);
		return "todo";
	}
	
	@GetMapping("/ex02List")
	public String ex02List(@RequestParam("idx") ArrayList<String> idx) {
		log.info(idx);
		return "ex02List";
	}
	
	@GetMapping("/ex02")
	public String ex02(String name, int age) {
		log.info(name);
		log.info(age);
		return "ex02";
	}
	
	@GetMapping("/ex01")
	public String ex01(SampleDTO dto) {
		log.info(dto);
		return "ex01";
	}
	
	@RequestMapping("/abc")   
	public String basic() {
		System.out.println("basic.............");
		return "/sample/basic";
	}
	
	@RequestMapping(value="/basic", method = {RequestMethod.GET, RequestMethod.POST})
	public void basicGet() {
		log.info("basicGet...........");
	}
	@GetMapping("/basicOnlyGet")
	public void basicGet2() {
		log.info("basicOnlyGet............");
	}
	
	@PostMapping("/basicOnlyGet")
	public void basicPost() {
		log.info("basicOnlyGet............");
	}
}
