package com.woniuxy.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.woniuxy.dao.RoomTypeDao;
import com.woniuxy.pojo.Type;
import com.woniuxy.service.RoomTypeService;
@Service
public class RoomTypeImpl implements RoomTypeService{
	@Resource
	private RoomTypeDao roomTypeDao;

	@Override
	public void insert(Type type) {
		roomTypeDao.insert(type);
	}

	@Override
	public void deleteBytype_id(Type type) {
		roomTypeDao.deleteBytype_id(type);
	}

	@Override
	public Type findOneBytype_id(Type type) {
		Type findOneBytype_id = roomTypeDao.findOneBytype_id(type);
		return findOneBytype_id;
	}

	@Override
	public List<Type> findAll() {
		List<Type> list = roomTypeDao.findAll();
		return list;
	}

	@Override
	public void updatePriceByType_id(Type type) {
		roomTypeDao.updatePriceByType_id(type);
		
	}

	@Override
	public void updateNumberByType_id(Type type) {
		roomTypeDao.updateNumberByType_id(type);
		
	}

	@Override
	public void updateDescriptionByType_id(Type type) {
		roomTypeDao.updateDescriptionById(type);
		
	}

}
