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
	private String tel;
	//会员id
	private Integer member_id;
	private Integer flag;
	private Boolean rm;
	private List<Person> persons;
	
	
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public Boolean getRm() {
		return rm;
	}
	public void setRm(Boolean rm) {
		this.rm = rm;
	}
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
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Login(Integer login_id, String account, String pwd, Integer member_id, Integer flag, String tel,
			Boolean rm) {
		super();
		this.login_id = login_id;
		this.account = account;
		this.pwd = pwd;
		this.member_id = member_id;
		this.flag = flag;
		this.tel = tel;
		this.rm = rm;
	}
	@Override
	public String toString() {
		return "Login [login_id=" + login_id + ", account=" + account + ", pwd=" + pwd + ", tel=" + tel + ", member_id="
				+ member_id + ", flag=" + flag + ", rm=" + rm + ", persons=" + persons + "]";
	}
	


	
}
