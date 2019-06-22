package com.woniuxy.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
		System.out.println(inTime);
		List<Type> types=typeService.show(inTime, outTime);
		System.out.println(types);
		return types;
	}
	//通过房间类型id查找到一个房间
	@RequestMapping("/oneRoomType")
	@ResponseBody
	public Type detail(Integer typeid){	
		System.out.println(typeid);
		Type type = typeService.findTypeByid(typeid);
		System.out.println(type);
		return type;
	}

	
	@RequestMapping("/text")	
	public String text(HttpSession session){

		/*session.setAttribute("uid", 1001);*/
		return "html/bookpage.html";
	}

	
}
