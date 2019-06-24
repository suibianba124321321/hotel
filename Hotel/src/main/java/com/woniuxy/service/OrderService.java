package com.woniuxy.service;

import java.util.List;
import java.util.Map;

import com.woniuxy.pojo.Item;
import com.woniuxy.pojo.Order;

public interface OrderService {

	//插入订单
	public Map<String, Object>   createOrder(Order order );
	//更新订单的pay_number
	public  String updatePayNumber(Order order); 
	//以id查找所有订单
	public List<Order> findAllOrder( Order order );
	
	//根据order_id 查找订单

	public Order findOrderById(Integer id);

	//删除订单
	public String deleteOrder(Order order);
	//通过order_id从order表中查询到order对象（order_state,in_time,out_time）
	public Order findOrderByOrder_id(Item item);
	
}
