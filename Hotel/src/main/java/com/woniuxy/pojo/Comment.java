package com.woniuxy.pojo;



public class Comment {
private Integer comment_id;
private Integer typeid;
private Integer loginid;
private String description;
private Integer flag;
private Login login;


public Login getLogin() {
	return login;
}
public void setLogin(Login login) {
	this.login = login;
}
public Integer getComment_id() {
	return comment_id;
}
public void setComment_id(Integer comment_id) {
	this.comment_id = comment_id;
}
public Integer getTypeid() {
	return typeid;
}
public void setTypeid(Integer typeid) {
	this.typeid = typeid;
}
public Integer getLoginid() {
	return loginid;
}
public void setLoginid(Integer loginid) {
	this.loginid = loginid;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public Integer getFlag() {
	return flag;
}
public void setFlag(Integer flag) {
	this.flag = flag;
}
@Override
public String toString() {
	return "Comment [comment_id=" + comment_id + ", typeid=" + typeid + ", loginid=" + loginid + ", description="
			+ description + ", flag=" + flag + ", login=" + login + "]";
}



}
