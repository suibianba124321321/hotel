package com.woniuxy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.woniuxy.pojo.Order;
import com.woniuxy.pojo.Room;
import com.woniuxy.pojo.RoomDate;


public interface RoomDateDAO {

	@Select("select date from room_date where room_id=#{room_id} and flag=0 ")
	public List<String> findDateByRoomid(Room room);
	@Insert("insert into room_date values (default,#{room_id},#{type_id},#{date},#{order_id},default)")
	public void insert(RoomDate roomState);

	@Update("update room_date set flag=1 where order_id=#{order_id}")

	public void delete(Order order);
}
