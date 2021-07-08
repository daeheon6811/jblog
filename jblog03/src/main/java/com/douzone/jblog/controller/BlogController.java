package com.douzone.jblog.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.jblog.dto.JsonResult;
import com.douzone.jblog.security.Auth;
import com.douzone.jblog.security.AuthUser;
import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.service.CategoryService;
import com.douzone.jblog.service.FileUploadService;
import com.douzone.jblog.service.PostService;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;
import com.douzone.jblog.vo.UserVo;

@Controller
@RequestMapping("/{id:(?!assets).*}") // assets 제외한 문자는 받아라 : ? 있어도 되고 없어도 되고 , ! 제외
public class BlogController {

	@Autowired
	CategoryService categoryService;

	@Autowired
	PostService postService;

	@Autowired
	BlogService blogService;

	@Autowired
	private FileUploadService fileUploadService;

	@RequestMapping({ "", "/{pathNo1}", "{pathNo1}/{pathNo2}" })
	public String index(@PathVariable("id") String id, @PathVariable("pathNo1") Optional<Long> pathNo1,
			@PathVariable("pathNo2") Optional<Long> pathNo2, Model model) {

		if (id.equals("guest")) {
			String title = "안녕하세요. 비회원 페이지 입니다. ";
			String contents = "안녕하세요. 비회원 내용 입니다. ";
			model.addAttribute("title", title);
			model.addAttribute("contents", contents);
			return "blog/main";
		}

		Long categoryNo = 0L;
		Long postNo = 0L;

		/* 포스트 글 눌렸을떄 */
		if (pathNo2.isPresent()) {
			categoryNo = pathNo1.get();
			postNo = pathNo2.get();

			PostVo postVo = postService.findByNo(id, postNo);
			if (postVo == null) {
				return "blog/main";
			} else {
				model.addAttribute("title", postVo.getTitle());
				model.addAttribute("contents", postVo.getContents());
				model.addAttribute("postList", postService.findByList(id, categoryNo));
			}
		}
		/* 카테고리 눌렸을때 */
		else if (pathNo1.isPresent()) {

			categoryNo = pathNo1.get();
			if (postService.findByList(id, categoryNo) == null) {
				return "blog/main";
			} else {
				CategoryVo categoryVo = categoryService.findByOne(id);
				PostVo postVo = postService.findByOne(id, categoryVo.getNo());

				model.addAttribute("title", postVo.getTitle());
				model.addAttribute("contents", postVo.getContents());
				model.addAttribute("postList", postService.findByList(id, categoryNo));
			}

		}
		/* 처음 입장 */
		else {

			CategoryVo categoryVo = categoryService.findByOne(id);
			PostVo postVo = postService.findByOne(id, categoryVo.getNo());
			if (postVo == null) {
				return "blog/main";
			} else {

				model.addAttribute("postList", postService.findByList(id, categoryVo.getNo()));
				model.addAttribute("title", postVo.getTitle());
				model.addAttribute("contents", postVo.getContents());
			}

		}

		List<CategoryVo> cgList = categoryService.findByList(id);
		model.addAttribute("blogVo", blogService.findByNo(id));
		model.addAttribute("categoryList", cgList);

		return "blog/main";
	}

	public  String id = "test";
	
	
	@Auth
	@GetMapping("admin/basic")
	public String adminBasic(@AuthUser UserVo authUser, Model model) {

		
		model.addAttribute("blogVo", blogService.findByNo(authUser.getId()));
		return "blog/admin/basic";
	}

	@Auth()
	@RequestMapping(value = "admin/basic", method = RequestMethod.POST)
	public String adminBasic(@RequestParam("file") MultipartFile file, @AuthUser UserVo authUser,
			@ModelAttribute BlogVo blogVo) {

		String url = fileUploadService.restore(file);

		blogVo.setId(authUser.getId());
		if (url == null || url.equals("null")) {
			blogVo.setLogo("default");
		} else {
			blogVo.setLogo(url);
		}
		blogService.update(blogVo);
		return "redirect:/" + authUser.getId();
	}

	@Auth
	@GetMapping("admin/category")
	public String adminCategory(@AuthUser UserVo authUser, Model model ) {
		model.addAttribute("blogVo", blogService.findByNo(authUser.getId()));
		
		List<CategoryVo> list = categoryService.findByList(authUser.getId());

		/*
		 * VO내의 COUNT값을 저장 시킨다 이유는 View에서 count를 출력 시켜야 하기 때문
		 */
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setPost_count((int) categoryService.findCountByPostNo(authUser.getId(), list.get(i).getNo()));
		}

		model.addAttribute("categoryList", list);
		return "blog/admin/category";
	}
	
	
	@Auth
	@ResponseBody
	@RequestMapping(value = "admin/category/ajax" , method = RequestMethod.GET)
	public JsonResult adminCategoryAjax(@AuthUser UserVo authUser, Model model ) {
		// model.addAttribute("blogVo", blogService.findByNo(authUser.getId()));
		List<CategoryVo> list = categoryService.findByList(authUser.getId());
		/*
		 * VO내의 COUNT값을 저장 시킨다 이유는 View에서 count를 출력 시켜야 하기 때문
		 */
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setPost_count((int) categoryService.findCountByPostNo(authUser.getId(), list.get(i).getNo()));
			System.out.println(list.get(i));
		}		
		return JsonResult.success(list);
	}

	@Auth
	@PostMapping("admin/category")
	public String adminCategory(@AuthUser UserVo authUser, @ModelAttribute CategoryVo categoryVo) {
	
		categoryVo.setBlog_id(authUser.getId());

		categoryService.insertCategory(categoryVo);
	;
		return "redirect:/" + authUser.getId() + "/admin/category";
	}
	
	@Auth
	@PostMapping("admin/delete")
	public JsonResult adminDelete(@AuthUser UserVo authUser ,  @PathVariable("no") Long no) {
		
	    
        Long data = 0L;
		
		if (!categoryService.delete(no)) {
			data = -1L;
			return JsonResult.success(data);
		} 
	
		data = no;
		return JsonResult.success(data);
	}
	
	
	@Auth
	@ResponseBody
	@RequestMapping(value = "admin/category/ajax" , method = RequestMethod.POST)
	public JsonResult adminCategoryAjax(@AuthUser UserVo authUser, @RequestBody CategoryVo categoryVo) {
		int statuscount = (int) categoryService.findCountByPostNo(authUser.getId(), categoryVo.getNo());
		categoryVo.setBlog_id(authUser.getId());
		categoryVo.setPost_count(statuscount);
		
	
		
		System.out.println("포스트 수 : " + statuscount);
		categoryService.insertCategory(categoryVo);
		return JsonResult.success(categoryVo);
	}

	
	@Auth
	@GetMapping("admin/write")
	public String adminWrite(@AuthUser UserVo authUser, Model model , @ModelAttribute BlogVo blogVo) {
		model.addAttribute("blogVo", blogService.findByNo(authUser.getId()));
		model.addAttribute("categoryList", categoryService.findByList(authUser.getId()));
		return "blog/admin/write";
	}

	@Auth
	@PostMapping("admin/write")
	public String adminWrite(@AuthUser UserVo authUser, @ModelAttribute PostVo postVo,
			@ModelAttribute(value = "category") Long no) {

		System.out.println("categoryNo" + no);

		CategoryVo categoryVo = categoryService.findByNo(authUser.getId(), no);

		postVo.setCategory_no(categoryVo.getNo());

		postService.insertPost(postVo);
		return "redirect:/" + authUser.getId();
	}
	
	
	
	
	
	
	

}
