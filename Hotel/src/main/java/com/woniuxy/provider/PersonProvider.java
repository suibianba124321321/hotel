package com.woniuxy.provider;

import org.apache.ibatis.jdbc.SQL;

import com.woniuxy.pojo.Person;

public class PersonProvider {

	public String select(Person person){
		SQL sql=new SQL().SELECT("*").FROM("person");
		if(person.getName()!=null && !person.getName().equals("")){
			sql.WHERE("name like "+"'%"+person.getName()+"%'");
		}
		if(person.getIdcard()!=null && !person.getIdcard().equals("")){
			sql.WHERE("idcard like "+"'%"+person.getIdcard()+"%'");
		}
		if(person.getTel()!=null && !person.getTel().equals("")){
			sql.WHERE("tel like "+"'%"+person.getTel()+"%'");
		}
		return sql.toString();
	}
}
