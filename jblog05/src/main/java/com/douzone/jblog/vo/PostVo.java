package com.douzone.jblog.vo;

public class PostVo {
	
	//PK
	private Long no;
	private String title;
	private String contents;
	private String reg_date;
	private Long category_no;
	
	
	/*FK*/
	private String blog_id;
	
	
	
	public String getBlog_id() {
		return blog_id;
	}
	public void setBlog_id(String blog_id) {
		this.blog_id = blog_id;
	}
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public Long getCategory_no() {
		return category_no;
	}
	public void setCategory_no(Long category_no) {
		this.category_no = category_no;
	}
	@Override
	public String toString() {
		return "Post [no=" + no + ", title=" + title + ", contents=" + contents + ", reg_date=" + reg_date
				+ ", category_no=" + category_no + "]";
	}
	
	

	
	
}
