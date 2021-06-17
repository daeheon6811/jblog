package com.douzone.jblog.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.PostVo;

@Repository
public class PostRepository {
	@Autowired
	private SqlSession sqlSession;

	String namespace = "post.";

	public void insertPost(PostVo vo) {
		sqlSession.insert(namespace + "insert", vo);
	}

	public List<PostVo> findByList(String id , Long no) {
		
		System.out.println( "```````````````````````id : " + id  +  "no :"+ no);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("no", no);
		
		return sqlSession.selectList(namespace + "findByList", map);
	}
	
	public PostVo findByNo(String id , Long no) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("no", no);
		return sqlSession.selectOne(namespace + "findByNo", map);
	}
	

}
