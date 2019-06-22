package com.woniuxy.pojo;

import java.io.Serializable;

public class RoomDate implements Serializable {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer room_id;
	private Integer type_id;
	private String date;
	private Integer order_id;
	private Integer flag;
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public Integer getOrder_id() {
		return order_id;
	}
	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public RoomDate(Integer room_id, Integer type_id, String date, Integer order_id) {
		super();
		this.room_id = room_id;
		this.type_id = type_id;
		this.date = date;
		this.order_id = order_id;
	}
	public RoomDate() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
