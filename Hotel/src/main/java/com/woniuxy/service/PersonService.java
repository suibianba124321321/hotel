package com.woniuxy.service;

import com.woniuxy.pojo.Person;

public interface PersonService {
	public void addPerson(Person person,int uid);

	public Person findPersonByIdcard(String idcard);

	public String changeItemPerson(Integer itemid, Person person);
}
