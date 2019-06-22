package com.woniuxy.pojo;
import java.io.Serializable;
/**
 * 入住信息
 * @author Administrator
 *
 */
import java.util.Date;



public class Information implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer information_id;
	private Integer person_id;
	private Integer room_id;
	//入住时间
	private Date in_time;
	//离开时间
	private Date out_time;
	
	private Integer flag;

	public Integer getInformation_id() {
		return information_id;
	}

	public void setInformation_id(Integer information_id) {
		this.information_id = information_id;
	}

	public Integer getPerson_id() {
		return person_id;
	}

	public void setPerson_id(Integer person_id) {
		this.person_id = person_id;
	}

	public Integer getRoom_id() {
		return room_id;
	}

	public void setRoom_id(Integer room_id) {
		this.room_id = room_id;
	}

	public Date getIn_time() {
		return in_time;
	}

	public void setIn_time(Date in_time) {
		this.in_time = in_time;
	}

	public Date getOut_time() {
		return out_time;
	}

	public void setOut_time(Date out_time) {
		this.out_time = out_time;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	
	
	
}
