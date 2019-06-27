package com.woniuxy.service;

import java.util.List;
import java.util.Map;

import com.woniuxy.pojo.Order;
import com.woniuxy.pojo.Person;

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
	//所有订单
	public List<Order> allOrder();
	//线下支付成功后修改订单状态
	public String updateState(Order order);
	//线下预定
	public Map<String,Object> preCreatOrder(Integer roomid, Order order, Person person);
	//线下开单
	public Map<String,Object> openOrder(Integer roomid, Order order, Person person);
	//根据条件查找订单
	public List<Order> searchOrders(String account, Integer state, String inTime);
	//修改订单信息
	public String updateOrder(Order order);

}
