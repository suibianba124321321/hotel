package com.woniuxy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.woniuxy.pojo.Order;
import com.woniuxy.pojo.Room;

public interface RoomDAO {

	@Select("select * from room where type_id=#{typeID}")
	public List<Room> findRoomByType(Integer typeID);
}
