package org.zerock.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criterial;
import org.zerock.mapper.BoardMapperTests;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardServiceTests {
	
	@Autowired
	private BoardService boardService;
	
	@Test
	public void testRegister() {
		BoardVO vo =  new BoardVO();
		//vo.setBno(null);
		vo.setTitle("서비스 제목1");
		vo.setContent("서비스 내용");
		vo.setWriter("서비스1");
		boardService.register(vo);
	}
	
	@Test
	public void testGet() {
		BoardVO vo = boardService.get(7L);
		log.info("vo======> " + vo);
	}
	
	@Test
	public void testModify() {
		BoardVO vo = new BoardVO();
		
		vo.setBno(21L);
		vo.setTitle("서비스 수정1");
		vo.setContent("서비스 수정 내용1");
		boolean bool = boardService.modify(vo);
		log.info(bool);
	}
	
	@Test
	public void testDelete() {
	   	boardService.remove(21L);
	}
	
	@Test
	public void testGetList() {
//		List<BoardVO> boardVO= boardService.getList();
//		boardVO.forEach(n->log.info(n));
		Criterial cri = new Criterial(3,15);
		boardService.getList(cri).forEach(n->log.info(n));
		
	}
	
	
	
	
	
	
	
	
}
