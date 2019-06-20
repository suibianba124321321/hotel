package com.woniuxy.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.woniuxy.pojo.Information;
import com.woniuxy.service.InformationService;
@Controller
@RequestMapping("/information")
public class InformationController {
		@Autowired
		private	InformationService informationService; 
		
		
		@RequestMapping("/list")
		public ModelAndView list(ModelAndView model){
//			model.addAllObjects("roomTypeList",informationService.findOne(information));
//			model.addAllObjects("roomList",informationService.add(information));
			model.setViewName("information/list");
			return model;
		}
	
		//添加入住信息
		@RequestMapping("/add")
		public Map<String,String> add(Information information){
			Map<String,String> ret =new HashMap<>();
			ret=informationService.add(information);
			return ret;
			
		}
		
		//修改入住信息
		@RequestMapping("/update")
		public Map<String,String> update(Information information){
			Map<String,String> ret =new HashMap<>();
			ret=informationService.update(information);
			return ret;
		}
		
		//删除入住信息
		@RequestMapping("/delete")
		public Map<String,String> delete(Information information){
			Map<String,String> ret =new HashMap<>();
			ret=informationService.delete(information);
			return ret;
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
