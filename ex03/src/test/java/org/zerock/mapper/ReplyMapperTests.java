package org.zerock.mapper;

import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.Criterial;
import org.zerock.domain.ReplyVO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j


public class ReplyMapperTests {
	
	private Long[] bnoArr= {7013656L,7013656L,7013655L,7013654L,7013653L};
	
	@Autowired
	private ReplyMapper mapper;
	
	@Test
	public void testInsert() {
		
		IntStream.range(1, 10).forEach(i->{
			
			ReplyVO vo = new ReplyVO();
			vo.setBno(bnoArr[i%5]);
			vo.setReply("탯글 테스트" + i);
			vo.setReplyer("reply" + i);
			mapper.insert(vo);
		});
	}
	
	@Test
	public void testRead() {
//		ReplyVO vo =  mapper.read(1L);
		log.info("vo ==> " + mapper.read(12L));
	}

	@Test
	public void testDelete() {
//		ReplyVO vo =  mapper.read(1L);
		log.info("vo ==> " + mapper.delete(1L));
	}

	@Test
	public void testUpdate() {
		ReplyVO vo =  new ReplyVO();
		vo.setReply("수정된 댓글");
		vo.setRno(1L);
		log.info("vo ==> " + mapper.update(vo));
	}

	@Test
	public void testGetListWithPaging() {
		
		log.info("vo ==> " + 
		mapper.getListWithPaging(new Criterial(), bnoArr[2]));
	}
	
	
	@Test
	public void testGetListWithPaging2() {
		
		log.info("vo ==> " + 
		mapper.getListWithPaging(new Criterial(2,5), bnoArr[0]));
	}
	
	
	@Test
	public void testGetCountByBno() {
		log.info("count ==> " + 
	mapper.getCountByBno(7013656L));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
