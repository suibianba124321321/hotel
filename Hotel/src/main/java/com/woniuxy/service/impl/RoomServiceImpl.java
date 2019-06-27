package com.woniuxy.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.woniuxy.dao.RoomDAO;
import com.woniuxy.dao.RoomDateDAO;
import com.woniuxy.dao.TypeDAO;
import com.woniuxy.pojo.Room;
import com.woniuxy.pojo.Type;
import com.woniuxy.service.RoomServcie;
@Service("roomServcie")
public class RoomServiceImpl implements RoomServcie {
	@Resource
	private RoomDAO roomDAO;
	@Resource
	private RoomDateDAO roomDateDAO;
	@Resource
	private TypeDAO typeDAO;
	
	public RoomDAO getRoomDAO() {
		return roomDAO;
	}
	public void setRoomDAO(RoomDAO roomDAO) {
		this.roomDAO = roomDAO;
	}
	public RoomDateDAO getRoomDateDAO() {
		return roomDateDAO;
	}
	public void setRoomDateDAO(RoomDateDAO roomDateDAO) {
		this.roomDateDAO = roomDateDAO;
	}
	public TypeDAO getTypeDAO() {
		return typeDAO;
	}
	public void setTypeDAO(TypeDAO typeDAO) {
		this.typeDAO = typeDAO;
	}
	@Override
	public List<Room> findAllRooms(String startTime,String endTime) {
		SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd");
		//获取预订时间段
		List<String> inDates=new ArrayList<>();
		
		Date start=null;
		Date end=null;
		try {
			start=sd.parse(startTime);
			end=sd.parse(endTime);
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		if(! start.before(end)){
			return null;
		}
		
		while(start.getTime()!=end.getTime()){
			
			inDates.add(sd.format(start));
			start=new Date(start.getTime()+24*60*60*1000);
		}
		
		//所有房间
		List<Room> rooms=roomDAO.allRoom();
		
		for(int i=0;i<inDates.size();i++){
			String date=inDates.get(i);
			//不可住的房间
			List<Room> roomNot=roomDateDAO.findRoomByDate(date);
			
			rooms.removeAll(roomNot);
			
		}
		
		//给房间加类型
		for(int i=0;i<rooms.size();i++){
			int typeid=rooms.get(i).getType_id();
			rooms.get(i).setType(typeDAO.findTypeByid(typeid));
			
		}
		return rooms;
	}
	@Override
	public List<Room> findRoomsByTimeAndType(String inTime, String outTime, Integer typeid) {
		SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd");
		//获取预订时间段
		List<String> inDates=new ArrayList<>();
		//获取类型
		Type type=typeDAO.findTypeByid(typeid);
		if(type==null){
			return null;
		}
		Date start=null;
		Date end=null;
		try {
			start=sd.parse(inTime);
			end=sd.parse(outTime);
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		if(! start.before(end)){
			return null;
		}
		//获取时间段
		while(start.getTime()!=end.getTime()){
			
			inDates.add(sd.format(start));
			start=new Date(start.getTime()+24*60*60*1000);
		}
		
		//该类型所有房间
		List<Room> rooms=roomDAO.findRoomByType(typeid);
		for(int i=0;i<inDates.size();i++){
			String date=inDates.get(i);
			//不可住的房间
			List<Room> roomNot=roomDateDAO.findRoomByDate(date);
			rooms.removeAll(roomNot);
		}
		//给房间加类型
		for(int i=0;i<rooms.size();i++){
					
			rooms.get(i).setType(type);
		}
		return rooms;
	}

}
