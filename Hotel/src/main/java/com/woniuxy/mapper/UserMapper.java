package com.woniuxy.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.woniuxy.pojo.Login;

public interface UserMapper {
   
	 @Insert("insert into login(tel) values(#{tel})")
	 public boolean phongRegister(String tel);
	 
	 @Insert("insert into login(account,pwd) values(#{account},#{pwd})")
	 public boolean accountRegister(Login login);
	 
	 @Select("select pwd from login where account=#{account}")
	 public String accountLogin(Login login);
}
