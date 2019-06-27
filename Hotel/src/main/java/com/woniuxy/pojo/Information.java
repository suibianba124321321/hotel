package com.woniuxy.pojo;

public class Information {
	private int information_id;
	private int person_id;
	private int room_id;
	private String in_time;
	private String out_time;
	private int state;
	private int flag;
	public int getInformation_id() {
		return information_id;
	}
	public void setInformation_id(int information_id) {
		this.information_id = information_id;
	}
	public int getPerson_id() {
		return person_id;
	}
	public void setPerson_id(int person_id) {
		this.person_id = person_id;
	}
	public int getRoom_id() {
		return room_id;
	}
	public void setRoom_id(int room_id) {
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
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	@Override
	public String toString() {
		return "Information [information_id=" + information_id + ", person_id=" + person_id + ", room_id=" + room_id
				+ ", in_time=" + in_time + ", out_time=" + out_time + ", state=" + state + ", flag=" + flag + "]";
	}
	

}
