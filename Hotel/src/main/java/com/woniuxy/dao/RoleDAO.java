package com.woniuxy.dao;

import org.apache.ibatis.annotations.Select;

import com.woniuxy.pojo.Role;

public interface RoleDAO {
	@Select("select * from role where role_id=#{role_id}")
	Role findRoleeById(Integer role_id);

}
