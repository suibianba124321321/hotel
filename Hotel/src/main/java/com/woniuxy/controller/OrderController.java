package com.woniuxy.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	public String createOrder(@RequestBody Order order){
		
		
		String msg=orderService.createOrder(order);
		
		return msg;
	}
	
	@RequestMapping("/all")
	@ResponseBody
	public List<Order> all(Order order){
		
		return orderService.findAllOrder(order);
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public String delete(Order order){
		
		String msg=orderService.deleteOrder(order);
		
		return msg;
	}
	
	
}
