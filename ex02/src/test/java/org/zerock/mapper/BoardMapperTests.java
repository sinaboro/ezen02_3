package org.zerock.mapper;

import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criterial;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTests {
	
	@Autowired
	private BoardMapper mapper;
	 
	@Test
	public void testGetList() {
//		List<BoardVO> lists = mapper.getList();
//		for ( BoardVO list : lists) {
//			log.info(list);
//		}
		mapper.getList().forEach(n->log.info(n));
	}
	
	@Test
	public void testInert() {
		BoardVO vo = new BoardVO();
		vo.setTitle("새로 작성하는 글");
		vo.setContent("새로 작성하는 내용");
		vo.setWriter("newble");
		
		int result = mapper.insert(vo);
		log.info("result = " + result);
	}

	@Test
	public void testInsertSelectKey() {
		BoardVO vo = new BoardVO();
		vo.setTitle("새로 작성하는 글2");
		vo.setContent("새로 작성하는 내용2");
		vo.setWriter("newble2");
		
		int result = mapper.insertSelectKey(vo);
		log.info("result = " + result);
	}
	
	
	@Test
	public void testRead() {
		BoardVO vo = mapper.read(4L);
		log.info(vo);
	}
	
	
	@Test
	public void testDelete() {
		int result = mapper.delete(4L);
		log.info("result : " + result);
	}
	
	
	@Test
	public void testUpdate() {
		BoardVO vo = new BoardVO();
		vo.setBno(3L);
		vo.setTitle("수정한 글 제목2");
		vo.setContent("수정한 글 내용2");
		int result = mapper.update(vo);
		log.info("result : " + result);
	}
	
	@Test
	public void getListWithPagin() {
		Criterial cri = new Criterial(3,15);
		
		List<BoardVO> list= mapper.getListWithPagin(cri);
		
		list.forEach(n->log.info(n));
	}
	
	
	@Test
	public void testSearch() {
		Criterial cri = new Criterial();
		
		cri.setKeyword("신용권");
		cri.setType("W");
		
		List<BoardVO> list = mapper.getListWithPagin(cri);
		list.forEach(n -> log.info(n));
	}

	@Test
	public void testGetTotalCount() {
		Criterial cri = new Criterial();
		
		cri.setKeyword("신용권");
		cri.setType("W");
		
		log.info(" ======= > " + mapper.getTotalCount(cri));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
