package com.woniuxy.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

import com.woniuxy.pojo.Item;
import com.woniuxy.pojo.Order;

public interface ItemDAO {

	@Insert("insert into item values (default,#{order_id},#{room_id},#{person_id},#{day_number},#{deposit},#{price},default)")
	public void insertItem(Item item);
	
	@Update("update item set flag=1 where order_id=#{order_id}")
	public void deleteItem(Order order);
}
