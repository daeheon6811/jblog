package com.douzone.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.CategoryRepository;
import com.douzone.jblog.vo.CategoryVo;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public void insertCategory(CategoryVo vo) {	
		categoryRepository.insertCategory(vo);			
	}
	
	public double findAllcount() {
		return categoryRepository.findAllcount();
	}
	
	public boolean delete(Long no) {
		 return categoryRepository.delete(no);
	}
	
	public List<CategoryVo> findByList(String id){
		return categoryRepository.findByList(id);
	}
	
	public CategoryVo findByNo(String id , Long no) {
		return categoryRepository.findByNo(id,no);
	}
	public double findCountByPostNo(String id , Long no) {
		return categoryRepository.findCountByPostNo(id,no);
	}
	
	public CategoryVo findByOne(String id) {
		return categoryRepository.findByOne(id);
	}
	
}
