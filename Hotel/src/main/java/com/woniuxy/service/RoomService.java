package com.woniuxy.service;

import java.util.List;

import com.woniuxy.pojo.Information;
import com.woniuxy.pojo.Room;

public interface RoomService {
	/**
	 * 新增房间
	 */
	public void insert(Room room);

	/**
	 * 通过房间号码删除房间
	 * 
	 * @param room_id
	 *            房间号码
	 */
	public void deleteByroom_id(Integer room_id);

	/**
	 * 通过房间号码查询房间信息
	 * 
	 * @param room_id
	 *            房间号码
	 * @return 房间对象
	 */
	public Room findOneByroom_id(Integer room_id);

	/**
	 * 通过房间状态查询所有该状态的房间
	 * 
	 * @param state
	 *            房间状态
	 * @return 房间对象集合
	 */
	public List<Room> findBystate(Integer state);

	/**
	 * 通过房间类型查询该类型所有的房间
	 * 
	 * @param type_id
	 *            房间类型ID
	 * @return 房间对象集合
	 */
	public List<Room> findByType_id(Integer type_id);

	/**
	 * 查询酒店所有的酒店
	 * 
	 * @return
	 */
	public List<Room> findAll();

	/**
	 * 通过房间ID更新房间状态
	 */
	public void updateState(Room room);
	public Room findRoomByRoom_id(Information information);
	//通过房间号更改房间状态
	public String updateStateByRoom_id(Information information);
	//通过新旧room_id更改房间状态
	public String updateOldAndNewRoomStateByRoom_id(Information information, Information oldInformation);

	

}
