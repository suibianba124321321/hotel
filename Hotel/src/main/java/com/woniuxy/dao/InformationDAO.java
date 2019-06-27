package com.woniuxy.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.woniuxy.pojo.Information;
import com.woniuxy.pojo.Person;
import com.woniuxy.provider.InformationProvider;

@Repository
public interface InformationDAO {

	@Insert("insert into information values(default,#{person_id},#{room_id},#{in_time},#{out_time},default)")
	public void insertInformation(Information information);
	@Select("select * from information where flag=0")
	public List<Information> findAllInformation();
	@Select("select * from information where flag=0 and person_id=#{person_id}")
	public List<Information> findInfomationsByPerson(Person nowPerson);
	//添加入住信息
		@Insert("insert into information(person_id,room_id,in_time,out_time) values(#{person_id},#{room_id},#{in_time},#{out_time})")
		public boolean add(Information information);
		//修改入住信息
		@Update("update information set room_id=#{room_id},in_time=#{in_time},out_time=#{out_time},state=#{state} where information_id=#{information_id} and flag=0")
		public boolean update(Information information);
		//退房
		@Update("update information set state=1 where information_id=#{information_id} and flag=0")
		public boolean check_out(Information information);
		//删除入住信息
		@Update("update information set flag=1 where information_id=#{information_id} and flag=0")
		public boolean delete(Information information);
		//根据条件查询入住信息
		@SelectProvider(type=InformationProvider.class,method="select")
		public List<Information> findList(Map<String,Object> queryMap);
		//模糊搜索总条数
		@SelectProvider(type=InformationProvider.class,method="selectCount")
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
