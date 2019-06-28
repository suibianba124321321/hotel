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
import com.woniuxy.dao.LoginDAO;
import com.woniuxy.dao.MemberDAO;
import com.woniuxy.dao.OrderDAO;
import com.woniuxy.dao.PersonDAO;
import com.woniuxy.dao.RoomDAO;
import com.woniuxy.dao.RoomDateDAO;
import com.woniuxy.dao.TypeDAO;
import com.woniuxy.datacenter.DataCenter;
import com.woniuxy.pojo.Item;
import com.woniuxy.pojo.Login;
import com.woniuxy.pojo.Member;
import com.woniuxy.pojo.Order;
import com.woniuxy.pojo.Person;
import com.woniuxy.pojo.Room;
import com.woniuxy.pojo.RoomDate;
import com.woniuxy.pojo.Type;
import com.woniuxy.service.OrderService;
import com.woniuxy.util.MemberDiscount;

@Service("orderService")
@Transactional
public class OrderServiceImpl implements OrderService {
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
	@Resource
	private PersonDAO personDAO;
	@Resource
	private LoginDAO loginDAO;
	@Resource
	private MemberDAO memberDAO;
	

	public MemberDAO getMemberDAO() {
		return memberDAO;
	}

	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}

	public PersonDAO getPersonDAO() {
		return personDAO;
	}

	public void setPersonDAO(PersonDAO personDAO) {
		this.personDAO = personDAO;
	}

	public LoginDAO getLoginDAO() {
		return loginDAO;
	}

	public void setLoginDAO(LoginDAO loginDAO) {
		this.loginDAO = loginDAO;
	}

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

	// 取消订单定时器
	public void autoCancel(OrderService orderService, Integer orderId) {

		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			public void run() {

				Order order = orderService.findOrderById(orderId);
				if (order == null) {
					timer.cancel();
					return;
				}
				int orderState = order.getOrder_state();
				int auto = order.getAuto_cancel();
				int flag=order.getFlag();
				if (flag==0 && orderState == 0 && auto == 1) {

					orderService.deleteOrder(order);
				}

				timer.cancel();

			}
		}, 1000 * 60 );// 设定指定的时间time,此处为过期时间，默认15分钟

	}

	// 创建订单
	@Override
	public Map<String, Object> createOrder(Order order) {
		Map<String, Object> map = new HashMap<>();

		map.put("msg", "创建成功");
		// 入住人员
		Integer[] personIds = order.getPersonID();

		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		// 获取订单的时间
		List<String> orderDates = new ArrayList<>();
		String inTime = order.getIn_time();
		String outTime = order.getOut_time();

		Date start = null;
		Date end = null;
		try {
			start = sd.parse(inTime);
			end = sd.parse(outTime);
		} catch (ParseException e) {

			e.printStackTrace();
		}
		if(! start.before(end)){
			return null;
		}
		while (start.getTime() != end.getTime()) {

			orderDates.add(sd.format(start));
			start = new Date(start.getTime() + 24 * 60 * 60 * 1000);
		}

		Type type = typeDAO.findTypeByid(order.getTypeID());
		// 获取该类型所有房间
		List<Room> rooms = roomDAO.findRoomByType(order.getTypeID());

		// 接收可住房间
		List<Room> inRoom = new ArrayList<>();

		// 遍历房间
		for (int i = 0; i < rooms.size(); i++) {
			Room room = rooms.get(i);
			// 获取该房间的时间信息
			List<String> dates = roomDateDAO.findDateByRoomid(room);

			// 如果没有时间信息，那么直接加入可住房间中
			if (dates == null) {

				room.setType(type);
				inRoom.add(room);
				// 当房间数量达到要求直接进行插入订单
				if (inRoom.size() == order.getPersonID().length) {
					break;
				}
				continue;
			}
			// 房间是否可住标志
			boolean flag = true;
			// 有时间信息，就与订单时间比较，筛选符合的房间，完全不包含订单时间
			for (int j = 0; j < orderDates.size(); j++) {

				// 获取比较的时间
				String date = orderDates.get(j);
				// 判断当前房间的时间信息中是否包含目标时间
				if (dates.contains(date)) {

					// 房间不可住
					flag = false;

				}
			}
			// 不包含订单中的时间，加入可住房间列表中
			if (flag == true) {

				room.setType(type);
				inRoom.add(room);
			}
			// 判断可住房间数量足够
			if (inRoom.size() == order.getPersonID().length) {
				break;
			}

		}
		// 判断可住房间数量足够
		if (inRoom.size() < order.getPersonID().length) {
			map.replace("msg", "抱歉！您来晚一步" + type.getType() + "在该时段房间不足请选择其他类型的房间或者其他时间段");
			return map;
		}

		// 取得订单创建时间
		Date creatTime = new Date();
		Date cancelTime = new Date(creatTime.getTime() + 15 * 60 * 1000);
		// 创建订单号
		String OrderNumber = UUID.randomUUID().toString();
		sd.applyPattern("yyyy-MM-dd HH:mm:ss");
		order.setOrder_number(OrderNumber);
		order.setCreat_time(sd.format(creatTime));
		order.setCancel_time(sd.format(cancelTime));
		// 订单总价
		BigDecimal sumPrice = type.getPrice()
				.multiply(new BigDecimal("" + inRoom.size()).multiply(new BigDecimal("" + orderDates.size())));
		//价格
		BigDecimal price=type.getPrice();
		//获取会员
		Integer memberId=order.getMember_id();
		if(memberId != null && memberId != 0){
			Member member=memberDAO.findMemberBId(memberId);
			if(member!=null){
				//会员折扣价
				MemberDiscount util=new MemberDiscount();
				sumPrice=util.getOnlieDiscount(member, sumPrice);
				price=util.getOnlieDiscount(member, price);
			}else{
				sumPrice=sumPrice.multiply(DataCenter.getDiscount());
				price=price.multiply(DataCenter.getDiscount());
			}
			
		}else{
			sumPrice=sumPrice.multiply(DataCenter.getDiscount());
			price=price.multiply(DataCenter.getDiscount());
		}
		
		//押金
		if (order.getMember_id() == null || order.getMember_id()<= 1) {
			BigDecimal deposit= type.getDeposit().multiply(new BigDecimal(""+inRoom.size()));
			sumPrice=sumPrice.add(deposit);
		} 
		//价格取整
		sumPrice=sumPrice.setScale(0, BigDecimal.ROUND_HALF_UP);
		price=price.setScale(0, BigDecimal.ROUND_HALF_UP);
		
		order.setSumprice(sumPrice);
		// 插入
		orderDAO.insertOrder(order);

		int orderId = orderDAO.findOrderByNumber(OrderNumber).getOrder_id();

		for (int i = 0; i < inRoom.size(); i++) {
			Room room = inRoom.get(i);
			if (order.getMember_id() != null && order.getMember_id() > 1) {
				itemDAO.insertItem(new Item(orderId, room.getRoom_id(), type.getType_id(), personIds[i],
						orderDates.size(), new BigDecimal("0"), price));
			} else {
				itemDAO.insertItem(new Item(orderId, room.getRoom_id(), type.getType_id(), personIds[i],
						orderDates.size(), room.getType().getDeposit(), price));

			}

		}

		// 插入房间状态表

		for (int j = 0; j < inRoom.size(); j++) {
			Room room = inRoom.get(j);
			for (int i = 0; i < orderDates.size(); i++) {

				String date = orderDates.get(i);
				roomDateDAO.insert(new RoomDate(room.getRoom_id(), room.getType_id(), date, orderId));
			}
		}
		// 超过规定时间未付款自动取消订单
		autoCancel(this, orderId);
		map.put("orderid", orderId);
		return map;
	}

	// 插入支付号
	@Override
	public String updatePayNumber(Order order) {
		String result = "插入支付号成功";

		orderDAO.updatePayNumber(order);

		return result;

	}

	// 以登录id所有订单
	@Override
	public List<Order> findAllOrder(Order order) {
		List<Order> orders = orderDAO.findOrder(order);
		return orders;
	}

	// 以订单id查询
	@Override
	public Order findOrderById(Integer id) {

		return orderDAO.findOrderById(id);
	}

	// 删除订单
	@Override
	public String deleteOrder(Order order) {

		orderDAO.deleteOrder(order);
		itemDAO.deleteItem(order);
		roomDateDAO.delete(order);
		return "退房成功";

	}

	// 所有订单
	@Override
	public List<Order> allOrder() {
		List<Order> orders = orderDAO.findAllOrder();

		return orders;
	}

	@Override
	public String updateState(Order order) {

		orderDAO.updateState(order);

		return "操作成功";
	}

	@Override
	public Map<String,Object> preCreatOrder(Integer roomid,Order order, Person person) {
		Map<String,Object> map=new HashMap<>();
		// 处理人员
		if (person.getPerson_id() == null || person.getPerson_id() == 0) {
			personDAO.addPerson(person);
			person = personDAO.findPersonIdByIdcard(person);
		}
		String inTime=order.getIn_time();
		String outTime=order.getOut_time();
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		Date start = null;
		Date end = null;
		try {
			start = sd.parse(inTime);
			end = sd.parse(outTime);
		} catch (ParseException e) {

			e.printStackTrace();
		}
		if(! start.before(end)){
			map.put("msg", "预定时间不正确");
			return map;
		}
		// 获取订单的时间
		List<String> orderDates = new ArrayList<>();
		while (start.getTime() != end.getTime()) {

			orderDates.add(sd.format(start));
			start = new Date(start.getTime() + 24 * 60 * 60 * 1000);
		}
		// 获取该房间的时间信息
		Room room = roomDAO.findRoomById(roomid);
		if (room == null) {
			map.put("msg", "房间不存在");
			return map;
		}
		
		List<String> dates = roomDateDAO.findDateByRoomid(room);
	
		if(dates!=null && dates.size()>0){
			for (int i = 0; i < orderDates.size(); i++) {
				String date = orderDates.get(i);
				if (dates.contains(date)) {
					map.put("msg","房间已经被订");
					return map;
				}
			}
		}
		
		// 生成订单
		// 房间类型
		Type type = typeDAO.findTypeByid(room.getType_id());
		// 取得订单创建时间
		Date creatTime = new Date();
		Date cancelTime = new Date(creatTime.getTime() + 15 * 60 * 1000);
		// 创建订单号
		String OrderNumber = UUID.randomUUID().toString();
		sd.applyPattern("yyyy-MM-dd HH:mm:ss");
		
		order.setOrder_number(OrderNumber);
		order.setCreat_time(sd.format(creatTime));
		order.setCancel_time(sd.format(cancelTime));
		// 订单总价
		BigDecimal sumPrice = null;
		//今日价格
		BigDecimal price=type.getPrice();
		if (order.getMember_id() != null && order.getMember_id() > 1) {
			
			//会员折扣价
			MemberDiscount util=new MemberDiscount();
			Member member=memberDAO.findMemberBId(order.getMember_id() );
			
			price=util.getDownDiscount(member, price);
			sumPrice=price.multiply(new BigDecimal(""+orderDates.size()));
		}else{
			
			sumPrice=type.getPrice().multiply(new BigDecimal("" + orderDates.size())).multiply(DataCenter.getDiscount()).add(type.getDeposit());
		}
		sumPrice=sumPrice.setScale(0, BigDecimal.ROUND_HALF_UP);
		price=price.setScale(0, BigDecimal.ROUND_HALF_UP);
		order.setSumprice(sumPrice);
		// 插入
		orderDAO.insertOrder(order);

		int orderId = orderDAO.findOrderByNumber(OrderNumber).getOrder_id();

		if (order.getMember_id() != null && order.getMember_id() > 1) {
			itemDAO.insertItem(new Item(orderId, room.getRoom_id(), type.getType_id(), person.getPerson_id(),
					orderDates.size(), new BigDecimal(0+""),price));
		} else {
			
			itemDAO.insertItem(new Item(orderId, room.getRoom_id(), type.getType_id(), person.getPerson_id(),
					orderDates.size(), type.getDeposit(),price));

		}

		// 插入房间状态表

		for (int i = 0; i < orderDates.size(); i++) {

			String date = orderDates.get(i);
			roomDateDAO.insert(new RoomDate(room.getRoom_id(), room.getType_id(), date, orderId));
		}
		map.put("msg","预定成功");
		map.put("orderid",orderId);
		// 超过规定时间未付款自动取消订单
		autoCancel(this, orderId);

		return map;
	}

	@Override
	public Map<String,Object> openOrder(Integer roomid, Order order, Person person) {
		Map<String,Object> map=new HashMap<>();
		// 处理人员
		if (person.getPerson_id() == null || person.getPerson_id() == 0) {
			personDAO.addPerson(person);
			person = personDAO.findPersonIdByIdcard(person);
		}
		String inTime=order.getIn_time();
		String outTime=order.getOut_time();
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		Date start = null;
		Date end = null;
		try {
			start = sd.parse(inTime);
			end = sd.parse(outTime);
		} catch (ParseException e) {

			e.printStackTrace();
		}
		if(! start.before(end)){
			map.put("msg", "预定时间不正确");
			return map;
		}
		// 获取订单的时间
		List<String> orderDates = new ArrayList<>();
		while (start.getTime() != end.getTime()) {

			orderDates.add(sd.format(start));
			start = new Date(start.getTime() + 24 * 60 * 60 * 1000);
		}
		// 获取该房间的时间信息
		Room room = roomDAO.findRoomById(roomid);
		if (room == null) {
			map.put("msg", "房间不存在");
			return map;
		}
		
		List<String> dates = roomDateDAO.findDateByRoomid(room);
	
		if(dates!=null && dates.size()>0){
			for (int i = 0; i < orderDates.size(); i++) {
				String date = orderDates.get(i);
				if (dates.contains(date)) {
					map.put("msg","房间已经被订");
					return map;
				}
			}
		}
		
		// 生成订单
		// 房间类型
		Type type = typeDAO.findTypeByid(room.getType_id());
		// 取得订单创建时间
		Date creatTime = new Date();
		Date cancelTime = new Date(creatTime.getTime() + 15 * 60 * 1000);
		// 创建订单号
		String OrderNumber = UUID.randomUUID().toString();
		sd.applyPattern("yyyy-MM-dd HH:mm:ss");
		
		order.setOrder_number(OrderNumber);
		order.setCreat_time(sd.format(creatTime));
		order.setCancel_time(sd.format(cancelTime));
		// 订单总价
		BigDecimal sumPrice = null;
		//今日价格
		BigDecimal price=type.getPrice();
		if (order.getMember_id() != null && order.getMember_id() > 1) {
			
			//会员折扣价
			MemberDiscount util=new MemberDiscount();
			Member member=memberDAO.findMemberBId(order.getMember_id() );
			
			price=util.getDownDiscount(member, price);
			sumPrice=price.multiply(new BigDecimal(""+orderDates.size()));
		}else{
			
			sumPrice=type.getPrice().multiply(new BigDecimal("" + orderDates.size())).multiply(DataCenter.getDiscount()).add(type.getDeposit());
		}
		sumPrice=sumPrice.setScale(0, BigDecimal.ROUND_HALF_UP);
		price=price.setScale(0, BigDecimal.ROUND_HALF_UP);
		order.setSumprice(sumPrice);
		// 插入
		orderDAO.insertOrder(order);

		int orderId = orderDAO.findOrderByNumber(OrderNumber).getOrder_id();

		if (order.getMember_id() != null && order.getMember_id() > 1) {
			itemDAO.insertItem(new Item(orderId, room.getRoom_id(), type.getType_id(), person.getPerson_id(),
					orderDates.size(), new BigDecimal(0+""),price));
		} else {
			
			itemDAO.insertItem(new Item(orderId, room.getRoom_id(), type.getType_id(), person.getPerson_id(),
					orderDates.size(), type.getDeposit(),price));

		}

		// 插入房间状态表

		for (int i = 0; i < orderDates.size(); i++) {

			String date = orderDates.get(i);
			roomDateDAO.insert(new RoomDate(room.getRoom_id(), room.getType_id(), date, orderId));
		}
		//开单修改房间的状态
		room.setState(1);
		roomDAO.updateState(room);
		map.put("msg","开单成功");
		map.put("orderid",orderId);
		// 超过规定时间未付款自动取消订单
		autoCancel(this, orderId);

		return map;
	}

	@Override
	public List<Order> searchOrders(String account, Integer state, String inTime) {
		List<Order> orders=null;
		Map<String, Object> map =new HashMap<>();
		map.put("state", state);
		map.put("inTime", inTime);
		if(account==null || account.equals("")){
			
		}else{
			List<Login> logins=loginDAO.findLoginByString(account);
			map.put("logins", logins);
		}
		orders=orderDAO.findOrdersByMap(map);
		if(orders==null){
			return null;
		}
		//查询购买户
				for(int i=0;i<orders.size();i++){
					Order order =orders.get(i);
					if(order.getLogin_id()!=null && order.getLogin_id()!=0){
						Login login=loginDAO.findLoginByid(order.getLogin_id());
						order.setLogin(login);
					}
					if(order.getMember_id()!=null && order.getMember_id()!=0){
						Member member=memberDAO.findMemberBId(order.getMember_id());
						order.setMember(member);
					}
				}
		
		return orders;
	}

	@Override
	public String updateOrder(Order order) {
		String msg="修改成功";
		orderDAO.updateOrder(order);
		
		return msg;
	}
	//通过order_id从order表中查询到order对象（order_state,in_time,out_time）
	@Override
	public Order findOrderByOrder_id(Item item) {
		
		return orderDAO.findOrderByOrder_id(item);
	}

	
}
