package com.douzone.jblog.vo;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

public class UserVo {
	
	
	@NotEmpty
	@Length(min=2 , max=8)
	private String id;
	
	@NotEmpty
	@Length(min=2 , max=8)
	private String name;
	
	
	@NotEmpty
	@Length(min = 4 , max  =  16)
	private String password;
	
	
	private String join_date;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getJoin_date() {
		return join_date;
	}
	public void setJoin_date(String join_date) {
		this.join_date = join_date;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", join_date=" + join_date + "]";
	}
	
	

}
