package com.woniuxy.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.woniuxy.dao.LoginDAO;
import com.woniuxy.dao.PersonDAO;
import com.woniuxy.pojo.Person;
import com.woniuxy.service.PersonService;
@Service("personService")
@Transactional
public class PersonServiceImpl implements PersonService{
@Resource	
private PersonDAO personDAO;
@Resource
private LoginDAO loginDAO;

	public PersonDAO getPersonDAO() {
	return personDAO;
}

public LoginDAO getLoginDAO() {
		return loginDAO;
	}

	public void setLoginDAO(LoginDAO loginDAO) {
		this.loginDAO = loginDAO;
	}

public void setPersonDAO(PersonDAO personDAO) {
	this.personDAO = personDAO;
}

	@Override
	public void addPerson(Person person,int uid) {
		personDAO.addPerson(person);
		//插入后再查出id 
		Person nperson = personDAO.findPersonIdByIdcard(person);
		 Integer person_id = nperson.getPerson_id();
		 //插入login-person表 建立loginid与personid的联系
		 loginDAO.bindloginIdAndPersonId(uid, person_id);
		
	}

}
