package com.douzone.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.vo.BlogVo;

@Service
public class BlogRepository {
	
	@Autowired
	private SqlSession sqlSession;
	
	String namespace = "blog.";
	
	public void insertBlog(BlogVo vo) {	
		sqlSession.insert(namespace + "insert" , vo);					
	}
}
