package com.douzone.jblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.service.CategoryService;
import com.douzone.jblog.service.UserService;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.UserVo;

@Controller
public class UserController {
	
	
	@Autowired
	UserService userService;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	BlogService blogService;
	
	@GetMapping("user/join")
	public String join() {
		return "user/join";
	}
	/*나중에 Service에서 처리 해보기!*/
	@PostMapping("user/join")
	public String join(@ModelAttribute UserVo userVo) {
		
		
		
		
		
		BlogVo blogVo = new BlogVo();
		userService.insertUser(userVo);	
		blogVo.setId(userVo.getId());
		blogVo.setTitle("test");
		blogVo.setLogo("test");
		blogService.insertBlog(blogVo);
		if(categoryService.findAllcount() == 0) {
			CategoryVo categoryVo = new CategoryVo();
			categoryVo.setDesc("기본");
			categoryVo.setName("기본");
			categoryVo.setBlog_id(userVo.getId());
			categoryService.insertCategory(categoryVo);
		}
		return "redirect:/";
	}
	
	@GetMapping("user/login")
	public String login() {
		return "user/login";
	}
	
	
	
	
	
	
}
