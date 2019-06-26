package com.woniuxy.pojo;

import java.io.Serializable;




public class Room implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer room_id;
	private Integer type_id;
	//房间状态（0：可入住  1：已入住  3：待打扫）
	private Integer state;
	private Integer flag;
	
	private Type type;
	
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public Integer getRoom_id() {
		return room_id;
	}
	public void setRoom_id(Integer room_id) {
		this.room_id = room_id;
	}
	public Integer getType_id() {
		return type_id;
	}
	public void setType_id(Integer type_id) {
		this.type_id = type_id;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	@Override
	public String toString() {
		return "Room [room_id=" + room_id + ", type_id=" + type_id + ", state=" + state + ", flag=" + flag + ", type="
				+ type + "]";
	}
	
	
}
