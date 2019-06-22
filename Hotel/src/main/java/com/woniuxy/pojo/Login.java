package com.woniuxy.pojo;

	
	

import java.io.Serializable;
import java.util.List;

public class Login implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer login_id;
	private String account;
	private String pwd;
	//会员id
	private Integer member_id;
	private Integer flag;
	private List<Person> persons;
	
	
	public List<Person> getPersons() {
		return persons;
	}
	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

	public Integer getLogin_id() {
		return login_id;
	}
	public void setLogin_id(Integer login_id) {
		this.login_id = login_id;
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
	public Integer getMember_id() {
		return member_id;
	}
	public void setMember_id(Integer member_id) {
		this.member_id = member_id;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	@Override
	public String toString() {
		return "Login [login_id=" + login_id + ", account=" + account + ", pwd=" + pwd + ", member_id=" + member_id
				+ ", flag=" + flag + "]";
	}
	
	
	
	
	
}
