package com.woniuxy.service;

import java.util.List;

import com.woniuxy.pojo.Item;

public interface ItemService {

	public List<Item> findItemsByOrderid(Integer id);
	
	public List<Item> findItemsByOrdeIdAndflagEquelsZero(Integer order_id);
}
