package com.woniuxy.pojo;

import java.io.Serializable;

public class Role implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer role_id;
	private String role;
	
	
	public Integer getRole_id() {
		return role_id;
	}
	public void setRole_id(Integer role_id) {
		this.role_id = role_id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
}
