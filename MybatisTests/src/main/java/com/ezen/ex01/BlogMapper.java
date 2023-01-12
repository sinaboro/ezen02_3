package com.ezen.ex01;

public interface BlogMapper {
	
	public BoardVO selectOne(int id);
	public void insert(BoardVO vo);
}
