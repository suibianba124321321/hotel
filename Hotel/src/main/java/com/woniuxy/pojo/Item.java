package com.woniuxy.pojo;

import java.io.Serializable;
import java.math.BigDecimal;

public class Item implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer item_id;

	private Integer order_id;
	private Integer room_id;

	private Integer type_id;
	//住客id
	private Integer person_id;
	//入住天数
	private Integer day_number ;
	//押金
	private BigDecimal deposit;
	//价格
	private BigDecimal price;
	private Integer flag;
	
	private Person person;
	private Type type;
	
	public Item() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Item(Integer order_id, Integer room_id, Integer type_id, Integer person_id, Integer day_number,
			BigDecimal deposit, BigDecimal price) {
		super();
		this.order_id = order_id;
		this.room_id = room_id;
		this.type_id = type_id;
		this.person_id = person_id;
		this.day_number = day_number;
		this.deposit = deposit;
		this.price = price;
	}
	@Override
	public String toString() {
		return "Item [item_id=" + item_id + ", order_id=" + order_id + ", room_id=" + room_id + ", type_id=" + type_id
				+ ", person_id=" + person_id + ", day_number=" + day_number + ", deposit=" + deposit + ", price="
				+ price + ", flag=" + flag + ", person=" + person + ", type=" + type + "]";
	}
	public Integer getItem_id() {
		return item_id;
	}
	public void setItem_id(Integer item_id) {
		this.item_id = item_id;
	}
	public Integer getOrder_id() {
		return order_id;
	}
	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
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
	public Integer getPerson_id() {
		return person_id;
	}
	public void setPerson_id(Integer person_id) {
		this.person_id = person_id;
	}
	public Integer getDay_number() {
		return day_number;
	}
	public void setDay_number(Integer day_number) {
		this.day_number = day_number;
	}
	public BigDecimal getDeposit() {
		return deposit;
	}
	public void setDeposit(BigDecimal deposit) {
		this.deposit = deposit;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	



	


	
	
}
