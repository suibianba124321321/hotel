package com.woniuxy.service;

import java.util.List;

import com.woniuxy.pojo.Item;

public interface ItemService {

	public List<Item> findItemsByOrderid(Integer id);

	public List<Item> allItem();

	public String inRoom(Item item);

	public String outRoom(Item item);
}
