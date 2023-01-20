package org.zerock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criterial;
import org.zerock.mapper.BoardMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@AllArgsConstructor
public class BoardServiceImpl implements BoardService{

	private final BoardMapper boardMapper;
		
	@Override
	public void register(BoardVO vo) {
	   int result = boardMapper.insertSelectKey(vo);
	}

	@Override
	public BoardVO get(Long bno) {
		return boardMapper.read(bno);
	}

	@Override
	public boolean modify(BoardVO vo) {
		return boardMapper.update(vo)==1 ? true: false;
	}

	@Override
	public boolean remove(Long bno) {
		return boardMapper.delete(bno) == 1? true:false;
	}

	@Override
	public List<BoardVO> getList(Criterial cri) {
		return boardMapper.getListWithPagin(cri);
	}

	@Override
	public int getTotal(Criterial cri) {
		return boardMapper.getTotalCount(cri);
	}

	
}
