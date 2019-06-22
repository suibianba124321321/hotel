package com.woniuxy.pojo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 房间类型
 * @author Administrator
 *
 */
public class Type implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer type_id;
	private String type;
	private BigDecimal price;
	private String description;
	private BigDecimal deposit;
	//库存
	private Integer number;
	private Integer flag;
	private String img;
	
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public Integer getType_id() {
		return type_id;
	}
	public void setType_id(Integer type_id) {
		this.type_id = type_id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BigDecimal getDeposit() {
		return deposit;
	}
	public void setDeposit(BigDecimal deposit) {
		this.deposit = deposit;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	@Override
	public String toString() {
		return "Type [type_id=" + type_id + ", type=" + type + ", price=" + price + ", description=" + description
				+ ", deposit=" + deposit + ", number=" + number + ", flag=" + flag + ", img=" + img + "]";
	}
	
}
