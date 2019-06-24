package com.woniuxy.dao;


import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.woniuxy.pojo.Person;

@Repository
public interface PersonDAO {

	@Select("select * from person where person_id=#{id} ")
	public Person findPersonById(Integer id);

//一对多查询	
@Select("select * from login_person,person where login_person.person_id=person.person_id and login_id=#{id}")
public List<Person> findPersonByLogin_id(int login_id);
//插入一个person
@Insert("insert into person (name,tel,idcard) values(#{name},#{tel},#{idcard})")	
public void addPerson(Person person);
//通过idcard查询出该房客的id 
@Select("select * from person where idcard=#{idcard} ")
public Person findPersonIdByIdcard(Person person);
//查找所有person
@Select("select * from person where flag=0")
public List<Person> findAllPerson();
//通过idcard从person表中查询到person对象（入住姓名、手机号、person_id）
@Select("select * from person where idcard = #{idcard}")
public Person findPersonByIdCard(String idcard);

}
