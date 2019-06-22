package com.woniuxy.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.woniuxy.pojo.Information;

public interface InformationDao {
	//添加入住信息
	@Insert("insert into information(person_id,room_id,in_time,out_time) values(#{person_id},#{room_id},#{in_time},#{out_time})")
	public boolean add(Information information);
	//修改入住信息
	@Update("update information set person_id=#{person_id},room_id=#{room_id},in_time=#{in_time},out_time=#{out_time},state=#{state} where information_id=#{information_id} and flag=0")
	public boolean update(Information information);
	//删除入住信息
	@Update("update information set flag=1 where information_id=#{information_id} and flag=0")
	public boolean delete(Information information);
	//根据条件查询入住信息
	@Select("select * from information where 1=1")
	public List<Information> findList(Map<String,Object> queryMap);
	//模糊搜索总条数
	public Integer getTotal(Map<String,Object> queryMap);
	//根据information_id查询单个入住信息
	@Select("select * from information where information_id=#{information_id}")
	public Information findOne(Information information);
	//根据下标查找入住信息
	@Select ("select information_id,person_id,room_id,in_time,out_time,state from information where flag=0 limit #{index},5") 
	public List<Information> findInformationByIndex(int index);
	//获取入住信息条数
	@Select("select count(information_id) from information where flag=0")
	public int findTotalIndex();
	

}
