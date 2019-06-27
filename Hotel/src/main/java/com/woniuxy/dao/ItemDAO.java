package com.woniuxy.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;

import com.woniuxy.pojo.Item;
import com.woniuxy.pojo.Order;
import com.woniuxy.pojo.Person;
import com.woniuxy.provider.ItemProvider;

public interface ItemDAO {

	@Insert("insert into item values (default,#{order_id},#{room_id},#{type_id},#{person_id},#{day_number},#{deposit},#{price},default)")
	public void insertItem(Item item);

	@Update("update item set flag=1 where order_id=#{order_id}")
	public void deleteItem(Order order);

	@Select("select * from item where order_id=#{orderid}")
	public List<Item> findItemsByOrdeId(Integer order_id);

	@Select("select * from item ")
	public List<Item> findAllItem();

	@UpdateProvider(type = ItemProvider.class, method = "updateByid")
	public void updateFlag(Item item);

	@Select("select * from item where item_id=#{item_id}")
	public Item findItemById(Integer item_id);

	@Select("select * from item where order_id=#{orderid} and flag=0")
	public List<Item> findItemsByOrdeIdAndflagEquelsZero(Integer order_id);

	@SelectProvider(type = ItemProvider.class, method = "selectBymap")
	public List<Item> findItemsBysome(Map<String, Object> map);

	@Update("update item set person_id=#{person_id} where item_id=#{item_id}")
	public void updatePerson(Item item);

	@Select("select * from item where person_id = #{person_id} and flag=0")
	public Item findItemByPerson_id(Person person);

}
