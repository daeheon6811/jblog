package com.douzone.jblog.repository;

import java.util.HashMap;
import java.util.Map;

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
	
	public UserVo getUser(String id) {
		return sqlSession.selectOne(namespace + "findById" , id);
	}
	
	public UserVo getUser(String id , String password) {
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("i", id);
		map.put("p", password);
		return sqlSession.selectOne(namespace + "findByIdAndPassword" , map);
		
	}
	
	
	
	
	

}
