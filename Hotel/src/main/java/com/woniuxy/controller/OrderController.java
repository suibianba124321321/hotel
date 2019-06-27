package com.woniuxy.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import com.woniuxy.pojo.Order;
import com.woniuxy.pojo.Person;
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
		order.setPersonID(persons);
		
		
	
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
		
		String msg=orderService.deleteOrder(order);
		
		return msg;
	}

	@RequestMapping("/pay")
	@ResponseBody
	public String pay(Order order){
		String msg=orderService.updateState(order);
		return msg;
	}

	
	@RequestMapping("/oneorder")
	@ResponseBody
	public Order currentOrder(Integer orderid){
		
		Order order = orderService.findOrderById(orderid);
		return order ;
	}
	@RequestMapping("/preCreatOrder")
	@ResponseBody
	public Map<String,Object> preCreatOrder(Integer roomid,Order order,Person person){
		
		
		Map<String,Object> map=orderService.preCreatOrder(roomid,order,person);
		return map;
	}
	@RequestMapping("/openOrder")
	@ResponseBody
	public Map<String,Object> openOrder(Integer roomid,Order order,Person person){
		Map<String,Object> map=orderService.openOrder(roomid,order,person);
		return map;
	}
	
	@RequestMapping("/searchOrders")
	@ResponseBody
	public List<Order> searchOreder(String account,Integer state,String inTime){
		List<Order> orders=orderService.searchOrders(account,state,inTime);
		return orders;
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public String updateOrder(Order order){
		
		String msg=orderService.updateOrder(order);
		
		return msg;
	}
}
