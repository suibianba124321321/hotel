package com.woniuxy.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;


import org.springframework.stereotype.Service;

import com.woniuxy.dao.ItemDAO;
import com.woniuxy.dao.OrderDAO;
import com.woniuxy.dao.PersonDAO;
import com.woniuxy.dao.RoomDAO;
import com.woniuxy.dao.RoomDateDAO;
import com.woniuxy.dao.TypeDAO;
import com.woniuxy.pojo.Item;
import com.woniuxy.pojo.Order;
import com.woniuxy.pojo.Person;
import com.woniuxy.pojo.Room;
import com.woniuxy.pojo.Type;
import com.woniuxy.service.ItemService;

@Service("itemService")
public class ItemServiceImpl implements ItemService{
	@Resource
	private ItemDAO itemDAO;
	@Resource
	private PersonDAO personDAO;
	
	@Resource
	private TypeDAO typeDAO;
	@Resource
	private OrderDAO orderDAO;
	@Resource
	private RoomDateDAO roomDateDAO;
	@Resource
	private RoomDAO roomDAO;
	
	
	
	public RoomDAO getRoomDAO() {
		return roomDAO;
	}

	public void setRoomDAO(RoomDAO roomDAO) {
		this.roomDAO = roomDAO;
	}

	public RoomDateDAO getRoomDateDAO() {
		return roomDateDAO;
	}

	public void setRoomDateDAO(RoomDateDAO roomDateDAO) {
		this.roomDateDAO = roomDateDAO;
	}

	public OrderDAO getOrderDAO() {
		return orderDAO;
	}

	public void setOrderDAO(OrderDAO orderDAO) {
		this.orderDAO = orderDAO;
	}

	public TypeDAO getTypeDAO() {
		return typeDAO;
	}

	public void setTypeDAO(TypeDAO typeDAO) {
		this.typeDAO = typeDAO;
	}

	public PersonDAO getPersonDAO() {
		return personDAO;
	}

	public void setPersonDAO(PersonDAO personDAO) {
		this.personDAO = personDAO;
	}

	public ItemDAO getItemDAO() {
		return itemDAO;
	}

	public void setItemDAO(ItemDAO itemDAO) {
		this.itemDAO = itemDAO;
	}

	@Override
	public List<Item> findItemsByOrderid(Integer id){
		
		List<Item> items=itemDAO.findItemsByOrdeId(id);
		
		for(int i=0;i<items.size();i++){
			Item item=items.get(i);
			
			Person person=personDAO.findPersonById(item.getPerson_id());
			items.get(i).setPerson(person);
			Type type=typeDAO.findTypeByid(item.getType_id());
			items.get(i).setType(type);
		}
		
		return items;
	}

	@Override
	public List<Item> allItem() {
		List<Item> items=itemDAO.findAllItem();
		for(int i=0;i<items.size();i++){
			Item item=items.get(i);
			
			Person person=personDAO.findPersonById(item.getPerson_id());
			items.get(i).setPerson(person);
			Type type=typeDAO.findTypeByid(item.getType_id());
			items.get(i).setType(type);
		}
		
		
		return items;
	}

	@Override
	public String inRoom(Item item) {
		String msg="入住成功";
		item.setFlag(2);
		itemDAO.updateFlag(item);
		Item oldItem=itemDAO.findItemById(item.getItem_id());
		if(oldItem==null){
			return "订单项不存在";
		}
				
		//修改房间状态
		Room room=new Room();
		room.setState(1);
		room.setRoom_id(oldItem.getRoom_id());
		roomDAO.updateState(room);
		return msg;
	}

	@Override
	public String outRoom(Item item) {
		String msg="退房成功";
		Date now=new Date();
		SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd");
		String today=sd.format(now);
		Item oldItem=itemDAO.findItemById(item.getItem_id());
		if(oldItem==null){
			return "订单项不存在";
		}
		Order order =orderDAO.findOrderById(oldItem.getOrder_id());
		//未住天数
		int number=item.getDay_number();
		//判断是否是提前退房
		if(!today.equals(order.getOut_time())){
			Date start=null;
			Date end=null;
			try {
				start = sd.parse(today);
				end=sd.parse(order.getOut_time());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			while(start.getTime()!=end.getTime()){
				
				number--;
				start=new Date(start.getTime()+24*60*60*1000);
			}
			//算出退款金额
			BigDecimal refund=item.getPrice().multiply(new BigDecimal(""+number)).add(item.getDeposit());
			msg=msg+",请退款：￥"+refund.doubleValue();
			
			item.setDay_number(number);
			
		}
		item.setFlag(1);
		
		//修改订单项状态
		itemDAO.updateFlag(item);
		//删除住房记录
		roomDateDAO.deleteByRoomId(item.getRoom_id());
		//修改房间状态
		Room room=new Room();
		room.setState(2);
		room.setRoom_id(oldItem.getRoom_id());
		roomDAO.updateState(room);
		
		return msg;
	}

	
}
