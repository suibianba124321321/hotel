package com.woniuxy.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.woniuxy.pojo.Information;
import com.woniuxy.pojo.Item;
import com.woniuxy.pojo.Login;
import com.woniuxy.pojo.Member;
import com.woniuxy.pojo.Order;
import com.woniuxy.pojo.Room;
import com.woniuxy.pojo.Type;
import com.woniuxy.service.BackService;
import com.woniuxy.service.InformationService;
import com.woniuxy.service.ItemService;
import com.woniuxy.service.LoginService;
import com.woniuxy.service.MemberService;
import com.woniuxy.service.OrderService;
import com.woniuxy.service.RoomServcie;
import com.woniuxy.service.TypeService;
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
	@Resource
	private RoomServcie roomService;
	@Resource
	private TypeService typeService;
	@Resource
	private InformationService informationService;
	
	public LoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

	public MemberService getMemberService() {
		return memberService;
	}

	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

	public RoomServcie getRoomService() {
		return roomService;
	}

	public void setRoomService(RoomServcie roomService) {
		this.roomService = roomService;
	}

	public TypeService getTypeService() {
		return typeService;
	}

	public void setTypeService(TypeService typeService) {
		this.typeService = typeService;
	}

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
	//欢迎页面
	@RequestMapping("/welcome.html")
	public ModelAndView wellcome(){
		ModelAndView modelAndView=new ModelAndView("/back/welcome");
		Map<String, Object> map=backService.number();
		modelAndView.addAllObjects(map);
		return modelAndView;
	}
	
	//订单列表
	@RequestMapping("/order-list.html")
	public ModelAndView orderList(){
		ModelAndView modelAndView=new ModelAndView("back/order-list");
		List<Order> orders=orderService.allOrder();
		
		//查询购买户
		for(int i=0;i<orders.size();i++){
			Order order =orders.get(i);
			if(order.getLogin_id()!=null && order.getLogin_id()!=0){
				Login login=loginService.findLoginByid(order.getLogin_id());
				order.setLogin(login);
			}
			if(order.getMember_id()!=null && order.getMember_id()!=0){
				Member member=memberService.findMemberByid(order.getMember_id());
				order.setMember(member);
			}
		}
		
		modelAndView.addObject("orders", orders);
	
		return modelAndView;
	}
	//已确定的订单项列表
	@RequestMapping("/item-list.html")
	public ModelAndView itemList(){
		ModelAndView modelAndView=new ModelAndView("/back/item-list");
		List<Item> items=itemService.allItem();
		modelAndView.addObject("items", items);
		return modelAndView;
	
	}
	//订单的所有订单项
	@RequestMapping("/order-view.html")
	public ModelAndView item(Integer id){
		ModelAndView modelAndView=new ModelAndView("/back/order-view");
		List<Item> items=itemService.findItemsByOrderid(id);
		Order order=orderService.findOrderById(id);
		
		modelAndView.addObject("items", items);
		modelAndView.addObject("orderstate", order.getOrder_state());
		return modelAndView;
	}
	//单个订单信息
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
	//打开开单房间页面
	@RequestMapping("/bookroom.html")
	public ModelAndView bookroom(){
		SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd");
		Date today=new Date();
		String startTime=sd.format(today);
		String endTime=sd.format(new Date(today.getTime()+1000*60*60*24));
		ModelAndView modelAndView=new ModelAndView("/back/bookroom");
		List<Room> rooms=roomService.findAllRooms(startTime, endTime);
		List<Type> types= typeService.findAllType();
		
		modelAndView.addObject("rooms", rooms);
		modelAndView.addObject("inTime", startTime);
		modelAndView.addObject("outTime", endTime);
		modelAndView.addObject("types", types);
		return modelAndView;
	}
	//打开开单、预定页面
	@RequestMapping("/creatorder.html")
	public ModelAndView creatorder(Integer roomid,String inTime,String outTime){
		ModelAndView modelAndView=new ModelAndView("/back/creatorder");
		modelAndView.addObject("roomid", roomid);
		modelAndView.addObject("inTime", inTime);
		modelAndView.addObject("outTime", outTime);
		return modelAndView;
	}
	//打开订单信息页面
	@RequestMapping("/orderInformation.html")
	public ModelAndView orderInformation(Integer orderid){
		ModelAndView modelAndView=new ModelAndView("/back/orderInformation");
		modelAndView.addObject("orderid", orderid);
	return modelAndView;
	}
	//打开订单项信息页面
	@RequestMapping("/itemInformation.html")
	public ModelAndView itemInformation(Integer id){
		ModelAndView modelAndView=new ModelAndView("/back/itemInformation");
		modelAndView.addObject("itemid", id);
		Map<String, Object> map=backService.itemInformation(id);
		modelAndView.addAllObjects(map);
		return modelAndView;
	}
	//打开修改订单的页面
	@RequestMapping("/updateorder.html")
	public ModelAndView updateorder(Integer id){
		ModelAndView modelAndView=new ModelAndView("/back/updateorder");
		//根据订单id找到订单
		Order order=orderService.findOrderById(id);
		modelAndView.addObject("order", order);
		Login login=null;
		Member member=null;
		//查询购买户		
		//购买账号
		if(order.getLogin_id()!=null && order.getLogin_id()!=0){
			login=loginService.findLoginByid(order.getLogin_id());
						
		}
		//购买会员
		if(order.getMember_id()!=null && order.getMember_id()!=0){
			member=memberService.findMemberByid(order.getMember_id());
						
		}
		modelAndView.addObject("login", login);
		modelAndView.addObject("member", member);	
		return modelAndView;
	}
	//打开入住信息页面
	@RequestMapping("/information-list.html")
	public ModelAndView information(Integer id){
		ModelAndView modelAndView=new ModelAndView("/back/information-list");
		//找到所有信息
		List<Information> informations=informationService.allInformation();
		modelAndView.addObject("informations", informations);
		return modelAndView;
	}
}
