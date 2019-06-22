package com.woniuxy.service;

import java.util.List;

import com.woniuxy.pojo.Order;

public interface OrderService {

	//插入订单
	public String  createOrder(Order order );
	//更新订单的pay_number
	public  String updatePayNumber(Order order); 
	//以id查找所有订单
	public List<Order> findAllOrder( Order order );
	
	//根据order_id 查找订单
	public Order findOrderById(Order order);
	
	//删除订单
	public String deleteOrder(Order order);
	
}
