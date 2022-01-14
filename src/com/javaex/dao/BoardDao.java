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
			pstmt.setInt(4, vo.getuserNo());
			
			int count = pstmt.executeUpdate();
			
			System.out.println(count + "건 등록되었습니다.(GuestDao)");

		} catch (SQLException e) {
			System.out.println("error: " + e);
		}

		close();
	}
	public List<BoardVo> boardList(){
		List<BoardVo> boardList=new ArrayList<BoardVo>();
		getConnection();
		
		try {
			String query = "";
			query +="select bo.no, ";
			query += "        title, ";
			query += "        name, ";
			query += "        hit, ";
			query += "        reg_date ";
			query += " from users us ,board bo ";
			query += " where us.no=bo.user_no ";
			query += " order by reg_date desc ";
			
			pstmt = conn.prepareStatement(query);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int no = rs.getInt("bo.no");
				String title = rs.getString("title");
				String name= rs.getString("name");
				int hit = rs.getInt("hit");
				String regDate = rs.getString("reg_date");

				BoardVo vo = new BoardVo(no,title,name,hit,regDate);
				boardList.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("error: " + e);
		}
		
		close();
	return boardList;
	}
	
}
