package com.woniuxy.service;

import java.util.List;

import com.woniuxy.pojo.Type;

public interface TypeService {

	public List<Type> show(String startTime ,String endTime);

	
	public Type findTypeByid(int id);


	public List<Type> findAllType();

}
