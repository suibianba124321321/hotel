package com.woniuxy.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.woniuxy.dao.ItemDAO;
import com.woniuxy.dao.OrderDAO;
import com.woniuxy.dao.RoomDAO;
import com.woniuxy.dao.RoomDateDAO;
import com.woniuxy.dao.TypeDAO;
import com.woniuxy.pojo.Item;
import com.woniuxy.pojo.Order;
import com.woniuxy.pojo.Room;
import com.woniuxy.pojo.RoomDate;
import com.woniuxy.pojo.Type;
import com.woniuxy.service.OrderService;
@Service("orderService")
@Transactional
public class OrderServiceImpl implements OrderService{
	@Resource
	private OrderDAO orderDAO;
	@Resource
	private RoomDAO roomDAO;
	@Resource
	private RoomDateDAO roomDateDAO;
	@Resource
	private TypeDAO typeDAO;
	@Resource
	private ItemDAO itemDAO;
	
	
	public ItemDAO getItemDAO() {
		return itemDAO;
	}

	public void setItemDAO(ItemDAO itemDAO) {
		this.itemDAO = itemDAO;
	}

	public TypeDAO getTypeDAO() {
		return typeDAO;
	}

	public void setTypeDAO(TypeDAO typeDAO) {
		this.typeDAO = typeDAO;
	}



	public RoomDateDAO getRoomDateDAO() {
		return roomDateDAO;
	}

	public void setRoomDateDAO(RoomDateDAO roomDateDAO) {
		this.roomDateDAO = roomDateDAO;
	}

	public RoomDAO getRoomDAO() {
		return roomDAO;
	}

	public void setRoomDAO(RoomDAO roomDAO) {
		this.roomDAO = roomDAO;
	}

	public OrderDAO getOrderDAO() {
		return orderDAO;
	}

	public void setOrderDAO(OrderDAO orderDAO) {
		this.orderDAO = orderDAO;
	}
	
	//取消订单定时器
	public  void autoCancel(OrderService orderService,Integer orderId) {
		
		Timer timer = new Timer();
		     timer.schedule(new TimerTask() {
		      public void run() {
	     
	        Order order=orderService.findOrderById(orderId);
	       if(order==null){
	    	   timer.cancel();
	    	   return;
	       }
	        int orderState=order.getOrder_state();
	         int auto=order.getAuto_cancel();
	        
	         if(orderState == 0 && auto ==1){
	        	
	        	 orderService.deleteOrder(order);
	         }
	        
	         timer.cancel();
	       
		      }
	     }, 1000*60*15);// 设定指定的时间time,此处为过期时间，默认15分钟
		
	}
	//创建订单
	@Override
	public Map<String, Object> createOrder(Order order) {
		Map<String, Object> map=new HashMap<>();
		
		map.put("msg", "创建成功");
		//入住人员
		Integer[] personIds=order.getPersonID();
		System.out.println(order.getOrder_state()+"啦啦啦啦啦啦啦啦绿绿绿绿绿绿绿绿绿绿绿绿");
		
		SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd");
		//获取订单的时间
		List<String> orderDates=new ArrayList<>();
		String inTime=order.getIn_time();
		String outTime=order.getOut_time();
		
		
		
		Date start=null;
		Date end=null;
		try {
			start=sd.parse(inTime);
			end=sd.parse(outTime);
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		while(start.getTime()!=end.getTime()){
			
			orderDates.add(sd.format(start));
			start=new Date(start.getTime()+24*60*60*1000);
		}
		
		Type type=typeDAO.findTypeByid(order.getTypeID());
		//获取该类型所有房间
		List<Room> rooms=roomDAO.findRoomByType(order.getTypeID());
		
		//接收可住房间
		List<Room> inRoom=new ArrayList<>();
		
		//遍历房间
		for(int i=0;i<rooms.size();i++){
			Room room=rooms.get(i);
			//获取该房间的时间信息
			List<String> dates=roomDateDAO.findDateByRoomid(room);
		
			//如果没有时间信息，那么直接加入可住房间中
			if(dates==null){
				
				room.setType(type);
				inRoom.add(room);
				//当房间数量达到要求直接进行插入订单
				if(inRoom.size()==order.getPersonID().length){
					break;
				}
				continue;
			}
			//房间是否可住标志
			boolean flag=true;
			//有时间信息，就与订单时间比较，筛选符合的房间，完全不包含订单时间
			for(int j=0;j<orderDates.size();j++){
				
				//获取比较的时间
				String date=orderDates.get(j);
				//判断当前房间的时间信息中是否包含目标时间
				if(dates.contains(date)){
					
					//房间不可住
					flag=false;
					
				}
				
			}
			
			//不包含订单中的时间，加入可住房间列表中
			if(flag==true){
				
				room.setType(type);
				inRoom.add(room);
			}
			//判断可住房间数量足够
			if(inRoom.size()==order.getPersonID().length){
				break;
			}
			
		}
		//判断可住房间数量足够
		if(inRoom.size()<order.getPersonID().length){
			map.replace("msg", "抱歉！您来晚一步"+type.getType()+"在该时段房间不足请选择其他类型的房间或者其他时间段");
			return  map;
		}
		
		//取得订单创建时间
		Date creatTime=new Date();
		Date cancelTime=new Date(creatTime.getTime()+15*60*1000);
		//创建订单号
		String OrderNumber=UUID.randomUUID().toString();
		sd.applyPattern("yyyy-MM-dd HH:mm:ss");
		order.setOrder_number(OrderNumber);
		order.setCreat_time(sd.format(creatTime));
		order.setCancel_time(sd.format(cancelTime));
		//订单总价
		BigDecimal sumPrice=type.getPrice().multiply(new BigDecimal(""+inRoom.size()).multiply(new BigDecimal(""+orderDates.size())));
		order.setSumprice(sumPrice);
		//插入
		orderDAO.insertOrder(order);
		
		int orderId=orderDAO.findOrderByNumber(OrderNumber).getOrder_id();
		
		for(int i=0;i<inRoom.size();i++){
			Room room=inRoom.get(i);
			if(order.getMember_id()!=null&&order.getMember_id()!=0){
				itemDAO.insertItem(new Item(orderId, room.getRoom_id(),type.getType_id(),personIds[i],orderDates.size(),new BigDecimal(0),room.getType().getPrice()));
			}else{
				itemDAO.insertItem(new Item(orderId, room.getRoom_id(),type.getType_id(),personIds[i],orderDates.size(),room.getType().getDeposit(),room.getType().getPrice()));
				
			}
			
		}
		
		//插入房间状态表
		
			for(int j=0;j<inRoom.size();j++){
				Room room=inRoom.get(j);
				for(int i=0;i<orderDates.size();i++){
					
					String date=orderDates.get(i);
				roomDateDAO.insert(new RoomDate(room.getRoom_id(), room.getType_id(), date, orderId));
			}
		}
		//超过规定时间未付款自动取消订单
		autoCancel(this,orderId);
		map.put("orderid", orderId);
		return map;
	}
	
	//插入支付号
	@Override
	public String updatePayNumber(Order order) {
		String result="插入支付号成功";
		
		orderDAO.updatePayNumber(order);
		
		
		return result;
		
	}
	//以登录id所有订单
	@Override
	public List<Order> findAllOrder(Order order) {
		List<Order> orders=orderDAO.findOrder(order);
		return orders;
	}
	

	//以订单id查询
	@Override
	public Order findOrderById(Integer id) {
		
		return orderDAO.findOrderById(id);
	}
	//删除订单
	@Override
	public String deleteOrder(Order order) {
		
		orderDAO.deleteOrder(order);
		itemDAO.deleteItem(order);
		roomDateDAO.delete(order);
		return "退房成功";

	}
	
	
	
}
