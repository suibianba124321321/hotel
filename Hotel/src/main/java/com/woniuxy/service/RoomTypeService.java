package com.woniuxy.service;

import java.util.List;

import com.woniuxy.pojo.Information;
import com.woniuxy.pojo.Room;
import com.woniuxy.pojo.RoomDate;
import com.woniuxy.pojo.Type;

public interface RoomTypeService {
	/**
	 * 新增房间类型
	 * @param type
	 */
	public void insert(Type type);
	
	/**
	 * 删除房间类型
	 * @param type
	 */
	public void deleteBytype_id(Type type);
	
	/**
	 * 查询某一类型
	 * @param type
	 * @return
	 */
	public Type findOneBytype_id(Type type);
	
	/**
	 * 查询所有类型
	 * @param type
	 * @return
	 */
	public List<Type> findAll();
	
	/**
	 * 修改房间价格通过房间类型ID
	 * @param type
	 */
	public void updatePriceByType_id(Type type);
	
	/**
	 * 修改房间数量
	 * @param type
	 */
	public void updateNumberByType_id(Type type);

	//通过type_id从room_type表中查询到room_type对象（type）
	public Type findTypeByType_id(RoomDate roomDate);

	public Type findTypeByRoom(Room room);

}
