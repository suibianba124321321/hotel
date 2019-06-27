package com.woniuxy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.woniuxy.pojo.Room;
import com.woniuxy.pojo.RoomDate;
import com.woniuxy.pojo.Type;

public interface RoomTypeDao {

	
	/**
	 * 新增房间类型
	 * @param type
	 */
	@Insert("insert into room_type(type,price,description,number,flag,img) values(#{type},#{price},#{description},#{number},0,#{img})")
	public void insert(Type type);
	
	/**
	 * 删除房间类型
	 * @param type
	 */
	@Update("update room_type set flag=1 where type_id=#{type_id}")
	public void deleteBytype_id(Type type);
	
	/**
	 * 查询某一类型
	 * @param type
	 * @return
	 */
	@Select("select * from room_type where type_id=#{type_id} and flag=0")
	public Type findOneBytype_id(Type type);
	
	/**
	 * 查询所有类型
	 * @param type
	 * @return
	 */
	@Select("select * from room_type where flag=0")
	public List<Type> findAll();
	
	/**
	 * 修改房间价格通过房间类型ID
	 * @param type
	 */
	@Update("update room_type set price=#{price} where type_id=#{type_id} and flag=0")
	public void updatePriceByType_id(Type type);
	
	/**
	 * 修改房间数量
	 * @param type
	 */
	@Update("update room_type set number=#{number} where type_id=#{type_id} and flag=0")
	public void updateNumberByType_id(Type type);
	
	//通过type_id从room_type表中查询到room_type对象（type）
		@Select("select * from room_type where type_id =#{type_id} and flag=0")
		public Type findTypeByType_id(RoomDate roomDate);

		@Select("select * from room_type where type_id=#{type_id} and flag=0")
		public Type findTypeByRoom(Room room);
	
	
	@Update("update room_type set description=#{description} where type_id=#{type_id} and flag=0")
	public void updateDescriptionById(Type type);
	

	
}
