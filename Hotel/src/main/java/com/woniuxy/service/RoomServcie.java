package com.woniuxy.service;

import java.util.List;

import com.woniuxy.pojo.Room;

public interface RoomServcie {

	public List<Room> findAllRooms(String startTime,String endTime);

	public List<Room> findRoomsByTimeAndType(String inTime, String outTime, Integer typeid);
	
}
