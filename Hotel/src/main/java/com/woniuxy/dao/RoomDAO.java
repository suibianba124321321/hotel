package com.woniuxy.dao;

import java.util.List;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.woniuxy.pojo.Information;
import com.woniuxy.pojo.Room;

/**
 * 房间的增删查改 状态的改变
 * 
 * @author Administrator
 *
 */
public interface RoomDAO {
	/**
	 * 新增房间
	 */
	@Insert("insert into room(type_id,state,room_id,flag) values(#{type_id},#{state},#{room_id},0)")
	public void insert(Room room);

	/**
	 * 通过房间号码删除房间
	 * 
	 * @param room_id
	 *            房间号码
	 */

	@Update("update room set flag=1 where room_id=#{room_id}")
	public void deleteByroom_id(Integer room_id);

	/**
	 * 通过房间号码查询房间信息
	 * 
	 * @param room_id
	 *            房间号码
	 * @return 房间对象
	 */
	@Select("select * from room where room_id=#{room_id} and flag=0")
	public Room findOneByroom_id(Integer room_id);

	/**
	 * 通过房间状态查询所有该状态的房间
	 * 
	 * @param state
	 *            房间状态
	 * @return 房间对象集合
	 */
	@Select("select * from room where state=#{state} and flag=0")
	public List<Room> findBystate(Integer state);

	/**
	 * 通过房间类型查询该类型所有的房间
	 * 
	 * @param type_id
	 *            房间类型ID
	 * @return 房间对象集合
	 */
	@Select("select * from room where type_id=#{type_id} and flag=0")
	public List<Room> findByType_id(Integer type_id);

	/**
	 * 查询酒店所有的酒店
	 * 
	 * @return
	 */
	@Select("select * from room where flag=0")
	public List<Room> findAll();

	/**
	 * 通过房间ID更新房间状态
	 */
	@Update("update room set state=#{state} where room_id=#{room_id} and flag=0")
	public void updateState(Room room);
	
	@Select("select * from room where type_id=#{typeID}")
	public List<Room> findRoomByType(Integer typeID);

	//通过room_id查询room对象
		@Select("select * from room where room_id=#{room_id} and flag=0")
		public Room findRoomByRoom_id(Information information);
		//修改
		@Update("update room set state=2 where room_id=#{room_id} and flag=0")
		public boolean updateOldRoomStateByOldRoom_id(Information oldInformation);
		@Update("update room set state=1 where room_id=#{room_id} and flag=0")
		public boolean updateNewRoomStateByNewRoom_id(Information information);
		//退房
		@Update("update room set state=2 where room_id=#{room_id} and flag=0")
		public boolean updateStateByRoom_id(Information information);



		
		@Select("select * from room where  flag=0")
		public List<Room> allRoom();

		@Select ("select * from room where room_id=#{roomid}")
		public Room findRoomById(Integer roomid);

}
