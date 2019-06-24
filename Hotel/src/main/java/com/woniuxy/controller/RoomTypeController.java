package com.woniuxy.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.woniuxy.pojo.Type;
import com.woniuxy.service.RoomTypeService;

@Controller
@RequestMapping("/type")
public class RoomTypeController {
	@Resource
	private RoomTypeService roomTypeService;
	

	
	/**
	 * 新增房间类型
	 * @param type
	 */
	@ResponseBody
    @RequestMapping("/insert")
	public String insert(Type type){
		System.out.println(type);
		roomTypeService.insert(type);
		return "添加成功";
	}
	
	/**
	 * 删除房间类型
	 * @param type
	 */
	@ResponseBody
    @RequestMapping("/deleteById")
	public String deleteBytype_id(Type type){
		roomTypeService.deleteBytype_id(type);
		return "删除成功";
	}
	
	/**
	 * 查询某一类型
	 * @param type
	 * @return
	 */
	@ResponseBody
    @RequestMapping("/findById")
	public Type findOneBytype_id(Type type){
		Type type1 = roomTypeService.findOneBytype_id(type);
		return type1;
	}
	
	/**
	 * 查询所有类型
	 * @param type
	 * @return
	 */
	@ResponseBody
    @RequestMapping("/all")
    public List<Type> findByRoomID(Type type){
		List<Type> all = roomTypeService.findAll(type);
    	return all;
    }
	
	/**
	 * 修改房间价格通过房间类型ID
	 * @param type
	 */
	@ResponseBody
    @RequestMapping("/updatePrice")
	public void updatePriceByType_id(Type type){
		roomTypeService.updatePriceByType_id(type);
	}
	
	/**
	 * 修改房间数量
	 * @param type
	 */
	@ResponseBody
    @RequestMapping("/updateNumber")
	public void updateNumberByType_id(Type type){
		roomTypeService.updateNumberByType_id(type);
	}

}
