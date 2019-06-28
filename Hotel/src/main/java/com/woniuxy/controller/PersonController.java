package com.woniuxy.controller;


import java.util.HashMap;
import java.util.Map;

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
	Object ouid = session.getAttribute("uid");
	int uid=(int) ouid;
	
	String result="添加失败";
	result = personService.addPerson(person, uid);
	
	
	return result;
	}	

	
@RequestMapping("/searchPerson")
@ResponseBody
public Map<String, Object> searchPerson(String idcard){
	Map<String, Object> map =new HashMap<String, Object>();
		map.put("msg", "成功");
		Person person=personService.findPersonByIdcard(idcard);
		if(person==null){
			map.put("msg", "查无此人");
		}
		map.put("person", person);
		return map;
	
}
@RequestMapping("/change")
@ResponseBody
public String changeItemPerson(Integer itemid,Person person){
	
	String msg=personService.changeItemPerson(itemid,person);
	return msg;
}

}
