package com.woniuxy.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.woniuxy.pojo.Login;
import com.woniuxy.pojo.Person;
import com.woniuxy.service.LoginService;

@Controller
@RequestMapping("/loginer")
public class LoginController {
@Resource
private LoginService loginService;	
	
	
public LoginService getLoginService() {
	return loginService;
}


public void setLoginService(LoginService loginService) {
	this.loginService = loginService;
}


@RequestMapping("/allPerson")
@ResponseBody
//通过登录用户查询出已存在的person住户 
public List<Person>	allPerson(HttpSession session){
	/*Object attribute = session.getAttribute("uid");
	int uid=(int) attribute;*/
	int uid=1001;
	Login login = loginService.findLoginByid(uid);
	List<Person> persons = login.getPersons();
	return persons;
	/*if(attribute!=null){		
		
	}*/
	/*return null*/
	
	}
	
}
