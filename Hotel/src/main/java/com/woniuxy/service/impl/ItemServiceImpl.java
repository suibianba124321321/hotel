package com.woniuxy.service.impl;

import java.util.List;

import javax.annotation.Resource;


import org.springframework.stereotype.Service;

import com.woniuxy.dao.ItemDAO;
import com.woniuxy.dao.PersonDAO;
import com.woniuxy.dao.RoomDAO;
import com.woniuxy.dao.TypeDAO;
import com.woniuxy.pojo.Item;
import com.woniuxy.pojo.Person;
import com.woniuxy.pojo.Type;
import com.woniuxy.service.ItemService;

@Service("itemService")
public class ItemServiceImpl implements ItemService{
	@Resource
	private ItemDAO itemDAO;
	@Resource
	private PersonDAO personDAO;
	
	@Resource
	private TypeDAO typeDAO;
	
	
	
	public TypeDAO getTypeDAO() {
		return typeDAO;
	}

	public void setTypeDAO(TypeDAO typeDAO) {
		this.typeDAO = typeDAO;
	}

	public PersonDAO getPersonDAO() {
		return personDAO;
	}

	public void setPersonDAO(PersonDAO personDAO) {
		this.personDAO = personDAO;
	}

	public ItemDAO getItemDAO() {
		return itemDAO;
	}

	public void setItemDAO(ItemDAO itemDAO) {
		this.itemDAO = itemDAO;
	}

	@Override
	public List<Item> findItemsByOrderid(Integer id){	
		List<Item> items=itemDAO.findItemsByOrdeId(id);	
		for(int i=0;i<items.size();i++){
			Item item=items.get(i);
			int personId=item.getPerson_id();
			Person person=personDAO.findPersonById(item.getPerson_id());
			items.get(i).setPerson(person);
			Type type=typeDAO.findTypeByid(item.getType_id());
			items.get(i).setType(type);
		}
		
		return items;
	}

	//通过person_id从item表中查询到item对象（order_id，price，number，deposit）
	@Override
	public Item findItemByPerson_id(Person person) {
		Item item =itemDAO.findItemByPerson_id(person);
		return item;
	}

	
}