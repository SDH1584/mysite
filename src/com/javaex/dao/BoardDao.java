package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.BoardVo;
import com.javaex.vo.GuestbookVo;

public class BoardDao {

	 Connection conn = null;
	 PreparedStatement pstmt = null;
	 ResultSet rs = null;

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "webdb";
	private String pw = "webdb";

	// 메소드 일반
	private void getConnection() {
		try {
			Class.forName(driver);
			
			conn = DriverManager.getConnection(url, id, pw);
			System.out.println("접속성공");
		
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error: " + e);
		}
	}

	private void close() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("error." + e);
		}
	}
	//등록
	public void insert(BoardVo vo) {
		getConnection();
		try {
			String query = "";
			query += " insert into board ";
			query += " values(seq_board_no.nextval, ?, ?, ?, sysdate,?)";

			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setInt(3, 0);
			pstmt.setInt(4, vo.getUserNo());
			
			int count = pstmt.executeUpdate();
			
			System.out.println(count + "건 등록되었습니다.(GuestDao)");

		} catch (SQLException e) {
			System.out.println("error: " + e);
		}

		close();
	}
	  public List<BoardVo> boardList() {
	         List<BoardVo> boardList = new ArrayList<BoardVo>();
	         getConnection();
	         try {
	         
	            String query = "";
	            query += " select  us.no no, ";
	            query += "         title, ";
	            query += "         content, ";
	            query += "         hit, ";
	            query += "         to_char(reg_date, 'yyyy-mm-dd') reg_date, ";
	            query += "         bo.user_no user_no, ";
	            query += "         name ";
	            query += " from board bo, users us ";
	            query += " where bo.user_no = us.no ";
	            
	            pstmt = conn.prepareStatement(query);
	               
	            rs = pstmt.executeQuery();
	            
	         while(rs.next()) {
	            int no = rs.getInt("no");
	            String title = rs.getString("title");
	            String content = rs. getString("content");
	            int hit = rs.getInt("hit");
	            String regDate = rs.getString("reg_date");
	            int userno = rs.getInt("user_no");
	            String name = rs.getString("name");
	            
	            BoardVo boardvo = new BoardVo(no, title, content, hit, regDate, userno, name);
	            
	            boardList.add(boardvo);
	            System.out.println(boardvo);
	         }
	         }catch (SQLException e) {
	            System.out.println("error:" + e);
	         }
	         close();
	         return boardList;
	      }
	  
	      

}
