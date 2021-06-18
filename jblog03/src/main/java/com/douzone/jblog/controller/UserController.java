package com.douzone.jblog.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.service.CategoryService;
import com.douzone.jblog.service.UserService;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;
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
	public String join(@ModelAttribute UserVo userVo) {
		return "user/join";
	}

	/* 나중에 Service에서 처리 해보기! */
	@PostMapping("user/join")
	public String join(@ModelAttribute @Valid UserVo userVo, BindingResult result, Model model) {
		if (result.hasErrors()) {
			/*
			 * List<ObjectError> list = result.getAllErrors(); for(ObjectError error : list)
			 * { System.out.println(error); }
			 */
			// model.addAttribute("UserVo", user);
			model.addAllAttributes(result.getModel());
			return "user/join";
		}
		if (userVo.getId().equals("guest")) {
			return "user/join";
		}
		
		CategoryVo categoryVo = new CategoryVo();
		categoryVo.setDesc("기본");
		categoryVo.setName("기본");
		categoryVo.setBlog_id(userVo.getId());
		BlogVo blogVo = new BlogVo();
		blogVo.setId(userVo.getId());
		blogVo.setTitle("test");
		blogVo.setLogo("/assets/images/spring-logo.jpg");
		PostVo postVo = new PostVo();
		postVo.setBlog_id(userVo.getId());
		postVo.setTitle("기본 제목");
		postVo.setContents("기본 내용");		
		
		
		userService.insertUser(userVo, blogVo, categoryVo , postVo);

		return "redirect:/user/joinsuccess";
	}

	@GetMapping("user/joinsuccess")
	public String joinsuccess() {
		return "/user/joinsuccess";
	}

	@GetMapping("user/login")
	public String login() {
		return "user/login";
	}

}
