package com.woniuxy.dao;



import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.woniuxy.pojo.Login;


public interface LoginDAO {
@Select("select * from login where flag=0 and login_id=#{id}")
@Results({@Result(id=true,property="login_id",column="login_id"),
	      @Result(property="account",column="account"),
	      @Result(property="pwd",column="pwd"),
	      @Result(property="member_id",column="member_id"),
	      @Result(property="flag",column="flag"),
	      @Result(property="persons",column="login_id",
	      one=@One(select="com.woniuxy.dao.PersonDAO.findPersonByLogin_id"))	
})
public Login findLoginByid(int login_id);

//将用户id与personid对应起来
@Insert("insert into login_person values(#{param1},#{param2})")
public void bindloginIdAndPersonId(int login_id,int person_id);

}
