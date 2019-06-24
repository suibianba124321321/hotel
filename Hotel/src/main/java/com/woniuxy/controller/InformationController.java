package com.woniuxy.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.woniuxy.pojo.Information;
import com.woniuxy.service.InformationService;
@Controller
@RequestMapping("/information")
public class InformationController {
		@Autowired
		private	InformationService informationService; 
		
		//根据页码查询入住信息
		@RequestMapping("/list")
		@ResponseBody
		public Map<String,Object> showInformationByPage(int currentpage){
			Map<String,Object> result=new HashMap<String,Object>();
			//根据当前页码查询入住信息
			List<Information> informations = informationService.findInformationByPage(currentpage);
			//根据person_id查询person表信息
			
			//根据room_id查询room表信息

			int totalPage=informationService.findTotalPage();
			//将当前页入住信息存到map中
			result.put("informations", informations);
			//将person表信息存到map中
			
			//将room表信息存到map中
			
			//将总页码存到map中
			result.put("totalPage", 4);
			return result;
		}
		
		//通过身份证号idcard查询是否存在该订单项
		@RequestMapping("findItemByIdCard")
		@ResponseBody
		public Map<String,Object> findItemByIdCard(String idcard){
			Map<String,Object> result=new HashMap<String,Object>();
			//通过idcard从person表中查询到person对象（入住姓名、手机号、person_id）
			
			//通过person_id从item表中查询到item对象（order_id，price，number，deposit）
			
			//通过order_id从room_date表中查询到room_date对象（room_id，type_id，date）
			
			//通过type_id从room_type表中查询到room_type对象（type）
			
			//通过order_id从order表中查询到order对象（order_state,in_time,out_time）
			
			return result;
			
			
		}
		
		
		
		//添加入住信息
		@RequestMapping("/add")
		@ResponseBody
		public String add(String idcard,Information information){
			System.out.println(idcard);
			System.out.println(information);
			
			String result="添加失败";
			//通过idcard 从person表中找到person_id
			
			//将person_id、room_id、in_time、out_time插入到information表中
			
			result=informationService.add(information);
			return result;
			
		}
		
		
		//退房
		@RequestMapping("/check_out")
		@ResponseBody
		public String check_out(Information information){
			System.out.println(information);
			String result="退房成功";
			result=informationService.check_out(information);
			System.out.println(result);
			return result;
		}
		
		
		//修改入住信息
		@RequestMapping("/update")
		@ResponseBody
		public String update(Information information){
			System.out.println(information);
			String result="修改失败";
			result=informationService.update(information);
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
		public List<Information> findList(Map<String,Object> queryMap){
			
			return informationService.findList(queryMap);
			
		}
		
		//模糊搜索总条数
		@RequestMapping("/getTotal")
		@ResponseBody
		public Integer getTotal(Map<String,Object> queryMap){
			return informationService.getTotal(queryMap);
			
		}
		
		//根据information_id查询单个入住信息
		@RequestMapping("/findOne")
		@ResponseBody
		public Information findOne(Information information){
			return informationService.findOne(information);
			
			
		}

}
