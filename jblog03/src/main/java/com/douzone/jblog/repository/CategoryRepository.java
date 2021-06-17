package com.douzone.jblog.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.CategoryVo;

@Repository
public class CategoryRepository {
	@Autowired
	private SqlSession sqlSession;

	String namespace = "category.";

	public void insertCategory(CategoryVo vo) {
		sqlSession.insert(namespace + "insert", vo);
	}

	public double findAllcount() {
		return sqlSession.selectOne(namespace + "findAllCount");
	}

	public List<CategoryVo> findByList(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		return sqlSession.selectList(namespace + "findByList", map);

	}

	public CategoryVo findByNo(String id, Long no) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("no", no);
		map.put("id", id);
		return sqlSession.selectOne(namespace + "findByNo", map);

	}

	public double findCountByPostNo(String id, Long no) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("no", no);
		map.put("id", id);
		return sqlSession.selectOne(namespace + "findCountByPostNo", map);
	}

}
