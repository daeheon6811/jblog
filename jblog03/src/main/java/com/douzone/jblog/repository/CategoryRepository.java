package com.douzone.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.vo.CategoryVo;

@Service
public class CategoryRepository {
	@Autowired
	private SqlSession sqlSession;
	
	String namespace = "category.";
	

	public void insertCategory(CategoryVo vo) {	
		sqlSession.insert(namespace + "insert" , vo);					
	}
	

	
}
