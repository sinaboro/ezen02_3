package com.ezen.ex02.board.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.ex02.board.BoardService;
import com.ezen.ex02.board.BoardVO;
import com.ezen.ex02.common.Log4jAdvice;
import com.ezen.ex02.common.LogAdvice;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardDAO boardDAO;
	
//	private LogAdvice log;
//	private Log4jAdvice log;
	
	public BoardServiceImpl() {
//		log = new LogAdvice();
//		log = new Log4jAdvice();
	}
	
	@Override
	public void insertBoard(BoardVO vo){
		try {
//			log.pringLog();
//			log.printLogging();
			
//			if(vo.getSeq()==0) {
//				throw new IllegalArgumentException();
//			}
			
			boardDAO.insertBoard(vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<BoardVO> getBoardList() {

//		log.pringLog();
//		log.printLogging();
		return boardDAO.getBoardList();
	}

	@Override
	public BoardVO getBoard(int seq) {
//		log.pringLog();
//		log.printLogging();
		return boardDAO.getBoard(seq);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
