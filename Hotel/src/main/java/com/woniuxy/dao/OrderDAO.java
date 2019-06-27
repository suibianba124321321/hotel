package com.woniuxy.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Select;

import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.springframework.stereotype.Repository;

import com.woniuxy.pojo.Order;
import com.woniuxy.provider.OrderProvider;

@Repository
public interface OrderDAO {

	@InsertProvider(type=OrderProvider.class,method="insert")
	public void  insertOrder(Order order ); 
	
	@Update("update `order` set pay_number=#{pay_number},order_state=1 where order_number=#{order_number}")
	public  void updatePayNumber(Order order); 
	
	
	@SelectProvider(type=OrderProvider.class,method="select")
	public List<Order> findOrder( Order order );
	
	@Select("update  `order` set flag=1 ,order_state=4 where order_id=#{order_id}")
	public void deleteOrder(Order order);
	@Select("select * from `order` where order_number=#{orderNumber} ")
	public Order findOrderByNumber(String orderNumber);
	@Select("select * from `order` where order_id=#{id} ")
	public Order findOrderById(Integer id);
	@Select("select * from `order` ")
	public List<Order> findAllOrder();
	@Update("update `order` set order_state=#{order_state} where  order_id=#{order_id}") 
	public void updateState(Order order);
	@SelectProvider(type=OrderProvider.class,method="selectBymap")
	public List<Order> findOrdersByMap(Map<String, Object> map);
	@UpdateProvider(type=OrderProvider.class,method="update")
	public void updateOrder(Order order);
	@Update("update `order` set sumprice=#{sumprice} where order_id=#{order_id}")
	public void updateSumpriceByid(Order order);

}
