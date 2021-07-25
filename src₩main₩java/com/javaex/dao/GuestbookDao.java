package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestbookVo;

@Repository
public class GuestbookDao {

	@Autowired
	private DataSource dataSource; // 내부적으로 가지고있어서 알아서 연결해줌

	// 0. import java.sql.*;
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private void getConnection() {
		try {

			conn = dataSource.getConnection();

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}

	public void close() {
		// 5. 자원정리
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

	// 방명록 전체 가져오기
	public List<GuestbookVo> getGuestbookList() {

		List<GuestbookVo> guestbookList = new ArrayList<GuestbookVo>();

		getConnection();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " select  no, ";
			query += "         name, ";
			query += "         password, ";
			query += "         content, ";
			query += "         reg_date ";
			query += " from guestbook ";
			query += " order by reg_date desc ";

			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();

			// 4.결과처리
			while (rs.next()) {
				int no = rs.getInt("no");
				String name = rs.getString("name");
				String password = rs.getString("password");
				String content = rs.getString("content");
				String regDate = rs.getString("reg_date");

				GuestbookVo guestbookVo = new GuestbookVo(no, name, password, content, regDate);
				guestbookList.add(guestbookVo);
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		close();

		return guestbookList;
	}

	// 방명록 저장하기
	public int guestbookInsert(GuestbookVo vo) {
		int count = -1;
		getConnection();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " insert into guestbook (no, name, password, content, reg_date) ";
			query += " values (seq_guestbook_no.nextval, ?, ?, ?, sysdate) ";

			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getContent());

			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건 등록");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		close();

		return count;
	}

	// 방명록 삭제
	public int guestbookDelete(GuestbookVo vo) {
		int count = -1;
		getConnection();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " delete from guestbook ";
			query += " where no = ? ";
			query += " and password = ? ";

			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, vo.getNo());
			pstmt.setString(2, vo.getPassword());

			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건 삭제");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		close();

		return count;
	}

}