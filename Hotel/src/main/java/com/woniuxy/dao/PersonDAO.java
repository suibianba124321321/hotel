package com.woniuxy.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.woniuxy.pojo.Person;

@Repository
public interface PersonDAO {

	@Select("select * from person where person_id=#{id} ")
	public Person findPersonById(Integer id);
}
