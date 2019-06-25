package com.woniuxy.service;

import java.util.List;

import com.woniuxy.pojo.Information;
import com.woniuxy.pojo.Person;

public interface PersonService {
	//添加person
	public void addPerson(Person person,int uid);
	//查找所有person
	public List<Person> findAllPerson();
	//通过idcard从person表中查询到person对象（入住姓名、手机号、person_id）
	public Person findPersonByIdCard(String idcard);
	//通过person_id从person表中查询到person对象（入住姓名、手机号、person_id）
	public Person findPersonByPerson_id(Information information);
}
