package org.zerock.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@Log4j
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class SampleTxServiceTests {

	@Autowired
	private SampleTxService service;
	
	
	@Test
	public void testLong() {
		String str = "이날 '뉴스토마토'는 천공이 대통령 관저의 결정 과정에 개입했다고 보도했다.\" 사실이 아니고 유감이면 뉴스 토마토 업체 고소하고 영장 청구해서 압수수색하고 허위인지 사실인지 밝";
				
		
		log.info(str.getBytes().length);
		service.addData(str);
	}
}










