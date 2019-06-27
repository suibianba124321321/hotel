package com.woniuxy.pojo;

import java.io.Serializable;

public class Manager implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer manager_id;
	private String account;
	private String pwd;
	private Integer role_id;
	private Integer flag;
	public Integer getManager_id() {
		return manager_id;
	}
	public void setManager_id(Integer manager_id) {
		this.manager_id = manager_id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public Integer getRole_id() {
		return role_id;
	}
	public void setRole_id(Integer role_id) {
		this.role_id = role_id;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public Manager(Integer manager_id, String account, String pwd, Integer role_id, Integer flag) {
		super();
		this.manager_id = manager_id;
		this.account = account;
		this.pwd = pwd;
		this.role_id = role_id;
		this.flag = flag;
	}
	public Manager() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
