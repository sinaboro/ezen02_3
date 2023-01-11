package com.ezen.ex02.common;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

@Service
@Aspect   // advice + pointcut
public class LogAdvice {
	
	@Pointcut("execution(* com.ezen.ex02..*Impl.*(..))")  //pointcut
	public void allPointcut() {}
	
	@Before("allPointcut()")  //advice
	public void pringLog() {
		System.out.println("[공통 로그] 비즈니스 로직 수행 전 동작");
	}
}
