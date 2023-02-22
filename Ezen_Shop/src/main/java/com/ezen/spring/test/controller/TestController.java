package com.ezen.spring.test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ezen.spring.test.dto.TestDto;

@RestController
public class TestController {

	@GetMapping(value="/")
	public String hello() {
		return "Hello World2";
	}
	
	@GetMapping(value = "/test")
	public TestDto test() {
		TestDto dto = new TestDto();
		dto.setName("남대문");
		dto.setAge(20);
		return dto;
	}
}
