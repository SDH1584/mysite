package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.javaex.vo.UserVo;

public class UserDao {

	// 0. import java.sql.*;
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "webdb";
	private String pw = "webdb";

	// �޼ҵ� gs

	// �޼ҵ� �Ϲ�
	// �����ϱ�
	private void getConnection() {
		try {
			// 1. JDBC ����̹� (Oracle) �ε�
			Class.forName(driver);

			// 2. Connection ������
			conn = DriverManager.getConnection(url, id, pw);
			// System.out.println("���Ӽ���");

		} catch (ClassNotFoundException e) {
			System.out.println("error: ����̹� �ε� ���� - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}

	// �ݱ�
	public void close() {
		// 5. �ڿ�����
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

	// ���� �޼ҵ�(ȸ������)
	public int insert(UserVo userVo) {

		int count = 0;

		getConnection();

		try {
			// 3. SQL�� �غ� / ���ε� / ����
			// ���ڿ�
			String query = "";
			query += " insert into users ";
			query += " values(seq_users_no.nextval, ?, ?, ?, ?) ";

			// ������
			pstmt = conn.prepareStatement(query);

			// ���ε�
			pstmt.setString(1, userVo.getId());
			pstmt.setString(2, userVo.getPassword());
			pstmt.setString(3, userVo.getName());
			pstmt.setString(4, userVo.getGender());

			// ����
			count = pstmt.executeUpdate();

			// 4.���ó��
			System.out.println(count + "���� ��ϵǾ����ϴ�(UserDao)");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		close();

		return count;
	}

	// ȸ������1�� ��������(�α��ο�)
	public UserVo getUser(String id, String password) {

		UserVo userVo = null;

		getConnection();

		try {
			// 3. SQL�� �غ� / ���ε� / ����
			// ���ڿ�
			String query = "";
			query += " select  no, ";
			query += "         name ";
			query += " from users ";
			query += " where id = ? ";
			query += " and password = ? ";

			// ������
			pstmt = conn.prepareStatement(query);

			// ���ε�
			pstmt.setString(1, id);
			pstmt.setString(2, password);

			// ����
			rs = pstmt.executeQuery();

			// 4.���ó��
			while (rs.next()) {
				int no = rs.getInt("no");
				String name = rs.getString("name");

				userVo = new UserVo();
				userVo.setNo(no);
				userVo.setName(name);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		close();
		return userVo;
	}

	// ����� ���� �������� �޼ҵ� (ȸ������ ������ ���), �޼ҵ� �����ε�
	public UserVo getUser(int no) {
		UserVo userVo = null;

		getConnection();

		try {

			// 3. SQL�� �غ� / ���ε� / ����
			String query = ""; // ������ ���ڿ������, ? ����
			query += " select no, id, password, name, gender ";
			query += " from users ";
			query += " where no = ? ";

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);

			rs = pstmt.executeQuery();

			// 4.���ó��
			while (rs.next()) {
				int rNo = rs.getInt("no");
				String id = rs.getString("id");
				String password = rs.getString("password");
				String name = rs.getString("name");
				String gender = rs.getNString("gender");

				userVo = new UserVo(rNo, id, password, name, gender);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		close();

		return userVo;
	}

	// ����� ���� ���� �޼ҵ�
	public int update(UserVo vo) {
		int count = 0;
		getConnection();

		try {
			// 3. SQL�� �غ� / ���ε� / ����
			String query = ""; // ������ ���ڿ������, ? ����

			query += " update users ";
			query += " set name = ?, ";
			query += "     password = ?, ";
			query += "     gender = ? ";
			query += " where no = ? ";

			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getGender());
			pstmt.setInt(4, vo.getNo());

			count = pstmt.executeUpdate();

			// 4.���ó��
			System.out.println("userDao.update(): " + count + "�� ȸ������ ����");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		close();

		return count;
	}

}