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
	private Integer type_id;
	//住客id
	private Integer person_id;
	//库存
	private Integer number ;
	//押金
	private BigDecimal deposit;
	//价格
	private BigDecimal price;
	private Integer flag;
	
	
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
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
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
	@Override
	public String toString() {
		return "Item [item_id=" + item_id + ", order_id=" + order_id + ", type_id=" + type_id + ", person_id="
				+ person_id + ", number=" + number + ", deposit=" + deposit + ", price=" + price + ", flag=" + flag
				+ "]";
	}
	
	
	
	
	
}
