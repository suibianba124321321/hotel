package com.woniuxy.service;

import java.util.List;

import com.woniuxy.pojo.Item;

import com.woniuxy.pojo.Person;

public interface ItemService {

	public List<Item> findItemsByOrderid(Integer id);

	public List<Item> findItemsByOrdeIdAndflagEquelsZero(Integer order_id);

	// 通过person_id从item表中查询到item对象（order_id，price，number，deposit）
	public Item findItemByPerson_id(Person person);

	public List<Item> allItem();

	public String inRoom(Item item);

	public String outRoom(Item item);

	public List<Item> findItemsBySome(String name, Integer state);

	public String cancelRoom(Item item);

}
