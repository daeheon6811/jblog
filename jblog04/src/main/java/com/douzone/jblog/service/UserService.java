package com.douzone.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.UserRepository;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;
import com.douzone.jblog.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	
	
	
	public void insertUser(UserVo userVo , BlogVo  blogVo , CategoryVo categoryVo
			, PostVo postVo) {
		
		userRepository.insertUser(userVo,blogVo,categoryVo , postVo);
	}
	
	public UserVo getUser(String id) {
		return  userRepository.getUser(id);
	}
	
	public UserVo getUser(String id , String password) {
		return  userRepository.getUser(id, password);
	}
	
	
	
	
}
