package com.woniuxy.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.woniuxy.dao.RoomDao;
import com.woniuxy.pojo.Room;
import com.woniuxy.service.RoomService;
@Service
public class RoomServiceImpl implements RoomService{
	@Resource
	private RoomDao roomDao;

	@Override
	public void insert(Room room) {
		roomDao.insert(room);
		
	}

	@Override
	public void deleteByroom_id(Integer room_id) {
		roomDao.deleteByroom_id(room_id);
	}

	@Override
	public Room findOneByroom_id(Integer room_id) {
		Room room = roomDao.findOneByroom_id(room_id);
		return room;
	}

	@Override
	public List<Room> findBystate(Integer state) {
		List<Room> list = roomDao.findBystate(state);
		return list;
	}

	@Override
	public List<Room> findByType_id(Integer type_id) {
		List<Room> list = roomDao.findByType_id(type_id);
		return list;
	}

	@Override
	public List<Room> findAll() {
		List<Room> all = roomDao.findAll();
		return all;
	}

	@Override
	public void updateState(Room room) {
		roomDao.updateState(room);
		
	}

	

}
