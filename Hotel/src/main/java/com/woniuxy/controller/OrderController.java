package com.woniuxy.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import com.woniuxy.pojo.Order;
import com.woniuxy.service.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {
	@Resource
	private OrderService orderService;
	

	public OrderService getOrderService() {
		return orderService;
	}


	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	@RequestMapping("/create")
	@ResponseBody
	public Map<String, Object> createOrder(Order order,@RequestParam(value="persons[]")Integer[] persons){
		
		order.setLogin_id(1001);
		System.out.println(persons);
		order.setPersonID(persons);
		System.out.println(order);
	
		 Map<String, Object> map=orderService.createOrder(order);
		
		return map;
	}
	
	@RequestMapping("/orders")
	@ResponseBody
	public List<Order> allOrderByid(){
		Order order=new Order();
		
		order.setLogin_id(1001);
		List<Order> orders=orderService.findAllOrder(order);
	   
		
		return orders;
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public String delete( Integer id){
		Order order=new Order();
		order.setOrder_id(id);
		System.out.println(order);
		String msg=orderService.deleteOrder(order);
		
		return msg;
	}
	
	
}
