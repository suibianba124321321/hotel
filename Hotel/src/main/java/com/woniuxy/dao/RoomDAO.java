package com.woniuxy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.woniuxy.pojo.Room;

public interface RoomDAO {

	@Select("select * from room where type_id=#{typeID}")
	public List<Room> findRoomByType(Integer typeID);
	
	@Select("select * from room where  flag=0")
	public List<Room> allRoom();
	@Update("update room set state=#{state} where room_id=#{room_id}")
	public void updateState(Room room);
}
