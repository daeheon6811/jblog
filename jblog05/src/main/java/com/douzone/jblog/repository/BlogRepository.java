package com.douzone.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.BlogVo;

@Repository
public class BlogRepository {
	
	@Autowired
	private SqlSession sqlSession;
	
	String namespace = "blog.";
	
	public void insertBlog(BlogVo vo) {	
		sqlSession.insert(namespace + "insert" , vo);					
	}
	
	public void update(BlogVo vo) {	
		sqlSession.update(namespace + "update" , vo);					
	}
	
    public BlogVo findByNo(String id) {
    	return sqlSession.selectOne(namespace + "findByNo" , id);
    	}
	

}
