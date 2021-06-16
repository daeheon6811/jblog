package com.douzone.jblog.service;

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

	

}
