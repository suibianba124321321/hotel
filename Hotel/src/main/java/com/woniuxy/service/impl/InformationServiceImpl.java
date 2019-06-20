package com.woniuxy.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.woniuxy.dao.InformationDao;
import com.woniuxy.pojo.Information;
import com.woniuxy.service.InformationService;

public class InformationServiceImpl implements InformationService{
	@Autowired
	private InformationDao informationDao;

	@Override
	public Map<String,String> add(Information information) {
		Map<String,String> ret =new HashMap<>();
		informationDao.add(information);
		return ret;
	}

	@Override
	public Map<String,String> update(Information information) {
		Map<String,String> ret =new HashMap<>();
		informationDao.update(information);
		return ret;
	}

	@Override
	public Map<String,String> delete(Information information) {
		Map<String,String> ret =new HashMap<>();
		informationDao.delete(information);
		return ret;
	}

	@Override
	public List<Information> findList(Map<String, Object> queryMap) {
		return informationDao.findList(queryMap);
	}

	@Override
	public Integer getTotal(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return informationDao.getTotal(queryMap);
	}

	@Override
	public Information findOne(Information information) {
		// TODO Auto-generated method stub
		return informationDao.findOne(information);
	}

}
