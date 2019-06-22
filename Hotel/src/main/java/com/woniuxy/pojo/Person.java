package com.woniuxy.pojo;

import java.io.Serializable;

/**
 * 入住人员信息（包括正在入住的，也包括住过的）
 * 
 * 		
 * @author Administrator
 *
 */

public class Person implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer person_id;
	private String name;
	private String tel;
	private String idcard;
	private Integer flag;
	public Integer getPerson_id() {
		return person_id;
	}
	public void setPerson_id(Integer person_id) {
		this.person_id = person_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	@Override
	public String toString() {
		return "Person [person_id=" + person_id + ", name=" + name + ", tel=" + tel + ", idcard=" + idcard + ", flag="
				+ flag + "]";
	}
	
	
	
}
