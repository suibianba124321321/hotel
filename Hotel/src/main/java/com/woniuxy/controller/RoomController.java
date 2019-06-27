package com.woniuxy.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.woniuxy.pojo.Room;
import com.woniuxy.service.RoomServcie;

@Controller
@RequestMapping("/room")
public class RoomController {

	@Resource
	private RoomServcie roomService;
	
	public RoomServcie getRoomService() {
		return roomService;
	}

	public void setRoomService(RoomServcie roomService) {
		this.roomService = roomService;
	}

	@RequestMapping("/searchRooms")
	@ResponseBody
	public List<Room> searchRooms(String inTime,String outTime,Integer typeid){
		List<Room> rooms=null;
		if(typeid==null || typeid!= 0){
			rooms=roomService.findRoomsByTimeAndType(inTime,outTime,typeid);
		}else{
			rooms=roomService.findAllRooms(inTime, outTime);
		}
		
	
		return rooms;
	}
	
}
