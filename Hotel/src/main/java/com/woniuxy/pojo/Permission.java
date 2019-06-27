package com.woniuxy.pojo;

import java.io.Serializable;

public class Permission implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer permission_id;
	private String permission;
	public Integer getPermission_id() {
		return permission_id;
	}
	public void setPermission_id(Integer permission_id) {
		this.permission_id = permission_id;
	}
	public String getPermission() {
		return permission;
	}
	public void setPermission(String permission) {
		this.permission = permission;
	}
	public Permission(Integer permission_id, String permission) {
		super();
		this.permission_id = permission_id;
		this.permission = permission;
	}
	public Permission() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
