package org.zerock.service;

import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class SampleServiceimpl implements SampleService{

	@Override
	public Integer doAdd(String str1, String str2) throws Exception {
		int result =  Integer.parseInt(str1)+Integer.parseInt(str2);
		return result;
	}

	@Override
	public String doReturn(String a , String b) {
		log.info("doReturn 함수 동작..");
		return a + b + "";
	}
	
	
}
