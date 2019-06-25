package com.woniuxy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.woniuxy.pojo.Type;

public interface TypeDAO {


	@Select("select * from room_type where type_id=#{id}")
	public Type findTypeByid(int id);

	@Select("select * from room_type where flag=0")
	public List<Type> findAllType();
}
