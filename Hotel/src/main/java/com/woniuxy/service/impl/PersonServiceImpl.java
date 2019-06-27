package com.woniuxy.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.woniuxy.dao.LoginDAO;
import com.woniuxy.dao.PersonDAO;
import com.woniuxy.pojo.Information;
import com.woniuxy.pojo.Person;
import com.woniuxy.service.PersonService;

@Service("personService")
@Transactional
public class PersonServiceImpl implements PersonService {
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
	public String addPerson(Person person, int uid) {
		// 首先要遍历出所有的persons的idcard进行比较 如果有重复的返回身份证重复了
		List<Person> persons = personDAO.findPersonByLogin_id(uid);
		for (Person person2 : persons) {
			if (person2.getIdcard().equals(person.getIdcard())) {
				return "身份证号已存在";
			}
		}
		personDAO.addPerson(person);
		// 插入后再查出id
		Person nperson = personDAO.findPersonIdByIdcard(person);
		Integer person_id = nperson.getPerson_id();
		// 插入login-person表 建立loginid与personid的联系
		loginDAO.bindloginIdAndPersonId(uid, person_id);

		return "添加成功";
	}

	// 查找所有person
	@Override
	public List<Person> findAllPerson() {

		return personDAO.findAllPerson();
	}

	// 通过idcard从person表中查询到person对象（入住姓名、手机号、person_id）
	@Override
	public Person findPersonByIdCard(String idcard) {
		Person person = personDAO.findPersonByIdCard(idcard);
		return person;
	}

	// 通过person_id从person表中查询到person对象（入住姓名、手机号、person_id）
	@Override
	public Person findPersonByPerson_id(Information information) {
		return personDAO.findPersonByPerson_id(information);
	}

	@Override
	public Person findPersonByName(Person person) {

		return personDAO.findPersonByName(person);
	}

}
