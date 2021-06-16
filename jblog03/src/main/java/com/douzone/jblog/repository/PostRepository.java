package com.douzone.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.vo.PostVo;

@Service
public class PostRepository {
	@Autowired
	private SqlSession sqlSession;
	
	String namespace = "post.";
	

	public void insertPost(PostVo vo) {	
		sqlSession.insert(namespace + "insert" , vo);					
	}
}
