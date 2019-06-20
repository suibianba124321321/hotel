package com.woniuxy.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.woniuxy.pojo.Type;
import com.woniuxy.service.TypeService;

@Controller
@RequestMapping("/type")
public class TypeController {
	@Resource
	private TypeService typeService;
	
	
	public TypeService getTypeService() {
		return typeService;
	}


	public void setTypeService(TypeService typeService) {
		this.typeService = typeService;
	}


	@RequestMapping("/show")
	@ResponseBody
	public List<Type> show(String inTime ,String outTime){
		
		List<Type> types=typeService.show(inTime, outTime);
		
		return types;
	}
}