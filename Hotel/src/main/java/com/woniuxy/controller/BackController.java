package com.woniuxy.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.woniuxy.pojo.Item;
import com.woniuxy.pojo.Login;
import com.woniuxy.pojo.Member;
import com.woniuxy.pojo.Order;
import com.woniuxy.service.BackService;
import com.woniuxy.service.ItemService;
import com.woniuxy.service.LoginService;
import com.woniuxy.service.MemberService;
import com.woniuxy.service.OrderService;
@Controller
@RequestMapping("/back")
public class BackController {
	@Resource
	private BackService backService;
	@Resource
	private OrderService orderService;
	
	@Resource
	private ItemService itemService;
	@Resource
	private LoginService loginService;
	@Resource
	private MemberService memberService;
	
	public OrderService getOrderService() {
		return orderService;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	public ItemService getItemService() {
		return itemService;
	}

	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}

	public BackService getBackService() {
		return backService;
	}

	public void setBackService(BackService backService) {
		this.backService = backService;
	}

	@RequestMapping("/welcome.html")
	public ModelAndView wellcome(){
		ModelAndView modelAndView=new ModelAndView("/back/welcome");
		Map<String, Object> map=backService.number();
		modelAndView.addAllObjects(map);
		return modelAndView;
	}
	
	
	@RequestMapping("/order-list.html")
	public ModelAndView orderList(){
		ModelAndView modelAndView=new ModelAndView("back/order-list");
		List<Order> orders=orderService.allOrder();
		List<Login> logins=new ArrayList<>();
		List<Member> members=new ArrayList<>();
		//查询购买户
		for(int i=0;i<orders.size();i++){
			Order order =orders.get(i);
			if(order.getLogin_id()!=null && order.getLogin_id()!=0){
				Login login=loginService.findLoginByid(order.getLogin_id());
				logins.add(i, login);
			}
			if(order.getMember_id()!=null && order.getMember_id()!=0){
				Member member=memberService.findMemberByid(order.getMember_id());
				members.add(i, member);
			}
		}
		
		modelAndView.addObject("orders", orders);
		modelAndView.addObject("logins", logins);
		modelAndView.addObject("members", members);
		return modelAndView;
	}
	
	@RequestMapping("/item-list.html")
	public ModelAndView itemList(){
		ModelAndView modelAndView=new ModelAndView("/back/item-list");
		List<Item> items=itemService.allItem();
		modelAndView.addObject("items", items);
		return modelAndView;
	
	}
	
	@RequestMapping("/order-view.html")
	public ModelAndView item(Integer id){
		ModelAndView modelAndView=new ModelAndView("/back/order-view");
		List<Item> items=itemService.findItemsByOrderid(id);
		
		modelAndView.addObject("items", items);
		return modelAndView;
	}

	@RequestMapping("/singleorder.html")
	public ModelAndView singleorder(Integer id){
		ModelAndView modelAndView=new ModelAndView("/back/singleorder");
		Order order=orderService.findOrderById(id);
		modelAndView.addObject("order", order);
		Login login=null;
		Member member=null;
		//查询购买户		
		if(order.getLogin_id()!=null && order.getLogin_id()!=0){
			login=loginService.findLoginByid(order.getLogin_id());
						
		}
		if(order.getMember_id()!=null && order.getMember_id()!=0){
			member=memberService.findMemberByid(order.getMember_id());
						
		}
		modelAndView.addObject("login", login);
		modelAndView.addObject("member", member);	
				
		return modelAndView;
	}
	
}
