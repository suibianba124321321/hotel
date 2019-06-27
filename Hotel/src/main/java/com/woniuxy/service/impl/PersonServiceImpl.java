package com.woniuxy.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.woniuxy.dao.ItemDAO;
import com.woniuxy.dao.LoginDAO;
import com.woniuxy.dao.PersonDAO;
import com.woniuxy.pojo.Item;
import com.woniuxy.pojo.Person;
import com.woniuxy.service.PersonService;
@Service("personService")
@Transactional
public class PersonServiceImpl implements PersonService{
@Resource	
private PersonDAO personDAO;
@Resource
private LoginDAO loginDAO;
@Resource
private ItemDAO itemDAO;

	public ItemDAO getItemDAO() {
	return itemDAO;
}

public void setItemDAO(ItemDAO itemDAO) {
	this.itemDAO = itemDAO;
}

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

	@Override
	public Person findPersonByIdcard(String idcard) {
		Person person=new Person();
		person.setIdcard(idcard);
		return personDAO.findPersonIdByIdcard(person);
	}

	@Override
	public String changeItemPerson(Integer itemid, Person person) {
		String msg="修改成功";
		Person per=personDAO.findPersonIdByIdcard(person);
		if(per!=null){
			person.setPerson_id(per.getPerson_id());
			personDAO.updatePersonByid(person);
			
		}else{
			personDAO.addPerson(person);
			person=personDAO.findPersonIdByIdcard(person);
		}
		Item item=new Item();
		item.setItem_id(itemid);
		item.setPerson_id(person.getPerson_id());
		itemDAO.updatePerson(item);
		
		return msg;
	}

}
