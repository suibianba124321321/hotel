package com.woniuxy.service;

import java.util.List;

import com.woniuxy.pojo.Type;

public interface RoomTypeService {
	//查找所有房间类型
	public List<Type> allRoomType();
	
	
	//通过id查找房间类型
	public Type findRoomById( Integer typeid);
	
}



