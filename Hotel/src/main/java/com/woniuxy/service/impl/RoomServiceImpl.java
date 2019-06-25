package com.woniuxy.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.woniuxy.dao.RoomDAO;
import com.woniuxy.pojo.Information;
import com.woniuxy.pojo.Room;
import com.woniuxy.service.RoomService;
@Service
public class RoomServiceImpl implements RoomService{
	@Resource
	private RoomDAO roomDAO;

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

}
