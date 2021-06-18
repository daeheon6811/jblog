package com.douzone.jblog.repository;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.UserVo;

@Repository
public class UserRepository {
	
	
	@Autowired
	private SqlSession sqlSession;
	
	String namespace = "user.";
	
	
	
	public void insertUser(UserVo userVo , BlogVo  blogVo , CategoryVo categoryVo ) {	
		sqlSession.insert(namespace + "insert" , userVo);	
		sqlSession.insert("blog." + "insert" , blogVo);
		sqlSession.insert("category." + "insert" , categoryVo);
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
