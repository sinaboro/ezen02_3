package com.ezen.ex02.board.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ezen.ex02.board.BoardVO;
import com.ezen.ex02.common.JDBCUtil;

@Repository 
public class BoardDAO {
	
	private Connection conn=null;
	private PreparedStatement pstmt =null;
	private ResultSet rs = null;
	
	
	//저장
	public void insertBoard(BoardVO vo) throws SQLException {
		System.out.println("insertBoard====>");
		
		String sql = "insert into board(seq, title, writer, content) "
				+ "values((select nvl(max(seq),0)+1 from board), ?, ?, ?)";
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getWriter());
			pstmt.setString(3, vo.getContent());
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(pstmt, conn);
		}
	}
	
	//전체 데이타 불러오기
	public List<BoardVO> getBoardList(){
		System.out.println("===> getBoardList");
		List<BoardVO> list = new ArrayList();
		
		String sql = "select * from board order by seq desc";
				
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BoardVO vo = new BoardVO();
				vo.setSeq(rs.getInt("seq"));
				vo.setTitle(rs.getString("title"));
				vo.setWriter(rs.getString("writer"));
				vo.setContent(rs.getString("content"));
				vo.setRegDate(rs.getDate("regdate"));
				vo.setCnt(rs.getInt("cnt"));
				
				list.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				JDBCUtil.close(rs, pstmt, conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	
	public BoardVO getBoard(int seq) {
		System.out.println("===> getBoard");
		BoardVO vo = new BoardVO();
		
		String sql = "select * from board where seq = ?";
				
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, seq);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				vo.setSeq(rs.getInt("seq"));
				vo.setTitle(rs.getString("title"));
				vo.setWriter(rs.getString("writer"));
				vo.setContent(rs.getString("content"));
				vo.setRegDate(rs.getDate("regdate"));
				vo.setCnt(rs.getInt("cnt"));
			}
				
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				JDBCUtil.close(rs, pstmt, conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return vo;
	}
	
	
	
	
	
	
	
	
}
