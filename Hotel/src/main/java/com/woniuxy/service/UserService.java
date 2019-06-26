package com.woniuxy.service;


import javax.servlet.http.HttpSession;

import com.woniuxy.pojo.Login;

public interface UserService {
   
	public String phongRegisterCheck(String tel);
	
	public String accountRegisterCheck(Login login);
	
	public String codeCheck(Login login,HttpSession session);
	
	public String accountLogin(Login login);
	
	public String checkPwdByaccount(Login login);
	
	public String ifPhoneExit(Login login,HttpSession session);
	
	

}
