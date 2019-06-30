package com.woniuxy.service.impl;


import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.woniuxy.dao.InformationDAO;
import com.woniuxy.dao.ItemDAO;
import com.woniuxy.dao.MemberDAO;
import com.woniuxy.dao.OrderDAO;
import com.woniuxy.dao.PersonDAO;
import com.woniuxy.dao.RoomDAO;
import com.woniuxy.dao.RoomDateDAO;
import com.woniuxy.dao.TypeDAO;
import com.woniuxy.pojo.Information;
import com.woniuxy.pojo.Item;
import com.woniuxy.pojo.Member;
import com.woniuxy.pojo.Order;
import com.woniuxy.pojo.Person;
import com.woniuxy.pojo.Room;
import com.woniuxy.pojo.Type;
import com.woniuxy.service.ItemService;
@Transactional
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
	@Resource
	private InformationDAO informationDAO;
	@Resource
	private MemberDAO memberDAO;
	
	
	
	public MemberDAO getMemberDAO() {
		return memberDAO;
	}

	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}

	public InformationDAO getInformationDAO() {
		return informationDAO;
	}

	public void setInformationDAO(InformationDAO informationDAO) {
		this.informationDAO = informationDAO;
	}

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
		List<Item> items=new ArrayList<>();
		List<Order> orders=orderDAO.findAllOrder();
		for(int j=0;j<orders.size();j++){
			Order order=orders.get(j);
			if(order.getOrder_state()!=0){
				 items.addAll(itemDAO.findItemsByOrdeId(order.getOrder_id()));
				for(int i=0;i<items.size();i++){
					Item item=items.get(i);
					
					Person person=personDAO.findPersonById(item.getPerson_id());
					items.get(i).setPerson(person);
					Type type=typeDAO.findTypeByid(item.getType_id());
					items.get(i).setType(type);
				}
			}
			
			
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
		Order order=new Order();
		order.setOrder_id(oldItem.getOrder_id());
		order.setOrder_state(5);
		orderDAO.updateState(order);;
		return msg;
	}
	
	@Override
	public String outRoom(Item item) {
		String msg="退房成功";
		Date now=new Date();
		SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd");
		String today=sd.format(now);
//		String today="2019-06-29";
		Item oldItem=itemDAO.findItemById(item.getItem_id());
		if(oldItem==null){
			return "订单项不存在";
		}
		Order order =orderDAO.findOrderById(oldItem.getOrder_id());
		//未住天数
		int number=oldItem.getDay_number();
		BigDecimal refund=null;
		//判断是否是提前退房
		if(!today.equals(order.getOut_time())){
			refund=oldItem.getPrice();
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
			 refund=refund.multiply(new BigDecimal(""+(oldItem.getDay_number()-number)));
			 refund=refund.add(oldItem.getDeposit());
			 msg=msg+",请退款：￥"+refund.doubleValue();
			
			
			item.setDay_number(number);
			//修改订单总价
			order.setSumprice(order.getSumprice().subtract(refund));
			orderDAO.updateSumpriceByid(order);
			
			
		}
		
		item.setFlag(1);
		//修改订单项状态
		itemDAO.updateFlag(item);
		//删除住房记录
		roomDateDAO.deleteByRoomId(oldItem);
		//修改房间状态
		Room room=new Room();
		room.setState(2);
		room.setRoom_id(oldItem.getRoom_id());
		roomDAO.updateState(room);
		//查询订单是否全部退房
		List<Item> items=itemDAO.findItemsByOrdeIdAndflagEquelsZero(oldItem.getOrder_id());
		if(items==null || items.size()==0){
			order.setOrder_state(3);
			orderDAO.updateState(order);;
		}
		//插入入住信息
		Information information=new Information(oldItem.getPerson_id(), oldItem.getRoom_id(), order.getIn_time(), today);
		informationDAO.insertInformation(information);
		//如果是会员计算积分
		if(order.getMember_id()!=null && order.getMember_id()!=0){
			Member member=memberDAO.findMemberBId(order.getMember_id());
			int grade=oldItem.getPrice().intValue()*number;
			int newGrade=member.getGrade()+grade;
			System.out.println(newGrade);
			member.setGrade(newGrade);
			memberDAO.updateGrade(member);
			if(1000<=newGrade && newGrade<2000){
				member.setRank(2);
				memberDAO.updateRank(member);
			}else if(newGrade>=2000){
				member.setRank(3);
				memberDAO.updateRank(member);
			}
		}
		
		
		return msg;
	}
	
	@Override
	public String cancelRoom(Item item) {
		String msg="退房成功";
		
		Item oldItem=itemDAO.findItemById(item.getItem_id());
		if(oldItem==null){
			return "订单项不存在";
		}
		Order order =orderDAO.findOrderById(oldItem.getOrder_id());
		if(order.getOrder_state()==0){
			return "订单未付款";
		}
		//未住天数
		int number=oldItem.getDay_number();
		BigDecimal refund=oldItem.getPrice();
	
			

			//算出退款金额
			 refund=refund.multiply(new BigDecimal(""+number));
			 refund=refund.add(oldItem.getDeposit());
			 msg=msg+",请退款：￥"+refund.doubleValue();
			
			
			item.setDay_number(number);
			//修改订单总价
			order.setSumprice(order.getSumprice().subtract(refund));
			orderDAO.updateSumpriceByid(order);
			item.setFlag(1);
		//修改订单项状态
		itemDAO.updateFlag(item);
		//删除住房记录
		roomDateDAO.deleteByRoomId(oldItem);
		//修改房间状态
		Room room=new Room();
		room.setState(2);
		room.setRoom_id(oldItem.getRoom_id());
		roomDAO.updateState(room);
		//查询订单是否全部退房
		List<Item> items=itemDAO.findItemsByOrdeIdAndflagEquelsZero(oldItem.getOrder_id());
		if(items==null || items.size()==0){
			order.setOrder_state(4);
			orderDAO.updateState(order);
		}
		
		return msg;
	}

	public List<Item> findItemsByOrdeIdAndflagEquelsZero(Integer order_id) {
		List<Item> items = itemDAO.findItemsByOrdeIdAndflagEquelsZero(order_id);
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
	public List<Item> findItemsBySome(String name, Integer state) {
		Map<String, Object> map=new HashMap<>();
		if(name!=null && !name.equals("")){
			name="%"+name+"%";
			List<Person> persons=personDAO.findPersonByName(name);
			
			if(persons!=null&&persons.size()>0){
				map.put("persons", persons);
			}
		}
		if(state!=null ){
			map.put("state", state);
		}
		
		
		
		List<Item> items=new ArrayList<>();
		List<Order> orders=orderDAO.findAllOrder();
		for(int j=0;j<orders.size();j++){
			Order order=orders.get(j);
			map.put("orderid", order.getOrder_id());
			if(order.getOrder_state()!=0){
				List<Item> nowItems=itemDAO.findItemsBysome(map);
				if(nowItems==null){
				continue;
				}
			for(int i=0;i<items.size();i++){
				Item item=items.get(i);
				Person person=personDAO.findPersonById(item.getPerson_id());
				Type type=typeDAO.findTypeByid(item.getType_id());
				items.get(i).setType(type);
				items.get(i).setPerson(person);
			}
			items.addAll(nowItems);
			}
		}
		
		return items;

	}
	//通过person_id从item表中查询到item对象（order_id，price，number，deposit）
	@Override
	public Item findItemByPerson_id(Person person) {
		Item item =itemDAO.findItemByPerson_id(person);
		return item;
	}
	
}
