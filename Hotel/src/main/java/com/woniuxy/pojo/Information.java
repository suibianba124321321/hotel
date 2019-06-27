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
	private String in_time;
	//离开时间
	private String out_time;
	
	private Integer flag;
	
	private Person person;
	

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

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

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public Information(Integer person_id, Integer room_id, String in_time, String out_time) {
		super();
		this.person_id = person_id;
		this.room_id = room_id;
		this.in_time = in_time;
		this.out_time = out_time;
	}

	public Information() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
