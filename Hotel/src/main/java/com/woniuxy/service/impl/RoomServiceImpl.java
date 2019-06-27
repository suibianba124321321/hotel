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
import com.woniuxy.pojo.Information;
import com.woniuxy.pojo.Room;
import com.woniuxy.pojo.Type;
import com.woniuxy.service.RoomService;


@Service("roomService")
public class RoomServiceImpl implements RoomService{
	@Resource
	private RoomDAO roomDAO;
	@Resource
	private RoomDateDAO roomDateDAO;
	@Resource
	private TypeDAO typeDAO;
	
	
	
	@Override
	public void insert(Room room) {
		roomDAO.insert(room);
		
	}

	@Override
	public void deleteByroom_id(Integer room_id) {
		roomDAO.deleteByroom_id(room_id);
	}

	@Override
	public Room findOneByroom_id(Integer room_id) {
		Room room = roomDAO.findOneByroom_id(room_id);
		return room;
	}

	@Override
	public List<Room> findBystate(Integer state) {
		List<Room> list = roomDAO.findBystate(state);
		return list;
	}

	@Override
	public List<Room> findByType_id(Integer type_id) {
		List<Room> list = roomDAO.findByType_id(type_id);
		return list;
	}

	@Override
	public List<Room> findAll() {
		List<Room> all = roomDAO.findAll();
		return all;
	}

	@Override
	public void updateState(Room room) {
		roomDAO.updateState(room);
		
	}

	@Override
	public Room findRoomByRoom_id(Information information) {
		return roomDAO.findRoomByRoom_id(information);
	}

	@Override
	public String updateStateByRoom_id(Information information) {
		String result="退房失败";
		boolean re=roomDAO.updateStateByRoom_id(information);
		if(re){
			result="退房成功";
		}
		return result;
	}

	@Override
	public String updateOldAndNewRoomStateByRoom_id(Information information, Information oldInformation) {
		String result="修改失败";
		boolean re=false;
		re=roomDAO.updateOldRoomStateByOldRoom_id(oldInformation);
		re=roomDAO.updateNewRoomStateByNewRoom_id(information);
		if(re){
			result="修改成功";
		}
		return result;
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
