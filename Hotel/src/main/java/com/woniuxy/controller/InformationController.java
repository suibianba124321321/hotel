package com.woniuxy.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.woniuxy.dao.RoomDateDAO;
import com.woniuxy.pojo.Information;
import com.woniuxy.pojo.Item;
import com.woniuxy.pojo.Order;
import com.woniuxy.pojo.Person;
import com.woniuxy.pojo.Room;
import com.woniuxy.pojo.RoomDate;
import com.woniuxy.pojo.Type;
import com.woniuxy.service.InformationService;
import com.woniuxy.service.ItemService;
import com.woniuxy.service.OrderService;
import com.woniuxy.service.PersonService;
import com.woniuxy.service.RoomService;
import com.woniuxy.service.RoomTypeService;
@Controller
@RequestMapping("/information")
public class InformationController {
		@Resource
		private	InformationService informationService; 
		@Resource
		private PersonService personService;
		@Resource
		private RoomTypeService roomTypeService;
		@Resource
		private RoomService roomService;
		@Resource
		private ItemService itemService;
		@Resource
		private RoomDateDAO roomDateDAO;
		@Resource
		private OrderService orderService;
		
		
		//根据页码查询入住信息
		@RequestMapping("/list")
		@ResponseBody
		public Map<String,Object> showInformationByPage(int currentpage){
			Map<String,Object> result=new HashMap<String,Object>();
			//根据当前页码查询入住信息
			List<Information> informations = informationService.findInformationByPage(currentpage);
			//查找所有person
			List<Person> person = personService.findAllPerson();
			//查找所有room
			List<Room> room =roomService.findAll();
			//查找所有的type
			List<Type> type =roomTypeService.findAll();
			//获取总页码
			int totalPage=informationService.findTotalPage();
			//将当前页入住信息存到map中
			result.put("informations", informations);
			//将person表信息存到map中
			result.put("person", person);
			//将room表信息存到map中
			result.put("room", room);
			//将type存到map中
			result.put("type", type);
			//将总页码存到map中
			result.put("totalPage", totalPage);
			return result;
		}
		
		//通过身份证号idcard查询是否存在该订单项
		@RequestMapping("findItemByIdCard")
		@ResponseBody
		public Map<String,Object> findItemByIdCard(String idcard){
			Map<String,Object> result=new HashMap<String,Object>();
			//通过idcard从person表中查询到person对象（入住姓名、手机号、person_id）
			Person person = personService.findPersonByIdCard(idcard);
			//通过person_id从item表中查询到item对象（order_id，price，number，deposit）
			Item item = itemService.findItemByPerson_id(person);
			//通过order_id从room_date表中查询到room_date对象（room_id，type_id，date）
			RoomDate roomDate = roomDateDAO.findRoomDateByOrder_id(item);
			//通过type_id从room_type表中查询到room_type对象（type）
			Type type = roomTypeService.findTypeByType_id(roomDate);
			//通过order_id从order表中查询到order对象（order_state,in_time,out_time）
			Order order =orderService.findOrderByOrder_id(item);
			//将person对象存到map中
			result.put("person", person);
			//将item对象存到map中
			result.put("item", item);
			//将roomDate对象存到map中
			result.put("roomDate", roomDate);
			//将type对象存到map中
			result.put("type", type);
			//将order对象存到map中
			result.put("order", order);
			//将当前页入住信息存到map中
			return result;
			
		}
		
		
		//添加入住信息
		@RequestMapping("/add")
		@ResponseBody
		public String add(String idcard,Information information){
			String result="添加失败";
			//通过idcard 从person表中找到person_id
			Person person = personService.findPersonByIdCard(idcard);
			//将person_id、room_id、in_time、out_time插入到information表中
			information.setPerson_id(person.getPerson_id());
			result=informationService.add(information);
			return result;
			
		}
		
		
		//退房
		@RequestMapping("/check_out")
		@ResponseBody
		public String check_out(Information information){
			String result="退房失败";
			result=informationService.check_out(information);
			if(result.contains("成功")){
				information=informationService.findOne(information);
				result=roomService.updateStateByRoom_id(information);
			}
			return result;
		}
		
		
		//修改入住信息
		@RequestMapping("/update")
		@ResponseBody
		public String update(Information information){
			String result="修改失败";
			Information oldInformation=informationService.findOne(information);
			result=roomService.updateOldAndNewRoomStateByRoom_id(information,oldInformation);
			if(result.contains("成功")){
				result=informationService.update(information);
			}
			return result;
		}
		
		//删除入住信息
		@RequestMapping("/delete")
		@ResponseBody
		public String delete(Information information){
			String result="删除失败";
			result=informationService.delete(information);
			return result;
		}
		
		//根据条件查询入住信息
		@RequestMapping("/findList")
		@ResponseBody
		public Map<String,Object> findList(Information information,int currentpage){
			Map<String,Object> result=new HashMap<String,Object>();
			Map<String,Object> queryMap=new HashMap<>();
			queryMap.put("information", information);
			if(information.getPerson_id()==0){
				queryMap.put("name", "");
			}else{
			Person person1=personService.findPersonByPerson_id(information);
			String name=person1.getName();
			queryMap.put("name", name);}
			queryMap.put("currentpage", currentpage);
			//根据当前页码查询入住信息
			List<Information> informations = informationService.findList(queryMap);
			System.out.println(informations);
			//查找所有person
			List<Person> person = personService.findAllPerson();
			//查找所有room
			List<Room> room =roomService.findAll();
			//查找所有的type
			List<Type> type =roomTypeService.findAll();
			//获取总页码
			int totalPage=informationService.getTotal(queryMap);
			//将当前页入住信息存到map中
			result.put("informations", informations);
			//将person表信息存到map中
			result.put("person", person);
			//将room表信息存到map中
			result.put("room", room);
			//将type存到map中
			result.put("type", type);
			//将总页码存到map中
			result.put("totalPage", totalPage);
			return result;
		}
		
		//根据information_id查询单个入住信息
		@RequestMapping("/findOne")
		@ResponseBody
		public Map<String,Object> findOne(Information information){
			Map<String,Object> result=new HashMap<String,Object>();
			information=informationService.findOne(information);
			Person person = personService.findPersonByPerson_id(information);
			//根据房间号查找房间
			Room room = roomService.findRoomByRoom_id(information);
			Type type =roomTypeService.findTypeByRoom(room);
			result.put("information", information);
			result.put("person", person);
			result.put("type", type);
			return result;
		}
		
		//模糊搜索，通过姓名查询person对象
		@RequestMapping("/findPersonByName")
		@ResponseBody
		public Person findPersonByName(Person person){
			try {
				person=personService.findPersonByName(person);
				return person;
			} catch (Exception e) {
				return new Person();
			}
		}
}
