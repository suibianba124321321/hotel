package com.woniuxy.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.woniuxy.pojo.Person;
import com.woniuxy.service.PersonService;

@Controller
@RequestMapping("/person")
public class PersonController {
@Resource
private PersonService  personService;

public PersonService getPersonService() {
	return personService;
}

public void setPersonService(PersonService personService) {
	this.personService = personService;
}

@RequestMapping("/addperson")
@ResponseBody
public String addPerson(Person person,HttpSession session){
	/*Object ouid = session.getAttribute("uid");
	int uid=(int) ouid;*/
	int uid=1001;
	String result="添加失败";
	result = personService.addPerson(person, uid);
	
	
	return result;}	
	
}
