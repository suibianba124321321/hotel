package com.woniuxy.service.impl;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.woniuxy.dao.LoginDAO;
import com.woniuxy.pojo.Login;

import com.woniuxy.service.LoginService;
@Service("loginService")
public class LoginServiceImpl implements LoginService{
@Resource
 private LoginDAO loginDao;
 
	public LoginDAO getLoginDao() {
	return loginDao;
}

public void setLoginDao(LoginDAO loginDao) {
	this.loginDao = loginDao;
}

@Override
public Login findLoginByid(int login_id) {
	
	return loginDao.findLoginByid(login_id);
}



}



	


