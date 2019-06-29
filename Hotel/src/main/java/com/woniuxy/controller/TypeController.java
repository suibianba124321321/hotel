package com.woniuxy.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.woniuxy.datacenter.DataCenter;
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
	public Map show(String inTime ,String outTime){
		Map<String, Object> map = new HashMap<>();
		List<Type> types=typeService.show(inTime, outTime);
		BigDecimal discount = DataCenter.getDiscount();
		map.put("discount", discount);
		map.put("types", types);
		return map;
	}

	



	//通过房间类型id查找到一个房间
	@RequestMapping("/oneRoomType")
	@ResponseBody
	public Type detail(Integer typeid){	

		Type type = typeService.findTypeByid(typeid);
		return type;
	}


}
