package org.zerock.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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
	
	@ResponseBody
	@RequestMapping(value="/basic", method = { RequestMethod.GET, RequestMethod.POST})
	public String basicGet() {
		log.info("basicGet...........");
		return "ok";
	}

	@ResponseBody
//	@GetMapping("/basicOnlyGet/{userID}/{age}")
	
	//http://localhost:8080/sample/basicOnlyGet/100
//	@GetMapping("/basicOnlyGet/{userID}")
	//http://localhost:8080/sample/basicOnlyGet/BBB/20
	@GetMapping("/basicOnlyGet/{userID}/{age}")
//	public String basicGet2(@PathVariable("userID") String userid) {
//	public String basicGet2(@PathVariable String userID) {
	public String basicGet2(@PathVariable String userID, @PathVariable int age) {
		log.info("basicOnlyGet............" + userID + " : " + age);
		return "abc";
	}
	
	
	@PostMapping("/basicOnlyGet")
	public void basicPost() {
		log.info("basicOnlyGet............");
	}
	
	@ResponseBody
	@GetMapping("/abc/{num}")
	public String abc(@PathVariable int num) {
		return "ok-get";
	}

	@ResponseBody
	@PostMapping("/abc/{num}")
	public String abc2(@PathVariable int num) {
		return "ok-post";
	}
	
	@ResponseBody
	@DeleteMapping("/abc/{num}")
	public String abc3(@PathVariable int num) {
		return "ok-delete";
	}
	
	

	@ResponseBody
	@RequestMapping("/hearder")
	public String headers(HttpServletRequest request,
						  HttpServletResponse response,
						  HttpMethod httpMethod,
						  Locale locale,
						  @RequestHeader MultiValueMap<String, String> headerMap,
						  @RequestHeader("host") String host,
						  @CookieValue(value="myCookie", required = false) String cookie) {
		
		log.info("-------------------------");
		log.info(request);
		log.info(response);
		log.info(httpMethod);
		log.info(locale);
		log.info(headerMap);
		log.info(host);
		log.info(cookie);
		return "ok";
	}
	
//	@ResponseBody
	@RequestMapping("/request-param1")
	public void requestParam1(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String userid = request.getParameter("userid");
		int  age =  Integer.parseInt(request.getParameter("age"));
		log.info(userid + " : " + age);
		response.getWriter().write("ok");
	}

	@ResponseBody
	@RequestMapping("/request-param2")
	public String requestParam2(@RequestParam String userid, 
							  @RequestParam int age 	){
		log.info(userid + " : " + age);
		return "ok";
	}

	@ResponseBody
	@RequestMapping("/request-param3")
	public String requestParam3(String userid, int age){
		log.info(userid + " : " + age);
		return "ok";
	}
	
	@ResponseBody
	@RequestMapping("/request-param4")
	public String requestParam4(@RequestParam(defaultValue = "noUser") String userid, 
							  @RequestParam(defaultValue = "20") int age 	){
		log.info(userid + " : " + age);
		return "ok";
	}

	@ResponseBody
	@RequestMapping("/request-param5")
	public String requestParam5(@ModelAttribute SampleDTO dto){
		log.info(dto.getName() + " : " + dto.getAge());
		return "ok";
	}

	@ResponseBody
	@RequestMapping("/request-param6")
	public String requestParam6(SampleDTO dto){
		log.info(dto.getName() + " : " + dto.getAge());
		return "ok";
	}
	
	@PostMapping("/request-body1")
	public void requestBody1(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ServletInputStream inputStream  = request.getInputStream();
		String message = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
		log.info("message : " + message);
		
		response.getWriter().write(message);
	}

//	@ResponseBody
	@PostMapping("/request-body2")
	public HttpEntity<String> requestBody2(HttpEntity<String> entity){
		
		String message = entity.getBody();
		HttpHeaders headers = entity.getHeaders();
		log.info("headers : " + headers);
		log.info("message : " + message);
		return new HttpEntity<String>(message);
	}

	@ResponseBody
	@PostMapping("/request-body3")
	public String requestBody3(@RequestBody String message){
		
		log.info("message : " + message);
		return message;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
