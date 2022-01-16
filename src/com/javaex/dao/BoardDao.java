package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.BoardVo;

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


	public void close() {
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
			System.out.println("error:" + e);
		}
	}

	public List<BoardVo> boardList() {
		List<BoardVo> boardList = new ArrayList<BoardVo>();

		getConnection();
		try {
			String query = "";
			query += " select   name, ";
			query += "          bo.no, ";
			query += "          title, ";
			query += "          content, ";
			query += "          hit, ";
			query += "          to_char(reg_date, 'YYYY-MM-DD HH:MI') reg_date, ";
			query += "          user_no ";
			query += " from     users us, board bo ";
			query += " where   	us.no=bo.user_no ";
			query += " order by bo.no desc ";

			pstmt = conn.prepareStatement(query);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				int no = rs.getInt("no");
				String title = rs.getString("title");
				String content = rs.getString("content");
				int hit = rs.getInt("hit");
				String regDate = rs.getString("reg_date");
				int userNo = rs.getInt("user_no");
				String name = rs.getString("name");
				
				BoardVo boardVo = new BoardVo(no, title, content, hit, regDate, userNo, name);
				boardList.add(boardVo);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		close();
		return boardList;
	}
	public BoardVo read(int num) {
		BoardVo boardVo = null;
		getConnection();
		try {
			String	query = "";
			query += " select   name, ";
			query += "          bo.no, ";
			query += "          title, ";
			query += "          content, ";
			query += "          hit, ";
			query += "          to_char(reg_date, 'YYYY-MM-DD HH:MI') reg_date, ";
			query += "          user_no ";
			query += " from     users us, board bo ";
			query += " where   	us.no=bo.user_no ";
			query += " and		bo.no = ? ";

			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, num);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				int no = rs.getInt("no");
				String title = rs.getString("title");
				String content = rs.getString("content");
				int hit = rs.getInt("hit");
				String regDate = rs.getString("reg_date");
				int userNo = rs.getInt("user_no");
				String name = rs.getString("name");
				
				boardVo = new BoardVo(no, title, content, hit, regDate, userNo, name);
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		close();
		return boardVo;
	}
	
	
	public void insert(BoardVo vo) {

		getConnection();

		try {

			String query = "";
			query += " insert into board ";
			query += " values (seq_board_no.nextval, ?, ?, 0, sysdate, ?) ";

			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setInt(3, vo.getUserNo());

			int count = pstmt.executeUpdate();

			System.out.println("[" + count + "건 추가되었습니다.(BoardDao)]");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		close();
	}
	public void delete(int no) {
		getConnection();

		try {

			String query = "";
			query += " delete board ";
			query += " where no = ? ";

			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, no);

			int count = pstmt.executeUpdate();

			System.out.println("[" + count + "건 삭제되었습니다.(BoardDao)]");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		close();
	}

	public void update(BoardVo vo) {
		getConnection();

		try {

			String query = "";
			query += " update board ";
			query += " set    title = ?, ";
			query += " 		  content = ? ";
			query += " where  no = ? ";

			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setInt(3, vo.getNo());

			int count = pstmt.executeUpdate();

			System.out.println("[" + count + "건 수정되었습니다.(BoardDao)]");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		close();
	}

	public   BoardVo hit(int no) {
		getConnection();
		try {
		String query = "";
		query = "";
		query += " update  board ";
		query += " set     hit = nvl(hit,0)+1 ";
		query += " where   no = ? ";

		
		
		pstmt = conn.prepareStatement(query);
		
		pstmt.setInt(1, no);
		
		int count = pstmt.executeUpdate();

		System.out.println("["+count+"건 실행되었습니다.(Board)]");
		}catch (SQLException e) {
			System.out.println("error:" + e);
		}
		close();
		return null;
	}
	
}