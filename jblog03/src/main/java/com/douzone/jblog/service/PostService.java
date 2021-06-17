package com.douzone.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.PostRepository;
import com.douzone.jblog.vo.PostVo;

@Service
public class PostService {
	
	@Autowired 
	PostRepository postRepository;
	
	public void insertPost(PostVo vo) {	
		postRepository.insertPost(vo);				
	}

	public List<PostVo> findByList(String id , Long no) {
		return postRepository.findByList(id , no);
	}
	
	public PostVo findByNo(String id , Long no) {
		return postRepository.findByNo(id , no);
	}

}
