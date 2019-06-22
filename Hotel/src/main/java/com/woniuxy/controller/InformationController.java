package com.woniuxy.controller;

import java.util.ArrayList;
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
		
		
		@RequestMapping("/list")
		@ResponseBody
		public List<Information> list(ModelAndView model,String name,String pwd){
//			model.addAllObjects("roomTypeList",informationService.findOne(information));
//			model.addAllObjects("roomList",informationService.add(information));
//			model.addObject("findAll",informationService.findList(null));
			List<Information> informations=new ArrayList<>();
//			informations=informationService.findList(null);
//			System.out.println(informations);
//			System.out.println(informations.get(0).getPerson_id());
			return informations;
		}
	
		//添加入住信息
		@RequestMapping("/add")
		public String add(Information information){
			String result="添加失败";
			result=informationService.add(information);
			return result;
			
		}
		
		//修改入住信息
		@RequestMapping("/update")
		public String update(Information information){
			String result="修改失败";
			result=informationService.update(information);
			return result;
		}
		
		//删除入住信息
		@RequestMapping("/delete")
		public String delete(Information information){
			String result="删除失败";
			result=informationService.delete(information);
			return result;
		}
		
		//根据条件查询入住信息
		@RequestMapping("/findList")
		public List<Information> findList(Map<String,Object> queryMap){
			
			return informationService.findList(queryMap);
			
		}
		
		//模糊搜索总条数
		@RequestMapping("/getTotal")
		public Integer getTotal(Map<String,Object> queryMap){
			return informationService.getTotal(queryMap);
			
		}
		
		//根据information_id查询单个入住信息
		@RequestMapping("/findOne")
		public Information findOne(Information information){
			
			return informationService.findOne(information);
			
		}

}