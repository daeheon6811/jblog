package com.douzone.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.UserVo;

@Repository
public class UserRepository {
	
	
	@Autowired
	private SqlSession sqlSession;
	
	String namespace = "user.";
	
	
	
	public void insertUser(UserVo vo) {	
		sqlSession.insert(namespace + "insert" , vo);					
	}
	
	
	
	
	

}
