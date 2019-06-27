package com.woniuxy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.woniuxy.pojo.Information;
import com.woniuxy.pojo.Person;

@Repository
public interface InformationDAO {

	@Insert("insert into information values(default,#{person_id},#{room_id},#{in_time},#{out_time},default)")
	public void insertInformation(Information information);
	@Select("select * from information where flag=0")
	public List<Information> findAllInformation();
	@Select("select * from information where flag=0 and person_id=#{person_id}")
	public List<Information> findInfomationsByPerson(Person nowPerson);
}
