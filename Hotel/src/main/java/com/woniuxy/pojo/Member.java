package com.woniuxy.pojo;

import java.io.Serializable;

public class Member implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer member_id;
	private String name;
	private String tel;
	private String idcard;
	//积分
	private Integer grade;
	//等级（1：普通会员   2：铂金会员   3：黑卡会员）
	private Integer rank;
	private Integer flag;
	public Integer getMember_id() {
		return member_id;
	}
	public void setMember_id(Integer member_id) {
		this.member_id = member_id;
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
	public Integer getGrade() {
		return grade;
	}
	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	public Integer getRank() {
		return rank;
	}
	public void setRank(Integer rank) {
		this.rank = rank;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	@Override
	public String toString() {
		return "Member [member_id=" + member_id + ", name=" + name + ", tel=" + tel + ", idcard=" + idcard + ", grade="
				+ grade + ", rank=" + rank + ", flag=" + flag + "]";
	}
	
	
	
}
