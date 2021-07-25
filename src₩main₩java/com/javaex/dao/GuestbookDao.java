package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestbookVo;

@Repository
public class GuestbookDao {

	@Autowired
	private SqlSession sqlSession;
	
	//리스트
	public List<GuestbookVo> getGuestbookList() {
		
		List<GuestbookVo> guestbookList = sqlSession.selectList("guestbook.selectList");
		
		return guestbookList;
	}
	
	//저장
	public int guestbookInsert(GuestbookVo guestbookVo) {
		int count = sqlSession.insert("guestbook.guestbookInsert", guestbookVo);
		return count;
	}
	
	//삭제
	public int guestbookDelete(GuestbookVo guestbookVo) {
		int count = sqlSession.delete("guestbook.guestbookDelete", guestbookVo);
		return count;
	}

}

