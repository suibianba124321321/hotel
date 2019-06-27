package com.woniuxy.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class Order implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//订单id
	private Integer order_id;
	//登录id（网上预订）
	private Integer login_id;
	//会员id
	private Integer member_id;
	//订单创建时间
	private String  creat_time;
	//支付号
	private String  pay_number;
	//订单号
	private String  order_number;
	//是否自动取消
	private Integer auto_cancel;
	//是否接机
	private Integer in_air;
	//顾客留言
	private String  msg;
	
	//预抵时间
	private String arrive_time;
	//入住时间
	
	private String 	in_time;
	//离开时间
	private String out_time;
	//取消时间
	private String cancel_time;
	//订单状态
	private Integer order_state;
	//总价
	private BigDecimal sumprice;
	
	private Integer flag;
	
	private Integer typeID;
	
	private Integer[] personID;
	
	private List<Item> items;
	

	
	
	public BigDecimal getSumprice() {
		return sumprice;
	}

	public void setSumprice(BigDecimal sumprice) {
		this.sumprice = sumprice;
	}


	public Integer getTypeID() {
		return typeID;
	}

	public void setTypeID(Integer typeID) {
		this.typeID = typeID;
	}

	
	
	
	
	
	public Integer[] getPersonID() {
		return personID;
	}

	public void setPersonID(Integer[] personID) {
		this.personID = personID;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public Integer getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}

	public Integer getLogin_id() {
		return login_id;
	}

	public void setLogin_id(Integer login_id) {
		this.login_id = login_id;
	}

	public Integer getMember_id() {
		return member_id;
	}

	public void setMember_id(Integer member_id) {
		this.member_id = member_id;
	}

	

	public String getPay_number() {
		return pay_number;
	}

	public void setPay_number(String pay_number) {
		this.pay_number = pay_number;
	}

	public String getOrder_number() {
		return order_number;
	}

	public void setOrder_number(String order_number) {
		this.order_number = order_number;
	}

	public Integer getAuto_cancel() {
		return auto_cancel;
	}

	public void setAuto_cancel(Integer auto_cancel) {
		this.auto_cancel = auto_cancel;
	}

	public Integer getIn_air() {
		return in_air;
	}

	public void setIn_air(Integer in_air) {
		this.in_air = in_air;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	

	public String getCreat_time() {
		return creat_time;
	}

	public void setCreat_time(String creat_time) {
		this.creat_time = creat_time;
	}

	public String getArrive_time() {
		return arrive_time;
	}

	public void setArrive_time(String arrive_time) {
		this.arrive_time = arrive_time;
	}

	public String getIn_time() {
		return in_time;
	}

	public void setIn_time(String in_time) {
		this.in_time = in_time;
	}

	public String getOut_time() {
		return out_time;
	}

	public void setOut_time(String out_time) {
		this.out_time = out_time;
	}

	public String getCancel_time() {
		return cancel_time;
	}

	public void setCancel_time(String cancel_time) {
		this.cancel_time = cancel_time;
	}

	public Integer getOrder_state() {
		return order_state;
	}

	public void setOrder_state(Integer order_state) {
		this.order_state = order_state;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	@Override
	public String toString() {
		return "Order [order_id=" + order_id + ", login_id=" + login_id + ", member_id=" + member_id + ", creat_time="
				+ creat_time + ", pay_number=" + pay_number + ", order_number=" + order_number + ", auto_cancel="
				+ auto_cancel + ", in_air=" + in_air + ", msg=" + msg + ", arrive_time=" + arrive_time + ", in_time="
				+ in_time + ", out_time=" + out_time + ", cancel_time=" + cancel_time + ", order_state=" + order_state
				+ ", sumprice=" + sumprice + ", flag=" + flag + ", typeID=" + typeID + ", personID=" + personID
				+ ", items=" + items + "]";
	}


	
}
