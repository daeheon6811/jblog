package com.douzone.jblog.vo;

public class CategoryVo {
	
	private Long no;
	private String name;
	private String desc;
	private String reg_date;
	private String blog_id;
	
	/* */
	private int post_count;
	
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public String getBlog_id() {
		return blog_id;
	}
	public void setBlog_id(String blog_id) {
		this.blog_id = blog_id;
	}
	@Override
	public String toString() {
		return "category [no=" + no + ", name=" + name + ", desc=" + desc + ", reg_date=" + reg_date + ", blog_id="
				+ blog_id + "]";
	}
	public double getPost_count() {
		return post_count;
	}
	public void setPost_count(int post_count) {
		this.post_count = post_count;
	}
	
	
	
	

}
